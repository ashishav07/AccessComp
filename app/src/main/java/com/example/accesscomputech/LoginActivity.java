package com.example.accesscomputech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    Button register,gOtp;
    TextView userid;
    public static final String NAMESPACE = "http://127.0.0.1/opalsevc/";
    public static String Method_Name = "DOC_UPLOAD(";
    private static String webSrvcLink = "http://103.231.5.35:85/opalsevc/OPAL_WEB_CALL.asmx";
    private static String webSrvcSoapAction = "http://127.0.0.1/opalsevc/";
    String abc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gOtp = findViewById(R.id.genotp);
        userid = findViewById(R.id.userid);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
            gOtp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(gOtp.getText().equals("Generate OTP")){
                        gOtp.setText("Login");
                        findViewById(R.id.otp_layout).setVisibility(View.VISIBLE);
                        abc = userid.getText().toString();
                        abc = abc + ", null)";
                        Method_Name = Method_Name + abc;
                        new SoapCall().execute();
                    } else{
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }
                }
            });

    }
    private class SoapCall extends AsyncTask<String,Object,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String responseString = null;
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
            return responseString;
    }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

        }
    }
}