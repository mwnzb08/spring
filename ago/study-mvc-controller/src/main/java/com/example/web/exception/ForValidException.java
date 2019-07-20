package com.example.web.exception;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)//返回码
public class ForValidException extends RuntimeException{
    private BindingResult result;

    public ForValidException(BindingResult result){
        this.result=result;
    }

    public BindingResult getResult() {
        return result;
    }
}
