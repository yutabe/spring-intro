package com.example.persistence.entity;

import java.time.LocalDate;

// TODO 01 エンティティクラスの内容を確認する（完成済み）
public class Employee {

    private Integer id;

    private String name;

    private LocalDate joinedDate;

    private String departmentName;

    private String email;

    private LocalDate birthDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", joinedDate=" + joinedDate +
                ", departmentName='" + departmentName + '\'' +
                ", email='" + email + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
