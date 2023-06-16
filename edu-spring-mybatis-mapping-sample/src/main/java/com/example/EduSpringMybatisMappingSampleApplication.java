package com.example;

import com.example.persistence.entity.Department;
import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.DepartmentMapper;
import com.example.persistence.mapper.EmployeeMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class EduSpringMybatisMappingSampleApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EduSpringMybatisMappingSampleApplication.class, args);

        // 社員の全件検索の確認
        EmployeeMapper employeeMapper = context.getBean(EmployeeMapper.class);
        System.out.println("==== 社員の全件検索 ====");
        List<Employee> employeeList = employeeMapper.findAll();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }

        // 部署の全件検索の確認
        DepartmentMapper departmentMapper = context.getBean(DepartmentMapper.class);
        System.out.println("==== 部署の全件検索 ====");
        List<Department> departmentList = departmentMapper.findAll();
        for (Department department :  departmentList) {
            System.out.println(department);
        }
    }

}
