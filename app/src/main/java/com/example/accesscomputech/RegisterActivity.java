package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.reg);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (register.getText().equals("Generate OTP")) {
                    register.setText("Register");
                    findViewById(R.id.otp_layout).setVisibility(View.VISIBLE);
                }
                else{
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                }
            }
        });
    }
}
