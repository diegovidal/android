package com.dvidal.navegador;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btnVoltar;
    private ImageButton btnPassar;
    private ProgressBar progressBar;
    private WebView webNavigator;
    private TextView textMsg;

    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVoltar = (ImageButton) findViewById(R.id.btnBack);
        btnPassar = (ImageButton) findViewById(R.id.btnPassa);
        progressBar = (ProgressBar) findViewById(R.id.progressId);
        webNavigator = (WebView) findViewById(R.id.webviewId);
        textMsg = (TextView) findViewById(R.id.textMsg);

        btnVoltar.setOnClickListener(this);
        btnPassar.setOnClickListener(this);

        webNavigator.loadUrl("http://www.google.com/");

        webNavigator.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);

                textMsg.setText(url.toString());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

                textMsg.setText("Erro: " + error.toString());
                progressBar.setVisibility(View.INVISIBLE);
            }

        });
    }

    @Override
    public void onClick(View v) {
//        if(v.getId(R.id.btnBack)){
//            webNavigator.goBack();
//        }
//        else{
//            webNavigator.goForward();
//        }

        switch (v.getId()){

            case R.id.btnBack:
                webNavigator.goBack();
                break;

            case R.id.btnPassa:
                webNavigator.goForward();
                break;
        }
    }
}
