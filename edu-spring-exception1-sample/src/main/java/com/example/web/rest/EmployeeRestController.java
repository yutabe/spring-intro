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
    public void update(@PathVariable Integer id, @RequestBody @Validated EmployeeRequest request) {
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

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
//        BindingResult bindingResult = exception.getBindingResult();
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        ErrorResponse errorResponse = new ErrorResponse("データ形式エラー");
//        for (FieldError fieldError : fieldErrors) {
//            errorResponse.addDetail(fieldError.getField(), fieldError.getDefaultMessage());
//        }
//        return errorResponse;
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {
//        String parameterName = exception.getName();
//        Object actualValue = exception.getValue();
//        ErrorResponse errorResponse = new ErrorResponse("パラメータの形式が不正");
//        errorResponse.addDetail(parameterName, "送信された値: " + actualValue + ", 正しい形式で入力してください。");
//        return errorResponse;
//    }
//
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
//        ErrorResponse errorResponse = new ErrorResponse("リクエストボディの解析に失敗");
//        return errorResponse;
//    }

}
