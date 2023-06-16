package com.example.service.impl;

import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    // EmployeeMapperをDIする（@Autowiredは省略）
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findByNameContainingAndEmailContaining(String name, String email) {
        List<Employee> employeeList = employeeMapper.findByNameContainingAndEmailContaining(name, email);
        return employeeList;
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Integer id) {
        Employee employee = employeeMapper.findById(id);
        if (employee == null) {
            // メソッドの戻り値がnullの場合は指定されたIDのデータが存在しない
            throw new RuntimeException("ID:" + id + " のデータは存在しません");
        }
        return employee;
    }

    @Override
    @Transactional
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        int count = employeeMapper.update(employee);
        if (count == 0) {
            // 更新された行数が0の場合は指定されたIDのデータが存在しない
            throw new RuntimeException("ID:" + employee.getId() + " のデータは存在しません");
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        int count = employeeMapper.delete(id);
        if (count == 0) {
            // 削除された行数が0の場合は指定されたIDのデータが存在しない
            throw new RuntimeException("ID:" + id + " のデータは存在しません");
        }
    }
}
