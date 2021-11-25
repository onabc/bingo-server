package com.bingo.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author lumen
 * @version 1.0
 * @description: TODO
 * @date 2021/11/25 13:50
 */
@RestController()
public class AccountController {

    @Value("${spring.redis.host}")
    private String host;

    @GetMapping("auth/login")
    public String login(){
        return host;
    }
}
