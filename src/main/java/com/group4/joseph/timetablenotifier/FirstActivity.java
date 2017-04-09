package com.group4.joseph.timetablenotifier;

        import android.content.Intent;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.Toast;

        import com.google.android.gms.auth.api.Auth;
        import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.auth.api.signin.GoogleSignInResult;
        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.SignInButton;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthCredential;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.GoogleAuthProvider;

public class FirstActivity extends AppCompatActivity{

    //Google Sign In API stuff
    CheckBox studentCheckbox, lecturerCheckbox;
    private SignInButton mGoogleButton;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;       //This is our GoogleSignIn API reference
    private FirebaseAuth mAuth;
    private static final String TAG = "MAIN_ACTIVITY";
    private FirebaseAuth.AuthStateListener mAuthListener;

    //Class for the user
    Person user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        user = Person.getInstance();
        user.person = -1;

        /*studentButton = (Button)findViewById(R.id.inputLocationsButton);
        studentButton.setOnClickListener(FirstActivity.this);

        lecturerButton = (Button)findViewById(R.id.lecturerBut);
        lecturerButton.setOnClickListener(FirstActivity.this);*/

        studentCheckbox = (CheckBox)findViewById(R.id.studentCheckbox);
        lecturerCheckbox = (CheckBox)findViewById(R.id.lecturerCheckbox);



        mGoogleButton = (SignInButton)findViewById(R.id.googleButton);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //If the user is authorized, then open the AccountActivity
                if(firebaseAuth.getCurrentUser() != null){
                    if(user.person == 1){
                        Toast.makeText(FirstActivity.this, "Signed in as " + user.email, Toast.LENGTH_SHORT).show();
                        Intent lec_intent = new Intent(FirstActivity.this, MainLecActivity.class);
                        startActivity(lec_intent);
                    }
                    else if(user.person == 0){
                        Toast.makeText(FirstActivity.this, "Signed in as " + user.email, Toast.LENGTH_SHORT).show();
                        Intent student_intent = new Intent(FirstActivity.this, MainActivity.class);
                        startActivity(student_intent);
                    }
                    else{
                        Toast.makeText(FirstActivity.this, "Please select lecturer or student", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };

        // Configure Google Sign In
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
                        Toast.makeText(FirstActivity.this, "Failed Connection to Google API", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)       //gso is our GoogleSigninOption above
                .build();

        mGoogleButton.setOnClickListener(new View.OnClickListener() {   //When the user clicks the button, it'll call the sign in method
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

    }

    /*@Override
    public void onClick(View v){
        Person user;
        user=Person.getInstance();

        switch(v.getId()){

            case R.id.lecturerBut:
                Intent lecturer_intent = new Intent(this, MainLecActivity.class);
                startActivity(lecturer_intent);
                user.person = 1;
                break;

            case R.id.inputLocationsButton:
                Intent main_activity = new Intent(this, MainActivity.class);
                startActivity(main_activity);
                user.person = 0;
                break;

        }
    }*/

    public void onCheckboxClicked(View view) {

        //Person user;
        user=Person.getInstance();

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.lecturerCheckbox:
                if (checked){
                    user.person = 1;
                    //String str = String.valueOf(user.person);
                    //Toast.makeText(FirstActivity.this, str, Toast.LENGTH_SHORT).show();
                    //Intent lecturer_intent = new Intent(this, MainLecActivity.class);
                    //startActivity(lecturer_intent);
                }

                break;

            case R.id.studentCheckbox:
                if (checked) {
                    user.person = 0;
                    //Intent main_activity = new Intent(this, MainActivity.class);
                    //startActivity(main_activity);
                }

                break;

        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {    //AFter the user selects something, this will be the returning result
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();        //The account that is selected
                String user_email = account.getEmail();
                user.email = user_email;

                //Toast.makeText(FirstActivity.this, "Signed in as " + user_email, Toast.LENGTH_SHORT).show();



                firebaseAuthWithGoogle(account);    //Selected account gets added to the Firebase Auth DB
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(FirstActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

}
