package com.example.p19;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class First_act extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    private List<Note> list= new ArrayList<Note>();
    private ListView LV;
    protected Base base;
    private AdapterForList adapterForList;

    DataBase db;
    int cureP = 0;

    protected static final int CREATE_ACTION = 0x000312;
    protected static final int EDIT_ACTION = 0x000313;
    protected static final int EXTRA_NOTE = 0x000315;
    protected static final int EXTRA_ID = 0x000314;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_act);

        db = new DataBase(this);

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor cursor = database.query(db.TABLE_CONTACTS, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            int Index = cursor.getColumnIndex(db.KEY_ID);
            int HeaderIndex = cursor.getColumnIndex(db.KEY_NAME);
            int ContentIndex = cursor.getColumnIndex(db.KEY_CONTENT);
            int TimeIndex = cursor.getColumnIndex(db.KEY_TIME);
            int ValueIndex = cursor.getColumnIndex(db.KEY_VALUE);

            do{
                String []ints = cursor.getString(TimeIndex).split(":");

                Note a = new Note(cursor.getString(HeaderIndex), cursor.getString(ContentIndex), ClassP.valueOf(cursor.getInt(ValueIndex)), new Time(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]), 0));
                list.add(a);
            }while (cursor.moveToNext());
        }
        cursor.close();

        adapterForList = new AdapterForList(list);

        LV = (ListView) findViewById(R.id.LV);
        LV.setAdapter(adapterForList);
        LV.setOnItemClickListener(this::onItemClick);


    }
    public void Add(View v)
    {
        Intent intent = new Intent(this, Second_act.class);
        startActivityForResult(intent, CREATE_ACTION);
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Note s = list.get(i);
        cureP =  i;
        Intent intent = new Intent(this, Second_act.class);
        intent.putExtra(String.valueOf(EXTRA_NOTE), s);
        intent.putExtra(String.valueOf(EXTRA_ID), i);
        startActivityForResult(intent, EDIT_ACTION);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)
        {

            Bundle bundle = data.getExtras();
            Note note = (Note) bundle.getSerializable(String.valueOf(EXTRA_NOTE));
            switch (requestCode)
            {
                case CREATE_ACTION:


                    SQLiteDatabase database = db.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(DataBase.KEY_NAME, note.getHeader());
                    contentValues.put(DataBase.KEY_CONTENT, note.getText());
                    contentValues.put(DataBase.KEY_TIME, note.getTime().toString());
                    contentValues.put(DataBase.KEY_VALUE, note.getValue().toString());

                    database.insert(DataBase.TABLE_CONTACTS, null, contentValues);
                    list.add(note);
                    break;

                case EDIT_ACTION:
                    ContentValues cv = new ContentValues();
                    cv.put(DataBase.KEY_NAME, note.getHeader());
                    cv.put(DataBase.KEY_CONTENT, note.getText());
                    cv.put(DataBase.KEY_TIME, note.getTime().toString());
                    cv.put(DataBase.KEY_VALUE, note.getValue().toString());
                    database = db.getWritableDatabase();
                    database.update(DataBase.TABLE_CONTACTS, cv, DataBase.KEY_ID + "="+ (cureP+1), null);
                    list.set(cureP, note);
                    break;
            }
            LV.invalidateViews();
        }


    }
}
