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
public class StudentRequestDto {

    private int id;
    private String name;
    private String phoneNumber;
    private String age;
    private String batch;
    private String email;
    private String password;
    private String gender;
    private String address;
    private String studentImg;
}
