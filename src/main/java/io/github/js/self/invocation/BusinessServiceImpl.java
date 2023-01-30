package io.github.js.self.invocation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    public void ready() {
        log.info("ready");

        // self-invocation 발생
//        this.go();

        // 해결 1
        // AopContext
        ((BusinessService) AopContext.currentProxy()).go();
    }

    public void go() {
        log.info("go");
    }

}
