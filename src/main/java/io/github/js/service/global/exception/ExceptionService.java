package io.github.js.service.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class ExceptionService {

    private final AsyncService asyncService;

    @ErrorSendSlack
    @Async
    public void service() {
        asyncService.asyncService();
    }

}
