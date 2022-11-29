package com.example.course.dao.repository;

import com.example.course.dao.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM student_courses WHERE student_id = :studentId"
            , nativeQuery = true)
    List<Course> registeredCourses(Long studentId);
}
