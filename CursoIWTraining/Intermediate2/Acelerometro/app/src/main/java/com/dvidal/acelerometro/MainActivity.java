package com.dvidal.acelerometro;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Declarações
    private TextView txtMenssagem;

    private TextView txtX;
    private TextView txtY;
    private TextView txtZ;

    private SeekBar skbX;
    private SeekBar skbY;
    private SeekBar skbZ;

    private SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instâncias
        txtMenssagem = (TextView) findViewById(R.id.txtMenssagem);
        txtX = (TextView) findViewById(R.id.txtX);
        txtY = (TextView) findViewById(R.id.txtY);
        txtZ = (TextView) findViewById(R.id.txtZ);

        skbX = (SeekBar) findViewById(R.id.skbX);
        skbY = (SeekBar) findViewById(R.id.skbY);
        skbZ = (SeekBar) findViewById(R.id.skbZ);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        txtX.setText(getString(R.string.lbl_x,x));
        txtY.setText(getString(R.string.lbl_y,y));

        txtZ.setText(getString(R.string.lbl_z,z));

        // SeekBar values
        skbX.setProgress((int)x*10);
        skbY.setProgress((int)y*10);
        skbZ.setProgress((int)z*10);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        txtMenssagem.setText(getString(R.string.mudanca, accuracy));
    }

}
