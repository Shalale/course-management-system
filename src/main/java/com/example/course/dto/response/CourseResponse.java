package com.example.course.dto.response;

import com.example.course.model.constant.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer duration;
    private Status status;

}
