package com.korit.fileupload_back.controller;

import com.korit.fileupload_back.exception.MemberInsertException;
import com.korit.fileupload_back.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MemberInsertException.class)
    public ResponseEntity<?> memberInsertException(MemberInsertException e) {
        return ResponseEntity
                .status((HttpStatus) e.getErrorMap().get("httpStatus"))
                .body(e.getErrorMap().get("message"));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
    }
}
