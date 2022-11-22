package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.activity.notificationActivity.NotificationActivity;
import com.drivill.courier.activity.supportActivity.SupportActivity;
import com.drivill.courier.adapter.DeliveryAdapter;
import com.drivill.courier.adapter.PickupAdapter;
import com.drivill.courier.bottomsheetFragment.BottomSheet;
import com.drivill.courier.databinding.ActivityDashboardBinding;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.model.activityModel.RiderDashboardViewModel;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.MyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drivill.courier.utils.Constant.DL_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.LOCATION_PERMISSION;

public class DashboardActivity extends BaseActivity implements View.OnClickListener,
        DeliveryAdapter.deliveryCallback,
        PickupAdapter.pickupCallback,
        BottomSheet.BottomSheetEventListener {

    boolean doubleBackToExitPressedOnce = false;
    AppBarConfiguration mAppBarConfiguration;
    NavController mNavController;
    NavHostFragment mNavHostFragment;
    Toolbar mToolbar;
    DrawerLayout mDrawerLayout;
    ActivityDashboardBinding mActivityBinding;

    RiderDashboardViewModel mViewModel;
    boolean isShowDialog;
    Location fusLocation = null;
    String mLat = "", mLon = "";

    FragmentCommunicator fragmentCommunicator;

    void initUI() {
        mToolbar = findViewById(R.id.toolbar);
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        mNavController = mNavHostFragment.getNavController();
        //NavigationView navView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph())
                .setOpenableLayout(mDrawerLayout).build();
        NavigationUI.setupWithNavController(mToolbar, mNavController, mAppBarConfiguration);
        mNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                switch (destination.getId()) {
                    case R.id.changeStatusFragment:
                    case R.id.deliversDetailFragment:
                    case R.id.cancellationFragment2:
                        hideAndGone(true, destination.getLabel().toString());
                        break;
                    default:
                        hideAndGone(false, "");
                        break;
                }
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //   navigate(mNavHostFragment);
                Intent intent = new Intent(DashboardActivity.this, NotificationActivity.class);
                startActivity(intent);
                return false;
            }
        });

        //LocationManager Intil



    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
        Toast.makeText(this,"This",Toast.LENGTH_SHORT).show();

        initUI();

    }


    @Override
    public void onClick(View view) {
        /*NavDirections action =
                SpecifyAmountFragmentDirections
                        .actionSpecifyAmountFragmentToConfirmationFragment();
        Navigation.findNavController(view).navigate(action);*/
        Log.d("click", "1");
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDrawer();

        if (!AppUtil.checkPermissionForLocation(this)) {
            showDialogForLocation();
        } else {
            sendLocationInFiveMin();
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavHostFragment.getChildFragmentManager().getBackStackEntryCount() == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
        }

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void hideAndGone(boolean type, String label) {
        if (type) {
            mActivityBinding.centerImageTool.setVisibility(View.GONE);
            mActivityBinding.toolbar.getMenu().findItem(R.id.action_notification).setVisible(false);
            mActivityBinding.toolbarTitleTxt.setVisibility(View.VISIBLE);
            mActivityBinding.toolbarTitleTxt.setText(label);
            mActivityBinding.toolbar.setNavigationIcon(R.drawable.forword_arrow);
        } else {
            mActivityBinding.centerImageTool.setVisibility(View.VISIBLE);
            mActivityBinding.toolbarTitleTxt.setVisibility(View.GONE);
            mActivityBinding.toolbar.getMenu().findItem(R.id.action_notification).setVisible(true);
            mActivityBinding.toolbar.setNavigationIcon(R.drawable.menu_bar);
        }
    }


    private void initDrawer() {
        mActivityBinding.drawerAction.drawerProfileLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(0, null);
            }
        });

        mActivityBinding.drawerAction.drawerStatementLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(1, null);
            }
        });
        mActivityBinding.drawerAction.drawerSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(2, null);

            }
        });
        mActivityBinding.drawerAction.drawerSupportLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataManager.getINSTANCE().isNetworkConnected(DashboardActivity.this))
                    startActivity(3, null);

            }
        });

        mActivityBinding.drawerAction.drawerLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(4, null);
            }
        });

        mActivityBinding.drawerAction.drawerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityBinding.drawerAction.depositLL.setVisibility(View.VISIBLE);
                mActivityBinding.drawerAction.drawerTxt.setVisibility(View.INVISIBLE);
            }
        });

        mActivityBinding.drawerAction.depositLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivityBinding.drawerAction.depositLL.setVisibility(View.INVISIBLE);
                mActivityBinding.drawerAction.drawerTxt.setVisibility(View.VISIBLE);
            }
        });

        mActivityBinding.headerLayoutDrawer.nameDrawer.setText(mBasePreferenceManager.getUserName());
        mActivityBinding.headerLayoutDrawer.compNameDrawer.setText(mBasePreferenceManager.getMobileNum());

       /* Glide.with(this) .load(mBasePreferenceManager.getProfileImg())
                           .into(mActivityBinding.headerLayoutDrawer.profileDrawer);*/

        AppUtil.setImg(this, mBasePreferenceManager.getProfileImg(), mActivityBinding.headerLayoutDrawer.profileDrawer);
        getCodToDeposit(this);
    }

    void startActivity(int pos, ShipmentModel mData) {
        switch (pos) {
            case 0:
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent2 = new Intent(DashboardActivity.this, StatementActivity.class);
                startActivity(intent2);
                break;
            case 2:
                Intent intent3 = new Intent(DashboardActivity.this, SettingActivity.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4 = new Intent(DashboardActivity.this, SupportActivity.class);
                startActivity(intent4);
                break;
            case 4:
                MyUtil.showAlertLogout(DashboardActivity.this);
                mActivityBinding.drawerLayout.close();
                break;
            case 5:
            case 6:
                Intent intent5 = new Intent(DashboardActivity.this, ProductDetailActivity.class);
                intent5.putExtra("from", pos);
                intent5.putExtra("lat", mLat);
                intent5.putExtra("lon", mLon);
                intent5.putExtra("data", mData);
                startActivity(intent5);
                break;
            default:
                break;


        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mActivityBinding.drawerLayout.isOpen())
                    mActivityBinding.drawerLayout.close();
            }
        }, 500);
    }


    @Override
    public void onAdapterClick(ShipmentModel value) {
        if (value.getStatus() == 2) {
            showAlertForPickup(value);
        } else if (value.getStatus() == 3) {
            showAlertForTransit(value);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("data", value);
            mNavController.navigate(R.id.action_homeFragment_to_changeStatusFragment, bundle);

        }

    }

    @Override
    public void onDeliveryDetail(ShipmentModel pos) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", pos);
        mNavController.navigate(R.id.action_homeFragment_to_deliversDetailFragment, bundle);

    }

    @Override
    public void bottomSheetClick(int pos, ShipmentModel mData) {
        switch (pos) {
            case 1:
                //  mNavController.navigate(R.id.action_changeStatusFragment_to_deliversDetailFragment);
                checkLocationPermission(5, mData);
                break;
            case 2:
                checkLocationPermission(6, mData);
                break;
            case 3:
                //mNavController.navigate(R.id.action_changeStatusFragment_to_deliversDetailFragment);
                break;
            case 4:
                // mNavController.navigate(R.id.action_changeStatusFragment_to_deliversDetailFragment);
                if (mData.getMerchant().getMobile() != null)
                    if (ContextCompat.checkSelfPermission(DashboardActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + mData.getMerchant().getMobile()));
                        startActivity(intent);
                    }
                break;
            case 5:
                showAlertForBackToHub(mData);

                break;
        }
       /* getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment, new DeliversDetailFragment())
                .addToBackStack(null)
                .commit();*/

    }

    void checkLocationPermission(int type, ShipmentModel mData) {
        if (AppUtil.checkPermissionForLocation(this)) {
            if (getLastBestLocation() == null) {
                Toast.makeText(DashboardActivity.this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
                return;
            }

            mLat = String.valueOf(getLastBestLocation().getLatitude());
            mLon = String.valueOf(getLastBestLocation().getLongitude());
            startActivity(type, mData);

        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                try {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION);
                } catch (Exception e) {
                    //     AppUtil.showAlert("Please allow permission for uploading file", activity);
                    e.printStackTrace();
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION);
            }
        }

    }

    public void getCodToDeposit(Context context) {
        Call<CODStatementModel> call = new ApiManagerImp().getRider_COD_statement(mBasePreferenceManager.getUserToken(), "1");
        call.enqueue(new Callback<CODStatementModel>() {
            @Override
            public void onResponse(Call<CODStatementModel> call, Response<CODStatementModel> response) {
                if (response.body() != null) {
                    CODStatementModel model = response.body();
                    mActivityBinding.drawerAction.depositAmount.setText("Tk." + model.getCodToDeposit());
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CODStatementModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (requestCode == LOCATION_PERMISSION) {
                if (getLastBestLocation() == null) {
                    // Toast.makeText(DashboardActivity.this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
                    return;
                }
                mLat = String.valueOf(getLastBestLocation().getLatitude());
                mLon = String.valueOf(getLastBestLocation().getLongitude());
            }
        } else {
            // showDialogForLocation();
        }

    }

    private Location getLastBestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
          /* if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            return null;
        }*/

        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (locationGPS == null && locationNet == null) {
           /* fusedLocationClient.getLastLocation().addOnCompleteListener(this, new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task != null) {
                        fusLocation = task.getResult();
                    }
                }
            });*/

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                                fusLocation = location;
                            }
                        }
                    });
            return fusLocation;
        }
        long GPSLocationTime = 0;
        if (null != locationGPS) {
            GPSLocationTime = locationGPS.getTime();
        }

        long NetLocationTime = 0;

        if (null != locationNet) {
            NetLocationTime = locationNet.getTime();
        }

        if (0 < GPSLocationTime - NetLocationTime) {
            return locationGPS;
        } else {
            return locationNet;
        }
    }

    public void showDialogForLocation() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView okBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView titleTxt = (TextView) dialog.findViewById(R.id.titleTxt);
        TextView hubName = (TextView) dialog.findViewById(R.id.shipIdTxt);
        TextView headingTitleTxt = (TextView) dialog.findViewById(R.id.headingTitleTxt);
        titleTxt.setText(getString(R.string.plse_provide_location));
        headingTitleTxt.setText(getString(R.string.permission));
        hubName.setText(getString(R.string.gps_permission));
        cancelBtn.setVisibility(View.GONE);
     /*   if (mSendTo.equals(Constant.MERCHANT)) {
          //  pickBtn.setText(R.string.picked_up);
            tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());
        } else {
           // pickBtn.setText(getString(R.string.delivered));
            tckParcel.setText("Take COD Payments Tk. ".concat(mData.getShipmentCost() + " \nand Handover the parcel to " + mData.getReceiverName()));
        }*/

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                if (ActivityCompat.shouldShowRequestPermissionRationale(DashboardActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                    try {
                        ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION);
                    } catch (Exception e) {
                        //     AppUtil.showAlert("Please allow permission for uploading file", activity);
                        e.printStackTrace();
                    }
                } else {
                    ActivityCompat.requestPermissions(DashboardActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, Constant.LOCATION_PERMISSION);
                }

            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    void sendLocationInFiveMin() {
        Timer timer = new Timer();
        TimerTask hourlyTask = new TimerTask() {
            @Override
            public void run() {
                // your code here...
              /*  runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Running...", Toast.LENGTH_SHORT).show();

                    }
                });*/
                locationApi();
            }
        };

// schedule the task to run starting now and then every hour...
        timer.schedule(hourlyTask, 1000, 1000 * 60 * 3);   // 1000*10*60 every 10 minut

    }

    void locationApi() {
        Log.i("arp", "----------location API called--------");
        String currentAddress = "";
        if (getLastBestLocation() != null) {
            mLat = String.valueOf(getLastBestLocation().getLatitude());
            mLon = String.valueOf(getLastBestLocation().getLongitude());
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> address = geocoder.getFromLocation(Double.parseDouble(mLat), Double.parseDouble(mLon), 5);
                currentAddress = address.get(0).getAddressLine(0);
            } catch (IOException e) {
                Log.i("arp", "location= "+ e.toString());
            }

            Call<JsonObject> call = new ApiManagerImp().riderSendCurrentLocation(mBasePreferenceManager.getUserToken(), currentAddress, mLat, mLon);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.body() != null) {
                        try {
                            JSONObject object = new JSONObject(String.valueOf(response.body()));
                            Log.i("arp", "locationapires= "+ object);
                        } catch (JSONException e) {
                            Log.i("arp", "locationapires= "+ e.toString());
                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Log.i("arp", "locationapires= "+ t.getMessage());
                }
            });

        }

    }

    //////////////////////////update status direct/////////////////////////////////////////////////

    public void passVal(FragmentCommunicator fragmentCommunicator) {
        this.fragmentCommunicator = fragmentCommunicator;
    }

    public void showAlertForPickup(ShipmentModel valu) {
     /*   final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_congrets_layout);*/

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView pickBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView tckParcel = (TextView) dialog.findViewById(R.id.shipIdTxt);
        TextView headingTitleTxt = (TextView) dialog.findViewById(R.id.headingTitleTxt);

        pickBtn.setText(R.string.picked_up);
        headingTitleTxt.setText("Congratulations!");
        tckParcel.setText("Take the parcel from ".concat(valu.getMerchant().getName()) + " and\nsend to " + valu.getReceiverName());


        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riderUpdateStatus(String.valueOf(valu.getId()), Constant.PICKUP);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    public void showAlertForTransit(ShipmentModel valu) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView okBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView tckParcel = (TextView) dialog.findViewById(R.id.titleTxt);

     /*   if (mSendTo.equals(Constant.MERCHANT)) {
          //  pickBtn.setText(R.string.picked_up);
            tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());
        } else {
           // pickBtn.setText(getString(R.string.delivered));
            tckParcel.setText("Take COD Payments Tk. ".concat(mData.getShipmentCost() + " \nand Handover the parcel to " + mData.getReceiverName()));
        }*/

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riderUpdateStatus(String.valueOf(valu.getId()), Constant.TRANSIT);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    public void showAlertForBackToHub(ShipmentModel valu) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView okBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView tckParcel = (TextView) dialog.findViewById(R.id.titleTxt);
        TextView shipIdTxt = (TextView) dialog.findViewById(R.id.shipIdTxt);
        TextView ET_return_note = (TextView) dialog.findViewById(R.id.ET_return_note);
        ET_return_note.setVisibility(View.VISIBLE);
        shipIdTxt.setText(getString(R.string.hubhint));

     /*   if (mSendTo.equals(Constant.MERCHANT)) {
          //  pickBtn.setText(R.string.picked_up);
            tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());
        } else {
           // pickBtn.setText(getString(R.string.delivered));
            tckParcel.setText("Take COD Payments Tk. ".concat(mData.getShipmentCost() + " \nand Handover the parcel to " + mData.getReceiverName()));
        }*/

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ET_return_note.getText().toString().trim().length()==0){
                    ET_return_note.setError("Reason is required");
                }else {
                    riderUpdateStatus_WithReason(String.valueOf(valu.getId()), ET_return_note.getText().toString().trim());
                    dialog.dismiss();
                }
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    void riderUpdateStatus(String shipID, String status) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShipmentUpdate(mBasePreferenceManager.getUserToken(), shipID, status, mBasePreferenceManager.getOneTimeOtp());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("res", String.valueOf(object));
                        Toast.makeText(DashboardActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        if (status.equals(Constant.RETURN)) {
                            // Navigation.findNavController(DashboardActivity.this, R.id.inTransitTxtBtn).navigate(R.id.action_changeStatusFragment_to_homeFragment);
                            onBackPressed();
                        } else {
                            fragmentCommunicator.passData("", DL_CAMERA_PERMISSION);

                        }

                        //  Navigation.findNavController(requireActivity(), R.id.inTransitTxtBtn).navigate(R.id.action_changeStatusFragment_to_homeFragment);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }

    void riderUpdateStatus_WithReason(String shipID, String reason) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShipmentUpdate_WithReason(mBasePreferenceManager.getUserToken(), shipID, Constant.RETURN,
                mBasePreferenceManager.getOneTimeOtp(),reason);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                       // Log.i("res", "riderestatus** "+object);
                        Toast.makeText(DashboardActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        if (Constant.RETURN.equals(Constant.RETURN)) {
                            // Navigation.findNavController(DashboardActivity.this, R.id.inTransitTxtBtn).navigate(R.id.action_changeStatusFragment_to_homeFragment);
                            onBackPressed();
                        } else {
                            fragmentCommunicator.passData("", DL_CAMERA_PERMISSION);

                        }

                        //  Navigation.findNavController(requireActivity(), R.id.inTransitTxtBtn).navigate(R.id.action_changeStatusFragment_to_homeFragment);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }
}