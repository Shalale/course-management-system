package com.example.course.dto.response;

import com.example.course.model.constant.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeacherResponse {
    private Long id;
    private String name;
    private String email;
    private Integer salary;
    private Status status;
//    private List<Course> courses;
}
