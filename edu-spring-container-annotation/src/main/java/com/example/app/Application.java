package com.example.app;

import com.example.config.AppConfig;
import com.example.data.DataLogic;
import com.example.view.ViewLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Application {
    public static void main(String[] args) {
        // DIコンテナの作成
        ApplicationContext context = null; // TODO DIコンテナを作成する

        // データを処理するBeanの取り出し・処理実行
        DataLogic dataLogic = null; // TODO Beanを取得する
        dataLogic.search();

        // 画面を表示するBeanの取り出し・処理実行
        ViewLogic viewLogic = null; // TODO Beanを取得する
        viewLogic.showView();
    }
}
