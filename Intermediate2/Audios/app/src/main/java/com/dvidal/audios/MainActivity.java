package com.dvidal.audios;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnCompletionListener {

    // Constantes
    private static byte AUDIO_PARADO = 0;
    private static byte AUDIO_PAUSADO = 1;
    private static byte AUDIO_TOCANDO = 2;

    // Declarações
    private ImageButton btnPlay;
    private ImageButton btnStop;
    private ImageButton btnPause;

    private MediaPlayer player;
    private byte audioStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.d("General", AUDIO_PARADO);

        instances();
        listeners();
    }

    private void instances(){

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnPause = (ImageButton) findViewById(R.id.btnPause);
    }

    private void listeners(){

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnPlay:
                playMusicSD();
                break;

            case R.id.btnStop:
                stopMusic();
                break;

            case R.id.btnPause:
                pauseMusic();
                break;
        }
    }

    private void playMusic(){

        if (audioStatus == AUDIO_PARADO){
            player = MediaPlayer.create(this, R.raw.musica);
        }
        player.start();
        audioStatus = AUDIO_TOCANDO;
    }

    private void playMusicSD(){
        if (audioStatus == AUDIO_PARADO) {
            player = new MediaPlayer();

            try {
                player.setDataSource("/sdcard/Music/musica.mp3");
                player.setOnCompletionListener(this);
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        player.start();
        audioStatus = AUDIO_TOCANDO;
    }

    private void stopMusic(){

        if (audioStatus != AUDIO_PARADO){
            player.stop();
            audioStatus = AUDIO_PARADO;
        }
    }

    private void pauseMusic(){

        if (audioStatus == AUDIO_TOCANDO){
            player.pause();
            audioStatus = AUDIO_PAUSADO;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.d("General", "Kbou");
    }
}
