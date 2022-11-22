package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.activity.DashboardActivityMerchant;
import com.drivill.courier.merchantModul.model.MerchantProfileModel;
import com.drivill.courier.model.model.ProfileModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.MyUtil;
import com.drivill.courier.utils.PrefsManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashUsingActivity extends FragmentActivity {
    ViewPager2 mViewPager2;
    TextView mAppLogo;
    private static final int NUM_PAGES = 4;
    MyPagerAdapter2 adapter;
    TextView skipTxt, mNextTxt;
    PrefsManager mPrefsManager;

    void initUI() {
        mViewPager2 = findViewById(R.id.vipager);
        skipTxt = findViewById(R.id.skipTxt);

        mAppLogo = findViewById(R.id.AppLogo);
        mAppLogo.setVisibility(View.INVISIBLE);

        adapter = new MyPagerAdapter2(SplashUsingActivity.this, NUM_PAGES);

        mViewPager2.setAdapter(adapter);

        skipTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextActivity();

            }
        });

        mNextTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mViewPager2.getCurrentItem() == 3) {
                    goNextActivity();
                } else
                    mViewPager2.setCurrentItem(mViewPager2.getCurrentItem() + 1);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyUtil.getFullScreen(this);
        setContentView(R.layout.activity_splash_using);

        mPrefsManager = new PrefsManager(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goNextActivity();
               /* if (mPrefsManager.getIsFirst()) {
                    initUI();
                    mPrefsManager.setIsFirst(false);
                } else
                    goNextActivity();*/
            }
        }, 4000);


    }

    void goNextActivity() {

      //  CheckAppVerson(SplashUsingActivity.this);

        if (mPrefsManager.getIS_LOGIN()) {
            if (Constant.isConnectingToInternet(this)) {
                if (mPrefsManager.getIsRider()) {
               /* Intent intent = new Intent(SplashUsingActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();*/
                    fetchProfileRider(mPrefsManager.getUserToken());
                } else {
             /*  Intent intent = new Intent(SplashUsingActivity.this, DashboardActivityMerchant.class);
                startActivity(intent);
                finish();
                */

                    fetchMerchantProfile(mPrefsManager.getUserToken());
                }
            } else {
                Toast.makeText(this, this.getResources().getString(R.string.check_internet), Toast.LENGTH_LONG).show();
            }


        } else {
            Intent intent = new Intent(SplashUsingActivity.this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public class MyPagerAdapter2 extends FragmentStateAdapter {
        int count;

        public MyPagerAdapter2(@NonNull FragmentActivity fragment, int count) {
            super(fragment);
            this.count = count;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new splashA();

                case 1:
                    return new splashB();

                case 2:
                    return new splashC();

                case 3:
                    return new splashD();

                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return count;
        }
    }

    public static class splashA extends Fragment {

        public splashA() {
            // Required empty public constructor
        }

        @Override
        public void onSaveInstanceState(@NonNull Bundle outState) {
            super.onSaveInstanceState(outState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.splash_a, container, false);
        }

    }

    public static class splashB extends Fragment {

        public splashB() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.splash_b1, container, false);
        }

    }

    public static class splashC extends Fragment {
        public splashC() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.splash_c, container, false);
        }
    }

    public static class splashD extends Fragment {

        public splashD() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.splash_d1, container, false);
        }

    }

    void fetchProfileRider(String tok) {
        Call<ProfileModel> call = new ApiManagerImp().riderProfile(tok);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.body() != null) {
                    ProfileModel model = response.body();
                    mPrefsManager.setIS_LOGIN(true);
                    mPrefsManager.setMobileNum(model.getMobile());
                    mPrefsManager.setEmailAddress(model.getEmail());
                    mPrefsManager.setUserName(model.getName());
                    mPrefsManager.setUserId(String.valueOf(model.getId()));
                    mPrefsManager.setProfileImg(Constant.getImageUrl(model.getPicture()));
                    if (model.getEmergency_no() != null)
                        mPrefsManager.set_emergencyNumMethod(String.valueOf(model.getEmergency_no()));
                    mPrefsManager.setAddress(model.getAddress());
                    mPrefsManager.setGENDER(model.getGender());
                    mPrefsManager.setHub_id(model.getHubId().toString());
                    mPrefsManager.setVehicle_type_id(model.getVehicleTypeId().toString());
                    try {
                        mPrefsManager.setLatitude(model.getLatitude());
                        mPrefsManager.setLongitude(model.getLongitude());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    mPrefsManager.setTotalDelivery(model.getDelivered());
                    mPrefsManager.setTotalEarn(model.getEarned());
                    mPrefsManager.setTotalYear(model.getYears());
                    Intent intent = new Intent(SplashUsingActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finishAffinity();

                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Log.d("t", String.valueOf(t));
            }
        });
    }

    void fetchMerchantProfile(String tok) {
        Call<MerchantProfileModel> call = new ApiManagerImp().getProfileMerchant(tok);
        call.enqueue(new Callback<MerchantProfileModel>() {
            @Override
            public void onResponse(Call<MerchantProfileModel> call, Response<MerchantProfileModel> response) {

                if (response.body() != null) {
                  //  Log.i("pkg**=","MerchantProf= "+ new Gson().toJson(response.body()));
                    MerchantProfileModel model = response.body();
                    mPrefsManager.setIS_LOGIN(true);
                    mPrefsManager.setMobileNum(model.getMobile());
                    mPrefsManager.setLatitude(model.getLatitude());
                    mPrefsManager.setLongitude(model.getLongitude());
                    mPrefsManager.setUserId(model.getMrid());
                    mPrefsManager.setEmailAddress(model.getEmail());
                    mPrefsManager.setUserName(model.getName());
                    mPrefsManager.setProfileImg(Constant.getImageUrl(model.getPicture()));
                    mPrefsManager.setBusinessLogo(Constant.getImageUrl(model.getBusinessLogo()));
                    mPrefsManager.setAddress(model.getAddress());
                    mPrefsManager.setPaymentMethod(String.valueOf(model.getPaymentMethod()));
                    mPrefsManager.set_emergencyNumMethod(String.valueOf(model.getEmergency_no()));
                    mPrefsManager.set_businessName(model.getBussName());

                    mPrefsManager.set_thana(model.getThana());
                    mPrefsManager.set_district(model.getDistrict());
                    mPrefsManager.set_division(model.getDivision());

                    mPrefsManager.setTotalDelivery(model.getAwards());
                    mPrefsManager.setTotalEarn(model.getEarned());
                    mPrefsManager.setTotalYear(model.getDelivered());

                    Intent intent = new Intent(SplashUsingActivity.this, DashboardActivityMerchant.class);
                    startActivity(intent);
                    finishAffinity();

                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MerchantProfileModel> call, Throwable t) {
                Log.d("t", String.valueOf(t));

            }
        });
    }
}