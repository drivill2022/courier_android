package com.drivill.courier;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.activity.LetsStartActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;
import com.drivill.courier.activity.LoginActivity;
import com.drivill.courier.interfaces.MvvmView;
import com.drivill.courier.receiver.NetworkReceiver;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.PrefsManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

abstract public class BaseActivity extends AppCompatActivity implements MvvmView, LocationListener {
    private Dialog dialogbox;
    public PrefsManager mBasePreferenceManager;
    public static final int MY_BACKGROUND_JOB = 0;
    private BroadcastReceiver MyReceiver = null;
    public LocationManager mLocationManager;
    public FusedLocationProviderClient fusedLocationClient;
    public final MutableLiveData<Location> locationLive = new MutableLiveData<>();

    protected abstract void setUp();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_base);
        mBasePreferenceManager = new PrefsManager(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplicationContext());
        MyReceiver = new NetworkReceiver();
        broadcastIntent();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(MyReceiver);
    }


    @Override
    public void showLoading() {
        dialogbox = new Dialog(this);
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.setCancelable(false);
        dialogbox.setContentView(R.layout.dialog_progress_layout);
        try {
            dialogbox.show();
        }
        catch (WindowManager.BadTokenException e) {
            //use a log message
            Log.i("arp","" + e.toString());
        }
    }

    @Override
    public void hideLoading() {
        if (dialogbox != null && dialogbox.isShowing()) {
            dialogbox.dismiss();
             Log.e("dismiss","true");
        } else {
             Log.e("dismiss","false");
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void openFullImagectivity(String url, String name) {

    }

    @Override
    public void onError(String message) {
        showSnackBar(message);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean isNetworkConnected() {
        return Constant.isConnectingToInternet(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void callBackPressed() {
        onBackPressed();
    }

    @Override
    public void login() {
        //fetchProfileRider();
    }

    @Override
    public void logout() {
        logoutRider();
    }

    @Override
    public void logoutMerchant() {
        logout_merchantApi();
    }

    public String getBusinessLogo() {
        return mBasePreferenceManager.getBusinessLogo();
    }

    private void showSnackBar(String message) {
     /*   Sneaker snack = Sneaker.with(this);
        View view = LayoutInflater.from(this).inflate(R.layout.snack_alert, snack.getView(), false);
        TextView txt = view.findViewById(R.id.tv_message);
        txt.setText(message);
        snack.sneakCustom(view);*/
       /* Sneaker.with(this) // Activity, Fragment or ViewGroup
                .setTitle("Error!!")
                .setMessage(message)
                .sneakCustom();*/

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) sbView.getLayoutParams();
        params.gravity = Gravity.BOTTOM;
        sbView.setLayoutParams(params);
        snackbar.show();
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (location != null) {
            Log.i("*****", "Location changed");
        }
    }

    void logoutRider() {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().logoutRider(mBasePreferenceManager.getUserToken());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    FirebaseMessaging.getInstance().deleteToken();
                    mBasePreferenceManager.setIS_LOGIN(false);
                    Intent intent = new Intent(getApplicationContext(), LetsStartActivity.class);
                    startActivity(intent);
                    finishAffinity();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                onError(getResources().getString(R.string.try_again));
            }
        });
    }

    void logout_merchantApi() {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().logoutMerchant(mBasePreferenceManager.getUserToken());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    FirebaseMessaging.getInstance().deleteToken();
                    mBasePreferenceManager.setIS_LOGIN(false);
                    Intent intent = new Intent(getApplicationContext(), LetsStartActivity.class);
                    startActivity(intent);
                    finishAffinity();

                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                onError(getResources().getString(R.string.try_again));
            }
        });
    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

   /* public static void scheduleJob(Context context) {
        JobScheduler js =
                (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo job = new JobInfo.Builder(
                MY_BACKGROUND_JOB,
                new ComponentName(context, MyJobService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresCharging(true)
                .build();
        js.schedule(job);
    }*/

    protected void location() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            locationLive.setValue(location);
                            // showMessage(String.valueOf(location));
                        }
                    }
                });

    }
}
