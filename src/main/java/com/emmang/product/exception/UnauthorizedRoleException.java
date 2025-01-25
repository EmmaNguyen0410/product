package com.emmang.product.exception;

public class UnauthorizedRoleException extends RuntimeException{
    public UnauthorizedRoleException(String message) {
        super(message);
    }
}
