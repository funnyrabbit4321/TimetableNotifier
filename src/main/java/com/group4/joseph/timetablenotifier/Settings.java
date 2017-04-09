package com.group4.joseph.timetablenotifier;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class Settings extends AppCompatActivity implements View.OnClickListener {

    private int PICK_IMAGE_REQUEST = 1;
    Button inputModulesButton, inputLocationsButton, backgroundButton, weatherButton, menuButton, logOutStudentButton;
    ImageView imageView;
    RelativeLayout activity_settings;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inputModulesButton = (Button)findViewById(R.id.inputModulesButton);
        inputModulesButton.setOnClickListener(Settings.this);

        inputLocationsButton = (Button)findViewById(R.id.inputLocationsButton);
        inputLocationsButton.setOnClickListener(Settings.this);

        backgroundButton = (Button)findViewById(R.id.backgroundButton);
        backgroundButton.setOnClickListener(Settings.this);

        weatherButton = (Button)findViewById(R.id.weatherButton);
        weatherButton.setOnClickListener(Settings.this);

        menuButton = (Button)findViewById(R.id.menuButton);
        menuButton.setOnClickListener(Settings.this);

        //imageView = (ImageView)findViewById(R.id.imageView);

        logOutStudentButton = (Button)findViewById(R.id.logOutStudentButton);

        //*** From here down is Log Out stuff ***
        mAuth = FirebaseAuth.getInstance();     //get instance of the auth DB
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //If the current user is null, the user is signed out, go back to sign in screen...
                if(firebaseAuth.getCurrentUser() == null){
                    Intent logOut_intent = new Intent(Settings.this, FirstActivity.class);
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
                        Toast.makeText(Settings.this, "Failed Connection to Google API", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)       //gso is our GoogleSigninOption above
                .build();

        logOutStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person user;
                user = Person.getInstance();
                Toast.makeText(Settings.this, user.email + " is logged out", Toast.LENGTH_SHORT).show();

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
                Intent logOut_intent = new Intent(Settings.this, FirstActivity.class);
                startActivity(logOut_intent);


            }
        });

    }

    @Override
    public void onClick(View v){
        switch(v.getId()) {
            case R.id.inputModulesButton:
                Intent modules_intent = new Intent(this, AddStringActivity.class);
                startActivity(modules_intent);
                break;

            case R.id.inputLocationsButton:
                Intent locations_intent = new Intent(this, AddLocationString.class);
                startActivity(locations_intent);
                break;

            case R.id.backgroundButton:
                Intent intent = new Intent();
                // Show only images, no videos or anything else
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // Always show the chooser (if there are multiple options available)
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                Toast.makeText(Settings.this, "Change the background", Toast.LENGTH_SHORT).show();
                break;

            case R.id.weatherButton:
                Intent weather_intent = new Intent(Settings.this, Weather.class);
                startActivity(weather_intent);
                break;

            case R.id.menuButton:
                Intent menu_intent = new Intent(this, MainActivity.class);
                startActivity(menu_intent);
                break;



        }
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                //ImageView imageView = (ImageView) findViewById(R.id.imageView);
                activity_settings = (RelativeLayout)findViewById(R.id.activity_settings);
                //imageView.setImageBitmap(bitmap);
                BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
                activity_settings.setBackground(ob);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
