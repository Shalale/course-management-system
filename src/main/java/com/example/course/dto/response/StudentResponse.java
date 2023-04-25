package com.example.course.dto.response;

import com.example.course.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private Status status;
}
