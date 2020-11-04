package com.java.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.apache.log4j.Logger;

import java.util.List;

public class HttpInboundServer {
    private static Logger logger = Logger.getLogger(HttpInboundServer.class);
    private boolean ssl;
    private int port;
    private List<String> candidateBackend;

    public HttpInboundServer(boolean ssl, int port, List<String> candidates) {
        this.ssl = ssl;
        this.port = port;
        this.candidateBackend = candidates;
    }

    public void run() throws Exception {
        final SslContext sslCtx;
        if (ssl) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(3);
        EventLoopGroup workerGroup = new NioEventLoopGroup(1000);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                .option(EpollChannelOption.SO_REUSEPORT, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new HttpInboundInitializer(sslCtx, candidateBackend));

            Channel ch = b.bind(port).sync().channel();
            logger.info("开启netty http服务器，监听地址和端口为 " + (ssl ? "https" : "http") + "://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
