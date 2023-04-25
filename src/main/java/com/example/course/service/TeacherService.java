package com.example.course.service;

import com.example.course.model.Teacher;
import com.example.course.repository.CourseRepository;
import com.example.course.repository.TeacherRepository;
import com.example.course.dto.request.TeacherRequest;
import com.example.course.dto.response.TeacherResponse;
import com.example.course.exception.ExceptionConstants;
import com.example.course.exception.NotFoundException;
import com.example.course.model.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;


    public TeacherResponse createTeacher(TeacherRequest request) {
        log.info("ActionLog.createTeacher is started");

        var saved = teacherRepository.save(mapper.map(request, Teacher.class));
        return mapper.map(saved, TeacherResponse.class);
    }

    public Page<TeacherResponse> getAllTeachers(Pageable pageable){
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);

        Page<TeacherResponse> teacherResponsePage = teacherPage.map(course -> mapper.map(teacherPage, TeacherResponse.class));

        return teacherResponsePage;
    }

    public TeacherResponse getTeacherById(Long id){
        log.info("ActionLog.getTeacherById is started id: {}", id);

        var teacher = fetchTeacherIfExist(id);
        return mapper.map(teacher, TeacherResponse.class);
    }

    public TeacherResponse updateTeacher(Long id, TeacherRequest request) {
        log.info("ActionLog.updateTeacher is started id: {}", id);

        var teacher = fetchTeacherIfExist(id);

        teacher.setName(request.getName());
        teacher.setAddress(request.getAddress());
        teacher.setEmail(request.getEmail());
        teacher.setAccount(request.getBankAccount());

        teacherRepository.save(teacher);
        return mapper.map(teacher, TeacherResponse.class);
    }

    public void registerToCourse(Long studentId, Long courseId) {
        log.info("ActionLog.registerToCourse is started student: {}, course: {}", studentId, courseId);

        var teacher = fetchTeacherIfExist(studentId);
        var course = courseRepository.findById(courseId).orElseThrow();
        teacher.getCourses().add(course);
    }

    public void deactivate(Long id){
        log.info("ActionLog.deactivate teacher is started. id: {}", id);

        var teacher = fetchTeacherIfExist(id);
        teacher.setStatus(Status.DEACTIVATED);
        teacherRepository.save(teacher);
    }

    public void activate(Long id){
        log.info("ActionLog.activate teacher is started. id: {}", id);

        var teacher = fetchTeacherIfExist(id);
        teacher.setStatus(Status.ACTIVE);
        teacherRepository.save(teacher);
    }

    private Teacher fetchTeacherIfExist(Long id) {
        return teacherRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format(ExceptionConstants.NOT_FOUND_EXCEPTION_MESSAGE, id),
                        "NOT_FOUND_EXCEPTION"));
    }
}
