package com.example.config;

import com.example.data.DataLogic;
import com.example.data.mybatis.MyBatisDataLogic;
import com.example.view.ViewLogic;
import com.example.view.json.JsonViewLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @ComponentScanは付加しない
public class AppConfig {

    @Bean
    public DataLogic dataLogic() {
        return new MyBatisDataLogic();
    }

    @Bean
    public ViewLogic viewLogic() {
        return new JsonViewLogic();
    }
}
