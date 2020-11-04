package com.java.gateway.filter;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpRequestMethodFilter implements HttpRequestFilter {
    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpMethod method = fullRequest.getMethod();

        if (method == HttpMethod.POST) {

            FullHttpResponse response = null;
            try {
                response = new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST);
                response.headers()
                        .set("Content-Type", "application/json");
                response.headers()
                        .setInt("Content-Length", response.content()
                                .readableBytes());
            } catch (Exception e) {
                response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            } finally {
                if (fullRequest != null) {
                    if (!HttpUtil.isKeepAlive(fullRequest)) {
                        ctx.write(response)
                                .addListener(ChannelFutureListener.CLOSE);
                    } else {
                        response.headers()
                                .set(CONNECTION, KEEP_ALIVE);
                        ctx.write(response);
                    }
                }
            }
        }

        return false;

    }
}
