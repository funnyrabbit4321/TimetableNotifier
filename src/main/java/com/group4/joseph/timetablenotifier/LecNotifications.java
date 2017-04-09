package com.group4.joseph.timetablenotifier;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LecNotifications extends AppCompatActivity{

    //EditText modInput;
    //DBHandler dbHandlerLec;
    Button sendToFirebase;
   // private FirebaseDatabase mDatabase;
    //Person user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lec_notifications);

        //Send Button
        sendToFirebase = (Button)findViewById(R.id.sendToFirebase);
        //sendToFirebase.setOnClickListener(LecNotifications.this);

        //Spinner initialisation
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String string2 = prefs.getString("string2", "no id");
        String string3 = prefs.getString("string3", "no id");
        String string4 = prefs.getString("string4", "no id");
        String string5 = prefs.getString("string5", "no id");
        String string6 = prefs.getString("string6", "no id");
        String string7 = prefs.getString("string7", "no id");
        String string8 = prefs.getString("string8", "no id");

        String string9 = "09:00";
        String string10 = "10:00";
        String string11 = "11:00";
        String string12 = "12:00";
        String string13 = "13:00";
        String string14 = "14:00";
        String string15 = "15:00";
        String string16 = "16:00";
        String string17 = "17:00";

        String string18 = "Monday";
        String string19 = "Tuesday";
        String string20 = "Wednesday";
        String string21 = "Thursday";
        String string22 = "Friday";

        String colors[] = {string2, string3, string4, string5, string6, string7, string8};
        String colors1[] = {string9, string10, string11, string12, string13, string14, string15, string16, string17};
        String colors2[] = {string18, string19, string20, string21, string22};
        // String colors2[] = {string2, string3, string4, string5, string6, string7, string8};

        Spinner spinner1 = (Spinner) findViewById(R.id.Module);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, colors);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner1.setAdapter(adapter);
        // String selected = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

        Spinner spinner2 = (Spinner) findViewById(R.id.Time);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,colors1);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = (Spinner) findViewById(R.id.Day);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,colors2);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner3.setAdapter(adapter3);

        //dbHandlerLec = new DBHandler(this, null, null, 1);


        /*sendToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LecNotifications.this, "test", Toast.LENGTH_SHORT).show();
            }
        });*/


        //Writing to firebase


        //final DatabaseReference myRef = mDatabase.getReference("cancellations");    //This will always add to the cancellations directory
        //final DatabaseReference myRef = mDatabase.getReference();       //This will point straight to the root directory

        sendToFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Spinner spinner1 = (Spinner)findViewById(R.id.Module);
                Spinner spinner2 = (Spinner)findViewById(R.id.Time);
                Spinner spinner3 = (Spinner)findViewById(R.id.Day);

                //Store selected spinner items to variables
                final String selected_module = spinner1.getItemAtPosition(spinner1.getSelectedItemPosition()).toString();
                final String selected_time = spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();
                final String selected_day = spinner3.getItemAtPosition(spinner3.getSelectedItemPosition()).toString();

                //Toast.makeText(LecNotifications.this, selected_module + " " + selected_time + " " + selected_day + " " + user.email, Toast.LENGTH_SHORT).show();

                //HashMap<String, String> dataMap = new HashMap<String, String>();
                //dataMap.put("Module", selected_module);
                //dataMap.put("Time", selected_time);
                //dataMap.put("Day", selected_day);
                //dataMap.put("Email", user.email);

                String notification_str = selected_module + "-" + selected_time + "-" + selected_day;
                final FirebaseDatabase mDatabase;
                mDatabase = FirebaseDatabase.getInstance();
                DatabaseReference myRef = mDatabase.getReference();
                //Toast.makeText(LecNotifications.this, "Made it to here", Toast.LENGTH_LONG).show();
                myRef.push().setValue(notification_str).addOnCompleteListener(new OnCompleteListener<Void>() {   //OnCompleteListener will check if our task was completed successfully
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Person user;
                        user=Person.getInstance();
                        if(task.isSuccessful()){
                            //Toast.makeText(LecNotifications.this, "Data for " + user.email + " stored successfully", Toast.LENGTH_LONG).show();
                            Toast.makeText(LecNotifications.this, "Lecture for " + selected_module + " cancelled at " + selected_time + " on " + selected_day, Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LecNotifications.this, "Error storing data for" + user.email, Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });


    }

    /*public void onClick(View view){

        switch (view.getId()){
            case R.id.sendToFirebase:

                Spinner spinner1 = (Spinner) findViewById(R.id.Module);
                Spinner spinner2 = (Spinner) findViewById(R.id.Time);
                Spinner spinner3 = (Spinner) findViewById(R.id.Day);

                //Get position of item on each spinner
                int spinner1_pos = spinner1.getSelectedItemPosition();
                int spinner2_pos = spinner2.getSelectedItemPosition();
                int spinner3_pos = spinner3.getSelectedItemPosition();

                //Store selected spinned items to variables
                String selected_module = spinner1.getItemAtPosition(spinner1.getSelectedItemPosition()).toString();
                String selected_time = spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();
                String selected_day = spinner2.getItemAtPosition(spinner3.getSelectedItemPosition()).toString();


                String name = nameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String age = "20";
                //mDatabase.child(key).setValue(name);
                //myRef.setValue(name);
                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Name", name);
                dataMap.put("Email", email);
                dataMap.put("Age", age);
                myRef.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {   //OnCompleteListener will check if our task was completed successfully
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Data stored successfully", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error storing data", Toast.LENGTH_LONG).show();

                        }
                    }
                });





        }


    }*/





}