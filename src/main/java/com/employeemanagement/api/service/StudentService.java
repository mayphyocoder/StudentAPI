package com.employeemanagement.api.service;

import com.employeemanagement.api.dto.request.StudentRequestDto;
import com.employeemanagement.api.dto.response.StudentResponse;
import com.employeemanagement.api.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    StudentRequestDto createStudent(StudentRequestDto studentRequestDto);

    StudentResponse getAllStudents(int pageNo, int pageSize);
//
    StudentRequestDto getStudentById(int id);
//
    StudentRequestDto updateStudent(StudentRequestDto studentRequestDto, int id);
//
    void deleteStudentById(int id);
}
