package com.example.course.mapper;

import com.example.course.dao.entity.Teacher;
import com.example.course.dto.request.TeacherRequest;
import com.example.course.dto.response.TeacherResponse;
import com.example.course.model.constant.Status;

public class TeacherMapper {
    public static Teacher requestToEntity(TeacherRequest teacherRequest) {
        return Teacher.builder()
                .name(teacherRequest.getName())
                .email(teacherRequest.getEmail())
                .address(teacherRequest.getAddress())
                .account(teacherRequest.getBankAccount())
                .status(Status.ACTIVE)
                .build();
    }

    public static TeacherResponse entityToResponse(Teacher teacher) {
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .salary(teacher.getSalary())
                .status(teacher.getStatus())
                .build();
    }

    public static void updateTeacher(Teacher teacher, TeacherRequest request) {
        teacher.setName(request.getName());
        teacher.setAddress(request.getAddress());
        teacher.setEmail(request.getEmail());
        teacher.setAccount(request.getBankAccount());
    }
}
