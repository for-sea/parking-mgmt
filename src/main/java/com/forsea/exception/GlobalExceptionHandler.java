package com.forsea.exception;

import com.forsea.enums.ResultCode;
import com.forsea.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获参数校验异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) throws Exception {
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult != null && bindingResult.hasErrors()) {
            StringBuffer message = new StringBuffer();
            for (ObjectError error : bindingResult.getAllErrors()) {
                message.append(error.getDefaultMessage()).append(",");
            }
            log.error("参数校验异常: ======> {}", message.toString().substring(0, message.length() - 1));
            return Result.fail()
                    .code(ResultCode.ARGS_NOT_VALIDATED.getCode())
                    .message(message.toString().substring(0, message.length() - 1));
        }
        log.error("参数校验异常");
        return Result.fail()
                .code(ResultCode.ARGS_NOT_VALIDATED.getCode())
                .message(ResultCode.ARGS_NOT_VALIDATED.getMessage());
    }

    /**
     * 捕获Assert异常
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) throws Exception{
        log.info("Assert异常: ======> {}", e.getMessage());
        return Result.fail().code(ResultCode.WRONG_USERNAME_OR_PASSWORD.getCode()).message(e.getMessage());
    }

    /**
     * 捕获自定义异常
     * @param e
     * @return
     * @throws IOException
     */
    @ExceptionHandler(value = CustomException.class)
    public Result handler(CustomException e) throws IOException {
        log.error("错误{}: ======> {}", e.getCode(), e.getMessage());
        return Result.fail().code(e.getCode()).message(e.getMessage());
    }
}
