package com.example.midtermrequirement_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class sign_up_page extends AppCompatActivity {

    EditText et_firstName, et_lastName, et_contactNumber, et_email, et_passWord, et_confirmPW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);


    }

    private void setRef(){
        et_lastName = findViewById(R.id.et_lastName);
        et_firstName = findViewById(R.id.et_firstName);
        et_contactNumber = findViewById(R.id.et_contactNumber);
        et_email = findViewById(R.id.et_email);
        et_passWord = findViewById(R.id.et_password_signup);
        et_confirmPW = findViewById(R.id.et_confirmPassword);
    }
}