package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.SendOtpNumberBinding;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.MyAnimation;
import com.drivill.courier.utils.MyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOTPActivity extends BaseActivity implements View.OnFocusChangeListener {


    SendOtpNumberBinding mBinding;
    int intentType;
    ApiManagerImp mApiManagerImp;
    BottomSheetBehavior behavior;
    int maxHeight, height;

    void initUI() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        height = displayMetrics.heightPixels;

        bottomSheet();
        mBinding.getRoot().setOnFocusChangeListener(this);
        mBinding.mobileET.setOnFocusChangeListener(this);
        MyAnimation.startAnimTopToBottom(this, mBinding.mobileET);
        mBinding.buttonOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataManager.getINSTANCE().isNetworkConnected(getApplicationContext()))
                    valid();

            }
        });

    /*    mBinding.mobileET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startTransition();


            }
        });*/

        mBinding.mobileET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                maxHeight = (int) (height * 0.80);
                behavior.setPeekHeight(maxHeight);
                behavior.setDraggable(false);
                return false;
            }
        });

        viewVisibility();
    }

    private void startTransition() {

        // mBinding.constraintLayout4.setTransition(R.id.startAnim);
        // mBinding.constraintLayout4.setTransitionDuration(200);
        // mBinding.constraintLayout4.transitionToEnd();

    }

    private void endTransition() {

        //mBinding.constraintLayout4.setTransition(R.id.endAnim);
        // mBinding.constraintLayout4.setTransitionDuration(200);
        //mBinding.constraintLayout4.transitionToStart();

    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentType = getIntent().getIntExtra("from", 0); //2=forgotpswd
        mApiManagerImp = new ApiManagerImp();
        MyUtil.getFullScreen(this);
        MyAnimation.runFadeInAnimation(SendOTPActivity.this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.send_otp_number);
        initUI();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        if (ev.getAction() == 0) {

            maxHeight = (int) (height * 0.70);
            behavior.setPeekHeight(maxHeight);
            behavior.setDraggable(false);
        }
        return super.dispatchTouchEvent(ev);
    }


    void valid() {
        String num = mBinding.mobileET.getText().toString();
        if (num.isEmpty()) {
            onError(getResources().getString(R.string.enter_mobile_num));

        } else if (AppUtil.isValidPhone(num)) {
            if (intentType == Constant.FORGOT) {
                if (mBasePreferenceManager.getIsRider()) {
                    sendOTPForgot_API(num);
                } else {
                    forgot_APIForMerchant(num);
                }
            } else {
                if (mBasePreferenceManager.getIsRider()) {
                    checkMobileNumFirst(num);
                } else {
                    checkMobileNumFirstMerchant(num);
                }

            }

        } else {
            // Toast.makeText(getApplicationContext(), (R.string.mobile_num_is_invalid), Toast.LENGTH_SHORT).show();
            onError(getResources().getString(R.string.mobile_num_is_invalid));
        }
    }

    void viewVisibility() {

        if (intentType == 2) {
            mBinding.enterMobileTxt.setText(getResources().getString(R.string.forgot_password2));
            mBinding.pleaseHint.setText(getResources().getString(R.string.enter_mobile_num)
                    .concat(". ")
                    .concat(getResources().getString(R.string.weWillSendYou)));

        } else {
            mBinding.enterMobileTxt.setText(getResources().getString(R.string.enter_num));
            mBinding.pleaseHint.setText(getResources().getString(R.string.weWillSendYou));
        }

    }

    void sendOTP_API(String mobile, String otp_for) {  // Rider
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        Call<JsonObject> call = mApiManagerImp.rideSendOtp(mobile, otp_for);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //  DataManager.getINSTANCE().hideProgressBar();
                hideLoading();
                Log.i("res==>", String.valueOf(response.body()));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        mBasePreferenceManager.setOneTimeOtp(object.getString("otp"));
                        Toast.makeText(getApplicationContext(),
                                object.getString("message"),
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SendOTPActivity.this, OTPVerificationActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("error")) {
                                // object.getJSONArray("error").get(0);
                                //  Toast.makeText(getApplicationContext(), object.getJSONArray("error").toString(), Toast.LENGTH_SHORT).show();
                                onError(object.getJSONArray("error").get(0).toString());
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // DataManager.getINSTANCE().hideProgressBar();
                //  Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                onError(getString(R.string.try_again));
                hideLoading();
            }
        });

    }

    void sendOTPForgot_API(String mobile) {   // Rider
        showLoading();
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        Call<JsonObject> call = mApiManagerImp.rideSendForgotOtp(mobile);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //  DataManager.getINSTANCE().hideProgressBar();
                hideLoading();
                Log.i("res==>", String.valueOf(response.body()));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        mBasePreferenceManager.setOneTimeOtp(object.getString("otp"));
                        mBasePreferenceManager.setUserId(object.getString("id"));
                        mBasePreferenceManager.setMobileNum(mBinding.mobileET.getText().toString());

                        Toast.makeText(getApplicationContext(),
                                object.getString("message"),
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SendOTPActivity.this, OTPVerificationActivity.class);
                        intent.putExtra("from", Constant.FORGOT);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("error")) {
                                // object.getJSONArray("error").get(0);
                                //  Toast.makeText(getApplicationContext(), object.getJSONArray("error").toString(), Toast.LENGTH_SHORT).show();
                                onError(object.getJSONArray("error").get(0).toString());
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // DataManager.getINSTANCE().hideProgressBar();
                //  Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                onError(getString(R.string.try_again));
                hideLoading();
            }
        });

    }

    void checkMobileNumFirst(String mobile) {  // Rider
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        showLoading();
        Call<JsonObject> call = mApiManagerImp.riderMobileChecking(mobile);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("res==>", String.valueOf(response.body()));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("t", String.valueOf(object));
                        mBasePreferenceManager.setMobileNum(mBinding.mobileET.getText().toString());
                        sendOTP_API(mBinding.mobileET.getText().toString(), "2");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // DataManager.getINSTANCE().hideProgressBar();
                    hideLoading();
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("error")) {
                                StringBuilder errors = new StringBuilder();
                                for (int i = 0; i < object.getJSONArray("error").length(); i++) {
                                    errors.append(object.getJSONArray("error").get(i).toString());
                                    errors.append("\n");
                                }
                                // object.getJSONArray("error").get(0);
                                Toast.makeText(getApplicationContext(), errors, Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("fail", String.valueOf(t));
                //DataManager.getINSTANCE().hideProgressBar();
                hideLoading();
                // Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                onError(getString(R.string.try_again));
            }
        });

    }


    ///////////////////////////////////////////////////////////////////////////
    // Merchant Api below
    ///////////////////////////////////////////////////////////////////////////
    void checkMobileNumFirstMerchant(String mobile) {
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        showLoading();
        Call<JsonObject> call = mApiManagerImp.MobileCheckingMerchant(mobile);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.i("res==>", String.valueOf(response.body()));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("t", String.valueOf(object));
                        mBasePreferenceManager.setMobileNum(mBinding.mobileET.getText().toString());
                        sendOTP_APIForMerchant(mBinding.mobileET.getText().toString(), "2");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // DataManager.getINSTANCE().hideProgressBar();
                    hideLoading();
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("error")) {
                                StringBuilder errors = new StringBuilder();
                                for (int i = 0; i < object.getJSONArray("error").length(); i++) {
                                    errors.append(object.getJSONArray("error").get(i).toString());
                                    errors.append("\n");
                                }
                                // object.getJSONArray("error").get(0);
                                Toast.makeText(getApplicationContext(), errors, Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("fail", String.valueOf(t));
                //DataManager.getINSTANCE().hideProgressBar();
                hideLoading();
                // Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                onError(getString(R.string.try_again));
            }
        });

    }

    void sendOTP_APIForMerchant(String mobile, String otp_for) {
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        Call<JsonObject> call = mApiManagerImp.sendOtpMerchant(mobile, otp_for);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //  DataManager.getINSTANCE().hideProgressBar();
                hideLoading();
                Log.i("res==>", String.valueOf(response.body()));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        mBasePreferenceManager.setOneTimeOtp(object.getString("otp"));
                        Toast.makeText(getApplicationContext(),
                                object.getString("message"),
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SendOTPActivity.this, OTPVerificationActivity.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("error")) {
                                // object.getJSONArray("error").get(0);
                                //  Toast.makeText(getApplicationContext(), object.getJSONArray("error").toString(), Toast.LENGTH_SHORT).show();
                                onError(object.getJSONArray("error").get(0).toString());
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // DataManager.getINSTANCE().hideProgressBar();
                //  Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                onError(getString(R.string.try_again));
                hideLoading();
            }
        });

    }

    void forgot_APIForMerchant(String mobile) {
        showLoading();
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        Call<JsonObject> call = mApiManagerImp.forgotPasswordMerchant(mobile);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //  DataManager.getINSTANCE().hideProgressBar();
                hideLoading();
                Log.i("res==>", String.valueOf(response.body()));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        mBasePreferenceManager.setOneTimeOtp(object.getString("otp"));
                        mBasePreferenceManager.setUserId(object.getString("id"));
                        mBasePreferenceManager.setMobileNum(mBinding.mobileET.getText().toString());

                        Toast.makeText(getApplicationContext(),
                                object.getString("message"),
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SendOTPActivity.this, OTPVerificationActivity.class);
                        intent.putExtra("from", Constant.FORGOT);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("error")) {
                                // object.getJSONArray("error").get(0);
                                //  Toast.makeText(getApplicationContext(), object.getJSONArray("error").toString(), Toast.LENGTH_SHORT).show();
                                onError(object.getJSONArray("error").get(0).toString());
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                // DataManager.getINSTANCE().hideProgressBar();
                //  Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                onError(getString(R.string.try_again));
                hideLoading();
            }
        });

    }

    void bottomSheet() {
        behavior = BottomSheetBehavior.from(mBinding.bottomSheet);

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int maxHeight = (int) (height * 0.70);
        behavior.setPeekHeight(maxHeight);
        behavior.setDraggable(false);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    // behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    //   behavior.setPeekHeight(500, true);

                    //    assert behavior != null;
                    //BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_EXPANDED);

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        Log.d("back", "back");
        if (mBinding.enterMobileTxt.requestFocus()) {
            getWindow()
                    .setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

            Log.d("back", "focus");
        }

    }
}