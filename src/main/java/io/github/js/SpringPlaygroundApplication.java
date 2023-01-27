package io.github.js;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPlaygroundApplication.class, args);
    }

}
