package com.example.p19;

import android.app.Application;
import android.os.Build;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.sql.Time;

public class Base extends Application {
    private final List<Note> notes = new ArrayList<>();



    public Base(){
        super();
    }

    List<Note> getAllNotes(){
        return notes;
    }

}
