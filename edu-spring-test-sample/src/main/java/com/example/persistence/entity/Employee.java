package com.example.persistence.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {

    private Integer id;

    private String name;

    private LocalDate joinedDate;

    private String departmentName;

    private String email;

    private LocalDate birthDay;

    public Employee() {

    }

    public Employee(Integer id, String name, LocalDate joinedDate, String departmentName, String email, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.joinedDate = joinedDate;
        this.departmentName = departmentName;
        this.email = email;
        this.birthDay = birthDay;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(name, employee.name) && Objects.equals(joinedDate, employee.joinedDate) && Objects.equals(departmentName, employee.departmentName) && Objects.equals(email, employee.email) && Objects.equals(birthDay, employee.birthDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, joinedDate, departmentName, email, birthDay);
    }
}
