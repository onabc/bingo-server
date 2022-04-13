package com.thirdparty.service;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.thirdparty.model.WeixinMessage;
import com.thirdparty.model.WeixinRes;
import com.thirdparty.model.WeixinSession;
import com.thirdparty.model.WeixinToken;
import retrofit2.http.*;

import java.util.Map;

@RetrofitClient(baseUrl = "${weixin.baseUrl}")
public interface WeixinApi {

    /**
     * 登录凭证校验
     */
    @GET("sns/jscode2session")
    WeixinSession code2Session(@QueryMap Map<String, String> options);

    @GET("cgi-bin/token")
    WeixinToken getAccessToken(@QueryMap Map<String, String> options);

    @POST("cgi-bin/message/subscribe/send")
    WeixinRes sendMessage(@Query("access_token") String token, @Body WeixinMessage msg);
}
