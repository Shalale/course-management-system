package com.example.course.controller;

import com.example.course.dto.request.CourseRequest;
import com.example.course.dto.response.CourseResponse;
import com.example.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponse createCourse(@RequestBody CourseRequest dto) {
        return service.createCourse(dto);
    }

    @GetMapping()
    public Page<CourseResponse> getAllCourses(Pageable pageable) {
        return service.getAllCourses(pageable);
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable Long id) {
        return service.getCourseById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseResponse updateCourse(@PathVariable Long id,
                                       @RequestBody CourseRequest request) {
        return service.updateCourse(id, request);
    }

    @PatchMapping("/deactivate/{id}")
    public void deactivate(@PathVariable Long id){
        service.deactivate(id);
    }

    @PatchMapping("/activate/{id}")
    public void activate(@PathVariable Long id){
        service.activate(id);
    }

}
