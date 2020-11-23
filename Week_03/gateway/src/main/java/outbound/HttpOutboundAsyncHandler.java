package com.java.gateway.outbound;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import com.java.gateway.router.CustomHttpEndpointRouter;
import com.java.gateway.util.NamedThreadFactory;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.HttpVersion;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpOutboundAsyncHandler extends ChannelOutboundHandlerAdapter {
    private ExecutorService executorService;
    private CloseableHttpAsyncClient httpClient;
    private CustomHttpEndpointRouter customHttpEndpointRouter;


    public HttpOutboundAsyncHandler() {
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new CallerRunsPolicy();
        executorService = new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit
            .MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize), new NamedThreadFactory
            ("proxyService"), handler);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
            .setConnectTimeout(1000)
            .setSoTimeout(1000)
            .setIoThreadCount(cores)
            .setRcvBufSize(32 * 1024)
            .build();

        httpClient = HttpAsyncClients.custom().setMaxConnTotal(40)
            .setMaxConnPerRoute(8)
            .setDefaultIOReactorConfig(ioConfig)
            .setKeepAliveStrategy((response,context) -> 6000)
            .build();
        httpClient.start();
    }

    public HttpOutboundAsyncHandler(List<String> candidateBackend) {
        this();
        this.customHttpEndpointRouter = new CustomHttpEndpointRouter(candidateBackend);
    }

    public void handle(final FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        final String url = customHttpEndpointRouter.route() + fullRequest.uri();
        executorService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, String
        url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpClient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse endpointResponse) {
                try {
                    handleResponse(fullRequest, ctx, endpointResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
            @Override
            public void failed(final Exception ex) {
                httpGet.abort();
                ex.printStackTrace();
            }
            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }

    private void handleResponse(FullHttpRequest fullRequest,
        ChannelHandlerContext ctx, HttpResponse httpResponse) {
        FullHttpResponse response = null;
        try {
            byte[] body = EntityUtils.toByteArray(httpResponse.getEntity());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK, Unpooled
                .wrappedBuffer(body));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt
                (httpResponse.getFirstHeader("Content-Length").getValue()));
        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    //response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Exception cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
