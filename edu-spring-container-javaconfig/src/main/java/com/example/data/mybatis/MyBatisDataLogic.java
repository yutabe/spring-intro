package com.example.data.mybatis;

import com.example.data.DataLogic;

// @Componentは付加しない
public class MyBatisDataLogic implements DataLogic {
    @Override
    public void search() {
        System.out.println("MyBatisでデータを検索します");
    }
}
