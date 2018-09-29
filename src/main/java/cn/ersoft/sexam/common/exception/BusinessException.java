package cn.ersoft.sexam.common.exception;

import cn.ersoft.sexam.constants.ResultCode;
import lombok.Getter;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    @Getter
    private String code;

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public static void throwException(ResultCode resultCode){
        throw  new BusinessException(resultCode);
    }

}
