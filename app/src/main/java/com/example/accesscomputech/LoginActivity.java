package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button register,gOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gOtp = findViewById(R.id.genotp);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
            gOtp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(gOtp.getText().equals("Generate OTP")){
                        gOtp.setText("Login");
                        findViewById(R.id.otp_layout).setVisibility(View.VISIBLE);
                    } else{
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));

                    }
                }
            });

    }
}