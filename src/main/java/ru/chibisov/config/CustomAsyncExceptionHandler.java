package ru.chibisov.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger log = LogManager.getLogger(CustomAsyncExceptionHandler.class.getName());

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        log.error(String.format("Exception message - %s \n Method name - %s", throwable.getMessage(), method.getName()));
    }
}

