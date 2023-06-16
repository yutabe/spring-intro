package com.example.service.impl;

import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// TODO 01 サービスクラスであることを示すアノテーションを付加してBean定義する
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    // EmployeeMapperをDIする（@Autowiredは省略）
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    // TODO 02 アノテーションを付加してトランザクション管理を有効化する
    //  readOnly要素をtrueに設定する
    @Override
    public List<Employee> findAll() {
        // TODO 03 EmployeeMapperのfindAllメソッドを呼び出し戻り値を変数に代入する
        List<Employee> employeeList = null;
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
    // TODO 04 アノテーションを付加してトランザクション管理を有効化する
    //  readOnly要素はデフォルト値とする
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
