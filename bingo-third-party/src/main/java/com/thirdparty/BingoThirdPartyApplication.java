package com.thirdparty;

import com.bingo.common.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableCustomSwagger2
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class )
public class BingoThirdPartyApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoThirdPartyApplication.class, args);

    }
}
