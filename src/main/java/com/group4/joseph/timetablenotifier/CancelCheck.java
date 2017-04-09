package com.group4.joseph.timetablenotifier;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;


public class CancelCheck extends AppCompatActivity {

    DBHandler dbHandlercheck;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public int stringcheckk(String valuee) {
        /*String daye;
        String timee;
        if (valuee != "1") {
            dbHandlercheck = new DBHandler(this, null, null, 1);

            if (valuee.contains("-")) {
                String[] parts = valuee.split("-");
                valuee = parts[0];
                timee = parts[1];
                daye = parts[1];

                String[] taim = timee.split(":");
                int sti = Integer.parseInt(taim[1]);


                if (daye == "Monday") {
                    Cursor c = dbHandlercheck.readAllMonday();

                    c.moveToFirst();
                    while (!c.isAfterLast()) {

                        int dbint = c.getInt(c.getColumnIndex("time"));
                        String modloc = c.getString(c.getColumnIndex("module_location"));

                        String[] modlocc = modloc.split("@");

                        if (modlocc[0] == valuee && dbint == sti) {
                            return 50;
                        } else {
                            c.moveToNext();
                        }
                        break;
                    }
                }

                if (daye == "Tuesday") {
                    Cursor c = dbHandlercheck.readAllTuesday();

                    c.moveToFirst();
                    while (!c.isAfterLast()) {

                        int dbint = c.getInt(c.getColumnIndex("time"));
                        String modloc = c.getString(c.getColumnIndex("module_location"));

                        String[] modlocc = modloc.split("-");

                        if (modlocc[1] == valuee && dbint == sti) {
                            return 50;
                        } else {
                            c.moveToNext();
                        }
                        break;
                    }
                }

                if (daye == "Wednesday") {
                    Cursor c = dbHandlercheck.readAllWednesday();

                    c.moveToFirst();
                    while (!c.isAfterLast()) {

                        int dbint = c.getInt(c.getColumnIndex("time"));
                        String modloc = c.getString(c.getColumnIndex("module_location"));

                        String[] modlocc = modloc.split("-");

                        if (modlocc[1] == valuee && dbint == sti) {
                            return 50;
                        } else {
                            c.moveToNext();
                        }
                        break;
                    }
                }

                if (daye == "Thursday") {
                    Cursor c = dbHandlercheck.readAllThursday();

                    c.moveToFirst();
                    while (!c.isAfterLast()) {

                        int dbint = c.getInt(c.getColumnIndex("time"));
                        String modloc = c.getString(c.getColumnIndex("module_location"));

                        String[] modlocc = modloc.split("-");

                        if (modlocc[1] == valuee && dbint == sti) {
                            return 50;
                        } else {
                            c.moveToNext();
                        }
                        break;
                    }
                }
                if (daye == "Friday") {
                    Cursor c = dbHandlercheck.readAllFriday();

                    c.moveToFirst();
                    while (!c.isAfterLast()) {

                        int dbint = c.getInt(c.getColumnIndex("time"));
                        String modloc = c.getString(c.getColumnIndex("module_location"));

                        String[] modlocc = modloc.split("-");

                        if (modlocc[1] == valuee && dbint == sti) {
                            return 50;
                        } else {
                            c.moveToNext();
                        }
                        break;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("String " + valuee + " does not contain -");
        }
        return 0;*/

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String string2= prefs.getString("string2","");
        String string3= prefs.getString("string3","");
        String string4= prefs.getString("string4","");
        String string5= prefs.getString("string5","");
        String string6= prefs.getString("string6","");
        String string7= prefs.getString("string7","");
        String string8= prefs.getString("string8","");

        String[] parts = valuee.split("-");
        String module = parts[0];

        if(module.equals(string2)){
            return 50;
        }
        else if(module.equals(string3)){
            return 50;
        }
        else if(module.equals(string4)){
            return 50;
        }
        else if(module.equals(string5)){
            return 50;
        }
        else if(module.equals(string6)){
            return 50;
        }
        else if(module.equals(string7)){
            return 50;
        }
        else if(module.equals(string8)){
            return 50;
        }

        else {
            throw new IllegalArgumentException("String " + valuee + " does not contain -");
        }

    }



}

