package com.thirdparty.controller;

import com.thirdparty.model.*;
import com.thirdparty.service.WeixinApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("third_party/weixin")
public class WeixinController {

    @Autowired
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
    @GetMapping("code/{code}/openid")
    public WeixinSession getOpenid(@PathVariable String code) {
        Map<String, String> map = new HashMap<>();
        map.put("appid", weixinAppid);
        map.put("secret", weixinSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        return weixinApi.code2Session(map);
    }

    @PostMapping("send/message/openid/{openid}")
    public void sendMessage(@PathVariable String openid) {
        String accessToken = getAccessToken();

        WeixinMessage wxMsg = new WeixinMessage();
        wxMsg.setTouser(openid);
        wxMsg.setTemplate_id("QcK4eWn-arQ462Lzoq2-oTiV9HZHbgCRirTlQKJmNBQ");

        SubscribeMsgData subscribeMsgData = SubscribeMsgData.create()
                .phrase(8, "消息通知")
                .thing(2, "醉后不知天在水,满船清梦压星河")
                .name(1, "bingo")
                .time(3, LocalDateTime.now())
                .thing(7, "这里是备注呀");
        wxMsg.setData(subscribeMsgData.data());

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
