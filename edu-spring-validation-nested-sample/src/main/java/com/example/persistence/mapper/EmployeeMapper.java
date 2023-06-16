package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "joined_date", property = "joinedDate"),
            @Result(column = "email", property = "email"),
            @Result(column = "birth_day", property = "birthDay"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "department_id", property = "department",
                    one = @One(select = "com.example.persistence.mapper.DepartmentMapper.findById"))
    })
    @Select("SELECT id, name, joined_date, email, birth_day, department_id FROM employee")
    List<Employee> findAll();

    @Select("SELECT id, name, joined_date, email, birth_day, department_id FROM employee WHERE department_id = #{departmentId}")
    List<Employee> findByDepartmentId(Integer departmentId);

    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "joined_date", property = "joinedDate"),
            @Result(column = "email", property = "email"),
            @Result(column = "birth_day", property = "birthDay"),
            @Result(column = "department_id", property = "departmentId"),
            @Result(column = "department_id", property = "department",
                    one = @One(select = "com.example.persistence.mapper.DepartmentMapper.findById"))
    })
    @Select("SELECT id, name, joined_date, email, birth_day, department_id FROM employee WHERE id = #{id}")
    Employee findById(Integer id);

    @Insert("INSERT INTO employee(name, joined_date, email, birth_day, department_id)" +
            " VALUES(#{name}, #{joinedDate}, #{email}, #{birthDay}, #{departmentId})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Employee employee);

    @Update("UPDATE employee SET name = #{name}, joined_date = #{joinedDate}," +
            " email = #{email}, birth_day = #{birthDay}, department_id = #{departmentId} WHERE id = #{id}")
    int update(Employee employee);

    @Delete("DELETE FROM employee WHERE id = #{id}")
    int delete(Integer id);

}
