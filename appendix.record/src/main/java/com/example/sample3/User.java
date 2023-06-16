package com.example.sample3;

public record User(long id, String name) {
    // コンパクトコンストラクターの宣言
    public User { // (引数リスト) は記述しない
        if ( id < 1 ){
            throw new IllegalArgumentException("idの値が不正(id=" + id + ")");
        }
        // 以下の処理は記述する必要ありません。暗黙的に行われます。
        // this.id = id;
        // this.name = name;
    }

    // 上記を通常のコンストラクター(非コンパクトコンストラクター)を使って宣言すると
    // 以下のようになる
//    public User(long id, String name) { // **レコードヘッダーに合わせる必要がある**
//        if ( id < 1 ){
//            throw new IllegalArgumentException("idの値が不正(id=" + id + ")");
//        }
//        // 以下の処理は暗黙的に行われません。記述する必要があります。
//        this.id = id;
//        this.name = name;
//    }

}