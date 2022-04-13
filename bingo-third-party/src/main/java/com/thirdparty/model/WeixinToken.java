package com.thirdparty.model;

import lombok.Data;

@Data
public class WeixinToken {
    /**
     * 凭证
     */
    private String access_token;

    /**
     * 凭证有效时间
     */
    private Integer expires_in;
    private String errcode;
    private String errmsg;
}
