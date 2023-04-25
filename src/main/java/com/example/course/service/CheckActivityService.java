package com.example.course.service;

import com.example.course.exception.DeactiveElementException;
import com.example.course.exception.ExceptionConstants;
import com.example.course.model.Course;
import com.example.course.model.Status;
import com.example.course.model.Student;
import org.springframework.stereotype.Service;

@Service
public class CheckActivityService {
    public void isActive(Course course) {
        if (course.getStatus() == Status.DEACTIVATED)
            throw new DeactiveElementException(
                    String.format(ExceptionConstants.DEACTIVE_ELEMENT_MESSAGE, course.getId()),
                    "COURSE_IS_DEACTIVE");
    }

    public void isActive(Student student) {
        if (student.getStatus() == Status.DEACTIVATED)
            throw new DeactiveElementException(
                    String.format(ExceptionConstants.DEACTIVE_ELEMENT_MESSAGE, student.getId()),
                    "STUDENT_IS_DEACTIVE");
    }
}
