package com.example.course.controller;

import com.example.course.dto.request.TeacherRequest;
import com.example.course.dto.response.TeacherResponse;
import com.example.course.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/teachers")
public class TeacherController {
    private final TeacherService service;

    @PostMapping
    public TeacherResponse crateTeacher(@Validated @RequestBody TeacherRequest request) {
        return service.createTeacher(request);
    }

    @GetMapping()
    public Page<TeacherResponse> getAllTeachers(Pageable pageable) {
        return service.getAllTeachers(pageable);
    }

    @GetMapping("/{id}")
    public TeacherResponse getTeacherById(@PathVariable Long id){
        return service.getTeacherById(id);
    }

    @PutMapping("/{id}")
    public TeacherResponse updateTeacher(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest) {
        return service.updateTeacher(id, teacherRequest);
    }

    @PatchMapping("/register")
    public void registerToCourse(@RequestParam Long studentId,
                                 @RequestParam Long courseId) {
        service.registerToCourse(studentId, courseId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.deactivate(id);
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
