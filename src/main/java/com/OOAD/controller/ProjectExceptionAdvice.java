package com.OOAD.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result doException (Exception e){
        return new Result(e.getMessage(), "系统错误，请重试", 3001);
    }
}
