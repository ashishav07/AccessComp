package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.util.Calendar;

public class WorkerEntry extends AppCompatActivity {

    private static final String TAG = "WorkerEntry";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView driving;
    private DatePickerDialog.OnDateSetListener drivingListener;

    private TextView blockingFrom;
    private DatePickerDialog.OnDateSetListener blockingFromListener;

    private TextView blockingUpto;
    private DatePickerDialog.OnDateSetListener blockingUptoListener;

    TextView personal,work;
    ScrollView personalDetails, workDetails;
    public static final String NAMESPACE = "http://127.0.0.1/opalsevc/";
    public static String Method_Name = "WRKR_ENTRY";
    private static String webSrvcLink = "http://103.231.5.35:85/opalsevc/OPAL_WEB_CALL.asmx";
    private static String webSrvcSoapAction = "http://127.0.0.1/opalsevc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_entry);

        personal= (TextView) findViewById(R.id.personal_details_button);
        work= (TextView) findViewById(R.id.work_details_button);

        personalDetails= (ScrollView) findViewById(R.id.personal_details);
        workDetails= (ScrollView) findViewById(R.id.work_details);

        personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalDetails.setVisibility(View.VISIBLE);
                workDetails.setVisibility(View.GONE);

            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personalDetails.setVisibility(View.GONE);
                workDetails.setVisibility(View.VISIBLE);
            }
        });

        /**
         * DOB date picker
         */

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
         * Driving Validity date picker
         */

        driving = (TextView) findViewById(R.id.driving_validity);

        driving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(WorkerEntry.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        drivingListener,day,month,year);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        drivingListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {

                month = month+1;
                String drivingValidity = year +"/"+ month +"/"+ day;
                driving.setText(drivingValidity);


            }
        };

        /**
         * Blocking from date picker
         */

        blockingFrom = (TextView) findViewById(R.id.blocking_from);

        blockingFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);



                DatePickerDialog dialog = new DatePickerDialog(WorkerEntry.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        blockingFromListener,day,month,year);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        blockingFromListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {

                month = month+1;
                String blockingFromDate = year +"/"+ month +"/"+ day;
                blockingFrom.setText(blockingFromDate);


            }
        };

        /**
         * Blocking Upto date picker
         */

        blockingUpto = (TextView) findViewById(R.id.blocking_upto);

        blockingUpto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal= Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);



                DatePickerDialog dialog = new DatePickerDialog(WorkerEntry.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        blockingUptoListener,day,month,year);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                dialog.show();
            }
        });

        blockingUptoListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {

                month = month+1;
                String blockingUptoDate = year +"/"+ month +"/"+ day;
                blockingUpto.setText(blockingUptoDate);


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
         */

        Spinner bloodSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> bloodAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.blood));

        bloodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodSpinner.setAdapter(bloodAdapter);


        /**
         * Domicile spinner
         */

        Spinner domicileSpinner = (Spinner) findViewById(R.id.domicile_spinner);

        ArrayAdapter<String> domicileAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.domicile));

        domicileAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        domicileSpinner.setAdapter(domicileAdapter);

        /**
         * Nationality spinner
         */

        Spinner nationalitySpinner = (Spinner) findViewById(R.id.nationality_spinner);

        ArrayAdapter<String> nationalityAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.nationality));

        nationalityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationalitySpinner.setAdapter(nationalityAdapter);


        /**
         * Qualification spinner
         */

        Spinner qualificationSpinner = (Spinner) findViewById(R.id.spinner3);

        ArrayAdapter<String> qualificationAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.qualification));

        qualificationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualificationSpinner.setAdapter(qualificationAdapter);



        /**
         * Worker type spinner
         */

        Spinner workerTypeSpinner = (Spinner) findViewById(R.id.spinner4);

        ArrayAdapter<String> workerTypeAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.worker_type));

        workerTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        workerTypeSpinner.setAdapter(workerTypeAdapter);



        /**
         * Skill spinner
         */

        Spinner skillSpinner = (Spinner) findViewById(R.id.spinner5);

        ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.skill));

        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        skillSpinner.setAdapter(skillAdapter);

        /**
         * Police verification spinner
         */

        Spinner poiceSpinner = (Spinner) findViewById(R.id.police_spinner);

        ArrayAdapter<String> policeAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.police));

        policeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        poiceSpinner.setAdapter(policeAdapter);

        /**
         * vehicle allowed spinner
         */
/**
        Spinner vehicleSpinner = (Spinner) findViewById(R.id.vehicle_spinner);

        ArrayAdapter<String> vehicleAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.vehicle));

        vehicleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vehicleSpinner.setAdapter(vehicleAdapter);
**/

        /**
         * Blocked spinner
         */

        Spinner blockedSpinner = (Spinner) findViewById(R.id.blocked_spinner);

        ArrayAdapter<String> blockedAdapter = new ArrayAdapter<String>(WorkerEntry.this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.blocked));

        blockedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blockedSpinner.setAdapter(blockedAdapter);
    }
    private class SoapCall extends AsyncTask<Void,Void,String>{

        @Override
        protected String doInBackground(Void... voids) {
            String responseString = "initialString";

            SoapObject request = new SoapObject(NAMESPACE, Method_Name);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setAddAdornments(false);
            envelope.setOutputSoapObject(request);

            HttpTransportSE androidHttpTransport;        // ON it for local server
            try {
                //-----------for http without TLS------------//
                androidHttpTransport = new HttpTransportSE(webSrvcLink, 20000);
                //------------------------------------------//

                //------------------------for https  with TLS------------------------------//
//                Host = LoginURL.toLowerCase().replace("https://", "").replace("http://", "");
//                ServiceName = Host.substring(Host.indexOf("/"));
//                Host = Host.substring(0, Host.indexOf("/"));
//                androidHttpTransport = new HttpsTls12TransportSE(Host, 443, ServiceName, 20000);
                //-------------------------------------------------------------------------//
                androidHttpTransport.debug = true;
                androidHttpTransport.call(webSrvcSoapAction, envelope);
                SoapObject result = (SoapObject) envelope.bodyIn;
                if (result != null) {
                    responseString = result.getProperty(0).toString();
                } else {
                    responseString = "";
                }
//                    Connected = true;
            } catch (IOException e) {
//                    Connected = false;
                String er = e.getMessage();
//                    if (er != null) {
//                        Error = er;
//                    }
            }
//                HttpsTls12TransportSE androidHttpTransport;
//                androidHttpTransport = new HttpsTls12TransportSE(host, 443, serviceName, 60000);
//                androidHttpTransport.debug = true;
//                androidHttpTransport.call(webSrvcSoapAction, envelope);
//                SoapObject result = (SoapObject) envelope.bodyIn;
//                if (result != null) {
//                    responseString = result.getProperty(0).toString();
//                } else {
//                    responseString = "";
//                }
            catch(Exception ex) {
                responseString = ex.getMessage();
            }
            Log.i("FinalResponse",responseString);
            return responseString;
        }
    }
}
