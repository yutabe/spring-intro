package com.example.web.request;

import jakarta.validation.constraints.NotNull;

public class DepartmentRequest {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
