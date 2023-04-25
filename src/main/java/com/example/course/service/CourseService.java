package com.example.course.service;

import com.example.course.model.Course;
import com.example.course.repository.CourseRepository;
import com.example.course.dto.request.CourseRequest;
import com.example.course.dto.response.CourseResponse;
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
public class CourseService{
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    public CourseResponse createCourse(CourseRequest dto){
        log.info("ActionLog.createCourse is started");
        var saved = courseRepository.save(mapper.map(dto, Course.class));
        return mapper.map(saved, CourseResponse.class);
    }

    public CourseResponse getCourseById(Long id) {
        log.info("ActionLog.getCourseById is started. Id: {}", id);

        var course = fetchCourseIfExist(id);

        return mapper.map(course, CourseResponse.class);
    }

    public Page<CourseResponse> getAllCourses(Pageable pageable){
        Page<Course> coursePage = courseRepository.findAll(pageable);

        Page<CourseResponse> courseResponsePage = coursePage.map(course -> mapper.map(course, CourseResponse.class));

        return courseResponsePage;
    }

    public CourseResponse updateCourse(Long id, CourseRequest request){
        log.info("ActionLog.updateCourse is started. id: {}", id);

        var course = fetchCourseIfExist(id);
        course.setDuration(request.getDuration());
        course.setName(request.getName());
        course.setPrice(request.getPrice());

        return mapper.map(course, CourseResponse.class);
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
        return courseRepository.findById(id).orElseThrow(()->
                new NotFoundException(
                        String.format(ExceptionConstants.NOT_FOUND_EXCEPTION_MESSAGE,id),
                        "NOT_FOUND_EXCEPTION")
        );
    }
}