package com.example.orderfoods.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfoods.R;

public class SplashSreen extends AppCompatActivity {
    Button btnSignIn, btnSignUp;
    TextView txtSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_sreen);
            btnSignIn =findViewById(R.id.btnSignInSplachScreen);
            btnSignUp =findViewById(R.id.btnSignupSplachScreen);
            txtSlogan=findViewById(R.id.txtSlogan);





            btnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SignIn.class));
                    finish();
                }
            });


            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), SignUp.class));
                    finish();
                }
            });

        }
    }