package cn.ersoft.sexam.common.exception;

import cn.ersoft.sexam.common.api.ApiResult;
import cn.ersoft.sexam.constants.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionResolver {


    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ApiResult handler(Exception e) {
        ApiResult result = new ApiResult();
        if (BusinessException.class.isAssignableFrom(e.getClass())) {
            BusinessException bex = (BusinessException) e;
            result.setCode(bex.getCode());
            result.setMsg(e.getMessage());
            log.error("HandlerExceptionResolver catch Exception: code->{}, msg->{}", bex.getCode(), bex.getMessage(),
                    e);
        } else if (BindException.class.isAssignableFrom(e.getClass())) {
            result.setCode(ResultCode.FRONT_PARAM_EXCEPTION.getCode());
            result.setMsg(e.getMessage());
            log.error("HandlerExceptionResolver catch Exception: msg->{}", e.getMessage(), e);
        } else if (ServletRequestBindingException.class.isAssignableFrom(e.getClass())) {
            result.setCode(ResultCode.FRONT_PARAM_EXCEPTION.getCode());
            result.setMsg(e.getMessage());
            log.error("ServletRequestBindingException catch Exception: msg->{}", e.getMessage(), e);
        } else if (HttpMessageNotReadableException.class.isAssignableFrom(e.getClass())) {
            result.setCode(ResultCode.FRONT_JSON_FORMATTER_EXCEPTION.getCode());
            result.setMsg(e.getMessage());
            log.error("HttpMessageNotReadableException catch Exception: msg->{}", e.getMessage(), e);
        } else {
            result.setCode(ResultCode.SERVER_EXCEPTION.getCode());
            result.setMsg(ResultCode.SERVER_EXCEPTION.getMessage());
            log.error("HandlerExceptionResolver catch Exception: msg->{}", e.getMessage(), e);
        }
        return result;
    }

}
