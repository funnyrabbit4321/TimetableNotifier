package com.group4.joseph.timetablenotifier;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Cancellations extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private ArrayList<String> mUsernames = new ArrayList<>();
    private ArrayList<String> mKeys = new ArrayList<>();
    private ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancellations);

        userList = (ListView)findViewById(R.id.userList);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsernames);
        userList.setAdapter(arrayAdapter);


        mDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = mDatabase.getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {     //This is called when the app is created ??
                String value = dataSnapshot.getValue(String.class);     //The DB values will be stored in value

                //int instance = new CancelCheck().stringcheckk(value);

                //if (instance == 50) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Cancellations.this);
                String string2= prefs.getString("string2","");
                String string3= prefs.getString("string3","");
                String string4= prefs.getString("string4","");
                String string5= prefs.getString("string5","");
                String string6= prefs.getString("string6","");
                String string7= prefs.getString("string7","");
                String string8= prefs.getString("string8","");

                String[] parts = value.split("-");
                String module = parts[0];

                int has_subject = 0;

                if(module.equals(string2)){
                    has_subject = 1;
                }
                else if(module.equals(string3)){
                    has_subject = 1;
                }
                else if(module.equals(string4)){
                    has_subject = 1;
                }
                else if(module.equals(string5)){
                    has_subject = 1;
                }
                else if(module.equals(string6)){
                    has_subject = 1;
                }
                else if(module.equals(string7)){
                    has_subject = 1;
                }
                else if(module.equals(string8)){
                    has_subject = 1;
                }

                if(has_subject == 1) {

                    mUsernames.add(value);                  //Add the value to the list

                    String key = dataSnapshot.getKey();
                    mKeys.add(key);

                    arrayAdapter.notifyDataSetChanged();    //Notify the adapter that the list has changed
                }

                /*else {

                        Toast.makeText(Cancellations.this, "No lecture cancellations", Toast.LENGTH_SHORT).show();

                }*/
                //}
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {   //This will run when any of the data in the DB gets changed
                String changed_value = dataSnapshot.getValue(String.class);     //Get value of changed item
                String key = dataSnapshot.getKey();     //Get key of changed item

                int index = mKeys.indexOf(key);         //Get index of this key

                mUsernames.set(index, changed_value);           //Update the index with the new value

                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {     //Runs when data is removed from the DB

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {     //Runs when data is moved from the DB

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {      //Runs when there is an error

            }
        });
    }
}
