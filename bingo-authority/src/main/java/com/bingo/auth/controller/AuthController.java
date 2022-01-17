package com.bingo.auth.controller;

import com.bingo.common.basic.enums.ResultCode;
import com.bingo.common.basic.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RestController
@RequestMapping("auth")
@RefreshScope
public class AuthController {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${bingo.project.author}")
    private String author;

    @GetMapping("test1")
    public String test1(){
        return host;
    }

    @GetMapping("test2")
    public String test2(){
        return author;
    }

    @GetMapping("test3")
    public String test3() throws BizException {
        throw new BizException(ResultCode.ACCESS_DENIED);
    }
}
