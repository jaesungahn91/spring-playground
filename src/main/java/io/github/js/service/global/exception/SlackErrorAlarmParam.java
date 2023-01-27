package io.github.js.service.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SlackErrorAlarmParam {

    private final String moduleName;
    private final String className;
    private final String methodName;
    private final Throwable throwable;

    @Builder
    private SlackErrorAlarmParam(String moduleName, String className, String methodName, Throwable throwable) {
        this.moduleName = moduleName;
        this.className = className;
        this.methodName = methodName;
        this.throwable = throwable;
    }

}
