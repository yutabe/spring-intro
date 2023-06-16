package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    @Select("""
            <script>
                SELECT id, name, joined_date, department_name, email, birth_day
                FROM employee
                <where>
                    <if test="name != null">
                        <bind name="namePattern" value="'%' + name + '%'" />
                        name like #{namePattern}
                    </if>
                    <if test="email != null">
                        <bind name="emailPattern" value="'%' + email + '%'" />
                        AND email like #{emailPattern}
                    </if>
                </where>
            </script>
            """)
    List<Employee> findByNameContainingAndEmailContaining(String name, String email);

}
