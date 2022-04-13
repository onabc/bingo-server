package com.thirdparty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 订阅消息对象
 */
public class SubscribeMsgData {

    /**
     * 数据data
     */
    private LinkedHashMap<String, Keyword> data = new LinkedHashMap<>();

    public static SubscribeMsgData create() {
        return new SubscribeMsgData();
    }

    /**
     * thing.DATA   事物  20个以内字符 可汉字、数字、字母或符号组合
     *
     * @param thing
     */
    public SubscribeMsgData thing(int index, String thing) {
        if (thing.length() > 20) {
            thing = thing.substring(0, 20);
        }
        data.put("thing" + index, new Keyword(thing));
        return this;
    }

    /**
     * number.DATA  数字  32位以内数字 只能数字，可带小数
     *
     * @param number
     */
    public SubscribeMsgData number(int index, String number) {
        if (number.length() > 32) {
            number = number.substring(0, 32);
        }
        data.put("number" + index, new Keyword(number));
        return this;
    }

    public SubscribeMsgData name(int index, String name) {
        if (name.length() > 10) {
            name = name.substring(0, 10);
        }
        data.put("name" + index, new Keyword(name));
        return this;
    }

    public SubscribeMsgData time(int index, LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        data.put("time" + index, new Keyword(time.format(formatter)));
        return this;
    }

    /**
     * letter.DATA  字母  32位以内字母 只能字母
     *
     * @param letter
     */
    public SubscribeMsgData letter(int index, String letter) {
        if (letter.length() > 32) {
            letter = letter.substring(0, 32);
        }
        data.put("letter" + index, new Keyword(letter));
        return this;
    }

    /**
     * date.DATA    日期  年月日格式（支持+24小时制时间）   例如：2019年10月1日，或：2019年10月1日 15:01
     *
     * @param date
     */
    public SubscribeMsgData date(int index, String date) {
        data.put("date" + index, new Keyword(date));
        return this;
    }

    /**
     * amount.DATA  金额  1个币种符号+10位以内纯数字，可带小数，结尾可带“元”    可带小数
     *
     * @param amount
     */
    public SubscribeMsgData amount(int index, String amount) {
        data.put("amount" + index, new Keyword(amount));
        return this;
    }

    /**
     * phrase.DATA  汉字  5个以内汉字  5个以内纯汉字，例如：配送中
     *
     * @param phrase
     */
    public SubscribeMsgData phrase(int index, String phrase) {
        if (phrase.length() > 5) {
            phrase = phrase.substring(0, 5);
        }
        data.put("phrase" + index, new Keyword(phrase));
        return this;
    }

    public Map<String, Keyword> data() {
        return this.data;
    }

    @Data
    @AllArgsConstructor
    public static class Keyword {
        private String value;
    }

}
