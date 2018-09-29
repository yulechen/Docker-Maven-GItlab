package cn.ersoft.sexam.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * api响应结果封装
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult {

    /**
     * 错误码, 0表示成功，非零表示自己的业务错误
     */
    private String code;

    /**
     * 错误信息描述
     */
    private String msg;

    /**
     * 返回的实际数据
     */
    private Object data;

}
