package cn.ersoft.sexam.common.util;

import java.util.Random;

public class RandomUtil {
    /**
     * 生成指定位数的随机数
     *
     * @param length
     * @return
     */
    public static String getRandom(int length) {
        StringBuffer buffer = new StringBuffer("");
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int a=0;
            while((a=random.nextInt(10))==0);
            buffer.append(String.valueOf(a));
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(Long.parseLong(getRandom(6)));
    }
}
