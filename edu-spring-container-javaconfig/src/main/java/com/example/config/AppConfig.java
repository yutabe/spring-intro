package com.example.config;

import com.example.data.DataLogic;
import com.example.data.mybatis.MyBatisDataLogic;
import com.example.view.ViewLogic;
import com.example.view.json.JsonViewLogic;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScanは付加しない
public class AppConfig {

    // TODO アノテーションを付加する

    public DataLogic dataLogic() {
        return new MyBatisDataLogic();
    }

    // TODO アノテーションを付加する

    public ViewLogic viewLogic() {
        return new JsonViewLogic();
    }
}
