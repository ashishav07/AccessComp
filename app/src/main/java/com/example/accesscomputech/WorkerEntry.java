package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class WorkerEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_entry);

        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner1);

        Spinner bloodSpinner = (Spinner) findViewById(R.id.spinner2);

        Spinner qualificationSpinner = (Spinner) findViewById(R.id.spinner3);

        Spinner workerTypeSpinner = (Spinner) findViewById(R.id.spinner4);

        Spinner skillSpinner = (Spinner) findViewById(R.id.spinner5);

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.gender));

        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.blood));

        ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.qualification));

        ArrayAdapter<String> workerTypeAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.worker_type));

        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.skill));


        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);

        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodSpinner.setAdapter(bloodAdapter);

        qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualificationSpinner.setAdapter(qualificationAdapter);

        workerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workerTypeSpinner.setAdapter(workerTypeAdapter);

        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillSpinner.setAdapter(skillAdapter);

    }
}
