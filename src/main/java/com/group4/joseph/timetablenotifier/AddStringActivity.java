package com.group4.joseph.timetablenotifier;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;


public class AddStringActivity extends AppCompatActivity{

    //DBHandler dbHandler;

    EditText edit_message, edit_message1, edit_message2, edit_message3;
    EditText  edit_message4, edit_message5, edit_message6;

    Person user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_string);

        edit_message = (EditText)findViewById(R.id.edit_message);
        edit_message1 = (EditText)findViewById(R.id.edit_message1);
        edit_message2 = (EditText)findViewById(R.id.edit_message2);
        edit_message3 = (EditText)findViewById(R.id.edit_message3);
        edit_message4 = (EditText)findViewById(R.id.edit_message4);
        edit_message5 = (EditText)findViewById(R.id.edit_message5);
        edit_message6 = (EditText)findViewById(R.id.edit_message6);



        //dbHandler = new DBHandler(this, null, null, 1);
        printUpToEditText();
    }

    public void addString(View view){
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
        SharedPreferences.Editor editor =prefs.edit();
        editor.putString("string2",string2);
        editor.putString("string3",string3);
        editor.putString("string4",string4);
        editor.putString("string5",string5);
        editor.putString("string6",string6);
        editor.putString("string7",string7);
        editor.putString("string8",string8);
        editor.commit();

        Toast.makeText(AddStringActivity.this, "Modules saved", Toast.LENGTH_LONG).show();

        Person user;
        user = Person.getInstance();

        //If its a lecturer
        if(user.person == 1){
            //String str = String.valueOf(user.person);
            //Toast.makeText(AddStringActivity.this, str, Toast.LENGTH_SHORT).show();
            Intent lecturer_intent = new Intent(this, MainLecActivity.class);
            startActivity(lecturer_intent);
        }
        //If its a student
        else if(user.person == 0){
            //String str = String.valueOf(user.person);
            //Toast.makeText(AddStringActivity.this, str, Toast.LENGTH_SHORT).show();
            Intent student_intent = new Intent(this, AddLocationString.class);
            startActivity(student_intent);
        }

    }

    public void mainMenuReturn(View view){
        Person user;
        user = Person.getInstance();

        if(user.person == 1){
            //String str = String.valueOf(user.person);
            //Toast.makeText(AddStringActivity.this, str, Toast.LENGTH_SHORT).show();
            Intent lecturer_intent = new Intent(this, MainLecActivity.class);
            startActivity(lecturer_intent);
        }
        //If its a student
        else if(user.person == 0){
            //String str = String.valueOf(user.person);
            //Toast.makeText(AddStringActivity.this, str, Toast.LENGTH_SHORT).show();
            Intent student_intent = new Intent(this, MainActivity.class);
            startActivity(student_intent);
        }
    }

    public void printUpToEditText(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String string2= prefs.getString("string2","");
        String string3= prefs.getString("string3","");
        String string4= prefs.getString("string4","");
        String string5= prefs.getString("string5","");
        String string6= prefs.getString("string6","");
        String string7= prefs.getString("string7","");
        String string8= prefs.getString("string8","");

        edit_message.setText(string2);
        edit_message1.setText(string3);
        edit_message2.setText(string4);
        edit_message3.setText(string5);
        edit_message4.setText(string6);
        edit_message5.setText(string7);
        edit_message6.setText(string8);

    }
}
