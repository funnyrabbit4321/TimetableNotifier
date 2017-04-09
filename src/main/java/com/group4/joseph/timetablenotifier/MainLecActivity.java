package com.group4.joseph.timetablenotifier;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainLecActivity extends AppCompatActivity implements View.OnClickListener{

    //Google Sign In log out stuff
    private Button mLogOutButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    //Person class
    Person user;

    Button timetableButton, notificationButton, addModules, logOutButton, weatherLecButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lec);

        timetableButton = (Button)findViewById(R.id.timetableButton);
        timetableButton.setOnClickListener(MainLecActivity.this);

        notificationButton = (Button)findViewById(R.id.notificationButton);
        notificationButton.setOnClickListener(MainLecActivity.this);

        addModules = (Button)findViewById(R.id.addModules);
        addModules.setOnClickListener(MainLecActivity.this);

        logOutButton = (Button)findViewById(R.id.logOutButton);

        weatherLecButton = (Button)findViewById(R.id.weatherLecButton);
        weatherLecButton.setOnClickListener(MainLecActivity.this);


        //*** From here down is Log Out stuff ***
        mAuth = FirebaseAuth.getInstance();     //get instance of the auth DB
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //If the current user is null, the user is signed out, go back to sign in screen...
                if(firebaseAuth.getCurrentUser() == null){
                    Intent logOut_intent = new Intent(MainLecActivity.this, FirstActivity.class);
                    startActivity(logOut_intent);
                }
            }
        };

        //This is setting the API client
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // This is setting the Google API client
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {    //if something goes wrong
                        Toast.makeText(MainLecActivity.this, "Failed Connection to Google API", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)       //gso is our GoogleSigninOption above
                .build();

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person user;
                user = Person.getInstance();
                Toast.makeText(MainLecActivity.this, user.email + " is logged out", Toast.LENGTH_SHORT).show();

                //Firebase sign out
                mAuth.signOut();

                // Google sign out
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(@NonNull Status status) {
                                //updateUI(null);
                            }
                        });

                user.person = -1;

                //Go back to first screen
                Intent logOut_intent = new Intent(MainLecActivity.this, FirstActivity.class);
                startActivity(logOut_intent);


            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.timetableButton:
                Intent timetable_intent = new Intent(this, Monday.class);
                startActivity(timetable_intent);
                break;

            case R.id.notificationButton:
                Intent notification_intent = new Intent(this, LecNotifications.class);
                startActivity(notification_intent);
                break;

            case R.id.addModules:
                //Toast.makeText(MainLecActivity.this, user.email, Toast.LENGTH_SHORT).show();
                Intent addmod_intent = new Intent(this, AddStringActivity.class);
                startActivity(addmod_intent);
                break;

            case R.id.weatherLecButton:
                Intent weather_intent = new Intent(this, Weather.class);
                startActivity(weather_intent);
                break;
        }
    }
}

