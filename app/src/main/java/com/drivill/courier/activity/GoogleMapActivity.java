package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.model.model.HubModel;
import com.drivill.courier.rest.ApiManagerImp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnPolygonClickListener {
    private GoogleMap mMap;
    ShipmentModel mData;
    HubModel mHublist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        mData = (ShipmentModel) getIntent().getSerializableExtra("data");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        // Add a marker in Sydney and move the camera
        /*for (int i = 0; i < 20; i++) {
            LatLng sydney = new LatLng(i + 1, i + 2);
            mMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    .title("Lat-" + i + 1 + " " + "Lon-" + i + 2));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
        }
        for (int i = 0; i < 20; i++) {
            LatLng sydney = new LatLng(i - 1, i - 2);
            mMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    .title("Lat-" + (i - 1) + " " + "Lon-" + (i - 2)));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(sydney));
        }*/
        if (mData != null) {
            if (mData.getsLatitude() != null && mData.getsLongitude() != null
                    && mData.getdLatitude() != null && mData.getdLongitude() != null) {
                LatLng pickPoint = new LatLng(Double.parseDouble(mData.getsLatitude().toString()),
                        Double.parseDouble((mData.getsLongitude()).toString()));
                mMap.addMarker(new MarkerOptions()
                        .position(pickPoint)
                        .title(mData.getsAddress()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(pickPoint));

                LatLng dropPoint = new LatLng(Double.parseDouble(mData.getdLatitude().toString()),
                        Double.parseDouble((mData.getdLongitude()).toString()));
                mMap.addMarker(new MarkerOptions()
                        .position(dropPoint)
                        .title(mData.getdAddress()));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(dropPoint));
                drawPolygonView();
            } else
                Toast.makeText(GoogleMapActivity.this, "Path not found", Toast.LENGTH_SHORT).show();

            getHubList();
        }

    }

    void drawPolygonView() {
        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(Double.parseDouble(mData.getsLatitude().toString()),
                                Double.parseDouble((mData.getsLongitude()).toString())),
                        new LatLng(Double.parseDouble(mData.getdLatitude().toString()),
                                Double.parseDouble((mData.getdLongitude()).toString()))));

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(Double.parseDouble(mData.getsLatitude().toString()), Double.parseDouble(mData.getsLongitude().toString())), 8));

        // Set listeners for click events.
        // mMap.setOnPolylineClickListener(this);
        mMap.setOnPolygonClickListener(this);
    }

    @Override
    public void onPolygonClick(@NonNull Polygon polygon) {

    }

    void getHubList() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Call<HubModel> call = new ApiManagerImp().getHubRider();
                call.enqueue(new Callback<HubModel>() {
                    @Override
                    public void onResponse(Call<HubModel> call, Response<HubModel> response) {
                        if (response.body() != null) {

                            mHublist = response.body();
                            for (HubModel.Hub data : mHublist.getHubs()
                            ) {
                                if (data.getLatitude() != null && data.getLongitude() != null) {

                                    MarkerOptions marker = new MarkerOptions()
                                            .position(new LatLng(data.getLatitude(),
                                                    data.getLongitude())).title(data.getName());

// Changing marker icon
                                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.app_logo_theme));

// adding marker
                                    mMap.addMarker(marker);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<HubModel> call, Throwable t) {

                    }
                });
            }
        });
    }
}