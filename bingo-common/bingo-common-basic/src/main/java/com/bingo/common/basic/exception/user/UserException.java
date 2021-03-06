package com.bingo.common.basic.exception.user;

import com.bingo.common.basic.exception.base.BaseException;

/**
 * 用户信息异常类
 * 
 * @author bingo
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
