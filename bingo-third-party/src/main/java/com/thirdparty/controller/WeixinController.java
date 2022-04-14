package com.thirdparty.controller;

import com.thirdparty.model.*;
import com.thirdparty.service.WeixinApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("weixin")
@Api(tags = "微信小程序服务")
public class WeixinController {

    @Resource
    private WeixinApi weixinApi;

    @Value("${weixin.appid}")
    private String weixinAppid;

    @Value("${weixin.secret}")
    private String weixinSecret;

    /**
     * 微信token过期时间
     */
    private LocalDateTime weixinTokenExpireTime;

    /**
     * 微信token
     */
    private String weixinToken;

    /**
     * 微信登录凭证校验
     *
     * @param code
     * @return
     */
    @ApiOperation(value ="获取openid", httpMethod = "GET" )
    @GetMapping("code/{code}/openid")
    public WeixinSession getOpenid(@ApiParam(name = "code", value = "登录返回code", required = true) @PathVariable String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", weixinAppid);
        map.put("secret", weixinSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        return weixinApi.code2Session(map);
    }

    @ApiOperation(value ="发送微信通知")
    @PostMapping("send/message/openid/{openid}")
    public void sendMessage(@ApiParam(name = "openid", value = "用户的微信开放id", required = true) @PathVariable String openid) {
        String accessToken = getAccessToken();

        SubscribeMsgData subscribeMsgData = SubscribeMsgData.create()
                .phrase(8, "消息通知")
                .thing(2, "醉后不知天在水,满船清梦压星河")
                .name(1, "bingo")
                .time(3, LocalDateTime.now())
                .thing(7, "这里是备注呀");
        WeixinMessage wxMsg = WeixinMessage.builder()
                .touser(openid)
                .template_id("QcK4eWn-arQ462Lzoq2-oTiV9HZHbgCRirTlQKJmNBQ")
                .data(subscribeMsgData.data())
                .build();

        WeixinRes res = weixinApi.sendMessage(accessToken, wxMsg);
        System.out.println(res.toString());
    }

    /**
     * 获取微信token
     *
     * @return
     */
    private String getAccessToken() {
        Duration duration = Optional.ofNullable(weixinTokenExpireTime)
                .map(time -> Duration.between(LocalDateTime.now(), time))
                .orElse(Duration.ZERO);
        if (duration.getSeconds() < 30) {
            Map<String, String> map = new HashMap<>();
            map.put("appid", weixinAppid);
            map.put("secret", weixinSecret);
            map.put("grant_type", "client_credential");
            WeixinToken token = weixinApi.getAccessToken(map);
            weixinToken = token.getAccess_token();
            weixinTokenExpireTime = LocalDateTime.now().plusSeconds(token.getExpires_in());
        }
        return weixinToken;
    }
}
