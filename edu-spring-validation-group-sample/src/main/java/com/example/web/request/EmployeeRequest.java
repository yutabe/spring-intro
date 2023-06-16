package com.example.web.request;

import com.example.web.validation.Group1;
import com.example.web.validation.Group2;
import com.example.web.validation.Group3;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EmployeeRequest {

    @NotBlank(groups = Group1.class)
    @Size(min = 1, max = 10, groups = Group2.class)
    private String name;

    @NotNull(groups = Group1.class)
    @PastOrPresent(groups = Group2.class)
    private LocalDate joinedDate;

    @NotBlank(groups = Group1.class)
    @Email(groups = Group2.class)
    private String email;

    @NotNull(groups = Group1.class)
    @PastOrPresent(groups = Group2.class)
    private LocalDate birthDay;

    @NotNull(groups = Group1.class)
    @Valid
    private DepartmentRequest department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public DepartmentRequest getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentRequest department) {
        this.department = department;
    }

    @AssertTrue(groups = Group3.class)
    public boolean isJoinedDateGreaterThanBirthDay() {
        return joinedDate.isAfter(birthDay);
    }
}
