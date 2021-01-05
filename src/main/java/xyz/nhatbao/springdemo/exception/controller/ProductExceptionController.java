package xyz.nhatbao.springdemo.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.nhatbao.springdemo.exception.ProductNotFoundException;

@ControllerAdvice
public class ProductExceptionController {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> notFoundException(ProductNotFoundException e) {
        return new ResponseEntity<Object>("Product not found", HttpStatus.NOT_FOUND);
    }
}
