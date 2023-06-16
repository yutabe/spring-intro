package com.example.web.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeRequest {

    @NotBlank
    @Size(min = 1, max = 10)
    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate joinedDate;

    @NotBlank
    @Size(min = 1, max = 20)
    private String departmentName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @PastOrPresent
    private LocalDate birthDay;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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

    @AssertTrue
    public boolean isJoinedDateGreaterThanBirthDay() {
        if (joinedDate != null && birthDay != null) {
            return joinedDate.isAfter(birthDay);
        }
        return true;
    }
}
