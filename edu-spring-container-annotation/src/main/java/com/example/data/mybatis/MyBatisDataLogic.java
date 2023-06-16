package com.example.data.mybatis;

import com.example.data.DataLogic;
import org.springframework.stereotype.Component;

// TODO アノテーションを付加する

public class MyBatisDataLogic implements DataLogic {
    @Override
    public void search() {
        System.out.println("MyBatisでデータを検索します");
    }
}
