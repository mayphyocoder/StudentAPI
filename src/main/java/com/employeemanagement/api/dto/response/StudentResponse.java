package com.employeemanagement.api.dto.response;

import com.employeemanagement.api.dto.request.StudentRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class  StudentResponse {

    private List<StudentRequestDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}
