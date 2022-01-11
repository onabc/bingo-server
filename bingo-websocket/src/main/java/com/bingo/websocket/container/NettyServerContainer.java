package com.bingo.websocket.container;

import com.bingo.websocket.server.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/12/13 16:27
 */
@Component
@Scope("singleton")
public class NettyServerContainer {

    private final Logger logger = LoggerFactory.getLogger(NettyServerContainer.class);

    @Autowired
    private WebSocketServer webSocketServer;

    /**
     *      启用一个新的线程监听webSocket端口
     *      1. 启动Netty WebSocket服务器；
     *      2. 加载用户数据；
     *      3. 加载用户交流群数据。
     */
    @PostConstruct
    public void init() {
       webSocketServer.init();
    }

    @SuppressWarnings("deprecation")
    @PreDestroy
    public void close() throws InterruptedException {
        logger.info("正在释放Netty Websocket相关连接...");
        webSocketServer.close();
        logger.info("系统关闭！");
    }
}

