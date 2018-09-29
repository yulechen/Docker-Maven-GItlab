package cn.ersoft.sexam.common.util;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class RestHelper {

    public static  String genUrlParameter(String url , Map<String, Object> params){
        String paramStr= params.keySet().stream().map(k-> String.format("%s={%s}",k,k))
                .collect(Collectors.joining("&"));
       return String.format("%s%s%s",url,"?",paramStr);
    }

}
