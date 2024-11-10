package com.employeemanagement.api.dto.response;

import com.employeemanagement.api.dto.request.CourseRequestDto;
import lombok.Data;

import java.util.List;
@Data
public class CourseResponse {

    private List<CourseRequestDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
