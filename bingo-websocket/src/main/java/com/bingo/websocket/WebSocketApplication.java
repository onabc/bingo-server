package com.bingo.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/12/13 15:23
 */
@SpringBootApplication
@ComponentScan(value = {"com.bingo.websocket"})
public class WebSocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }
}
