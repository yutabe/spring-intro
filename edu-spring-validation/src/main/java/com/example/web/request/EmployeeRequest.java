package com.example.web.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeRequest {

    // TODO Bean Validation-01 名前に対しアノテーションを付加して下記の制約を設定する
    //  ・必須入力
    //  ・1 文字以上 10 文字以下
    private String name;

    // TODO Bean Validation-02 入社年月日に対しアノテーションを付加して下記の制約を設定する
    //  ・必須入力
    //  ・現在より過去または現在の日時
    private LocalDate joinedDate;

    // TODO Bean Validation-03 部署名に対しアノテーションを付加して下記の制約を設定する
    //  ・必須入力
    //  ・1 文字以上 20 文字以下
    private String departmentName;

    // TODO Bean Validation-04 メールアドレスに対しアノテーションを付加して下記の制約を設定する
    //  ・必須入力
    //  ・Eメールアドレス形式
    private String email;

    // TODO Bean Validation-05 生年月日に対しアノテーションを付加して下記の制約を設定する
    //  ・必須入力
    //  ・現在より過去または現在の日時
    private LocalDate birthDay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    // TODO 相関バリデーション-02 アノテーションを付加する
    public boolean isJoinedDateGreaterThanBirthDay() {
        // TODO 相関バリデーション-01 入社年月日が生年月日よりも大きい(未来)の場合 true を返す
        //  入社年月日が生年月日と同じ、もしくは小さい(過去)の場合 false を返す
        //  入社年月日もしくは生年月日が null の場合は比較を行わず true を返す
        return false;
    }
}
