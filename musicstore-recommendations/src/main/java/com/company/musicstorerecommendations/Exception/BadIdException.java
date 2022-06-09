package com.company.musicstorerecommendations.Exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadIdException extends RuntimeException {
    public BadIdException(String message) {super(message);}
    public BadIdException() {super();}
}
