package com.bingo.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lumen
 * @version 1.0
 * @description: WebSocketServer.
 * @date 2021/12/13 16:07
 */
@Service
public class WebSocketServer {

    @Value("${websocket.port}")
    private int port;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private ServerBootstrap serverBootstrap;

    private ChannelFuture serverChannelFuture;

    @Resource(name="httpRequestHandler")
    public ChannelHandler httpRequestHandler;

    @Resource(name = "webSocketServerHandler")
    public ChannelHandler webSocketServerHandler;

    public void init() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(592048))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("logging",new LoggingHandler("DEBUG"));//设置log监听器，并且日志级别为debug，方便观察运行流程
                            socketChannel.pipeline().addLast("http-codec",new HttpServerCodec());//设置解码器
                            socketChannel.pipeline().addLast("aggregator",new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
                            socketChannel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());//用于大数据的分区传输
                            socketChannel.pipeline().addLast("http-handler", httpRequestHandler);
                            socketChannel.pipeline().addLast("websocket-handler",webSocketServerHandler);
                        }
                    });
            serverChannelFuture = serverBootstrap.bind(port).sync();
        } catch (Exception ex) {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            ex.printStackTrace();
        }
    }

    public void close() throws InterruptedException {
        serverChannelFuture.channel().closeFuture().sync();
        Future<?> bossGroupFuture = bossGroup.shutdownGracefully();
        Future<?> workerGroupFuture = workerGroup.shutdownGracefully();

        try {
            bossGroupFuture.await();
            workerGroupFuture.await();
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
        }
    }
}


