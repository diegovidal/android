package com.dvidal.sensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SensorEventListener {

    private Spinner spnSensores;
    private SensorManager sm;
    private List<Sensor> sensores;
    private List<String> sensoresString;

    private TextView txtSensores;

    private int sensorAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnSensores = (Spinner) findViewById(R.id.spnSensores);
        txtSensores = (TextView) findViewById(R.id.txtSensores);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensores = sm.getSensorList(Sensor.TYPE_ALL);

        spnSensores.setOnItemSelectedListener(this);

        sensoresString = new ArrayList<>();

        for (Sensor s: sensores) {
            sensoresString.add(s.getName());
        }

        ArrayAdapter<String> adptSensores = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, sensoresString);
        spnSensores.setAdapter(adptSensores);

    }

    // Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sm.unregisterListener(this);
        sensorAtual = position;
        sm.registerListener(this, sensores.get(position), SensorManager.SENSOR_DELAY_NORMAL);
}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Sensor
    @Override
    public void onSensorChanged(SensorEvent event) {
        String texto = "";

        for (int i = 0; i < event.values.length; i++){
            texto += "Valor "+ i + " : " + event.values[i] + "\n";
        }

        txtSensores.setText(texto);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensores.size() > 0){
            sm.registerListener(this, sensores.get(sensorAtual), SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
