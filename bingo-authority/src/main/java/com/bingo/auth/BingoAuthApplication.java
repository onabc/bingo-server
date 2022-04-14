package com.bingo.auth;

import com.bingo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/11/25 13:34
 */
@EnableDiscoveryClient
@EnableCustomSwagger2
@EnableSwagger2
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BingoAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoAuthApplication.class, args);
    }
}
