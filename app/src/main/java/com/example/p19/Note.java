package com.example.p19;

import java.io.Serializable;
import java.sql.Time;

public class Note implements Serializable {
    private String header;
    private String text;
    private ClassP type;
    private Time time;

    public Note(String h, String t,ClassP type, Time ti){
        this.header=h;
        this.text=t;
        this.type = type;
        this.time=ti;
    }

    String getHeader(){
        return header;
    }
    String getText(){
        return text;
    }
    ClassP getValue(){
        return type;
    }
    Time getTime(){
        return time;
    }

    void setHeader(String h){
        this.header = h;
    }
    void setText(String t){
        this.text = t;
    }
    void setTime(Time ti){
        this.time = ti;
    }
}
