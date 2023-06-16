package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> findAll();

}
