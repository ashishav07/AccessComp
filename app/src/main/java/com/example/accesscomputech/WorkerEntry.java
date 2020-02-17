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
import org.ksoap2.serialization.PropertyInfo;
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

            PropertyInfo info = new PropertyInfo();
            info.setName("WRKR_CODE");
            info.setValue(null);
            info.setType(String.class);
            request.addProperty(info);

            PropertyInfo info1 = new PropertyInfo();
            info1.setName("CONT_CODE");
            info1.setValue(null);
            info1.setType(String.class);
            request.addProperty(info1);

            PropertyInfo info2 = new PropertyInfo();
            info2.setName("LOC_CODE");
            info2.setValue(null);
            info2.setType(String.class);
            request.addProperty(info2);

            PropertyInfo info3 = new PropertyInfo();
            info3.setName("FIRSTNAME");
            info3.setValue(null);
            info3.setType(String.class);
            request.addProperty(info3);

            PropertyInfo info4 = new PropertyInfo();
            info4.setName("MIDDLENAME");
            info4.setValue(null);
            info4.setType(String.class);
            request.addProperty(info4);

            PropertyInfo info5 = new PropertyInfo();
            info5.setName("LASTNAME");
            info5.setValue(null);
            info5.setType(String.class);
            request.addProperty(info5);

            PropertyInfo info6 = new PropertyInfo();
            info6.setName("DOB");
            info6.setValue(null);
            info6.setType(String.class);
            request.addProperty(info6);

            PropertyInfo info7 = new PropertyInfo();
            info7.setName("LOCAL_ADDR");
            info7.setValue(null);
            info7.setType(String.class);
            request.addProperty(info7);

            PropertyInfo info8 = new PropertyInfo();
            info8.setName("JOIN_DATE");
            info8.setValue(null);
            info8.setType(String.class);
            request.addProperty(info8);

            PropertyInfo info9 = new PropertyInfo();
            info9.setName("CONTACT_NO");
            info9.setValue(null);
            info9.setType(Integer.class);
            request.addProperty(info9);

            PropertyInfo info10 = new PropertyInfo();
            info10.setName("SKILL_TYPE");
            info10.setValue(null);
            info10.setType(String.class);
            request.addProperty(info10);

            PropertyInfo info11 = new PropertyInfo();
            info11.setName("GENDER");
            info11.setValue(null);
            info11.setType(String.class);
            request.addProperty(info11);

            PropertyInfo info12 = new PropertyInfo();
            info12.setName("QUALI");
            info12.setValue(null);
            info12.setType(String.class);
            request.addProperty(info12);

            PropertyInfo info13 = new PropertyInfo();
            info13.setName("WRKR_TYPE");
            info13.setValue(null);
            info13.setType(String.class);
            request.addProperty(info13);

            PropertyInfo info14 = new PropertyInfo();
            info14.setName("RELIGION");
            info14.setValue(null);
            info14.setType(String.class);
            request.addProperty(info14);

            PropertyInfo info15 = new PropertyInfo();
            info15.setName("BLD_GP");
            info15.setValue(null);
            info15.setType(String.class);
            request.addProperty(info15);

            PropertyInfo info16 = new PropertyInfo();
            info16.setName("DOMICILE");
            info16.setValue(null);
            info16.setType(String.class);
            request.addProperty(info16);

            PropertyInfo info17 = new PropertyInfo();
            info17.setName("HEIGHT");
            info17.setValue(null);
            info17.setType(String.class);
            request.addProperty(info17);

            PropertyInfo info18 = new PropertyInfo();
            info18.setName("ID_MARK");
            info18.setValue(null);
            info18.setType(String.class);
            request.addProperty(info18);

            PropertyInfo info19 = new PropertyInfo();
            info19.setName("POLICE_VER");
            info19.setValue(null);
            info19.setType(String.class);
            request.addProperty(info19);

            PropertyInfo info20 = new PropertyInfo();
            info20.setName("SAFETY_TR_FROM");
            info20.setValue(null);
            info20.setType(Integer.class);
            request.addProperty(info20);

            PropertyInfo info21 = new PropertyInfo();
            info21.setName("SAFETY_TR_TO");
            info21.setValue(null);
            info21.setType(String.class);
            request.addProperty(info21);

            PropertyInfo info22 = new PropertyInfo();
            info22.setName("SAFETY_REMARK");
            info22.setValue(null);
            info22.setType(String.class);
            request.addProperty(info22);

            PropertyInfo info23 = new PropertyInfo();
            info23.setName("VH_FLAG");
            info23.setValue(null);
            info23.setType(String.class);
            request.addProperty(info23);

            PropertyInfo info24 = new PropertyInfo();
            info24.setName("DRV_LIC_NO");
            info24.setValue(null);
            info24.setType(String.class);
            request.addProperty(info24);

            PropertyInfo info25 = new PropertyInfo();
            info25.setName("DRV_LIC_VAL");
            info25.setValue(null);
            info25.setType(Integer.class);
            request.addProperty(info25);

            PropertyInfo info26 = new PropertyInfo();
            info26.setName("AADHAR");
            info26.setValue(null);
            info26.setType(String.class);
            request.addProperty(info26);

            PropertyInfo info27 = new PropertyInfo();
            info27.setName("COUNTRYNAME");
            info27.setValue(null);
            info27.setType(String.class);
            request.addProperty(info27);

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
