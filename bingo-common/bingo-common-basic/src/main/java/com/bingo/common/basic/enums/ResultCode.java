package com.bingo.common.basic.enums;

/**
 * @author lumen
 * @version 1.0
 * @date 2022/1/4 14:41
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAIL(500, "操作失败"),

    INVALID_TOKEN(2001,"访问令牌不合法"),
    ACCESS_DENIED(2003,"没有权限访问该资源"),
    CLIENT_AUTHENTICATION_FAILED(1001,"客户端认证失败"),
    USERNAME_OR_PASSWORD_ERROR(1002,"用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式");

    /**
     * 响应状态码
     **/
    private final int code;

    /**
     * 响应信息
     **/
    private final String message;

     ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
