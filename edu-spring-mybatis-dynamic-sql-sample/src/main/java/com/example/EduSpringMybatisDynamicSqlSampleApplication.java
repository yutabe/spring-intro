package com.example;

import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.EmployeeMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class EduSpringMybatisDynamicSqlSampleApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(EduSpringMybatisDynamicSqlSampleApplication.class, args);
        EmployeeMapper employeeMapper = context.getBean(EmployeeMapper.class);

        List<Employee> list1 = employeeMapper.findByNameContainingAndEmailContaining("田", null);
        for (Employee employee : list1) {
            System.out.println(employee);
        }

        List<Employee> list2 = employeeMapper.findByNameContainingAndEmailContaining(null, "yamada");
        for (Employee employee : list2) {
            System.out.println(employee);
        }

        List<Employee> list3 = employeeMapper.findByNameContainingAndEmailContaining("田", "yamada");
        for (Employee employee : list3) {
            System.out.println(employee);
        }
    }
}
