package com.example.course.service;

import com.example.course.dao.entity.Teacher;
import com.example.course.dao.repository.CourseRepository;
import com.example.course.dao.repository.TeacherRepository;
import com.example.course.dto.request.TeacherRequest;
import com.example.course.dto.response.TeacherResponse;
import com.example.course.exception.ExceptionConstants;
import com.example.course.exception.NotFoundException;
import com.example.course.mapper.TeacherMapper;
import com.example.course.model.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherResponse crateTeacher(TeacherRequest request) {
        log.info("ActionLog.createTeacher is started");
        var teacher = TeacherMapper.requestToEntity(request);
        var saved = teacherRepository.save(teacher);
        return TeacherMapper.entityToResponse(saved);
    }


    public TeacherResponse getTeacherById(Long id){
        log.info("ActionLog.getTeacherById is started id: {}", id);

        var teacher = fetchTeacherIfExist(id);
        return TeacherMapper.entityToResponse(teacher);
    }

    public TeacherResponse updateTeacher(Long id, TeacherRequest teacherRequest) {
        log.info("ActionLog.updateTeacher is started id: {}", id);

        var teacher = fetchTeacherIfExist(id);
        TeacherMapper.updateTeacher(teacher, teacherRequest);
        teacherRepository.save(teacher);
        return TeacherMapper.entityToResponse(teacher);
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
