package com.emmang.product.exception;

import com.emmang.product.constant.ExceptionMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<?> notAuthenticatedExceptionHandling(NotAuthenticatedException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

//    @ExceptionHandler(UnauthorizedRoleException.class)
//    public ResponseEntity<?> unAuthorizedRoleExceptionHandling(UnauthorizedRoleException exception) {
//        Map<String, Object> body = new HashMap<>();
//        body.put("message", exception.getMessage());
//        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> unAuthorizedRoleExceptionHandling(AccessDeniedException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ExceptionMessage.UNAUTHORIZED_ROLE);
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(RestTemplateFailureException.class)
    public ResponseEntity<?> restTemplateFailureExceptionHandling(UnauthorizedRoleException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
