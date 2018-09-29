package cn.ersoft.sexam.common.util;

import java.util.regex.Pattern;


public class RegUtil {

    private static final String MOBILE_REG = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

    public static boolean isMobile(String mobile) {
        return Pattern.compile(MOBILE_REG).matcher(mobile).matches();
    }

}
