package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("SELECT id, name, joined_date, department_name, email, birth_day FROM employee ORDER BY id")
    List<Employee> findAll();

    @Select("SELECT id, name, joined_date, department_name, email, birth_day FROM employee WHERE id = #{id}")
    Employee findById(Integer id);

    @Insert("INSERT INTO employee(name, joined_date, department_name, email, birth_day)" +
            " VALUES(#{name}, #{joinedDate}, #{departmentName}, #{email}, #{birthDay})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Employee employee);

    @Update("UPDATE employee SET name = #{name}, joined_date = #{joinedDate}," +
            " department_name = #{departmentName}, email = #{email}, birth_day = #{birthDay} WHERE id = #{id}")
    int update(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    int delete(Integer id);
}
