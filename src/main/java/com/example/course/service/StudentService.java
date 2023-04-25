package com.example.course.service;

import com.example.course.model.Student;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.StudentRepository;
import com.example.course.dto.request.StudentRequest;
import com.example.course.dto.response.CourseResponse;
import com.example.course.dto.response.StudentResponse;
import com.example.course.exception.ExceptionConstants;
import com.example.course.exception.NotFoundException;
import com.example.course.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ModelMapper mapper;

    private final CheckActivityService checkActivityService;


    public StudentResponse createStudent(StudentRequest studentRequest) {
        log.info("ActionLog.createStudent is started");
        var saved = studentRepository.save(mapper.map(studentRequest, Student.class));

        return mapper.map(saved, StudentResponse.class);
    }

    public Page<StudentResponse> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentRepository.findAll(pageable);

        Page<StudentResponse> studentResponsePage = studentPage.map(course -> mapper.map(studentPage, StudentResponse.class));

        return studentResponsePage;
    }

    public StudentResponse getStudentById(Long id) {
        log.info("ActionLog.getStudentById is started id: {}", id);

        var student = fetchStudentIfExist(id);

        return mapper.map(student, StudentResponse.class);
    }


    @Transactional
    public void registerToCourse(Long studentId,
                                 Long courseId) {
        log.info("ActionLog.registerToCourse is started for student: {} to course: {}", studentId, courseId);

        var student = fetchStudentIfExist(studentId);

        var course = courseRepository.findById(courseId).orElseThrow(() ->
                new NotFoundException(
                        String.format(ExceptionConstants.NOT_FOUND_EXCEPTION_MESSAGE, studentId),
                        "NOT_FOUND_EXCEPTION"));

        checkActivityService.isActive(student);
        checkActivityService.isActive(course);

        student.getCourses().add(course);
        course.getStudents().add(student);
    }

    public List<CourseResponse> registeredCourses(Long studentId) {
        log.info("ActionLog.registeredCourses method is started for student: {}", studentId);
        var student = fetchStudentIfExist(studentId);

        return student.getCourses()
                .stream()
                .map(course -> mapper.map(course, CourseResponse.class))
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

    public void activate(Long id) {
        log.info("ActionLog.activate student is started. id: {}", id);

        var student = fetchStudentIfExist(id);
        student.setStatus(Status.ACTIVE);
        studentRepository.save(student);
    }

    private Student fetchStudentIfExist(Long id) {
        return studentRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format(ExceptionConstants.NOT_FOUND_EXCEPTION_MESSAGE, id),
                        "NOT_FOUND_EXCEPTION"));
    }
}
