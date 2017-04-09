package com.group4.joseph.timetablenotifier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddLocationString extends AppCompatActivity {

    EditText edit_message, edit_message1, edit_message2, edit_message3;
    EditText  edit_message4, edit_message5, edit_message6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location_string);

        edit_message = (EditText)findViewById(R.id.edit_message);
        edit_message1 = (EditText)findViewById(R.id.edit_message1);
        edit_message2 = (EditText)findViewById(R.id.edit_message2);
        edit_message3 = (EditText)findViewById(R.id.edit_message3);
        edit_message4 = (EditText)findViewById(R.id.edit_message4);
        edit_message5 = (EditText)findViewById(R.id.edit_message5);
        edit_message6 = (EditText)findViewById(R.id.edit_message6);

        printUpToEditText();

    }

    public void addLocation(View view){
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        String string2 = edit_message.getText().toString();

        //EditText editText2 = (EditText) findViewById(R.id.edit_message1);
        String string3 = edit_message1.getText().toString();

        //EditText editText3 = (EditText) findViewById(R.id.edit_message2);
        String string4 = edit_message2.getText().toString();


        //EditText editText4 = (EditText) findViewById(R.id.edit_message3);
        String string5 = edit_message3.getText().toString();


        //EditText editText5 = (EditText) findViewById(R.id.edit_message4);
        String string6 = edit_message4.getText().toString();


        //EditText editText6 = (EditText) findViewById(R.id.edit_message5);
        String string7 = edit_message5.getText().toString();


        //EditText editText7 = (EditText) findViewById(R.id.edit_message6);
        String string8 = edit_message6.getText().toString();


        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor1 =prefs.edit();
        editor1.putString("string12",string2);
        editor1.putString("string13",string3);
        editor1.putString("string14",string4);
        editor1.putString("string15",string5);
        editor1.putString("string16",string6);
        editor1.putString("string17",string7);
        editor1.putString("string18",string8);
        editor1.commit();

        Toast.makeText(AddLocationString.this, "Locations saved", Toast.LENGTH_LONG).show();

        Intent menu_intent = new Intent(this, MainActivity.class);
        startActivity(menu_intent);
    }

    public void mainMenuReturn1(View view){
        Intent menu_intent = new Intent(this, MainActivity.class);
        startActivity(menu_intent);
    }

    public void printUpToEditText(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String string2= prefs.getString("string12","");
        String string3= prefs.getString("string13","");
        String string4= prefs.getString("string14","");
        String string5= prefs.getString("string15","");
        String string6= prefs.getString("string16","");
        String string7= prefs.getString("string17","");
        String string8= prefs.getString("string18","");

        edit_message.setText(string2);
        edit_message1.setText(string3);
        edit_message2.setText(string4);
        edit_message3.setText(string5);
        edit_message4.setText(string6);
        edit_message5.setText(string7);
        edit_message6.setText(string8);

    }
}
