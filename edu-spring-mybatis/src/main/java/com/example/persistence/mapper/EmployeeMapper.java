package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

// TODO 02 Mapperインタフェースを示すアノテーションを付加する
public interface EmployeeMapper {

    // TODO 03 アノテーションを付加してメソッドにSELECT文を設定する
    // SQLは下記を使う
    // "SELECT id, name, joined_date, department_name, email, birth_day FROM employee"
    List<Employee> findAll();

    // TODO 04 アノテーションを付加してメソッドにSELECT文を設定する
    // SQLは下記を使う
    // "SELECT id, name, joined_date, department_name, email, birth_day FROM employee WHERE id = #{id}"
    Employee findById(Integer id);

    // TODO 05 アノテーションを付加してメソッドにINSERT文を設定する
    // SQLは下記を使う
    /*
       "INSERT INTO employee(name, joined_date, department_name, email, birth_day)" +
                  " VALUES(#{name}, #{joinedDate}, #{departmentName}, #{email}, #{birthDay})"
     */
    // TODO 06 アノテーションを付加して採番された主キーの値をidフィールドに自動的に代入するように設定する
    void insert(Employee employee);

    // TODO 07 アノテーションを付加してメソッドにUPDATE文を設定する
    // SQLは下記を使う
    /*
       "UPDATE employee SET name = #{name}, joined_date = #{joinedDate}," +
                  " department_name = #{departmentName}, email = #{email}, birth_day = #{birthDay} WHERE id = #{id}"
     */
    int update(Employee employee);

    // TODO 08 アノテーションを付加してメソッドにDELETE文を設定する
    // SQLは下記を使う
    // "DELETE FROM employee WHERE id = #{id}"
    int delete(Integer id);
}
