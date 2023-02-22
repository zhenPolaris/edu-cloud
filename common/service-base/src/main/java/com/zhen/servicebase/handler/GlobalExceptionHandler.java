package com.zhen.servicebase.handler;


import com.zhen.commonutils.R;
import com.zhen.servicebase.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error();
    }


    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public R error(SystemException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }


}