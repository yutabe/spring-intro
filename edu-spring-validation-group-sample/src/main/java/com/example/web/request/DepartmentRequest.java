package com.example.web.request;

import com.example.web.validation.Group1;

import jakarta.validation.constraints.NotNull;

public class DepartmentRequest {

    @NotNull(groups = Group1.class)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
