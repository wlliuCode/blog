package com.wlliu.blog.base.service.handler;


import com.wlliu.blog.base.service.exception.GlobalException;
import com.wlliu.blog.base.service.result.Result;
import com.wlliu.blog.base.service.result.ResultCodeEnum;
import com.wlliu.blog.base.utils.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author helen
 * @since 2020/4/1
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
//        e.printStackTrace();
        log.error(ExceptionUtils.getMessage(e));
        return Result.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Result error(HttpMessageNotReadableException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.setResult(ResultCodeEnum.JSON_PARSE_ERROR);
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public Result error(GlobalException e){
        log.error(ExceptionUtils.getMessage(e));
        return Result.error().message(e.getMessage()).code(e.getCode());
    }
}
