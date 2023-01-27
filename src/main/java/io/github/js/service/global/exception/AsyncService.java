package io.github.js.service.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AsyncService {

    @ErrorSendSlack(moduleName = "테스트모듈")
    @Async
    public void asyncService() {
        log.error("async service logging...");
        throw new RuntimeException("RuntimeException Message");
    }

}
