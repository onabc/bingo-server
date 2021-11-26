package com.bingo.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/11/25 13:34
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BingoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoAuthApplication.class, args);
    }
}
