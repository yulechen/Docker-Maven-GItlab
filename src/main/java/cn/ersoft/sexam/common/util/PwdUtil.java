package cn.ersoft.sexam.common.util;

import org.springframework.util.DigestUtils;

public abstract class PwdUtil {

    public static String encodePwd(String pwd){
        return DigestUtils.md5DigestAsHex(pwd.getBytes());
    }

    public static boolean pwdEqual(String pwd ,String encodedPwd){
        return encodePwd(pwd).equals(encodedPwd);
    }
}
