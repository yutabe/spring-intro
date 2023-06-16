package com.example.app;

import com.example.config.AppConfig;
import com.example.data.DataLogic;
import com.example.view.ViewLogic;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class Application {
    public static void main(String[] args) {
        // DIコンテナの作成
        ApplicationContext context = SpringApplication.run(AppConfig.class, args);

        // データを処理するBeanの取り出し・処理実行
        DataLogic dataLogic = context.getBean(DataLogic.class);
        dataLogic.search();

        // 画面を表示するBeanの取り出し・処理実行
        ViewLogic viewLogic = context.getBean(ViewLogic.class);
        viewLogic.showView();
    }
}
