package cn.ersoft.sexam.common.api;

import cn.ersoft.sexam.common.exception.BusinessException;
import cn.ersoft.sexam.constants.ResultCode;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.Map;
import java.util.Objects;


/**
 * api响应结果封装
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {


    private Map<String, Object> header;

    /**
     * 返回的实际数据
     */
    private Map<String, Object> body;


    public <T> T findBodyToObject(String key, Class<T> t) {
        checkKeyExist(key);
        Object data = getBody().get(key);
        String jsonConfig = JSON.toJSONString(data);
        return JSON.parseObject(jsonConfig, t);
    }

    public <T> T findBodyToObject(String key, TypeReference<T> type, boolean required) {
        if (required) {
            checkKeyExist(key);
        }
        Object data = getBody().get(key);
        String jsonConfig = JSON.toJSONString(data);
        return JSON.parseObject(jsonConfig, type);
    }


    public <T> T findBodyToObject(String key, TypeReference<T> type) {
        return findBodyToObject(key, type, false);
    }


    public <T> T body2Object(Class<T> t) {
        return JSON.parseObject(JSON.toJSONString(getBody()), t);
    }


    public String findBodyStringValue(String bodyKey) {
        return findBodyStringValue(bodyKey, false);
    }


    public String findBodyStringValue(String bodyKey, boolean required) {
        if (required) {
            checkKeyExist(bodyKey);
        }
        return (String) body.get(bodyKey);
    }


    public Integer findBodyIntegerValue(String bodyKey) {
        return findBodyIntegerValue(bodyKey, false);
    }


    public Long findBodyLongValue(String bodyKey) {
        checkKeyExist(bodyKey);
        Object value = body.get(bodyKey);
        if (value instanceof Integer) {
            return ((Integer) value).longValue();
        }
        else if (value instanceof Long) {
            return (Long) value;
        }else  if(value instanceof  String){
            return Long.parseLong((String) value);
        }
        return null;
    }


    public Integer findBodyIntegerValue(String bodyKey, boolean required) {
        if (required) {
            checkKeyExist(bodyKey);
        }
        return (Integer) body.get(bodyKey);
    }


    private void checkKeyExist(String bodyKey) {
        if (!body.containsKey(bodyKey)) {
            throw new BusinessException(ResultCode.FRONT_PARAM_EXCEPTION.getCode(), "缺失参数: " + bodyKey);
        }
    }


    public PageRequest findBodyPageRequest() {
        PageReq pageReq = findBodyToObject("page", PageReq.class);
        if (Objects.isNull(pageReq)) {
            return null;
        }
        if (Objects.isNull(pageReq.getDirection()) && Objects.isNull(pageReq.getProperty())) {
            return PageRequest.of(pageReq.getPageNo(), pageReq.getSize());
        }
        return PageRequest.of(pageReq.getPageNo(), pageReq.getSize(), pageReq.getDirection(),
            pageReq.getProperty());
    }
}
