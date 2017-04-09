package com.group4.joseph.timetablenotifier;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomAdapter extends ArrayAdapter<String> {


    public CustomAdapter(Context context, String[] items) {
        super(context, R.layout.custom_row, items);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {     //for the string array that is passed in, this is how to lay them out
        LayoutInflater list_inflater = LayoutInflater.from(getContext());
        View custom_View = list_inflater.inflate(R.layout.custom_row, parent, false);   //inflaters is androids way to render a view

        String single_item = getItem(position);     //Gets the item that was clicked
        TextView custom_row_text = (TextView) custom_View.findViewById(R.id.customrowText);      //reference the text

        custom_row_text.setText(single_item);
        return custom_View;
    }
}