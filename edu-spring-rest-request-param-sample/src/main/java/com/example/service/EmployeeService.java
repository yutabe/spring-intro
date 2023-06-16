package com.example.service;

import com.example.persistence.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findByNameContainingAndEmailContaining(String name, String email);

    Employee findById(Integer id);

    void insert(Employee employee);

    void update(Employee employee);

    void delete(Integer id);
}
