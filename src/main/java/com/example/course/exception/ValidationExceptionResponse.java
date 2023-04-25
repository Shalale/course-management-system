package com.example.course.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationExceptionResponse {

    private int status;
    private List<String> errorMessages;
    private Long timeStamp;
}
