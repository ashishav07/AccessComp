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

public class documentupload extends AppCompatActivity{
    /*Form type spinner*/
    Spinner Form_spinner,Month_spinner,Year_Spinner;
    RelativeLayout pf_for_month,pf_for_year,wcp_for_month,wcp_for_year;
    TextView validupto,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentupload);
        Form_spinner=(Spinner)findViewById(R.id.Form_spinner);
        Month_spinner=(Spinner)findViewById(R.id.pf_Month_spinner);
        Year_Spinner=(Spinner)findViewById(R.id.pf_Year_spinner);

        List<String> form_list=new ArrayList<String>();
        form_list.add("PF");
        form_list.add("WCP Document");

        List<String> Month_list=new ArrayList<String>();
        Month_list.add("January");
        Month_list.add("February");
        Month_list.add("March");
        Month_list.add("April");
        Month_list.add("May");
        Month_list.add("June");
        Month_list.add("July");
        Month_list.add("August");
        Month_list.add("September");
        Month_list.add("October");
        Month_list.add("November");
        Month_list.add("December");

        List<String> Year_list=new ArrayList<>();
        int s=Calendar.getInstance().get(Calendar.YEAR);
        String y=Integer.toString(s);
        Year_list.add(y);
        for(int i=0;i<7;i++){
            s++;
            y=Integer.toString(s);
            Year_list.add(y);
        }

        ArrayAdapter<String> FormAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,form_list);
        FormAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Form_spinner.setAdapter(FormAdapter);
        Form_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Form_spinner.setSelection(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> MonthAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Month_list);
        MonthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Month_spinner.setAdapter(MonthAdapter);
        Month_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int k, long l) {
                Month_spinner.setSelection(k);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> YearAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Year_list);
        YearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year_Spinner.setAdapter(YearAdapter);
        Year_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                Year_Spinner.setSelection(j);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}
