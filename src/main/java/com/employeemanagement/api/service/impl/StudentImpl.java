package com.employeemanagement.api.service.impl;

import com.employeemanagement.api.dto.request.StudentRequestDto;
import com.employeemanagement.api.dto.response.StudentResponse;
import com.employeemanagement.api.exceptions.StudentNotFoundException;
import com.employeemanagement.api.model.Student;
import com.employeemanagement.api.repository.StudentRepository;
import com.employeemanagement.api.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentRequestDto createStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setId(studentRequestDto.getId());
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setBatch(studentRequestDto.getBatch());
        student.setGender(studentRequestDto.getGender());
        student.setEmail(studentRequestDto.getEmail());
        student.setPassword((studentRequestDto.getPassword()));
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setAddress(studentRequestDto.getAddress());
        student.setStudentImg(studentRequestDto.getStudentImg());
        return mapToDto(studentRepository.save(student));

    }

    @Override
    public StudentResponse getAllStudents(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Student> students = studentRepository.findAll(pageable);
        List<Student> listofStudent = students.getContent();
        List<StudentRequestDto> content = listofStudent.stream().map(this::mapToDto).collect(Collectors.toList());
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setLast(students.isLast());
        return studentResponse;
    }

    @Override
    public StudentRequestDto getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
        return mapToDto(student);
    }

    @Override
    public StudentRequestDto updateStudent(StudentRequestDto studentRequestDto, int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Students could not be updated"));
        student.setId(studentRequestDto.getId());
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setBatch(studentRequestDto.getBatch());
        student.setGender(studentRequestDto.getGender());
        student.setEmail(studentRequestDto.getEmail());
        student.setPassword((studentRequestDto.getPassword()));
        student.setPhoneNumber(studentRequestDto.getPhoneNumber());
        student.setAddress(studentRequestDto.getAddress());
        student.setStudentImg(studentRequestDto.getStudentImg());
        Student updateStudent = studentRepository.save(student);
        return mapToDto(updateStudent);
    }

    @Override
    public void deleteStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student could not be deleted"));
        studentRepository.delete(student);
    }


    private StudentRequestDto mapToDto(Student student){
        return StudentRequestDto.builder()
                .id(student.getId())
                .name(student.getName())
                .phoneNumber(student.getPhoneNumber())
                .age(student.getAge())
                .batch(student.getBatch())
                .email(student.getEmail())
                .password(student.getPassword())
                .gender(student.getGender())
                .address(student.getAddress())
                .studentImg(student.getStudentImg())
                .build();
    }
    private Student mapToEntity(StudentRequestDto studentRequestDto){
        return Student.builder()
                .id(studentRequestDto.getId())
                .name(studentRequestDto.getName())
                .age(studentRequestDto.getAge())
                .batch(studentRequestDto.getBatch())
                .gender(studentRequestDto.getGender())
                .email(studentRequestDto.getEmail())
                .password(studentRequestDto.getPassword())
                .address(studentRequestDto.getAddress())
                .phoneNumber(studentRequestDto.getPhoneNumber())
                .studentImg(studentRequestDto.getStudentImg())
                .build();

    }
}
