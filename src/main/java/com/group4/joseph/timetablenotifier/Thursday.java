package com.group4.joseph.timetablenotifier;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

public class Thursday extends AppCompatActivity implements View.OnClickListener{

    DBHandler dbHandlerThursday;

    Button toWednesdayButton;
    Button toFridayButton;
    Button submitThursdayButton;
    Button mainMenuButton;
    Button settingsButton;

    EditText modInput9, locInput9, modInput10, locInput10;
    EditText modInput11, locInput11, modInput12, locInput12;
    EditText modInput1, locInput1, modInput2, locInput2;
    EditText modInput3, locInput3, modInput4, locInput4;
    EditText modInput5, locInput5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thursday);

        toWednesdayButton = (Button)findViewById(R.id.backbutton);
        toWednesdayButton.setOnClickListener(Thursday.this);
        toFridayButton = (Button)findViewById(R.id.forwardButton);
        toFridayButton.setOnClickListener(Thursday.this);
        //submitThursdayButton = (Button)findViewById(R.id.submitFridayButton);
        //submitThursdayButton.setOnClickListener(Thursday.this);
        mainMenuButton = (Button)findViewById(R.id.mainMenuButton);
        mainMenuButton.setOnClickListener(Thursday.this);
        settingsButton = (Button)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(Thursday.this);

        /*modInput9 = (EditText) findViewById(R.id.modInput9);
        locInput9 = (EditText) findViewById(R.id.locInput9);
        modInput10 = (EditText) findViewById(R.id.modInput10);
        locInput10 = (EditText) findViewById(R.id.locInput10);
        modInput11 = (EditText) findViewById(R.id.modInput11);
        locInput11 = (EditText) findViewById(R.id.locInput11);
        modInput12 = (EditText) findViewById(R.id.modInput12);
        locInput12 = (EditText) findViewById(R.id.locInput12);
        modInput1 = (EditText) findViewById(R.id.modInput1);
        locInput1 = (EditText) findViewById(R.id.locInput1);
        modInput2 = (EditText) findViewById(R.id.modInput2);
        locInput2 = (EditText) findViewById(R.id.locInput2);
        modInput3 = (EditText) findViewById(R.id.modInput3);
        locInput3 = (EditText) findViewById(R.id.locInput3);
        modInput4 = (EditText) findViewById(R.id.modInput4);
        locInput4 = (EditText) findViewById(R.id.locInput4);
        modInput5 = (EditText) findViewById(R.id.modInput5);
        locInput5 = (EditText) findViewById(R.id.locInput5);*/

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String string2= prefs.getString("string2","no id");
        String string3= prefs.getString("string3","no id");
        String string4= prefs.getString("string4","no id");
        String string5= prefs.getString("string5","no id");
        String string6= prefs.getString("string6","no id");
        String string7= prefs.getString("string7","no id");
        String string8= prefs.getString("string8","no id");

        // Array of choices
        String colors[] = {string2,string3,string4,string5,string6, string7,string8};

        Spinner spinner = (Spinner) findViewById(R.id.modInput9);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,colors);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // String selected = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

        Spinner spinner2 = (Spinner) findViewById(R.id.locInput9);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.spinners2_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);


        Spinner spinner3 = (Spinner) findViewById(R.id.modInput10);
        spinner3.setAdapter(adapter);
        Spinner spinner4 = (Spinner) findViewById(R.id.modInput11);
        spinner4.setAdapter(adapter);
        Spinner spinner5 = (Spinner) findViewById(R.id.modInput12);
        spinner5.setAdapter(adapter);
        Spinner spinner6 = (Spinner) findViewById(R.id.modInput1);
        spinner6.setAdapter(adapter);
        Spinner spinner7 = (Spinner) findViewById(R.id.modInput2);
        spinner7.setAdapter(adapter);
        Spinner spinner8 = (Spinner) findViewById(R.id.modInput3);
        spinner8.setAdapter(adapter);
        Spinner spinner9 = (Spinner) findViewById(R.id.modInput4);
        spinner9.setAdapter(adapter);
        Spinner spinner10 = (Spinner) findViewById(R.id.modInput5);
        spinner10.setAdapter(adapter);


        Spinner spinner13 = (Spinner) findViewById(R.id.locInput10);
        spinner13.setAdapter(adapter2);
        Spinner spinner14 = (Spinner) findViewById(R.id.locInput11);
        spinner14.setAdapter(adapter2);
        Spinner spinner15 = (Spinner) findViewById(R.id.locInput12);
        spinner15.setAdapter(adapter2);
        Spinner spinner16 = (Spinner) findViewById(R.id.locInput1);
        spinner16.setAdapter(adapter2);
        Spinner spinner17 = (Spinner) findViewById(R.id.locInput2);
        spinner17.setAdapter(adapter2);
        Spinner spinner18 = (Spinner) findViewById(R.id.locInput3);
        spinner18.setAdapter(adapter2);
        Spinner spinner19 = (Spinner) findViewById(R.id.locInput4);
        spinner19.setAdapter(adapter2);
        Spinner spinner110 = (Spinner) findViewById(R.id.locInput5);
        spinner110.setAdapter(adapter2);


        dbHandlerThursday = new DBHandler(this, null, null, 1);

        //Toast.makeText(Thursday.this, "Press SUBMIT to save the timetable", Toast.LENGTH_LONG).show();

        printToEditText();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.forwardButton:
                final Intent friday_intent = new Intent(this, Friday.class);
                AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(
                        Thursday.this);
                alertDialog1.setTitle("Save?");

                // Setting Dialog Message
                alertDialog1.setMessage("Would you like to save any changes?");
                alertDialog1.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(),
                                        "Changes saved", Toast.LENGTH_LONG)
                                        .show();
                                dataSaveThursday();
                                startActivity(friday_intent);
                            }
                        });

                // Setting Negative "NO" Btn
                alertDialog1.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(),
                                        "Changes not saved", Toast.LENGTH_LONG)
                                        .show();
                                dialog.cancel();
                                startActivity(friday_intent);

                            }
                        });
                alertDialog1.show();

                //Intent friday_intent = new Intent(this, Friday.class);
                //startActivity(friday_intent);
                break;

            case R.id.backbutton:

                final Intent wednesday_intent = new Intent(this, Wednesday.class);
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        Thursday.this);
                alertDialog2.setTitle("Save?");

                // Setting Dialog Message
                alertDialog2.setMessage("Would you like to save any changes?");
                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(),
                                        "Changes saved", Toast.LENGTH_LONG)
                                        .show();
                                dataSaveThursday();
                                startActivity(wednesday_intent);
                            }
                        });

                // Setting Negative "NO" Btn
                alertDialog2.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                Toast.makeText(getApplicationContext(),
                                        "Changes not saved", Toast.LENGTH_LONG)
                                        .show();
                                dialog.cancel();
                                startActivity(wednesday_intent);

                            }
                        });
                alertDialog2.show();
                //Intent wednesday_intent = new Intent(this, Wednesday.class);
                //startActivity(wednesday_intent);
                break;
            case R.id.mainMenuButton:
                Intent menu_intent = new Intent(this, MainActivity.class);
                startActivity(menu_intent);
                break;

            //case R.id.submitFridayButton:


            //dbHandler.insertModuleTimeMonday(mondayList);

            //    Toast.makeText(Thursday.this,"SAVED DATA", Toast.LENGTH_LONG).show();
            //    break;

            case R.id.settingsButton:
                Intent settings_intent = new Intent(this, Settings.class);
                startActivity(settings_intent);
                break;

        }
    }

    public void printToEditText() {
        Cursor c = dbHandlerThursday.readAllThursday();
        String str = "@";
        int length = 0;
        int position = -1;
        String module, location;

        c.moveToFirst();
        while (!c.isAfterLast()) {

            int dbint = c.getInt(c.getColumnIndex("time"));
            String dbString = c.getString(c.getColumnIndex("modulelocation"));
            int spinner_position = c.getInt(c.getColumnIndex("spinner_pos"));
            int spinner2_position = c.getInt(c.getColumnIndex("spinner_pos_2"));

            switch (dbint) {
                case 9:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner = (Spinner) findViewById(R.id.modInput9);
                    Spinner spinner2 = (Spinner) findViewById(R.id.locInput9);
                    spinner2.setSelection(spinner2_position);
                    spinner.setSelection(spinner_position);

                    c.moveToNext();
                    break;

                case 10:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner3 = (Spinner) findViewById(R.id.modInput10);
                    spinner3.setSelection(spinner_position);
                    Spinner spinner13 = (Spinner) findViewById(R.id.locInput10);
                    spinner13.setSelection(spinner2_position);
                    //modInput10.setText(module);
                    //locInput10.setText(location);
                    c.moveToNext();
                    break;

                case 11:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner4 = (Spinner) findViewById(R.id.modInput11);
                    spinner4.setSelection(spinner_position);
                    Spinner spinner14 = (Spinner) findViewById(R.id.locInput11);
                    spinner14.setSelection(spinner2_position);

                    c.moveToNext();
                    break;

                case 12:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner5 = (Spinner) findViewById(R.id.modInput12);
                    spinner5.setSelection(spinner_position);
                    Spinner spinner15 = (Spinner) findViewById(R.id.locInput12);
                    spinner15.setSelection(spinner2_position);

                    c.moveToNext();
                    break;

                case 13:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner6 = (Spinner) findViewById(R.id.modInput1);
                    spinner6.setSelection(spinner_position);
                    Spinner spinner16 = (Spinner) findViewById(R.id.locInput1);
                    spinner16.setSelection(spinner2_position);

                    c.moveToNext();
                    break;

                case 14:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner7 = (Spinner) findViewById(R.id.modInput2);
                    spinner7.setSelection(spinner_position);
                    Spinner spinner17 = (Spinner) findViewById(R.id.locInput2);
                    spinner17.setSelection(spinner2_position);

                    c.moveToNext();
                    break;

                case 15:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner8 = (Spinner) findViewById(R.id.modInput3);
                    spinner8.setSelection(spinner_position);
                    Spinner spinner18 = (Spinner) findViewById(R.id.locInput3);
                    spinner18.setSelection(spinner2_position);

                    c.moveToNext();
                    break;

                case 16:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner9 = (Spinner) findViewById(R.id.modInput4);
                    spinner9.setSelection(spinner_position);
                    Spinner spinner19 = (Spinner) findViewById(R.id.locInput4);
                    spinner19.setSelection(spinner2_position);

                    c.moveToNext();
                    break;

                case 17:
                    str = "@";
                    length = dbString.length();
                    position = dbString.indexOf(str);
                    module = dbString.substring(0, position);
                    location = dbString.substring(position + 1, length);

                    Spinner spinner10 = (Spinner) findViewById(R.id.modInput5);
                    spinner10.setSelection(spinner_position);
                    Spinner spinner110 = (Spinner) findViewById(R.id.locInput5);
                    spinner110.setSelection(spinner2_position);

                    c.moveToNext();
                    break;


            }

        }
    }

    public void dataSaveThursday(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        dbHandlerThursday.deleteModuleThursday();     //Delete the DB just before it gets updated again, this allows us to update it
        Spinner spinner = (Spinner) findViewById(R.id.modInput9);
        Spinner spinner2 = (Spinner) findViewById(R.id.locInput9);
        int spinner_pos9 = spinner.getSelectedItemPosition();
        int spinner2_pos9 = spinner2.getSelectedItemPosition();
        String module9 = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        String location9 = spinner2.getItemAtPosition(spinner2.getSelectedItemPosition()).toString();
        String module_location_9 = module9 + "@" + location9;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_9, 9, spinner_pos9, spinner2_pos9);
        if (day == Calendar.THURSDAY) {
            callNotification(8, 50, 0, module9, location9);
        }

        Spinner spinner3 = (Spinner) findViewById(R.id.modInput10);
        Spinner spinner13 = (Spinner) findViewById(R.id.locInput10);
        int spinner_pos10 = spinner3.getSelectedItemPosition();
        int spinner2_pos10 = spinner13.getSelectedItemPosition();
        String module10 = spinner3.getItemAtPosition(spinner3.getSelectedItemPosition()).toString();
        String location10 = spinner13.getItemAtPosition(spinner13.getSelectedItemPosition()).toString();
        String module_location_10 = module10 + "@" + location10;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_10, 10, spinner_pos10, spinner2_pos10);
        if (day == Calendar.THURSDAY) {
            callNotification(9, 50, 0, module10, location10);
        }

        Spinner spinner4 = (Spinner) findViewById(R.id.modInput11);
        Spinner spinner14 = (Spinner) findViewById(R.id.locInput11);
        int spinner_pos11 = spinner4.getSelectedItemPosition();
        int spinner2_pos11 = spinner14.getSelectedItemPosition();
        String module11 = spinner4.getItemAtPosition(spinner4.getSelectedItemPosition()).toString();
        String location11 = spinner14.getItemAtPosition(spinner14.getSelectedItemPosition()).toString();
        String module_location_11 = module11 + "@" + location11;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_11, 11, spinner_pos11, spinner2_pos11);
        if (day == Calendar.THURSDAY) {
            callNotification(10, 50, 0, module11, location11);
        }
        Spinner spinner5 = (Spinner) findViewById(R.id.modInput12);
        Spinner spinner15 = (Spinner) findViewById(R.id.locInput12);
        int spinner_pos12 = spinner5.getSelectedItemPosition();
        int spinner2_pos12 = spinner15.getSelectedItemPosition();
        String module12 = spinner5.getItemAtPosition(spinner5.getSelectedItemPosition()).toString();
        String location12 = spinner15.getItemAtPosition(spinner15.getSelectedItemPosition()).toString();
        String module_location_12 = module12 + "@" + location12;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_12, 12, spinner_pos12, spinner2_pos12);
        if (day == Calendar.THURSDAY) {
            callNotification(11, 50, 0, module12, location12);
        }

        Spinner spinner6 = (Spinner) findViewById(R.id.modInput1);
        Spinner spinner16 = (Spinner) findViewById(R.id.locInput1);
        int spinner_pos1 = spinner6.getSelectedItemPosition();
        int spinner2_pos1 = spinner16.getSelectedItemPosition();
        String module1 = spinner6.getItemAtPosition(spinner6.getSelectedItemPosition()).toString();
        String location1 = spinner16.getItemAtPosition(spinner16.getSelectedItemPosition()).toString();
        String module_location_1 = module1 + "@" + location1;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_1, 13, spinner_pos1, spinner2_pos1);       //Use 13 (13:00) for time from 1-5pm
        if (day == Calendar.THURSDAY) {
            callNotification(12, 50, 0, module1, location1);
        }

        Spinner spinner7 = (Spinner) findViewById(R.id.modInput2);
        Spinner spinner17 = (Spinner) findViewById(R.id.locInput2);
        int spinner_pos2 = spinner7.getSelectedItemPosition();
        int spinner2_pos2 = spinner17.getSelectedItemPosition();
        String module2 = spinner7.getItemAtPosition(spinner7.getSelectedItemPosition()).toString();
        String location2 = spinner17.getItemAtPosition(spinner17.getSelectedItemPosition()).toString();
        String module_location_2 = module2 + "@" + location2;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_2, 14, spinner_pos2, spinner2_pos2);
        if (day == Calendar.THURSDAY) {
            callNotification(13, 50, 0, module2, location2);
        }

        Spinner spinner8 = (Spinner) findViewById(R.id.modInput3);
        Spinner spinner18 = (Spinner) findViewById(R.id.locInput3);
        int spinner_pos3 = spinner8.getSelectedItemPosition();
        int spinner2_pos3 = spinner18.getSelectedItemPosition();
        String module3 = spinner8.getItemAtPosition(spinner8.getSelectedItemPosition()).toString();
        String location3 = spinner18.getItemAtPosition(spinner18.getSelectedItemPosition()).toString();
        String module_location_3 = module3 + "@" + location3;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_3, 15, spinner_pos3, spinner2_pos3);
        if (day == Calendar.THURSDAY) {
            callNotification(14, 50, 0, module3, location3);
        }

        Spinner spinner9 = (Spinner) findViewById(R.id.modInput4);
        Spinner spinner19 = (Spinner) findViewById(R.id.locInput4);
        int spinner_pos4 = spinner9.getSelectedItemPosition();
        int spinner2_pos4 = spinner19.getSelectedItemPosition();
        String module4 = spinner9.getItemAtPosition(spinner9.getSelectedItemPosition()).toString();
        String location4 = spinner19.getItemAtPosition(spinner19.getSelectedItemPosition()).toString();
        String module_location_4 = module4 + "@" + location4;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_4, 16, spinner_pos4, spinner2_pos4);
        if (day == Calendar.THURSDAY) {
            callNotification(15, 50, 0, module4, location4);
        }

        Spinner spinner10 = (Spinner) findViewById(R.id.modInput5);
        Spinner spinner110 = (Spinner) findViewById(R.id.locInput5);
        int spinner_pos5 = spinner10.getSelectedItemPosition();
        int spinner2_pos5 = spinner110.getSelectedItemPosition();
        String module5 = spinner10.getItemAtPosition(spinner10.getSelectedItemPosition()).toString();
        String location5 = spinner110.getItemAtPosition(spinner110.getSelectedItemPosition()).toString();
        String module_location_5 = module5 + "@" + location5;
        //dbHandler.deleteRow( module_location_9);
        dbHandlerThursday.addModuleThursday(module_location_5, 17, spinner_pos5, spinner2_pos5);
        if (day == Calendar.THURSDAY) {
            callNotification(16, 50, 0, module5, location5);
        }
    }
    public void callNotification(int hour, int min, int sec, String module10, String location10 ){
        //new NotificationReceiver(id);
        /*Random rand = new Random();

        int  n = rand.nextInt(1000) + 1;
        int notify_id = n;
        */
        int n= hour+107;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, sec);

        //String extra = module + " - " + location;
        Intent intentNotif = new Intent(this, NotificationReceiver.class);
        intentNotif.putExtra("NOTIFY_ID", n );
        intentNotif.putExtra("MODULE", module10 );
        intentNotif.putExtra("LOCATION", location10 );
        //startActivity(intentNotif);9
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),n,intentNotif,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        //RTC_WAKEUP makes sure that the alarm will be triggered even if the device goes into sleep mode
        //calendar.getTimeInMillis() will be the time that we want the alarm to go off at
        //alarmManager.INTERVAL_DAY is the interval, how often it should get called, INTERVAL_DAY makes it show up on a daily basis
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY*7,pendingIntent);
    }
}
