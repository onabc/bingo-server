package com.bingo.common.basic.handler;

import com.bingo.common.basic.domain.ApiResult;
import com.bingo.common.basic.exception.BizException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author lumen
 * @version 1.0
 * @date 2022/1/4 15:25
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public ApiResult fail(Exception e) {
        e.printStackTrace();
        return ApiResult.fail("全局异常");
    }

    /**
     * 算术异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(ArithmeticException.class)
    public ApiResult fail(ArithmeticException e) {
        e.printStackTrace();
        return ApiResult.fail("算术异常");
    }

    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BizException.class)
    public ApiResult fail(BizException e) {
        e.printStackTrace();
        return ApiResult.fail(e.getCode(), e.getMessage());
    }
}

