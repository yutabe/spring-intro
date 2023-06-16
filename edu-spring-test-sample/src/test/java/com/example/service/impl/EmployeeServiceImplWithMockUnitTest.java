package com.example.service.impl;

import com.example.exception.EmployeeNotFoundException;
import com.example.persistence.entity.Employee;
import com.example.persistence.mapper.EmployeeMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * テスト対象:EmployeeServiceImplの単体テスト(モックを利用)
 *   永続化層(EmployeeMapper)のモックオブジェクトを作り、利用する
 */

// ビジネスロジック層(EmployeeServiceImpl)の単体テストなので、@SpringBootTestは付けない
class EmployeeServiceImplWithMockUnitTest {
    // モックを作成したいインターフェース型のフィールドを用意し、@Mockを付けると、
    // Mockitoにより、モックオブジェクトが生成される
    @Mock
    private EmployeeMapper employeeMapper;

    // モックオブジェクトをDIしたいテスト対象クラス型のフィールドを用意し、@InjectMocksを付けると、
    // Mockitoにより、テスト対象クラスのインスタンスが生成され、モックオブジェクトがDIされる。
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private AutoCloseable closeable; // openMocks()の戻り値 openしたら、closeする必要がある

    @BeforeEach
    void setUp(){
        // テストメソッド単位で、@Mock/@InjectMockが付いたオブジェクトを生成する
        // このメソッドを呼び出すタイミングで、thisインスタンスに@Mock/@InjectMockが付いたオブジェクトが生成され、
        // @InjectMockが付いたオブジェクトに、モックがDIされる(差し込まれる)
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        // openMocks()と対でclose()しておく。(Mockitoの作法)
        closeable.close();
    }

    @Nested
    class 検索系テスト {
        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void findAll_レコード2件() {
            // 期待値リストの作成
            List<Employee> expectedList = List.of(
                    new Employee(101, "山田太郎", LocalDate.of(2010, 4, 1),
                            "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11)),
                    new Employee(102, "鈴木次郎", LocalDate.of(2010, 5, 1),
                            "開発部", "suzuki@example.com", LocalDate.of(1989, 6, 19))
            );
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.findAll()が呼び出された際、expectedListがリターンされるよう設定
            Mockito.doReturn(expectedList).when(employeeMapper).findAll();

            // テスト対象を呼び出し、実際値のリストを取得
            Iterable<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void findAll_レコード0件() {
            // 期待値リストの作成
            List<Employee> expectedList = new ArrayList<>();
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.findAll()が呼び出された際、expectedListがリターンされるよう設定
            Mockito.doReturn(expectedList).when(employeeMapper).findAll();

            // テスト対象を呼び出し、実際値のリストを取得
            Iterable<Employee> actualList = employeeService.findAll();
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedList, actualList);
        }

        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void findById_存在するID() {
            // 期待値の作成
            Employee expectedEmployee =
                    new Employee(101, "山田太郎", LocalDate.of(2010, 4, 1),
                            "営業部", "yamada@example.com", LocalDate.of(1986, 5, 11));
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.findById(101)が呼び出された際、expectedEmployeeがリターンされるよう設定
            Mockito.doReturn(expectedEmployee).when(employeeMapper).findById(101);

            // テスト対象を呼び出し、実際値のリストを取得
            Employee actualEmployee = employeeService.findById(101);
            // 結果の検証 期待値と実際値が等しいか
            assertEquals(expectedEmployee, actualEmployee);
        }

        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void findById_存在しないID() {
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.findById(101)が呼び出された際、nullがリターンされるよう設定
            Mockito.doReturn(null).when(employeeMapper).findById(103);

            // 存在しないIDで検索を実行し、EmployeeNotFoundExceptionが発生すれば、仕様通り
            assertThrows(EmployeeNotFoundException.class,
                    () -> employeeService.findById(103));
        }
    }

    @Nested
    class 更新系テスト {
        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void insert(){
            // 追加するEmployeeを生成
            Employee newEmployee = new Employee(null, "高田純平", LocalDate.of(2011, 4, 1),
                    "管理部", "takada@example.com", LocalDate.of(1979, 12, 1));
            // モックオブジェクトのメソッドが呼び出された際のふるまいの設定ですが、
            //   employeeMapper.insert()メソッドは戻り値無し(void)。
            //   例外を発生させる必要が無い場合は、メソッドが呼び出された際のふるまいは設定する必要はない。

            // テスト対象を呼び出す
            employeeService.insert(newEmployee);
            // モックを使った更新系のテストでは、実際のDBには反映されないので、呼び出し後の状態は検証できない。
            // テスト対象のメソッド呼び出しで、例外が発生しなければ、検証OKとする
        }

        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void delete_存在するID(){
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.delete(101)が呼び出された際、1がリターンされるよう設定
            Mockito.doReturn(1).when(employeeMapper).delete(101);

            // テスト対象を呼び出す
            employeeService.delete(101);
            // モックを使った更新系のテストでは、実際のDBには反映されないので、呼び出し後の状態は検証できない。
            // テスト対象のメソッド呼び出しで、例外が発生しなければ、検証OKとする
        }

        @Test
        // 永続化層はモックを使ってテストする(実際のDBアクセスは行わない)ので、@Sql(・・・)は不要
        void delete_存在しないID(){
            // モックオブジェクトのメソッドが呼び出された際のふるまいを設定しておく。
            //   employeeMapper.delete(999)が呼び出された際、0がリターンされるよう設定
            Mockito.doReturn(0).when(employeeMapper).delete(999);

            // 存在しないIDで削除を実行し、EmployeeNotFoundExceptionが発生すれば、仕様どおり
            assertThrows(EmployeeNotFoundException.class,
                    () -> employeeService.delete(999));
        }
    }
}