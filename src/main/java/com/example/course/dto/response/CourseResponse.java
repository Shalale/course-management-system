package com.example.course.dto.response;

import com.example.course.model.Status;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private Integer price;
    private Integer duration;
    private Status status;

}
