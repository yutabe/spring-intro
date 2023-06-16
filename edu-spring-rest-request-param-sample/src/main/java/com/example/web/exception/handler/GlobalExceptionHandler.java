package com.example.web.exception.handler;

import com.example.web.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        ErrorResponse errorResponse = new ErrorResponse("入力エラー");
        for (FieldError fieldError : fieldErrors) {
            errorResponse.addDetail(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
        String parameterName = exception.getName();
        Object actualValue = exception.getValue();
        ErrorResponse errorResponse = new ErrorResponse("パラメータの形式が不正");
        errorResponse.addDetail(parameterName, "送信された値: " + actualValue + ", 正しい形式で入力してください。");
        return errorResponse;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        ErrorResponse errorResponse = new ErrorResponse("リクエストボディの解析に失敗");
        return errorResponse;
    }

}
