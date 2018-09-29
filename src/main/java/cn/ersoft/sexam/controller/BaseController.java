package cn.ersoft.sexam.controller;


import cn.ersoft.sexam.common.api.ApiResult;
import cn.ersoft.sexam.common.api.PageReq;
import cn.ersoft.sexam.common.api.PageVO;
import cn.ersoft.sexam.constants.ResultCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Objects;

/**
 * 控制层基类
 */
public class BaseController {

    /**
     * 成功
     * @param data
     * @return
     */
    protected ApiResult success(Object data) {
        ApiResult result = new ApiResult();
        if(data instanceof Page){
            Page page= ((Page) data);
            result.setData(new PageVO<>(page,page.getContent()));
        }else
        result.setData(data);
        result.setCode(ResultCode.SUCCESS.getCode());
        return result;
    }

    protected ApiResult success() {
        return success(null);
    }

    protected ApiResult error(String msg) {
        return error(ResultCode.SERVER_EXCEPTION.getCode(), msg);
    }

    /**
     * 错误
     * @param code
     * @param msg
     * @return
     */
    protected ApiResult error(String code, String msg) {
        ApiResult result = new ApiResult();
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }

    protected PageRequest getPageRequestByDefaultSort(PageReq pageReq) {
        pageReq.setDirection(PageReq.DEFAULT_DIRECTION);
        pageReq.setProperty(PageReq.DEFAULT_PROPERTY);
        return getPageRequest(pageReq);
    }

    protected PageRequest getPageRequest(PageReq pageReq) {
        if (Objects.isNull(pageReq)) {
            return null;
        }
        if (Objects.isNull(pageReq.getDirection()) && Objects.isNull(pageReq.getProperty())) {
            return PageRequest.of(pageReq.getPageNo(), pageReq.getSize());
        }
        return PageRequest.of(pageReq.getPageNo(), pageReq.getSize(), pageReq.getDirection(), pageReq.getProperty());
    }

}
