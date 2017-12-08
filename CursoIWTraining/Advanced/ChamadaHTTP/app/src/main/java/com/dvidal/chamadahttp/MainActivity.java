package com.dvidal.chamadahttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Debug;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView txtMessages;
    private ImageView imgPhoto;
    private Bitmap photo;

    private Handler handlerLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMessages = (TextView) findViewById(R.id.txtMessages);
        imgPhoto = (ImageView) findViewById(R.id.imgPhoto);

        handlerLoading = new HandlerLoading();

        if (verifyConnection()){
            new Thread(){
                @Override
                public void run() {

                    // Estamos em outra thread
                    Bitmap image = null;
                    try {
                        URL url = new URL("http://ichef-1.bbci.co.uk/news/ws/660/amz/worldservice/live/assets/images/2016/04/17/160417163624_carreta_furacao_640x360_reproducao_nocredit.jpg");
                        photo = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                        Message message = new Message();
                        message.what = 1;
                        handlerLoading.sendMessage(message);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }

    private boolean verifyConnection(){

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();

        String netType = "N/D";
        boolean isConnected = false;

        if (net != null){
            isConnected = true;

            if (net.getType() == ConnectivityManager.TYPE_WIFI) netType = "Wifi";
            if (net.getType() == ConnectivityManager.TYPE_MOBILE) netType = "Mobile";
        }

        txtMessages.setText(netType);

        return isConnected;
    }

    private class HandlerLoading extends Handler{

        @Override
        public void handleMessage(Message msg) {

            Log.d("general", String.valueOf(msg.what));

            if (msg.what == 1){
                Log.d("general", "Deu certo!");
                imgPhoto.setImageBitmap(photo);
            }
        }
    }
}
