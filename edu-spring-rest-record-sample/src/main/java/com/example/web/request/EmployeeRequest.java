package com.example.web.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record EmployeeRequest(
        @NotBlank
        @Size(min = 1, max = 10)
        String name,
        @NotNull
        @PastOrPresent
        LocalDate joinedDate,
        @NotBlank
        @Size(min = 1, max = 20)
        String departmentName,
        @Email
        String email,
        @NotNull
        @PastOrPresent
        LocalDate birthDay) {

    @AssertTrue
    public boolean isJoinedDateGreaterThanBirthDay() {
        if (joinedDate != null && birthDay != null) {
            return joinedDate.isAfter(birthDay);
        }
        return true;
    }
}
