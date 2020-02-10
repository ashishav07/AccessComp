package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class documentupload extends AppCompatActivity{
    /*Form type spinner*/
    Spinner Form_spinner;
    RelativeLayout wcp_for_month,wcp_for_year;
    TextView mDisplayDate,wcp,pf;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    public static final String NAMESPACE = "http://127.0.0.1/opalsevc/";
    public static String Method_Name = "GET_PAGERIGHTS(";
    private static String webSrvcLink = "http://103.231.5.35:85/opalsevc/OPAL_WEB_CALL.asmx";
    private static String webSrvcSoapAction = "http://127.0.0.1/opalsevc/";
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentupload);
        upload = findViewById(R.id.upload);
        Form_spinner=(Spinner)findViewById(R.id.Form_spinner);
        pf=(TextView)findViewById(R.id.pf);
        wcp=(TextView)findViewById(R.id.wcp);

        List<String> form_list=new ArrayList<String>();
        form_list.add("PF");
        form_list.add("WCP Document");
        mDisplayDate = (TextView) findViewById(R.id.date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();

                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);



                DatePickerDialog dialog = new DatePickerDialog(documentupload.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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



        ArrayAdapter<String> FormAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,form_list);
        FormAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Form_spinner.setAdapter(FormAdapter);
        Form_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Form_spinner.setSelection(i);
                if(i==0){
                    pf.setVisibility(View.VISIBLE);
                    wcp.setVisibility(View.GONE);
                }
                if(i==1){
                    pf.setVisibility(View.GONE);
                    wcp.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public class SoapCall extends AsyncTask<String,Object,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
