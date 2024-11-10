package com.employeemanagement.api.service.impl;

import com.employeemanagement.api.dto.request.CourseRequestDto;
import com.employeemanagement.api.dto.request.StudentRequestDto;
import com.employeemanagement.api.dto.response.CourseResponse;
import com.employeemanagement.api.dto.response.StudentResponse;
import com.employeemanagement.api.exceptions.CourseNotFoundException;
import com.employeemanagement.api.exceptions.StudentNotFoundException;
import com.employeemanagement.api.model.Course;
import com.employeemanagement.api.model.Student;
import com.employeemanagement.api.repository.CourseRepository;
import com.employeemanagement.api.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;

    }


    @Override
    public CourseRequestDto createCourse(CourseRequestDto courseRequestDto) {
        Course course = new Course();

        course.setId(courseRequestDto.getId());
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCoursePrice(courseRequestDto.getCoursePrice());
        course.setCourseDuration(courseRequestDto.getCourseDuration());
        course.setMaxStudent(courseRequestDto.getMaxStudent());
        course.setCourseImg(courseRequestDto.getCourseImg());
        return mapToDto(courseRepository.save(course));
    }

    @Override
    public CourseResponse getAllCourses(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Course> courses = courseRepository.findAll(pageable);
        List<Course> listofCourse = courses.getContent();
        List<CourseRequestDto> content = listofCourse.stream().map(this::mapToDto).collect(Collectors.toList());

        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setContent(content);
        courseResponse.setPageNo(courses.getNumber());
        courseResponse.setPageSize(courses.getSize());
        courseResponse.setTotalElements(courses.getTotalElements());
        courseResponse.setTotalPages(courses.getTotalPages());
        courseResponse.setLast(courses.isLast());
        return courseResponse;



    }

    @Override
    public CourseRequestDto getCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));
        return mapToDto(course);


    }

    @Override
    public CourseRequestDto updateCourse(CourseRequestDto courseRequestDto, int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course could not be updated"));
        course.setId(courseRequestDto.getId());
        course.setCourseName(courseRequestDto.getCourseName());
        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCoursePrice(courseRequestDto.getCoursePrice());
        course.setCourseDuration(courseRequestDto.getCourseDuration());
        course.setMaxStudent(courseRequestDto.getMaxStudent());
        course.setCourseImg(courseRequestDto.getCourseImg());
        Course updateCourse = courseRepository.save(course);
        return mapToDto(updateCourse);
    }

    @Override
    public void deleteCourseById(int id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new CourseNotFoundException("Course not be deleted"));
        courseRepository.delete(course);
    }


    private CourseRequestDto mapToDto(Course course){
        return CourseRequestDto.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .courseCode(course.getCourseCode())
                .coursePrice(course.getCoursePrice())
                .courseDuration(course.getCourseDuration())
                .maxStudent(course.getMaxStudent())
                .courseImg(course.getCourseImg())
                .build();
    }
    private Course mapToEntity(CourseRequestDto courseRequestDto){
        return Course.builder()
                .id(courseRequestDto.getId())
                .courseName(courseRequestDto.getCourseName())
                .courseCode(courseRequestDto.getCourseCode())
                .coursePrice(courseRequestDto.getCourseCode())
                .courseDuration(courseRequestDto.getCourseDuration())
                .maxStudent(courseRequestDto.getMaxStudent())
                .courseImg(courseRequestDto.getCourseImg())
                .build();
    }
}
