package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器类，用于统一处理系统中未被捕获的异常
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理方法，捕获所有类型的异常并返回统一格式的错误响应
     *
     * @param e 捕获到的异常对象
     * @return 包含错误信息的统一响应结果
     */
    @ExceptionHandler
    public Result handle(Exception e){
        // 记录异常日志，便于问题排查
        log.error("服务器发生异常:{}",e.getMessage());
        // 返回统一的错误响应格式
        return Result.error("服务器发生异常");
    }
}
