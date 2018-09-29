package cn.ersoft.sexam.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 日志切面
 *
 */
@Component
@Aspect
@Slf4j
public class MethodLogAop {

    @Around("@annotation(methodLog) && execution(* cn.ersoft.sexam.*.*(..))")
    public Object methodAround(ProceedingJoinPoint joinPoint, MethodLog methodLog) throws Throwable {
        Object returnVal = null;
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = signature instanceof MethodSignature ? ((MethodSignature) signature) : null;
        Object target = joinPoint.getTarget();
        if (Objects.nonNull(methodSignature) && Objects.nonNull(methodLog)) {
            boolean inputLog = methodLog.inputLog();
            boolean outputLog = methodLog.outputLog();
            boolean processTime = methodLog.processTime();
            String methodName = "[" + methodSignature.getName() + "] in Class " + target.getClass().getName();
            Object[] args = joinPoint.getArgs();
            List<String> params = Lists.newArrayListWithCapacity(args.length);
            if (inputLog) {
                Arrays.stream(args).forEach(arg -> params.add(JSONObject.toJSONString(arg)));
                log.info("方法{}, 参数:{}", methodName, StringUtils.join(params, ","));
            }
            long beforeProcessTime = System.currentTimeMillis();
            try {
                returnVal = joinPoint.proceed();
            } catch (Exception e) {
                // 调用接口报错，记录异常，并向上抛出
                log.error("方法调用异常, 方法{}, 参数:{}", methodName, Joiner.on(",").skipNulls().join(params), e);
                throw e;
            }
            long afterProcessTime = System.currentTimeMillis();
            if (outputLog) {
                if (processTime) {
                    log.info("方法{}, 返回数据:{}, 执行时间:{}ms", methodName,
                            JSONObject.toJSONString(returnVal), afterProcessTime - beforeProcessTime);
                } else {
                    log.info("方法{}, 返回数据:{}", methodName, JSONObject.toJSONString(returnVal));
                }
            }
            if (!outputLog && processTime) {
                log.info("方法{}, 执行时间:{}ms", methodName, afterProcessTime - beforeProcessTime);
            }
        }
        return returnVal;
    }
}
