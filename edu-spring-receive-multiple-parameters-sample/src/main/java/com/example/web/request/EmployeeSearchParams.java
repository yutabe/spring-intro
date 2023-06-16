package com.example.web.request;

import jakarta.validation.constraints.Size;

public class EmployeeSearchParams {

    @Size(max = 10)
    private String name;

    @Size(max = 20)
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
