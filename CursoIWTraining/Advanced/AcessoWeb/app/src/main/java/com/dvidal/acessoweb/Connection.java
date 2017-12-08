package com.dvidal.acessoweb;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by diegovidal on 22/05/16.
 */
public class Connection {

    private URL url;

    public Connection(URL url){
        this.url = url;
    }

    // protected é modificador de acesso de apenas a classe e seus filhos podem usar
    protected HttpURLConnection startConnection(){
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection)this.url.openConnection();
            conn.setConnectTimeout(30 * 1000);
            conn.setReadTimeout(30 * 1000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(false);

            conn.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static boolean verifyConnection(Context ctx) {
        ConnectivityManager cm = (ConnectivityManager)ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo rede = cm.getActiveNetworkInfo();

        return (rede != null);
    }
}
