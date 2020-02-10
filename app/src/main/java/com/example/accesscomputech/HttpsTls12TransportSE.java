package com.example.accesscomputech;

import org.ksoap2.transport.HttpsServiceConnectionSE;
import org.ksoap2.transport.HttpsServiceConnectionSEIgnoringConnectionClose;
import org.ksoap2.transport.HttpsTransportSE;
import org.ksoap2.transport.ServiceConnection;

import java.io.IOException;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by ACPL on 12-08-2017.
 */

public class HttpsTls12TransportSE extends HttpsTransportSE
{
    public HttpsTls12TransportSE(String host, int port, String file, int timeout) {
        super(host, port, file, timeout);
    }


    @Override
    public ServiceConnection getServiceConnection() throws IOException
    {
        ServiceConnection serviceConnection =
                new HttpsServiceConnectionSEIgnoringConnectionClose(host, port, file, timeout);
        serviceConnection.setRequestProperty("Connection", "keep-alive");

        SSLSocketFactory factory = new Tls12SocketFactory(null);
        ((HttpsServiceConnectionSE)serviceConnection).setSSLSocketFactory(factory);

        return serviceConnection;
    }

}