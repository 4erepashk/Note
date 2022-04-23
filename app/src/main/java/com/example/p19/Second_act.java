package com.example.p19;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Calendar;

public class Second_act extends First_act {
    int ID;
    Button Ok;
    Button Cancel;
    Button sTime;
    public EditText Text;
    public EditText Header;
    public Note obj;
    Calendar dateAndTime = Calendar.getInstance();
    Time time;
    TextView TimeV;
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_act);
        Text = (EditText) findViewById(R.id.Text);
        Header = (EditText) findViewById(R.id.Header);
        TimeV = (TextView) findViewById(R.id.time);
        sTime = (Button) findViewById(R.id.setTime);
        spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,ClassP.values());
        spinner.setAdapter(adapter);

        Header.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().trim().length() == 0||Text.getText().toString().trim().length()==0)
                {
                    //butOk.setBackgroundColor(Color.parseColor("@color/black"));
                    Ok.setEnabled(false);
                }
                else
                {
                    //butOk.setBackgroundColor(Color.parseColor("@color/light_green"));
                    Ok.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().trim().length() == 0||Header.getText().toString().trim().length()==0)
                {
                    //butOk.setBackgroundColor(Color.parseColor("@color/black"));
                    Ok.setEnabled(false);
                }
                else
                {
                    //butOk.setBackgroundColor(Color.parseColor("@color/light_green"));
                    Ok.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Ok = (Button)findViewById(R.id.OK);
        Ok.setEnabled(false);
        Cancel = (Button)findViewById(R.id.Cancel);

        Ok.setOnClickListener(this::Edit);

        Cancel.setOnClickListener(this::Cancel);

        sTime.setOnClickListener(this::setTime);

        Bundle bundle = getIntent().getExtras();

        if (bundle !=null)
        {

            obj = (Note) bundle.getSerializable(String.valueOf(EXTRA_NOTE));
            Header.setText(obj.getHeader());
            Text.setText(obj.getText());
            String []t = obj.getTime().toString().split(":");
            time = new Time(Integer.parseInt(t[0]),Integer.parseInt(t[1]),0);
            int i=0;
            if(obj.getValue() == ClassP.Первое){
                i=0;
            }
            if(obj.getValue() == ClassP.Второе){
                i=1;
            }
            if(obj.getValue() == ClassP.Третье){
                i=2;
            }
            spinner.setSelection(i);
            ID = bundle.getInt(String.valueOf(EXTRA_ID));

        }
        else
        {
            time = new Time(0,0,0);
        }
        Set();
    }

    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            time = new Time(hourOfDay, minute,0);
            Set();
        }
    };

    private void Set() {
        TimeV.setText("Время: " + time.getHours() + " ч " + time.getMinutes() + " мин");
    }

    public void setTime(View v) {

        new TimePickerDialog(Second_act.this,t,time.getHours(),time.getMinutes(),false).show();
    }

    public void Edit(View v)
    {
        obj = new Note(Header.getText().toString(),Text.getText().toString(),ClassP.valueOf(spinner.getSelectedItemPosition()),new Time(time.getHours(),time.getMinutes(),0));

        Intent intent = getIntent();
        intent.putExtra(String.valueOf(EXTRA_NOTE), obj);
        intent.putExtra(String.valueOf(EXTRA_ID), ID);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void Cancel(View v)
    {
        setResult(RESULT_CANCELED);
        finish();
    }


}
