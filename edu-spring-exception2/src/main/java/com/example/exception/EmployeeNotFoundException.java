package com.example.exception;

// TODO 01 独自例外クラスの内容を確認する（完成済み）
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
