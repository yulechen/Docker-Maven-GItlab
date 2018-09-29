package cn.ersoft.sexam.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@Slf4j
public class ExampleUtil {


    public static <T> Example<T> copyToExample(Object source, Class<T> targetClazz, ExampleMatcher... matcher) {
        T targetObj = BeanCopyUtil.copyProperties(source, targetClazz, false);
        if (null == matcher || matcher.length == 0) {
            return Example.of(targetObj);
        }
        return Example.of(targetObj, matcher[0]);
    }

    public static ExampleMatcher like(String fieldName) {
        return ExampleMatcher.matching().withMatcher(fieldName, contains().ignoreCase());
    }


}
