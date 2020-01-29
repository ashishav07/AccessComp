package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void workerEntry(View view) {
        Intent in = new Intent(this,WorkerEntry.class);
        startActivity(in);
    }

    public void logOut(View view) {
        Intent in = new Intent(this,LoginActivity.class);
        startActivity(in);
    }


}
