package com.example.p19;

import java.io.Serializable;

public enum ClassP implements Serializable {

    Выполнено,
    Неᅠвыполненно,
    Вᅠпроцессе;

    public static ClassP valueOf(int i){
        for (ClassP item: values()){
            if(item.ordinal() == i){
                return item;
            }
        }
        throw new IllegalArgumentException();
    }
}



