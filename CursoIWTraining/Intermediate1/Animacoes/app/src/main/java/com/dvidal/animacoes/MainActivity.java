package com.dvidal.animacoes;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.animacaoComAnimationDrawable();
        this.animacaoComAnimationViewViaCodigo();
        this.animacaoComAnimationViewViaXML();
        this.animacaoDeRotacao();
        this.animacaoDeRotacaoViaXML();
        this.animacaoScala();
        this.animacaoScalaViaXML();
    }

    private void animacaoComAnimationDrawable(){

        ImageView imgAnimacao = (ImageView) findViewById(R.id.imgAnimacaoId);

        AnimationDrawable ad = (AnimationDrawable) imgAnimacao.getDrawable();
        ad.start();
    }

    private void animacaoComAnimationViewViaCodigo(){

        ImageView imgReplay = (ImageView) findViewById(R.id.imgReplayId);

        AlphaAnimation sumir = new AlphaAnimation(1.0f, 0.0f);
        sumir.setDuration(3000);
        sumir.setRepeatCount(-1);

        imgReplay.startAnimation(sumir);
    }

    private void animacaoComAnimationViewViaXML(){

        ImageView imgReplayXML = (ImageView) findViewById(R.id.imgReplayXMLId);

        AlphaAnimation sumir = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.anima_alpha);
        imgReplayXML.startAnimation(sumir);
    }

    private void animacaoDeRotacao(){

        ImageView imgReplayRotation = (ImageView) findViewById(R.id.imgRotationId);

        RotateAnimation rot = new RotateAnimation(360,0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rot.setDuration(400);
        rot.setRepeatCount(-1);
        imgReplayRotation.startAnimation(rot);
    }

    private void animacaoDeRotacaoViaXML(){

        ImageView imgReplayRotation = (ImageView) findViewById(R.id.imgRotationXMLId);

        RotateAnimation rot = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.anima_rotation);
        imgReplayRotation.startAnimation(rot);
    }

    private void animacaoScala(){

        ImageView imgReplayEscala = (ImageView) findViewById(R.id.imgScaleId);

        ScaleAnimation escala = new ScaleAnimation(
                1.0f,0.0f,
                1.0f,0.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        escala.setDuration(2000);
        escala.setRepeatCount(-1);
        //escala.setFillAfter(true);
        escala.setRepeatMode(ScaleAnimation.REVERSE);

        imgReplayEscala.startAnimation(escala);
    }

    private void animacaoScalaViaXML(){

        ImageView imgReplayScala = (ImageView) findViewById(R.id.imgScaleXMLId);

        ScaleAnimation scale = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.anima_scale);
        imgReplayScala.startAnimation(scale);
    }
}
