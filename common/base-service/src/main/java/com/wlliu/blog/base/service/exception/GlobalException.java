package com.wlliu.blog.base.service.exception;


import com.wlliu.blog.base.service.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author helen
 * @since 2020/4/15
 */
@Data
public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public GlobalException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "BlogException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
