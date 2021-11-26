package com.bingo.auth.model;

import lombok.Data;

/**
 * @author lumen
 * @version 1.0
 * @description: 用户登录输入对象
 * @date 2021/11/26 10:28
 */
@Data
public class LoginVo {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
