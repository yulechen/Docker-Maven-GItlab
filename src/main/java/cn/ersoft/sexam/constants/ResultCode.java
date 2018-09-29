package cn.ersoft.sexam.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误码，根据需要在下面随时添加，注意错误码号段
 * 错误码号段：第一位1表示后端，2表示前端，第二三位表示错误分类，最后四位为具体的错误码
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS("0", "请求成功"),
    // 1010001
	SERVER_EXCEPTION("1010001", "服务器异常，请联系管理员。"),
	LOGIN_EXCEPTION("1010002", "登录异常"),
	USERNAME_OR_PWD_EXCEPTION("1010003", "用户名或密码错误"),

    // 2010001
	FRONT_EXCEPTION("2010001", "前端异常"),
	FRONT_PARAM_EXCEPTION("2010002", "前端参数异常"),
	FRONT_JSON_FORMATTER_EXCEPTION("2010003", "JSON格式异常"),

    // TOKEN类异常
	TOKEN_EXPIRED("2020001", "Token已过期"),
	TOKEN_FORMATTER_ERROR("2020002", "Token格式错误"),
	TOKEN_MALFORMED("2020003", "Token没有被正确构造"),
	TOKEN_SIGNATURE_ERROR("2020004", "签名失败"),
	TOKEN_IS_NULL("2020005", "Token为空"),

    ;

    /**
     * 业务码
     */
    private final String code;

    /**
     * 描述
     */
    private final String message;

}
