package com.example.course.mapper;

import com.example.course.dao.entity.Student;
import com.example.course.dto.request.StudentRequest;
import com.example.course.dto.response.StudentResponse;
import com.example.course.model.constant.Status;

public class StudentMapper {
    public static StudentResponse mapEntityToDto(Student student){
        return  StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .status(student.getStatus())
                .build();
    }

    public static Student dtoToEntity(StudentRequest studentRequest){
        return Student.builder()
                .name(studentRequest.getName())
                .email(studentRequest.getEmail())
                .status(Status.ACTIVE)
                .build();
    }
}
