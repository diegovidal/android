package com.dvidal.layout;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, RatingBar.OnRatingBarChangeListener, View.OnClickListener {

    private  Button btnSubmit;
    private Button btnSend;
    private LinearLayout linearReason;

    private TextView lblReason;
    private EditText textReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        //
        btnSend = (Button) findViewById(R.id.submit_id);
        linearReason = (LinearLayout) findViewById(R.id.reason_layout);
        lblReason = (TextView) findViewById(R.id.lblReason_id);
        textReason = (EditText) findViewById(R.id.reason_id);

        Switch s = (Switch) findViewById(R.id.form_switch);
        s.setOnCheckedChangeListener(this);

        RatingBar rb = (RatingBar) findViewById(R.id.form_rating);
        rb.setOnRatingBarChangeListener(this);

        btnSubmit = (Button) findViewById(R.id.submit_id);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Log.d("switch", "ligou");
            //Toast.makeText(this, "Ligou", Toast.LENGTH_SHORT).show();
            btnSend.setEnabled(true);
        }
        else{
            Log.d("switch", "desligou");
            //Toast.makeText(this, "Desligou", Toast.LENGTH_SHORT).show();
            btnSend.setEnabled(false);
        }
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        //Toast.makeText(this, rating+ " Estrelas", Toast.LENGTH_SHORT).show();

        if(rating <= 3){
            linearReason.setVisibility(View.VISIBLE);

            // Método pra redefinir uma posição lembrando que tem q transformar pra dpi
            textReason.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 80));

        }
        else {
            linearReason.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submit_id) {
            Toast.makeText(this, "Cadastrou", Toast.LENGTH_SHORT).show();
        }
    }


}
