package com.example.course.service;

import com.example.course.dao.entity.Course;
import com.example.course.dao.repository.CourseRepository;
import com.example.course.dto.request.CourseRequest;
import com.example.course.dto.response.CourseResponse;
import com.example.course.mapper.CourseMapper;
import com.example.course.model.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService{
    private final CourseRepository courseRepository;

    public void createCourse(CourseRequest dto){
        log.info("ActionLog.createCourse is started");
        courseRepository.save(CourseMapper.requestToEntity(dto));
    }

    public CourseResponse getCourseById(Long id) {
        log.info("ActionLog.getCourseById is started. Id: {}", id);

        var course = fetchCourseIfExist(id);

        return CourseMapper.entityToDto(course);
    }

    public CourseResponse updateCourse(Long id, CourseRequest request){
        log.info("ActionLog.updateCourse is started. id: {}", id);

        var course = fetchCourseIfExist(id);
        CourseMapper.updateCourse(request, course);

        return CourseMapper.entityToDto(courseRepository.save(course));
    }

    public void deactivate(Long id){
        log.info("ActionLog.deactivate course is started. id: {}", id);

        var course = fetchCourseIfExist(id);
        course.setStatus(Status.DEACTIVATED);
        courseRepository.save(course);
    }

    public void activate(Long id){
        log.info("ActionLog.activate course is started. id: {}", id);

        var course = fetchCourseIfExist(id);
        course.setStatus(Status.ACTIVE);
        courseRepository.save(course);
    }


    private Course fetchCourseIfExist(Long id){
        return courseRepository.findById(id).orElseThrow();
    }
}