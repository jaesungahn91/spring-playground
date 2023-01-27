package io.github.js.service.global.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
@Component
@Aspect
public class ExceptionAspect {

    private final SlackService slackService;

    @Around("@annotation(ErrorSendSlack)")
    public Object serviceExceptionHandler(ProceedingJoinPoint proceedingJoinPoint) throws IOException {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            SlackErrorAlarmParam param = getSlackErrorAlarmParam(proceedingJoinPoint, e);
            slackService.sendErrorAlarmBySlackApi(param);
            return null;
        }
    }

    @AfterThrowing(value = "@annotation(ErrorSendSlackForApi)", throwing = "e")
    public void serviceExceptionHandler(JoinPoint joinPoint, Exception e) {
        SlackErrorAlarmParam param = getSlackErrorAlarmParam(joinPoint, e);
        slackService.sendErrorAlarmBySlackWebhook(param);
    }

    private SlackErrorAlarmParam getSlackErrorAlarmParam(JoinPoint proceedingJoinPoint, Throwable e) {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String moduleName = signature.getMethod().getAnnotation(ErrorSendSlack.class).moduleName();
        String className = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        return SlackErrorAlarmParam.builder()
                .moduleName(moduleName)
                .className(className)
                .methodName(methodName)
                .throwable(e)
                .build();
    }

}
