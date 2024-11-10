package com.employeemanagement.api.service;

import com.employeemanagement.api.dto.request.CourseRequestDto;
import com.employeemanagement.api.dto.response.CourseResponse;
import com.employeemanagement.api.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    CourseRequestDto createCourse(CourseRequestDto courseRequestDto);
    CourseResponse getAllCourses(int pageNo, int pageSize);
    CourseRequestDto getCourseById(int id);
    CourseRequestDto updateCourse(CourseRequestDto courseRequestDto, int id);
    void deleteCourseById(int id);
}
