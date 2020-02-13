package com.example.accesscomputech;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;

public class documentupload extends AppCompatActivity{
    /*Form type spinner*/
    Spinner Form_spinner;
    RelativeLayout wcp_for_month,wcp_for_year;
    TextView mDisplayDate,wcp,pf;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    public static final String NAMESPACE = "http://127.0.0.1/opalsevc/";
    public static String Method_Name = "DOC_UPLOAD";
    private static String webSrvcLink = "http://103.231.5.35:85/opalsevc/OPAL_WEB_CALL.asmx";
    private static String webSrvcSoapAction = "http://127.0.0.1/opalsevc/";
    Button upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentupload);

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

        upload = findViewById(R.id.upload);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
                return;
            }
        }
        enable_button();

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
    private class SoapCall extends AsyncTask<String,Object,String>{
        public SoapCall(){
            super();

        }
        @Override
        protected String doInBackground(String... strings) {
            String responseString = null;
            SoapObject request = new SoapObject(NAMESPACE, Method_Name);
//            request.addProperty("formth",);
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
    private void enable_button() {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialFilePicker()
                        .withActivity(documentupload.this)
                        .withRequestCode(10)
                        .start();

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 100 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
            enable_button();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
            }
        }
    }

    ProgressDialog progress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK) {
            progress = new ProgressDialog(documentupload.this);
            progress.setTitle("Uploading");
            progress.setMessage("Please wait...");
            progress.show();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    File f = new File(data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH));
                    String content_type = getMimeType(f.getPath());

                    String file_path = f.getAbsolutePath();
                    OkHttpClient client = new OkHttpClient();
//                    new SoapCall();
                }
            });
            t.start();
        }
    }

    private String getMimeType(String path) {

        String extension = MimeTypeMap.getFileExtensionFromUrl(path);

        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }
}

