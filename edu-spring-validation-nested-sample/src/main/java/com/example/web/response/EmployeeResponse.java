package com.example.web.response;

import com.example.persistence.entity.Employee;

import java.time.LocalDate;

public class EmployeeResponse {

    private Integer id;

    private String name;

    private LocalDate joinedDate;

    private String email;

    private LocalDate birthDay;

    private DepartmentResponse department;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.joinedDate = employee.getJoinedDate();
        this.email = employee.getEmail();
        this.birthDay = employee.getBirthDay();
        this.department = new DepartmentResponse(employee.getDepartment().getId(),
                employee.getDepartment().getName());
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

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public DepartmentResponse getDepartment() {
        return department;
    }
}
