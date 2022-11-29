package com.example.course.dao.repository;

import com.example.course.dao.entity.Student;
import com.example.course.dto.response.StudentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
