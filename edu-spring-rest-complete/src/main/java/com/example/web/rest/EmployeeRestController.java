package com.example.web.rest;

import com.example.persistence.entity.Employee;
import com.example.service.EmployeeService;
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
        // TODO 01 EmployeeServiceの全件検索のメソッドを呼び出す
        //  その結果を変数に代入する
        List<Employee> employeeList = null;
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
        // TODO 02 レスポンスクラスを戻り値とする
        return null;
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        // TODO 03 EmployeeServiceの主キー検索のメソッドを呼び出す
        //  その結果を変数に代入する
        Employee employee = null;
        // エンティティからレスポンスクラスへ変換する
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getId(),
                employee.getName(),
                employee.getJoinedDate(),
                employee.getDepartmentName(),
                employee.getEmail(),
                employee.getBirthDay());
        // TODO 04 レスポンスクラスを戻り値とする
        return null;
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
        // TODO 05 EmployeeServiceの更新メソッドを呼び出す

    }

    // TODO 06 リクエストメソッドおよびURLとコントローラークラスのメソッドを結び付けるアノテーションを付加する
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

        // TODO 07 ServletUriComponentsBuilderを使って追加したデータを表すURLを作成する
        URI location = null;
        // TODO 08 ResponseEntityを使ってレスポンスステータスコードやレスポンスヘッダーを設定する
        return null;
    }

    // TODO 09 リクエストメソッドおよびURLとコントローラークラスのメソッドを結び付けるアノテーションを付加する
    // TODO 10 レスポンスステータスコードを指定するアノテーションを付加する
    public void delete(@PathVariable Integer id) {
        // TODO 11 EmployeeServiceの削除メソッドを呼び出す

    }

}
