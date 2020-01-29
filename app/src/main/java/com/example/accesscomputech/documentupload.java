package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class documentupload extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    RelativeLayout r1,r2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentupload);
        spinner=(Spinner)findViewById(R.id.spinner);
        r1=(RelativeLayout)findViewById(R.id.rel_lay0);
        r2=(RelativeLayout)findViewById(R.id.rel_lay1);


        List<String> list=new ArrayList<String>();
        list.add("PF");
        list.add("WCP Document");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinner.setSelection(i);
        if(i==1){
            r1.setVisibility(View.GONE);
           r2.setVisibility(View.VISIBLE);
        }
        if(i==0){
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
