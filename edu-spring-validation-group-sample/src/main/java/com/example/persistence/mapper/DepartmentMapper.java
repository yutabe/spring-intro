package com.example.persistence.mapper;

import com.example.persistence.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    // 引数の値で部署テーブルを主キー検索する
    @Select("SELECT id, name FROM department WHERE id = #{id}")
    Department findById(Integer id);

    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "id", property = "employees",
                    many = @Many(select = "com.example.persistence.mapper.EmployeeMapper.findByDepartmentId"))
    })
    @Select("SELECT id, name FROM department")
    List<Department> findAll();
}
