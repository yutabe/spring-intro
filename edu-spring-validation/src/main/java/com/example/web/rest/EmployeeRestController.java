package com.example.web.rest;

import com.example.web.request.EmployeeRequest;
import com.example.web.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    @GetMapping
    public List<EmployeeResponse> findAll() {
        List<EmployeeResponse> employeeResponseList = List.of(
                new EmployeeResponse(101, "山田太郎", LocalDate.of(2010, 4, 1),
                        "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11)),
                new EmployeeResponse(102, "鈴木次郎", LocalDate.of(2010, 4, 1),
                        "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19)),
                new EmployeeResponse(103, "高田純平", LocalDate.of(2010, 4, 1),
                        "管理部", "takada@example.com", LocalDate.of(1979, 12, 1)),
                new EmployeeResponse(104, "大山里美", LocalDate.of(2010, 4, 1),
                        "開発部", "oyama@example.com", LocalDate.of(1990, 1, 11)),
                new EmployeeResponse(105, "橋本哲也", LocalDate.of(2010, 4, 1),
                        "営業部", "hashimoto@example.com", LocalDate.of(1989, 7, 17)));
        return employeeResponseList;
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        List<EmployeeResponse> employeeResponseList = List.of(
                new EmployeeResponse(101, "山田太郎", LocalDate.of(2010, 4, 1),
                        "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11)),
                new EmployeeResponse(102, "鈴木次郎", LocalDate.of(2010, 4, 1),
                        "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19)),
                new EmployeeResponse(103, "高田純平", LocalDate.of(2010, 4, 1),
                        "管理部", "takada@example.com", LocalDate.of(1979, 12, 1)),
                new EmployeeResponse(104, "大山里美", LocalDate.of(2010, 4, 1),
                        "開発部", "oyama@example.com", LocalDate.of(1990, 1, 11)),
                new EmployeeResponse(105, "橋本哲也", LocalDate.of(2010, 4, 1),
                        "営業部", "hashimoto@example.com", LocalDate.of(1989, 7, 17)));
        EmployeeResponse employeeResponse = null;
        for (EmployeeResponse response : employeeResponseList) {
            if (id.equals(response.getId())) {
                employeeResponse = response;
            }
        }
        return employeeResponse;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody /* TODO Bean Validation-06 入力検証を実行するアノテーションを付加する */ EmployeeRequest request) {
        String name = request.getName();
        LocalDate joinedDate = request.getJoinedDate();
        String departmentName = request.getDepartmentName();
        String email = request.getEmail();
        LocalDate birthDay = request.getBirthDay();
        System.out.println("更新対象のデータのID: " + id);
        System.out.println("name: " + name);
        System.out.println("joinedDate: " + joinedDate);
        System.out.println("departmentName: " + departmentName);
        System.out.println("email: " + email);
        System.out.println("birthDay: " + birthDay);
    }

}
