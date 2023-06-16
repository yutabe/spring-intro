package com.example.view.json;

import com.example.view.ViewLogic;

// @Componentは付加しない
public class JsonViewLogic implements ViewLogic {
    @Override
    public void showView() {
        System.out.println("{");
        System.out.println(" \"message\" : \"JSONで表示します\"");
        System.out.println("}");
    }
}
