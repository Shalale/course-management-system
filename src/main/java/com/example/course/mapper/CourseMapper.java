package com.example.course.mapper;

import com.example.course.dao.entity.Course;
import com.example.course.dto.request.CourseRequest;
import com.example.course.dto.response.CourseResponse;
import com.example.course.model.constant.Status;

public class CourseMapper {
    public static CourseResponse entityToDto(Course course){
        return  CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .price(course.getPrice())
                .duration(course.getDuration())
                .status(course.getStatus())
                .build();
    }

    public static Course requestToEntity(CourseRequest courseRequest){
        return Course.builder()
                .name(courseRequest.getName())
                .price(courseRequest.getPrice())
                .duration(courseRequest.getDuration())
                .status(Status.ACTIVE)
                .build();
    }

    public static Course updateCourse(CourseRequest request, Course course){
        course.setDuration(request.getDuration());
        course.setName(request.getName());
        course.setPrice(request.getPrice());
        return course;
    }
}
