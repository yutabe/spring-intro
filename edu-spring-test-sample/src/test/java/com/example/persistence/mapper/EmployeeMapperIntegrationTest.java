package com.example.persistence.mapper;

import com.example.persistence.entity.Employee;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// テスト対象:EmployeeMapperの統合テスト

@SpringBootTest
// @SpringBootTestを利用すると、@SpringBootApplicationが付いたクラスを見つけ出し、
// コンポーネントスキャン対象のBeanをテスト対象として使うことができます。
class EmployeeMapperIntegrationTest {
    // テスト対象(EmployeeMapper)をDIする
    @Autowired
    private EmployeeMapper employeeMapper;

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
            List<Employee> actualList = employeeMapper.findAll();
            System.out.println("actualList: " + actualList);
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql"})
        void findAll_レコード0件() {
            // 期待値リストの作成
            List<Employee> expectedList = new ArrayList<>();
            // テスト対象を呼び出し、実際値のリストを取得
            List<Employee> actualList = employeeMapper.findAll();
            System.out.println("actualList: " + actualList);
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
            Employee actualEmployee = employeeMapper.findById(101);
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedEmployee, actualEmployee);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void findById_存在しないID_レコード2件() {
            // テスト対象を呼び出し、実際値のリストを取得
            Employee actualEmployee = employeeMapper.findById(103);
            // 結果の検証 存在しなければ、期待通り
            assertNull(actualEmployee);
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

            // テスト対象を呼び出す
            employeeMapper.insert(newEmployee);
            System.out.println("newEmployee: " + newEmployee); // idには、自動採番された値が入っている

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
            List<Employee> actualList = employeeMapper.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void deleteById_存在するID_レコード2件(){
            // テスト対象の呼び出し
            employeeMapper.delete(101);

            // 全件検索して、削除できているかを検証する
            // 期待値リストの作成
            List<Employee> expectedList = List.of(
                    new Employee(102, "鈴木次郎", LocalDate.of(2010, 5, 1),
                            "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19))
            );
            // データベース上の実際値を取得
            List<Employee> actualList = employeeMapper.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void deleteById_存在しないID_レコード2件(){
            // 存在しないIDで削除、メソッドの戻り値が0ならば、期待どおり
            int count = employeeMapper.delete(999);
            assertEquals(0, count);
        }
    }
}