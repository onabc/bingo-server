package com.bingo.websocket.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/12/13 17:11
 */
public class Constant {

    public static final String USER_TOKEN = "userId";

    public static Map<String, WebSocketServerHandshaker> webSocketHandshakerMap =
            new ConcurrentHashMap<String, WebSocketServerHandshaker>();

    /**
     * 用户对应连接的ChannelHandlerContext
     */
    public static Map<String, ChannelHandlerContext> onlineUserMap =
            new ConcurrentHashMap<String, ChannelHandlerContext>();

}

