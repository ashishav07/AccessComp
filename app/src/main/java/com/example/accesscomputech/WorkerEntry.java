package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class WorkerEntry extends AppCompatActivity {

    private static final String TAG = "WorkerEntry";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_entry);

        mDisplayDate = (TextView) findViewById(R.id.dob);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);



                DatePickerDialog dialog = new DatePickerDialog(WorkerEntry.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,day,month,year);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {

                month = month+1;
                String date = year +"/"+ month +"/"+ day;
                mDisplayDate.setText(date);


            }
        };


        /**
         * Gender Spinner
         */

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.gender));

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);



        /**
         * Blood Spinner


        Spinner bloodSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.blood));

        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodSpinner.setAdapter(bloodAdapter);

         */

        /**
         * Qualification spinner


        Spinner qualificationSpinner = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.qualification));

        qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualificationSpinner.setAdapter(qualificationAdapter);

         */

        /**
         * Worker type spinner


        Spinner workerTypeSpinner = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<String> workerTypeAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.worker_type));

        workerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workerTypeSpinner.setAdapter(workerTypeAdapter);

         */

        /**
         * Skill spinner


        Spinner skillSpinner = (Spinner) findViewById(R.id.spinner5);

        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.skill));

        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillSpinner.setAdapter(skillAdapter);

         */

    }
}
