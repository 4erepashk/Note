package com.example.p19;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterForList extends BaseAdapter {

    private List<Note> list;

    public AdapterForList (List<Note> list1)
    {
        this.list = list1;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context  = viewGroup.getContext();
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//получаем макет
            view = inflater.inflate(R.layout.le, viewGroup, false);
        }

        Note n = (Note)getItem(i);

        TextView head = (TextView) view.findViewById(R.id.H);
        head.setText(n.getHeader());

        TextView text  = (TextView) view.findViewById(R.id.Text);
        text.setText(n.getText());

        TextView time = (TextView) view.findViewById(R.id.Time);
        time.setText(n.getTime().toString());

        TextView value = view.findViewById(R.id.value);
        if(n.getValue()==ClassP.Первое){((TextView)view.findViewById(R.id.value)).setText(ClassP.valueOf(0).toString());}
        if(n.getValue()==ClassP.Второе){((TextView)view.findViewById(R.id.value)).setText(ClassP.valueOf(1).toString());}
        if(n.getValue()==ClassP.Третье){((TextView)view.findViewById(R.id.value)).setText(ClassP.valueOf(2).toString());}



        return view;
    }

}
