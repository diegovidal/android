package com.dvidal.videos;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Declarações
    private ImageButton btnPlay;
    private ImageButton btnStop;
    private ImageButton btnPause;
    private ImageButton btnNativo;
    private VideoView videoView;

    private byte videoStatus = 0;
    private float pausedTime = 0;

    // Constantes
    private static final byte VIDEO_PARADO = 0;
    private static final byte VIDEO_PAUSADO = 1;
    private static final byte VIDEO_ROLANDO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instances();
        listeners();

        videoView.setMediaController(new MediaController(this));
    }

    private void instances(){

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnNativo = (ImageButton) findViewById(R.id.btnNativo);
        videoView = (VideoView) findViewById(R.id.videoView);
    }

    private void listeners(){

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnNativo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnPlay:
                playVideoView();
                break;

            case R.id.btnStop:
                stopVideoView();
                break;

            case R.id.btnPause:
                pauseVideoView();
                break;

            case R.id.btnNativo:
                carregarVideoNativo();
                break;
        }
    }

    private void playVideoView(){
        if (videoStatus != VIDEO_ROLANDO) {
            if (videoStatus == VIDEO_PARADO) videoView.setVideoURI(getVideoUri());
            videoView.start();
            videoStatus = VIDEO_ROLANDO;
        }


    }

    private void stopVideoView(){
        videoView.stopPlayback();
        videoStatus = VIDEO_PARADO;
    }

    private void pauseVideoView(){
        videoView.pause();
        videoStatus = VIDEO_PAUSADO;
    }

    private void carregarVideoNativo() {

        try {
            Log.d("general", "android.resource://" + getPackageName() + "/" + R.raw.needforspeed);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(getVideoUri(), "video/mp4");
            startActivity(Intent.createChooser(intent, "Escolha uma mídia:"));
        } catch (ActivityNotFoundException e){
            Log.d("Error", e.getMessage());
        }
    }

    private Uri getVideoUri(){
        //Uri uriVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.needforspeed);
        Uri uriVideo = Uri.parse("/sdcard/Movies/needforspeed.mp4");

        return uriVideo;
    }
}
