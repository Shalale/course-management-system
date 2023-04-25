package com.example.course.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CourseRequest {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Min(value = 1, message = "Price should be greater than or equal to 1")
    private Integer price;

    @Min(value = 1, message = "Duration should be greater than or equal to 1")
    private Integer duration;
}
