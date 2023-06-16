package com.example.web.response;

import java.time.LocalDate;

public record EmployeeResponse(
        Integer id,
        String name,
        LocalDate joinedDate,
        String departmentName,
        String email,
        LocalDate birthDay) {
}
