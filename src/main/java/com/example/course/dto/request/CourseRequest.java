package com.example.course.dto.request;

import lombok.Data;

@Data
public class CourseRequest {
    private String name;
    private Integer price;
    private Integer duration;
}
