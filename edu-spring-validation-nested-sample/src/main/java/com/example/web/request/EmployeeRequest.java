package com.example.web.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class EmployeeRequest {

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate joinedDate;

    @Email
    private String email;

    @NotNull
    @PastOrPresent
    private LocalDate birthDay;

    @NotNull
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

    @AssertTrue
    public boolean isJoinedDateGreaterThanBirthDay() {
        if (joinedDate != null && birthDay != null) {
            return joinedDate.isAfter(birthDay);
        }
        return true;
    }
}
