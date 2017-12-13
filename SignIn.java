package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class SignIn extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private SignInButton SignIn;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    public static TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);


        SignIn = (SignInButton)findViewById(R.id.bn_login); // Find the Sign in button
        SignIn.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("721206072569-9sc13agj89a8tp3649ch3eakpt0tfflv.apps.googleusercontent.com") // had to hardcode the web client ID
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();
    }


    // Google Sign-in API button
    // When you click it, signIn() will run
    private void signIn()
    {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.bn_login: // If the Sign In button is pressed...
                signIn(); // Run Sign In function for Google OAuth
                break;
        }
    }

    // Handle sign in failure
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // Handle successful sign in
    private void handleResult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName(); // Grab username from Google OAuth API
            String email = account.getEmail(); // Grab email from Google OAuth API
            Intent myIntent = new Intent(SignIn.this, MainActivity.class);
            myIntent.putExtra("savedUser", name); // Store the username from Google and pass to the next activity
            myIntent.putExtra("savedEmail", email); // Store the email from Google and pass to the next activity
            startActivity(myIntent); // Start MainActivity
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }


}
