package com.java.gateway.inbound;

import com.java.gateway.inbound.HttpInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

import java.util.List;

public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private final SslContext sslCtx;
    private List<String> candidateBackend;


    public HttpInboundInitializer(SslContext sslCtx, List<String> candidateBackend) {
        this.sslCtx = sslCtx;
        this.candidateBackend = candidateBackend;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }
        p.addLast(new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpInboundHandler(candidateBackend));
    }
}
