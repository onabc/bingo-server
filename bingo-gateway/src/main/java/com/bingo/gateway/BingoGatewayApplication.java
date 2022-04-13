package com.bingo.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/11/25 11:13
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class BingoGatewayApplication {
    public static void main(String[] args) {
        //SpringApplication.run(BingoGatewayApplication.class, args);
        new SpringApplicationBuilder(BingoGatewayApplication.class).build().run(args);
    }
}
