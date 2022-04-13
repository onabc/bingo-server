package com.thirdparty.model;

import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString()
public class WeixinMessage {
    private String touser;// 用户openId
    private String template_id;// 订阅消息模版id
    private String page = "pages/index/index";
    private String miniprogram_state = "developer";
    private String lang = "zh_CN";
    private Map<String, SubscribeMsgData.Keyword> data;
}
