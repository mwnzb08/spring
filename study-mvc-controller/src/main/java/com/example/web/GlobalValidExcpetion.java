package com.example.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalValidExcpetion {
    @ExceptionHandler(ValidationException.class)//添加对
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> validExcptionHandel(ValidationException v){
        Map<String,String> map = new HashMap<>();
        map.put("message",v.getMessage());
        return map;
    }
}
