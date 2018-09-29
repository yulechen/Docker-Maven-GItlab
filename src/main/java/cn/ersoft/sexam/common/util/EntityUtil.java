package cn.ersoft.sexam.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Date;

public abstract class EntityUtil {

    public static <T> T instantiateWithDefaultValue(Class<T> clazz, Object ... sourceValue){
        T t = BeanUtils.instantiateClass(clazz);
        Method setCreateTime = ReflectionUtils.findMethod(clazz, "setCreateTime", Date.class);
        if(null != setCreateTime)
          ReflectionUtils.invokeMethod(setCreateTime,t, new Date());
          Method setUpdateTime = ReflectionUtils.findMethod(clazz, "setUpdateTime", Date.class);
         if(null != setUpdateTime)
            ReflectionUtils.invokeMethod(setUpdateTime,t, new Date());
         if(null != sourceValue && sourceValue.length>0)
             BeanCopyUtil.copyProperties(sourceValue,t,false);
        return t;
    }
}
