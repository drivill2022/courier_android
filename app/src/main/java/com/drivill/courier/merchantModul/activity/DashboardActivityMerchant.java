package com.drivill.courier.merchantModul.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.fragment.BottmSheetDialog;
import com.drivill.courier.fragment.DeliveryStatusFragment;
import com.drivill.courier.merchantModul.fragment.MerchantHomeFragment;
import com.drivill.courier.merchantModul.fragment.ScheduledFragment;
import com.drivill.courier.utils.Constant;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.activity.notificationActivity.NotificationActivity;
import com.drivill.courier.activity.ProfileActivity;
import com.drivill.courier.activity.SettingActivity;
import com.drivill.courier.activity.supportActivity.SupportActivity;
import com.drivill.courier.databinding.ActivityDashboardMerchantBinding;
import com.drivill.courier.merchantModul.adapter.RecentDeliveryAdapter;
import com.drivill.courier.merchantModul.interfases.FragmentListener;
import com.drivill.courier.merchantModul.model.MerchantNotificationModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.MyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivityMerchant extends BaseActivity implements FragmentListener,
        View.OnClickListener, RecentDeliveryAdapter.RecentAdapterClick {
    ActivityDashboardMerchantBinding mBinding;
    ActionBarDrawerToggle mDrawerToggle;
    NavHostFragment mNavHostFragment;
    boolean doubleBackToExitPressedOnce = false;
    TextView mNotCount;
    FrameLayout frameLayout;
    Bundle bundle=new Bundle();
    private RelativeLayout bottomSheetParentLayout;
    private BottomSheetBehavior mBottomSheetBehaviour;

    void initUI() {
        mNavHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragmentMechant);
        NavController mNavController = mNavHostFragment.getNavController();
        //bottmSheetDialogFragment =BottmSheetDialogFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragmentMechant);
        //NavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration mAppBarConfiguration = new AppBarConfiguration.Builder(mNavController.getGraph())
                .setOpenableLayout(mBinding.drawerLayout)
                .build();
        setSupportActionBar(mBinding.toolbar);
        NavigationUI.setupWithNavController(mBinding.toolbar, mNavController, mAppBarConfiguration);

        mNavController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.scheduledFragment:
                    case R.id.cancellationFragment:
                        if (destination.getLabel() != null) {
                            hideAndGone(true, destination.getLabel().toString());
                        }
                        break;
                    default:
                        hideAndGone(false, "");
                        break;
                }
            }
        });

        mBinding.toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //   navigate(mNavHostFragment);
                if (item.getItemId() == R.id.shareApp) {
                    Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(DashboardActivityMerchant.this, NotificationActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        mBinding.bottomSheetParent32.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", "All");
                ldf.setArguments(args);
                switchFragment(ldf);
            }
        });

        mBinding.bottomSheetParent32.requested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", Constant.REQUESTED);
                ldf.setArguments(args);
                switchFragment(ldf);
            }
        });

        mBinding.bottomSheetParent32.transit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                Log.e("gdsagjdjagsdjsd","dagshdgahjsgdhjgdas"+Constant.SCHEDULED.toString());

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", Constant.TRANSITT);
                ldf.setArguments(args);
                switchFragment(ldf);

            }
        });

        mBinding.bottomSheetParent32.ontheway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", Constant.ONGOING);
                ldf.setArguments(args);
                switchFragment(ldf);
            }
        });

        mBinding.bottomSheetParent32.delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", Constant.DELIVERED);
                ldf.setArguments(args);
                switchFragment(ldf);

            }
        });

        mBinding.bottomSheetParent32.retn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", Constant.CANCELLED);
                ldf.setArguments(args);
                switchFragment(ldf);
            }
        });

      /*  mBinding.bottomSheetParent32.rlTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
                mBinding.addNewBtn.setVisibility(View.VISIBLE);
                mBinding.dropBtn.setVisibility(View.GONE);

                ScheduledFragment ldf = new ScheduledFragment ();
                Bundle args = new Bundle();
                args.putString("status", Constant.DELIVERED);
                ldf.setArguments(args);
                switchFragment(ldf);

            }
        });*/



        mBinding.bottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mBinding.bottomNV.setSelected(true);

                switch (item.getItemId()) {
                    case R.id.homeBV:
                        switchFragment(new MerchantHomeFragment());
                        break;
                    case R.id.ern_payBV:
                        Intent intent = new Intent(DashboardActivityMerchant.this, MerchantStatementActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.helpBV:
                        Intent intent1 = new Intent(DashboardActivityMerchant.this, NotificationActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.messageBV:
                        //  case R.id.AddBV:
                        DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                        // If the navigation drawer is not open then open it, if its already open then close it.
                        if(!navDrawer.isDrawerOpen(GravityCompat.END)) navDrawer.openDrawer(GravityCompat.END);
                        else navDrawer.closeDrawer(GravityCompat.START);
                       /* Intent intent4 = new Intent(DashboardActivityMerchant.this, TrackingActivity.class);
                        startActivity(intent4);*/
                        break;
                }
                return false;
            }
        });

        mBinding.addNewBtn.setOnClickListener(v -> {
           // getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment_container, BottmSheetDialogFragment).commit();

            //animateViewFromBottomToTop();


            /*Transition transition = new Slide(Gravity.BOTTOM);
            transition.setDuration(1000);
            transition.addTarget(R.id.bottom_sheet_parent);
            bottomSheetParentLayout = findViewById(R.id.bottom_sheet_parent);
            TransitionManager.beginDelayedTransition(bottomSheetParentLayout, transition);
            mBinding.bottomSheetParent.setVisibility(View.VISIBLE);*/
            //mBinding.bottomSheetParent.animate().alpha(0.0f);
           // mBinding.bottomSheetParent.animate().alpha(0.0f).setDuration(5000);
            mBinding.addNewBtn.setVisibility(View.GONE);
            mBinding.dropBtn.setVisibility(View.VISIBLE);
            mBinding.bottomSheetParent32.rlTop.setVisibility((View.VISIBLE));
            //v.animate().alpha(0.0f).setDuration(2000);
            //BottmSheetDialog bottomSheet = new BottmSheetDialog();
           // bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");


        });

        mBinding.dropBtn.setOnClickListener(v -> {

            //BottmSheetDialogFragment bottomSheet = new BottmSheetDialogFragment();
            //bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
           // bottomSheet.dismiss();

            mBinding.bottomSheetParent32.rlTop.setVisibility(View.GONE);
            mBinding.addNewBtn.setVisibility(View.VISIBLE);
            mBinding.dropBtn.setVisibility(View.GONE);
        });




       // mBottomSheetBehaviour = BottomSheetBehavior.from(bottomSheetParentLayout);


           /* @Override
            public void onClick(View view) {
                DataManager.getINSTANCE().setCurrentItemListSize(0);
                Intent intent = new Intent(DashboardActivityMerchant.this, PackagingActivity.class);
                startActivity(intent);
            }
        });*/

        mBinding.headerLayoutDrawerMerchant.btnShip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.getINSTANCE().setCurrentItemListSize(0);
                Intent intent = new Intent(DashboardActivityMerchant.this, PackagingActivity.class);
                startActivity(intent);
            }
        });

        mBinding.drawerActionMerchant.drawerProfileLL.setOnClickListener(this);
        mBinding.drawerActionMerchant.drawerLogout.setOnClickListener(this);
        mBinding.drawerActionMerchant.drawerSetting.setOnClickListener(this);
        mBinding.drawerActionMerchant.drawerSupportLL.setOnClickListener(this);
        mBinding.drawerActionMerchant.ernPayLL.setOnClickListener(this);
        mBinding.drawerActionMerchant.getQuote.setOnClickListener(this);
        mBinding.drawerActionMerchant.trukingLL.setOnClickListener(this);
        mBinding.drawerActionMerchant.shippingHistory.setOnClickListener(this);
       // mBinding.drawerActionMerchant.shippingPkg.setOnClickListener(this);
        mBinding.drawerActionMerchant.offerLL.setOnClickListener(this);

       /*     Glide.with(this)
                    .load(mBasePreferenceManager.getProfileImg())
                    .into(mActivityBinding.headerLayoutDrawer.profileDrawer);*/

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard_merchant);
        initUI();


    }

    public static void animateViewFromBottomToTop(final View view){

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                final int TRANSLATION_Y = view.getHeight();
                view.setTranslationY(TRANSLATION_Y);
                view.setVisibility(View.GONE);
                view.animate()
                        .translationYBy(-TRANSLATION_Y)
                        .setDuration(500)
                        .setStartDelay(200)
                        .setListener(new AnimatorListenerAdapter() {

                            @Override
                            public void onAnimationStart(final Animator animation) {

                                view.setVisibility(View.VISIBLE);
                            }
                        })
                        .start();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        mBinding.headerLayoutDrawerMerchant.nameDrawer.setText(mBasePreferenceManager.getUserName());
        mBinding.headerLayoutDrawerMerchant.compNameDrawer.setText(mBasePreferenceManager.getMobileNum());
        AppUtil.setImgCircle(this, mBasePreferenceManager.getProfileImg(), mBinding.headerLayoutDrawerMerchant.profileDrawer);

        getNotificationCount(mBasePreferenceManager.getUserToken());

    }

/*    @Override
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

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu_merchant, menu);
        menu.findItem(R.id.action_notification).setActionView(R.layout.notification_bag_layout);
        View count = menu.findItem(R.id.action_notification).getActionView();

        frameLayout = (FrameLayout) count.findViewById(R.id.bagCountFL);
        mNotCount = (TextView) count.findViewById(R.id.bagCountTxt);


        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivityMerchant.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    void hideAndGone(boolean type, String label) {
        if (type) {
            mBinding.centerImageTool.setVisibility(View.GONE);
            frameLayout.setVisibility(View.GONE);
            // mBinding.toolbar.getMenu().findItem(R.id.action_notification).setVisible(false);
            // mBinding.toolbar.getMenu().findItem(R.id.shareApp).setVisible(false);
            mBinding.toolbarTitleTxt.setVisibility(View.VISIBLE);
            mBinding.toolbar.setNavigationIcon(R.drawable.forword_arrow);
        } else {
            mBinding.centerImageTool.setVisibility(View.VISIBLE);
            mBinding.toolbarTitleTxt.setVisibility(View.GONE);
            // mBinding.toolbar.getMenu().findItem(R.id.action_notification).setVisible(true);
            //  mBinding.toolbar.getMenu().findItem(R.id.shareApp).setVisible(true);
            mBinding.toolbar.setNavigationIcon(R.drawable.menu_bar);

            if (frameLayout != null)
                frameLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void changeToolTitle(String title) {
        Log.i("arp","title= "+ title);
        if(title.equalsIgnoreCase("Current Order")){
            mBinding.toolbarTitleTxt.setText(getResources().getString(R.string.req_orders));
        }else {
            mBinding.toolbarTitleTxt.setText(title);
        }

    }

    @Override
    public void onClick(View view) {
        openActivity(view.getId());
    }

    @SuppressLint("NonConstantResourceId")
    void openActivity(int type) {
        switch (type) {
            case R.id.drawerProfileLL:
                Intent intent = new Intent(DashboardActivityMerchant.this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.shippingHistory:
                Intent intent1 = new Intent(DashboardActivityMerchant.this, SeeAllItemActivity.class);
                DataManager.from = "";
                startActivity(intent1);
                break;
            /*case R.id.shippingPkg:
                DataManager.getINSTANCE().setCurrentItemListSize(0);
                Intent intent2 = new Intent(DashboardActivityMerchant.this, PackagingActivity.class);
                startActivity(intent2);
                break;*/
            case R.id.ernPayLL:
                Intent intent3 = new Intent(DashboardActivityMerchant.this, MerchantStatementActivity.class);
                startActivity(intent3);
                break;
            case R.id.getQuote:
                // Intent intent4 = new Intent(DashboardActivityMerchant.this, TrackingActivity.class);
                // startActivity(intent4);
                break;
            case R.id.trukingLL:
                Toast.makeText(this,"Coming Soon",Toast.LENGTH_SHORT).show();
                /*Intent intent4 = new Intent(DashboardActivityMerchant.this, TrackingActivity.class);
                startActivity(intent4);*/
                break;
            case R.id.offerLL:
                break;
            case R.id.drawerSetting:
                Intent intent7 = new Intent(DashboardActivityMerchant.this, SettingActivity.class);
                startActivity(intent7);
                break;
            case R.id.drawerSupportLL:
                if (DataManager.getINSTANCE().isNetworkConnected(this)) {
                    Intent intent8 = new Intent(DashboardActivityMerchant.this, SupportActivity.class);
                    startActivity(intent8);
                }

                break;
            case R.id.drawerLogout:
                MyUtil.showAlertLogout(DashboardActivityMerchant.this);
                break;

        }
    }


    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragmentMechant, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void adapterClick(String shipID) {
        showAlertDialogComplain(shipID);
    }

    void showAlertDialogComplain(String shipID) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_complain_layout);


        TextView title, okTxtBtn, cancelBtn;
        EditText amountET;
        okTxtBtn = (TextView) dialog.findViewById(R.id.okTxt);
        cancelBtn = (TextView) dialog.findViewById(R.id.cancelTxt);
        title = (TextView) dialog.findViewById(R.id.titleTxt);
        amountET = (EditText) dialog.findViewById(R.id.amountET);


        String riderAmount, merchantAmount;
        title.setText("Enter Your Feedback");


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountET.getText().toString().isEmpty()) {
                    Toast.makeText(DashboardActivityMerchant.this, "Please Enter Message", Toast.LENGTH_SHORT).show();
                } else {
                    // depositAmountApi(amountET.getText().toString());
                    sendComplain(shipID, amountET.getText().toString().trim());
                    dialog.dismiss();
                }
            }
        });
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    void sendComplain(String shiId, String cmpTxt) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().merchantShipComplain(
                mBasePreferenceManager.getUserToken(), shiId, cmpTxt
        );
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        showMessage(object.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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

    public void getNotificationCount(String token) {

        Call<MerchantNotificationModel> call = new ApiManagerImp().getMerchantNotificationList(token);
        call.enqueue(new Callback<MerchantNotificationModel>() {
            @Override
            public void onResponse(Call<MerchantNotificationModel> call, Response<MerchantNotificationModel> response) {
                Log.d("res", "");
                if (response.body() != null) {
                    boolean isNew = false;
                    int count = 0;
                    MerchantNotificationModel model = response.body();

                    for (MerchantNotificationModel.Datum data : model.getData()) {
                        if (data.getIsViewed().equals("0")) {
                            isNew = true;
                            count++;
                        }
                    }

                    if (isNew) {
                        mNotCount.setVisibility(View.VISIBLE);
                        mNotCount.setText(String.valueOf(count));
                    } else {
                        mNotCount.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MerchantNotificationModel> call, Throwable t) {
                Log.d("fel", "");
            }
        });
    }
}