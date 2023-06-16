package com.example.service.impl;

import com.example.exception.EmployeeNotFoundException;
import com.example.persistence.entity.Employee;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * テスト対象:EmployeeServiceImplの統合テスト
 */
@SpringBootTest
class EmployeeServiceImplIntegrationTest {
    // フィールド・インジェクションを使って、テスト対象のインターフェース(EmployeeService)をDIする
    // ここで、employeeServiceフィールドにDIされるインスタンスは、既に永続化層(employeeMapper)と関連付いているため、
    // 実際の永続化層もDIコンテナ上のBeanを利用したテストを行う事ができる。(永続化層も統合したテストが行える)
    @Autowired
    private EmployeeService employeeService;

    @Nested
    class 検索系テスト {
        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void findAll_レコード2件() {
            // 期待値リストの作成
            List<Employee> expectedList = List.of(
                    new Employee(101, "山田太郎", LocalDate.of(2010, 4, 1),
                            "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11)),
                    new Employee(102, "鈴木次郎", LocalDate.of(2010, 5, 1),
                            "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19))
            );
            // テスト対象を呼び出し、実際値のリストを取得
            Iterable<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql"})
        void findAll_レコード0件() {
            // 期待値リストの作成
            List<Employee> expectedList = new ArrayList<>();
            // テスト対象を呼び出し、実際値のリストを取得
            Iterable<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void findById_存在するID_レコード2件() {
            // 期待値リストの作成
            Employee expectedEmployee =
                    new Employee(101, "山田太郎", LocalDate.of(2010, 4, 1),
                            "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11));
            // テスト対象を呼び出し、実際値のリストを取得
            Employee actualEmployee = employeeService.findById(101);
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedEmployee, actualEmployee);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void findById_存在しないID_レコード2件() {
            // 存在しないIDで検索を実行し、EmployeeNotFoundExceptionが発生すれば、仕様通り
            assertThrows(EmployeeNotFoundException.class,
                    () -> employeeService.findById(103));
        }
    }

    @Nested
    class 更新系テスト {
        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void insert_レコード2件(){
            // 追加するEmployeeを生成
            Employee newEmployee = new Employee(null, "高田純平", LocalDate.of(2011, 4, 1),
                    "管理部", "takada@example.com", LocalDate.of(1979, 12, 1));

            // テスト対象を呼び出し、実際値のリストを取得
            employeeService.insert(newEmployee);

            // 全件検索して、登録できているかを検証する
            // 期待値リストの作成 (test-data-2records.sqlの2件 + 追加したnewEmployee)
            List<Employee> expectedList = List.of(
                    new Employee(101, "山田太郎", LocalDate.of(2010, 4, 1),
                            "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11)),
                    new Employee(102, "鈴木次郎", LocalDate.of(2010, 5, 1),
                            "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19)),
                    newEmployee  // 追加したnewEmployee
            );
            // データベース上の実際値を取得
            List<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void delete_存在するID_レコード2件(){
            // テスト対象を呼び出し、実際値のリストを取得
            employeeService.delete(101);

            // 全件検索して、削除できているかを検証する
            // 期待値リストの作成
            List<Employee> expectedList = List.of(
                    new Employee(102, "鈴木次郎", LocalDate.of(2010, 5, 1),
                            "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19))
            );
            List<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void delete_存在しないID_レコード2件(){
            // 存在しないIDで削除を実行し、EmployeeNotFoundExceptionが発生すれば、仕様どおり
            assertThrows(EmployeeNotFoundException.class,
                    () -> employeeService.delete(999));
        }
    }
}