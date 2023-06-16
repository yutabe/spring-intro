package com.example.sample3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // レコードクラス型のインスタンスの生成
        User user = new User(100, "鈴木");

        // toString()メソッドが暗黙的にオーバーライドされている
        // Object.toString() => com.example.sample3.User@xxxxx にはならない
        System.out.println(user); // User[id=100, name=鈴木]

        // 同じフィールド値をもつsameインスタンスを生成する
        User same = new User(100, "鈴木");

        // user.equals(same)メソッドを呼び出し、等しいか確認する。

        // 暗黙的にequals()メソッドがオーバーライドされているので、
        // 明示的にオーバーライドしなくても、以下の結果はtrueとなる
        System.out.println(user.equals(same)); // true

        // Listにuserを入れておき、list.contains(same)メソッドを行う
        List<User> list = new ArrayList<>();
        list.add(user);
        // 暗黙的に、equals()メソッドがオーバーライドされているので、
        // list.contains(same) で正しくtrueが返る
        System.out.println(list.contains(same)); // true

        // HashMapにキー userで値を入れておき、キー sameで取得する
        Map<User, String> map = new HashMap<>();
        map.put(user, "suzuki@example.com");

        // 暗黙的にhash()メソッドがオーバーライドされているので
        // map.get(same)メソッドで正しく"suzuki@example.com"が取得できる
        System.out.println(map.get(same)); // suzuki@example.com
    }
}
