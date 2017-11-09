package com.dvidal.beastmainproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.RushEvent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 19/10/17.
 */

public class CampusMapActivity extends BaseActivity {

    @BindView(R.id.activity_map_rush_name)
    TextView txtRushName;

    @BindView(R.id.activity_map_rush_date)
    TextView txtRushDate;

    @BindView(R.id.activity_map_rush_time)
    TextView txtRushTime;

    @BindView(R.id.activity_map_rush_description)
    TextView txtRushDescription;

    @BindView(R.id.activity_map_rush_location)
    TextView txtRushLocation;

    @BindView(R.id.activity_campus_map_webView)
    WebView webView;

    @BindView(R.id.activity_campus_map_progressBar)
    ProgressBar progressBar;

    public static final String RUSH_EVENT_INFO = "RUSH_EVENT_INFO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);
        ButterKnife.bind(this);

        RushEvent rushEvent = getIntent().getParcelableExtra(RUSH_EVENT_INFO);

        txtRushName.setText(rushEvent.getEventName());
        txtRushDate.setText(rushEvent.getEventDate());
        txtRushTime.setText(rushEvent.getEventTime());
        txtRushDescription.setText(rushEvent.getEventDescription());
        txtRushLocation.setText(rushEvent.getEventLocation());

        String googleDocs = "http://docs.google.com/gview?embedded=true&url=";

        progressBar.setMax(100);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        webView.loadUrl(googleDocs + "http://www.asu.edu/map/pdf/asu_map_tempe_2015.pdf");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public static Intent newIntent(Context context, RushEvent rushEvent){

        Intent intent = new Intent(context, CampusMapActivity.class);
        intent.putExtra(RUSH_EVENT_INFO, rushEvent);

        return intent;
    }
}
