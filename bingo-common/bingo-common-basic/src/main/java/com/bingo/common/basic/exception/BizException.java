package com.bingo.common.basic.exception;

import com.bingo.common.basic.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 *
 * @author lumen
 * @version 1.0
 * @date 2022/1/4 14:26
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BizException extends Exception {
    private int code;

    private String message;

    public BizException(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
