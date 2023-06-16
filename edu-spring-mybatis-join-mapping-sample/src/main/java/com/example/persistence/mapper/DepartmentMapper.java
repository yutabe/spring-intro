package com.example.persistence.mapper;

import com.example.persistence.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    List<Department> findAll();

}
