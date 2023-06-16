package com.example.web.response;

import java.time.LocalDate;

public class EmployeeResponse {

    private Integer id;

    private String name;

    private LocalDate joinedDate;

    private String departmentName;

    private String email;

    private LocalDate birthDay;

    public EmployeeResponse(Integer id, String name, LocalDate joinedDate, String departmentName, String email, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.joinedDate = joinedDate;
        this.departmentName = departmentName;
        this.email = email;
        this.birthDay = birthDay;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }
}
