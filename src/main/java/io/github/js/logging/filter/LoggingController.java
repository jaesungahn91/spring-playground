package io.github.js.logging.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggingController {

    @PostMapping(value = "/logging-test")
    public ResponseEntity<TestRequest> loggingTest(@RequestBody TestRequest request) {
        log.info(request.toString());
        return ResponseEntity.ok(request);
    }

}
