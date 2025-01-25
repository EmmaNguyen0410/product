package com.emmang.product.exception;

public class RestTemplateFailureException extends RuntimeException{
    public RestTemplateFailureException(String message) {
        super(message);
    }
}
