package com.employeemanagement.api.dto.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class CourseRequestDto {

    private int id;
    private String courseName;
    private String  courseCode;
    private String coursePrice;
    private String courseDuration;
    private String maxStudent;
    private String courseImg;
}
