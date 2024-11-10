package com.employeemanagement.api.controller;


import com.employeemanagement.api.dto.request.CourseRequestDto;
import com.employeemanagement.api.dto.request.StudentRequestDto;
import com.employeemanagement.api.dto.response.CourseResponse;
import com.employeemanagement.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/courses")
public class CourseController {


    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CourseRequestDto> saveCourse(@RequestBody CourseRequestDto courseRequestDto){
        return new ResponseEntity<>(courseService.createCourse(courseRequestDto), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<CourseResponse> getAllCourses(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize

    ){
        return new ResponseEntity<>(courseService.getAllCourses(pageNo, pageSize), HttpStatus.OK );
    }

    @GetMapping("/{id}/")
    public ResponseEntity<CourseRequestDto> getCourseById(@PathVariable int id){
        CourseRequestDto courseRequestDto = courseService.getCourseById(id);
        return ok(courseRequestDto);

    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CourseRequestDto> updateCourse(@RequestBody CourseRequestDto courseRequestDto, @PathVariable("id") int courseId){
        return  new ResponseEntity<>(courseService.updateCourse(courseRequestDto, courseId), HttpStatus.OK);

    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<String> deleteCourseById(@PathVariable("id") int courseId){
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("Course deleted", HttpStatus.OK);
    }


}
