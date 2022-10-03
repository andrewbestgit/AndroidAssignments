package com.example.AndroidAssignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i(ACTIVITY_NAME, "In onCreate()");

        EditText email_field = findViewById(R.id.email_field);
        EditText password_field = findViewById(R.id.password_field);
        Button login_button = findViewById(R.id.login_button);
        SharedPreferences s_prefs = getSharedPreferences("DefaultEmail", MODE_PRIVATE);

        // set default email
        String default_email = s_prefs.getString("DefaultEmail", "email@domain.com");
        email_field.setText(default_email);

        //login_button onClick()
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.i(ACTIVITY_NAME, "login_button Pressed()");
                String email_text = email_field.getText().toString();
                String password_text = password_field.getText().toString();
                Toast login_error;
                //handles valid email
                if (validateEmail(email_text)){
                    // handles empty password
                    if (password_text.length() > 0) {
                        //shared preferences editor
                        SharedPreferences.Editor s_prefs_edit = s_prefs.edit();
                        s_prefs_edit.putString("DefaultEmail", email_text);
                        s_prefs_edit.commit();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else {
                        login_error = Toast.makeText(getApplicationContext(), getString(R.string.inv_passw_msg), Toast.LENGTH_SHORT);
                        login_error.show();
                    }
                }else {
                    login_error = Toast.makeText(getApplicationContext(), getString(R.string.inv_login_msg), Toast.LENGTH_SHORT);
                    login_error.show();
                }
            }
        });
    }
    // validates email address
    protected Boolean validateEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
    // print debug messages
    protected void print(String message) {
        Toast debug_message = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        debug_message.show();
    }
}