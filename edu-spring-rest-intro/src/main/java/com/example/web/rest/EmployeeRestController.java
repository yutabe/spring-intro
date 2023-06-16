package com.example.web.rest;

import com.example.web.request.EmployeeRequest;
import com.example.web.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// TODO 社員の全件検索-02 コントローラークラスを表すアノテーションを付加する
// TODO 社員の全件検索-03 リクエストURLとコントローラークラスを結び付けるアノテーションを付加する
// TODO 社員のID検索-01 社員の全件検索-02 で付加したアノテーションを@RestControllerに置き換える
public class EmployeeRestController {

    // TODO 社員の全件検索-04 リクエストメソッドおよびURLとコントローラークラスのメソッドを結び付けるアノテーションを付加する
    // TODO 社員の全件検索-05 メソッドの戻り値をレスポンスボディとしてレスポンスすることを指定するアノテーションを付加する
    // TODO 社員のID検索-02 社員の全件検索-05 で付加したアノテーションを削除する
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
        // TODO 社員の全件検索-06 employeeResponseListを戻り値とする
        return null;
    }

    // TODO 社員のID検索-03 リクエストメソッドおよびURLとコントローラークラスのメソッドを結び付けるアノテーションを付加する
    public EmployeeResponse findById(/* TODO 社員のID検索-04 パスパラメータと引数を結び付けるアノテーションを付加する */ Integer id) {
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

    // TODO 社員の更新-02 リクエストメソッドおよびURLとコントローラークラスのメソッドを結び付けるアノテーションを付加する
    // TODO 社員の更新-03 レスポンスステータスコードを指定するアノテーションを付加する
    public void update(@PathVariable Integer id, /* TODO 社員の更新-04 リクエストボディと引数を結び付けるアノテーションを付加する */ EmployeeRequest request) {
        String name = request.getName();
        LocalDate joinedDate = request.getJoinedDate();
        String departmentName = request.getDepartmentName();
        String email = request.getEmail();
        LocalDate birthDay = request.getBirthDay();
        // 本来であればパスパラメータやリクエストボディで送信された値を使ってデータベースの更新を行うがそれについては後述
        System.out.println("更新対象のデータのID: " + id);
        System.out.println("name: " + name);
        System.out.println("joinedDate: " + joinedDate);
        System.out.println("departmentName: " + departmentName);
        System.out.println("email: " + email);
        System.out.println("birthDay: " + birthDay);
    }

}
