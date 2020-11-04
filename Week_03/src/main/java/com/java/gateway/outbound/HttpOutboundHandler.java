package com.java.gateway.outbound;

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
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.*;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpOutboundHandler extends ChannelOutboundHandlerAdapter {
    private String proxyServer;
    private ExecutorService executorService;
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public HttpOutboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        long keepAliveTime = 1000;
        int queueSize = 2048;
        RejectedExecutionHandler handler = new CallerRunsPolicy();
        executorService = new ThreadPoolExecutor(cores, cores, keepAliveTime, TimeUnit
            .MILLISECONDS, new ArrayBlockingQueue<Runnable>(queueSize), new NamedThreadFactory
            ("proxyService"), handler);
    }

    public void handle(final FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        final String url = this.proxyServer + fullRequest.uri();
        executorService.submit(() -> fetchGet(fullRequest, ctx, url));
    }

    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, String
        url) {
        final HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);

        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            handleResponse(fullRequest, ctx, httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
