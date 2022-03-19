package com.example.midtermrequirement_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_up_page extends AppCompatActivity {

    EditText et_firstName, et_lastName, et_contactNumber, et_email, et_password_signup,
            et_confirmPassword;
    TextView tv_signIn;
    Button btn_signUp;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        setRef();



        setOnClickListener();

    }

    private void setOnClickListener() {

        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sign_up_page.this, login_page.class);
                startActivity(intent);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password_signup.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    et_email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    et_password_signup.setError("Password is Required");
                    return;
                }

                if(password.length() < 8){
                    et_password_signup.setError("Password must be more the 8 characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(sign_up_page.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(sign_up_page.this, "Creation Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void setRef(){
        et_lastName = findViewById(R.id.et_lastName);
        et_firstName = findViewById(R.id.et_firstName);
        et_contactNumber = findViewById(R.id.et_contactNumber);
        et_email = findViewById(R.id.et_email);
        et_password_signup = findViewById(R.id.et_password_signup);
        et_confirmPassword= findViewById(R.id.et_confirmPassword);
        tv_signIn= findViewById(R.id.tv_signIn);
        btn_signUp = findViewById(R.id.btn_signUp);
        fAuth = FirebaseAuth.getInstance();
    }
}