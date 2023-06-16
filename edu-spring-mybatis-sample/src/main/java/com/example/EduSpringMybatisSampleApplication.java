package com.example;

import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.EmployeeMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class EduSpringMybatisSampleApplication {

    public static void main(String[] args) {
        // DIコンテナの作成
        ApplicationContext context = SpringApplication.run(EduSpringMybatisSampleApplication.class, args);
        // EmployeeMapperのBeanを取得
        EmployeeMapper employeeMapper = context.getBean(EmployeeMapper.class);
        // 新規追加の確認
        System.out.println("==== 新規追加 ====");
        Employee newEmployee = new Employee();
        newEmployee.setName("和田三郎");
        newEmployee.setJoinedDate(LocalDate.of(2022, 1, 1));
        newEmployee.setDepartmentName("営業部");
        newEmployee.setEmail("wada@example.com");
        newEmployee.setBirthDay(LocalDate.of(1986, 5, 13));
        employeeMapper.insert(newEmployee);
        // 全件検索の確認1
        System.out.println("==== 全件検索 ====");
        List<Employee> list1 = employeeMapper.findAll();
        for (Employee employee : list1) {
            System.out.println(employee);
        }
        // 主キー検索の確認
        System.out.println("==== 主キー検索 ====");
        Employee employee = employeeMapper.findById(110);
        System.out.println(employee);
        // 更新の確認
        System.out.println("==== 更新 ====");
        employee.setName("斎藤四郎");
        employee.setJoinedDate(LocalDate.of(2012, 1, 1));
        employee.setDepartmentName("開発部");
        employee.setEmail("saito@example.com");
        employee.setBirthDay(LocalDate.of(1976, 12, 31));
        int count1 = employeeMapper.update(employee);
        System.out.println(count1 + "件のデータが更新されました。");
        // 削除の確認
        System.out.println("==== 削除 ====");
        int count2 = employeeMapper.delete(110);
        System.out.println(count2 + "件のデータが削除されました。");
    }

}
