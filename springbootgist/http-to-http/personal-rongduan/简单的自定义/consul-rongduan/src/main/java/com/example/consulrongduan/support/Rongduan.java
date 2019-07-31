package com.example.consulrongduan.support;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Component
@Slf4j
public class Rongduan {
    private static final Integer TIMES=3;//设置超时的请求次数
    private Map<String, AtomicInteger> counter = new ConcurrentHashMap<>();//记录失败的次数
    private Map<String,AtomicInteger> breakCounter = new ConcurrentHashMap<>();//记录熔断的次数

    @Around("execution(* com.example.consulrongduan.inter..*(..))")//扫描拦截包下所有的方法
    public Object doWithCircuitBreaker(ProceedingJoinPoint pjp) throws Throwable {
        String signature = pjp.getSignature().toLongString();//获取方法
        log.info("Invoke {}", signature);
            Object retVal;
            try {
                if (counter.containsKey(signature)) {//有
                    if (counter.get(signature).get() > TIMES &&//判断值，当大于3进入
                            breakCounter.get(signature).get() < TIMES) {
                        log.warn("Circuit breaker return null, break {} times.",
                                breakCounter.get(signature).incrementAndGet());
                        return null;
                    }
                } else {
                    counter.put(signature, new AtomicInteger(0));//没有就设置为0
                    breakCounter.put(signature, new AtomicInteger(0));
                }
                retVal = pjp.proceed();//获取实
                counter.get(signature).set(0);
                breakCounter.get(signature).set(0);
            } catch (Throwable t) {
                log.warn("Circuit breaker counter: {}, Throwable {}",
                        counter.get(signature).incrementAndGet(), t.getMessage());//失败+1
                breakCounter.get(signature).set(0);
            throw t;
        }
        return retVal;
    }
}
