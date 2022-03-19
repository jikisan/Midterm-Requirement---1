package com.example.midtermrequirement_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_page extends AppCompatActivity {

    EditText et_username, et_password;
    CheckBox checkBox_rememberMe;
    TextView tv_forgotPassword, tv_signUp;
    Button btn_login, btn_guest;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        
        setRef();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        setOnClickListener();
        
    }

    private void setOnClickListener() {

        tv_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSignUp = new Intent(login_page.this, sign_up_page.class);
                startActivity(intentSignUp);

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    et_username.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    et_password.setError("Password is Required");
                    return;
                }

                if(password.length() < 8){
                    et_password.setError("Password must be more the 8 characters");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login_page.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else {
                            Toast.makeText(login_page.this, "Login Failed " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }                    }
                });
            }
        });
    }

    private void setRef() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        checkBox_rememberMe = findViewById(R.id.cb_rememberMe);
        tv_forgotPassword = findViewById(R.id.tv_ForgotPassword);
        tv_signUp = findViewById(R.id.tv_signUp);
        btn_login = findViewById(R.id.btn_login);
        btn_guest = findViewById(R.id.btn_guest);
        fAuth = FirebaseAuth.getInstance();
    }
}