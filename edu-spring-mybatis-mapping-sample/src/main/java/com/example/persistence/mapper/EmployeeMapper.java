package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    // @Resultsおよび@Resultアノテーションを使ってSELECT文の列とエンティティクラスのフィールドをマッピングする
    @Results({
            // 列とフィールドの1つの組に対して@Resultアノテーションで1つずつマッピングする
            // 主キー列のマッピングにはid属性をtrueにすると処理の高速化が期待できる
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "joined_date", property = "joinedDate"),
            @Result(column = "email", property = "email"),
            @Result(column = "birth_day", property = "birthDay"),
            @Result(column = "department_id", property = "departmentId"),
            // 社員テーブルのdepartment_id列の値に該当する部署のデータを取得する
            // Mapperインタフェースのメソッドを@Oneアノテーションで指定する
            @Result(column = "department_id", property = "department",
                    one = @One(select = "com.example.persistence.mapper.DepartmentMapper.findById"))
    })
    @Select("SELECT id, name, joined_date, email, birth_day, department_id FROM employee")
    List<Employee> findAll();

    // 社員テーブルを部署IDで絞り込み検索する
    @Select("SELECT id, name, joined_date, email, birth_day, department_id FROM employee WHERE department_id = #{departmentId}")
    List<Employee> findByDepartmentId(Integer departmentId);

}
