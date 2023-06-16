package com.example.web.rest;

import com.example.exception.EmployeeNotFoundException;
import com.example.persistence.entity.Employee;
import com.example.service.EmployeeService;
import com.example.web.exception.response.ErrorResponse;
import com.example.web.request.EmployeeRequest;
import com.example.web.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    // DIでEmployeeServiceのインスタンスを取得する
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> findAll() {
        // EmployeeServiceの全件検索のメソッドを呼び出し社員テーブルの全データを取得する
        List<Employee> employeeList = employeeService.findAll();
        // エンティティからレスポンスクラスへ変換する
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();
        for (Employee employee : employeeList) {
            EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(),
                    employee.getName(),
                    employee.getJoinedDate(),
                    employee.getDepartmentName(),
                    employee.getEmail(),
                    employee.getBirthDay());
            employeeResponseList.add(employeeResponse);
        }
        // レスポンスクラスを戻り値とする
        return employeeResponseList;
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        // 引数として渡ってきたパスパラメータを使い主キー検索を行う
        Employee employee = employeeService.findById(id);
        // エンティティからレスポンスクラスへ変換する
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(),
                employee.getName(),
                employee.getJoinedDate(),
                employee.getDepartmentName(),
                employee.getEmail(),
                employee.getBirthDay());
        // レスポンスクラスを戻り値とする
        return employeeResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Validated EmployeeRequest request) {
        // 主キーに該当するフィールドはパスパラメータ、それ以外のフィールドはリクエストボディ
        // の値を使い新しエンティティを作成する
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(request.getName());
        employee.setJoinedDate(request.getJoinedDate());
        employee.setDepartmentName(request.getDepartmentName());
        employee.setEmail(request.getEmail());
        employee.setBirthDay(request.getBirthDay());
        // 作成したエンティティを使いデータベースの更新を行う
        employeeService.update(employee);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Validated EmployeeRequest request) {
        // リクエストボディの値を使いidフィールドがnullの新しいエンティティを作成する
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setJoinedDate(request.getJoinedDate());
        employee.setDepartmentName(request.getDepartmentName());
        employee.setEmail(request.getEmail());
        employee.setBirthDay(request.getBirthDay());
        // 作成したエンティティを使い新しいデータを登録する
        // Mapperインタフェースのメソッドに付加した@Optionsアノテーションの設定により
        // データベース登録後に新しく採番されたID列の値がエンティティのidフィールドに自動的に代入される
        employeeService.insert(employee);

        // 追加したデータを表すURLを作成する
        // http://localhost:8080/employees
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        // http://localhost:8080/employees/新しい社員ID
                .pathSegment(employee.getId().toString())
                .build().encode().toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        // 削除処理を呼び出す
        employeeService.delete(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEmployeeNotFound(EmployeeNotFoundException exception) {
        String message = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(message);
        return errorResponse;
    }

}
