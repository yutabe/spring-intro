package com.example.web.response;

public class DepartmentResponse {

    private Integer id;

    private String name;

    public DepartmentResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
