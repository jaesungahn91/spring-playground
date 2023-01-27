package io.github.js.service.global.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExceptionServiceTest {

    @Autowired
    private ExceptionService exceptionService;

    @Test
    void service() throws InterruptedException {
        exceptionService.service();
        Thread.sleep(3000);
    }

}