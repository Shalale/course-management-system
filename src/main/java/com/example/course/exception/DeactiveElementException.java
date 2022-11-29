package com.example.course.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeactiveElementException extends RuntimeException {
    private String code;

    public DeactiveElementException(String code, String message) {
        super(message);
        this.code = code;
    }
}
