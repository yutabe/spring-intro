package com.example.service.impl;

import com.example.exception.EmployeeNotFoundException;
import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    KKK
    ビジネスロジック層のロジックで、EmployeeMapperから例外が返る場合がないので、
    Spyを使った良いサンプルには、ならない。Spyのサンプルは、検討が必要。
 */


/**
 * テスト対象:【MockitoExtensionで機能拡張版】EmployeeServiceImplの統合テスト(@SpyBeanを利用)
 *   永続化層(EmployeeMapper)のBeanを継承したスパイオブジェクトを作り、利用する
 *   @MockBean(モックオブジェクト)を使う場合は、呼び出されるメソッドのふるまいを１つ１つ設定する必要がありますが、
 *   @SpyBean(スパイオブジェクト)を使うと、実物のオブジェクトのふるまいを変更したいメソッドのみオーバーライドすることができます
 *   例：
 *     戻り値が返る正常系テストは、実際のEmployeeMapperの実装クラスのメソッドを呼び出してテストを行い、
 *     例外が返る場合の処理の検証を行うエラー系のテストでは、発生させたい例外が返るよう呼び出されるメソッドのふるまいを設定する。
 */

@SpringBootTest
// ビジネスロジック層(EmployeeService)のBeanを使いたいので、@SpringBootTestを付ける
@ExtendWith(SpringExtension.class)
// @MockBean/@SpyBeanを使いたい場合は、@ExtendWith(SpringExtension.class)を使う
class EmployeeServiceImplWithSpyIntegrationTest {
    // モックを作成したいインターフェース型のフィールドを用意し、@SpyBeanを付けると、
    // DIコンテナより取得したBeanを継承したスパイオブジェクトが生成される
    // [注意]
    //   Mockitoの@Spyを使うと、EmployeeMapperインターフェースのスパイオブジェクトになってしまう。
    //   DIコンテナ上で管理されるEmployeeMapperの実装インスタンスのスパイを作成したい場合は、
    //   @SpyBeanを使う。
    @SpyBean
    private EmployeeMapper employeeMapper;

    // @ExtendWith(SpringExtension.class)を利用する場合は、
    // Mockitoの@InjectMocksではなく、Springの@Autowiredを利用する。
    // このemployeeServiceのemployeeMapperフィールドには、@SpyBeanで指定したスパイオブジェクトがDIされる。
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
            // スパイのふるまいの設定(when/thenReturnメソッドによるシミュレーション)は、行わない。
            // → EmployeeMapperの実装クラスのfindAllメソッドが呼び出される

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
            // スパイのふるまいの設定(when/thenReturnメソッドによるシミュレーション)は、行わない。
            // → EmployeeMapperの実装クラスのfindAllメソッドが呼び出される

            // テスト対象を呼び出し、実際値のリストを取得
            Iterable<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        @Sql({"/test-schema.sql", "/test-data-2records.sql"})
        void findById_存在するID_レコード2件() {
            // 期待値の作成
            Employee expectedEmployee =
                    new Employee(101, "山田太郎", LocalDate.of(2010, 4, 1),
                            "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11));

            // スパイのふるまいの設定(when/thenReturnメソッドによるシミュレーション)は、行わない。
            // → EmployeeMapperの実装クラスのfindByIdメソッドが呼び出される

            // テスト対象を呼び出し、実際値のリストを取得
            Employee actualEmployee = employeeService.findById(101);
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedEmployee, actualEmployee);
        }

        @Test
        // スパイをシミュレートしてテストするので、データベースに実データを投入しない
        void findById_存在しないID() {
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.findById(103)が呼び出された際、nullがリターンされるよう設定
            Mockito.doReturn(null).when(employeeMapper).findById(103);

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
        // スパイをシミュレートしてテストするので、データベースに実データを投入しない
        void delete_存在しないID(){
            // スパイのふるまいの設定(when/thenReturnメソッドによるシミュレーション)する。
            // スパイオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.delete(999)が呼び出された際、0がリターンされるよう設定
            Mockito.doReturn(0).when(employeeMapper).delete(999);

            // 存在しないIDで削除を実行し、EmployeeNotFoundExceptionが発生すれば、仕様どおり
            assertThrows(EmployeeNotFoundException.class,
                    () -> employeeService.delete(999));
        }
    }
}