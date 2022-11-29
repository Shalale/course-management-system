package com.example.course.service;

import com.example.course.dao.entity.Course;
import com.example.course.dao.entity.Student;
import com.example.course.dao.repository.CourseRepository;
import com.example.course.dao.repository.StudentRepository;
import com.example.course.dto.request.StudentRequest;
import com.example.course.dto.response.CourseResponse;
import com.example.course.dto.response.StudentResponse;
import com.example.course.mapper.CourseMapper;
import com.example.course.mapper.StudentMapper;
import com.example.course.model.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void createStudent(StudentRequest studentRequest) {
        log.info("ActionLog.createStudent is started");
        var student = StudentMapper.dtoToEntity(studentRequest);
        studentRepository.save(student);
    }

    public StudentResponse getStudentById(Long id) {
        log.info("ActionLog.getStudentById is started id: {}", id);

        var student = fetchStudentIfExist(id);

        return StudentMapper.mapEntityToDto(student);
    }


    @Transactional
    public void registerToCourse(Long studentId,
                                 Long courseId) {
        log.info("ActionLog.registerToCourse is started for student: {} to course: {}", studentId, courseId);

        var student = fetchStudentIfExist(studentId);
        var course = courseRepository.findById(courseId).orElseThrow();

        isActive(student);
        isActive(course);

        student.getCourses().add(course);
        course.getStudents().add(student);
    }

    public List<CourseResponse> registeredCourses(Long studentId) {
        log.info("ActionLog.registeredCourses method is started for student: {}", studentId);
        var student = fetchStudentIfExist(studentId);

        return courseRepository.registeredCourses(studentId)
                .stream()
                .map(CourseMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public void updateStudentName(Long id, String name) {
        log.info("ActionLog.updateStudentName started id: {}, name: {} ", id, name);
        var student = fetchStudentIfExist(id);
        student.setName(name);
        studentRepository.save(student);
    }

    public void deactivate(Long id) {
        log.info("ActionLog.deactivate student started id: {}", id);

        var student = fetchStudentIfExist(id);
        student.setStatus(Status.DEACTIVATED);
        studentRepository.save(student);
    }

    public void activate(Long id){
        log.info("ActionLog.activate student is started. id: {}", id);

        var student = fetchStudentIfExist(id);
        student.setStatus(Status.ACTIVE);
        studentRepository.save(student);
    }

    private void isActive(Course course) {
        if (course.getStatus() == Status.DEACTIVATED)
            throw new RuntimeException("COURSE IS NOT ACTIVE");
    }

    private void isActive(Student student) {
        if (student.getStatus() == Status.DEACTIVATED)
            throw new RuntimeException("STUDENT IS NOT ACTIVE");
    }

    private Student fetchStudentIfExist(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

}
