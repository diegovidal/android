package com.dvidal.beastmainproject.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.dvidal.beastmainproject.R;
import com.dvidal.beastmainproject.enties.RushEvent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by diegovidal on 19/10/17.
 */

public class MapsActivity extends BaseActivity {

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

    private GoogleApiClient mClient;
    private GoogleMap mMap;
    private RushEvent mRushEvent;
    public static final String RUSH_EVENT_INFO = "RUSH_EVENT_INFO";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);

        mRushEvent = getIntent().getParcelableExtra(RUSH_EVENT_INFO);

        txtRushName.setText(mRushEvent.getEventName());
        txtRushDate.setText(mRushEvent.getEventDate());
        txtRushTime.setText(mRushEvent.getEventTime());
        txtRushDescription.setText(mRushEvent.getEventDescription());
        txtRushLocation.setText(mRushEvent.getEventLocation());

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {

                        updateUI();
                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                }).build();

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.activity_maps_map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClient.disconnect();
    }

    private void updateUI(){

        LatLng rushEventPoint = new LatLng(mRushEvent.getEventLatitude(), mRushEvent.getEventLongitude());

        MarkerOptions rushEventMarker = new MarkerOptions()
                .position(rushEventPoint)
                .title("Rush Event Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        mMap.clear();
        mMap.addMarker(rushEventMarker);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rushEventPoint, 15));
    }

    public static Intent newIntent(Context context, RushEvent rushEvent){
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(RUSH_EVENT_INFO, rushEvent);

        return  intent;
    }
}
