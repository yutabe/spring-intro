package com.example.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void findAll_レコード2件() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("size()").value(2))
                .andExpect(jsonPath("[0].id").value(101))
                .andExpect(jsonPath("[0].name").value("山田太郎"))
                .andExpect(jsonPath("[0].joinedDate").value("2010-04-01"))
                .andExpect(jsonPath("[0].departmentName").value("営業部"))
                .andExpect(jsonPath("[0].email").value("yamada@example.com"))
                .andExpect(jsonPath("[0].birthDay").value("1986-05-11"))
                .andExpect(jsonPath("[1].id").value(102))
                .andExpect(jsonPath("[1].name").value("鈴木次郎"))
                .andExpect(jsonPath("[1].joinedDate").value("2010-05-01"))
                .andExpect(jsonPath("[1].departmentName").value("開発部"))
                .andExpect(jsonPath("[1].email").value("suzuki@example.com"))
                .andExpect(jsonPath("[1].birthDay").value("1989-06-19"));
    }

    @Test
    @Sql({"/test-schema.sql"})
    public void findAll_レコード0件() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("size()").value(0));
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void findById_存在するID_レコード2件() throws Exception {
        mockMvc.perform(get("/employees/{id}", 101))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value(101))
                .andExpect(jsonPath("name").value("山田太郎"))
                .andExpect(jsonPath("joinedDate").value("2010-04-01"))
                .andExpect(jsonPath("departmentName").value("営業部"))
                .andExpect(jsonPath("email").value("yamada@example.com"))
                .andExpect(jsonPath("birthDay").value("1986-05-11"));
    }

    @Test
    @Sql({"/test-schema.sql"})
    public void findById_存在しないID_レコード2件() throws Exception {
        mockMvc.perform(get("/employees/{id}", "103"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("ID:103 のデータは存在しません"));
    }


    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void insert() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "高田純平",
                                    "joinedDate": "2011-04-01",
                                    "departmentName": "管理部",
                                    "email": "takada@example.com",
                                    "birthDay": "1979-12-01"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/employees/103"));
    }

    @Test
    public void insert_制約違反_必須チェック() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "",
                                    "joinedDate": "",
                                    "departmentName": "",
                                    "email": "",
                                    "birthDay": ""
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void insert_制約違反_最大文字列長チェック() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "12345678901",
                                    "joinedDate": "2011-04-01",
                                    "departmentName": "123456789012345678901",
                                    "email": "1234567890@example.com",
                                    "birthDay": "1979-12-01"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void insert_制約違反_形式チェック() throws Exception {
        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "1234567890",
                                    "joinedDate": "aaa",
                                    "departmentName": "12345678901234567890",
                                    "email": "1234567890@example.com",
                                    "birthDay": "1979-12-01"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }


    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void update_存在するID_レコード2件() throws Exception {
        mockMvc.perform(put("/employees/{id}", "101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "高田純平",
                                    "joinedDate": "2011-04-01",
                                    "departmentName": "管理部",
                                    "email": "takada@example.com",
                                    "birthDay": "1979-12-01"
                                }
                                """))
                .andExpect(status().isNoContent());
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void update_存在しないID_レコード2件() throws Exception {
        mockMvc.perform(put("/employees/{id}", "103")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "高田純平",
                                    "joinedDate": "2011-04-01",
                                    "departmentName": "管理部",
                                    "email": "takada@example.com",
                                    "birthDay": "1979-12-01"
                                }
                                """))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("ID:103 のデータは存在しません"));
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void delete_存在するID_レコード2件() throws Exception {
        mockMvc.perform(delete("/employees/{id}", "101"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Sql({"/test-schema.sql", "/test-data-2records.sql"})
    public void delete_存在しないID_レコード2件() throws Exception {
        mockMvc.perform(delete("/employees/{id}", "103"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("message").value("ID:103 のデータは存在しません"));
    }

}