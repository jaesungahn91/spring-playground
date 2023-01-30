package io.github.js.self.invocation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class SelfInvocationAspect {

    @Pointcut("execution(void io.github.js.self.invocation.BusinessService*.go(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        log.info("aspect");
    }

}
