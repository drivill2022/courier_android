package com.drivill.courier.activity;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityOTPVarificationBinding;
import com.drivill.courier.interfaces.OTPVerification;
import com.drivill.courier.merchantModul.activity.SignUpActivityMerchant;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.GenericTextWatcher;
import com.drivill.courier.utils.MyAnimation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OTPVerificationActivity extends BaseActivity implements OTPVerification {
    int intentType;
    public static Context otpContext;
    ActivityOTPVarificationBinding mBinding;

    ShipmentModel mData;
    String msg = "", mSendTo = "", mStatus = "";
    TextView tvotp ;

    void initUI() {
        //For next index
        mBinding.editText1.addTextChangedListener(new GenericTextWatcher(mBinding.editText1, mBinding.editText2));
        mBinding.editText2.addTextChangedListener(new GenericTextWatcher(mBinding.editText2, mBinding.editText3));
        mBinding.editText3.addTextChangedListener(new GenericTextWatcher(mBinding.editText3, mBinding.editText4));
        mBinding.editText4.addTextChangedListener(new GenericTextWatcher(mBinding.editText4, null));
        //  mBinding.editText5.addTextChangedListener(new GenericTextWatcher(mBinding.editText5, mBinding.editText6));
        // mBinding.editText6.addTextChangedListener(new GenericTextWatcher(mBinding.editText6, null));

        //For prev index
        mBinding.editText1.setOnKeyListener(new eventListener(mBinding.editText1, null));
        mBinding.editText2.setOnKeyListener(new eventListener(mBinding.editText2, mBinding.editText1));
        mBinding.editText3.setOnKeyListener(new eventListener(mBinding.editText3, mBinding.editText2));
        mBinding.editText4.setOnKeyListener(new eventListener(mBinding.editText4, mBinding.editText3));
        //   mBinding.editText5.setOnKeyListener(new eventListener(mBinding.editText5, mBinding.editText4));
        //   mBinding.editText6.setOnKeyListener(new eventListener(mBinding.editText6, mBinding.editText5));

        hideAndGone();
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentType = getIntent().getIntExtra("from", 0);
        mData = (ShipmentModel) getIntent().getSerializableExtra("data");
        msg = getIntent().getStringExtra("msg");
        mSendTo = getIntent().getStringExtra("send");
        mStatus = getIntent().getStringExtra("status");

        MyAnimation.runFadeInAnimation(OTPVerificationActivity.this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_o_t_p_varification);
        otpContext = this;
        initUI();
        countdownStart();

        tvotp = findViewById(R.id.tvotp);
        tvotp.setText(mBasePreferenceManager.getOneTimeOtp());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void otpVerify(boolean otp) {
        StringBuilder builder = new StringBuilder();

        if (!mBinding.editText1.getText().toString().isEmpty() && !mBinding.editText2.getText().toString().isEmpty() &&
                !mBinding.editText3.getText().toString().isEmpty() && !mBinding.editText4.getText().toString().isEmpty()
            /* && !mBinding.editText5.getText().toString().isEmpty() && !mBinding.editText6.getText().toString().isEmpty()*/) {

            builder.append(mBinding.editText1.getText().toString());
            builder.append(mBinding.editText2.getText().toString());
            builder.append(mBinding.editText3.getText().toString());
            builder.append(mBinding.editText4.getText().toString());
            // builder.append(mBinding.editText5.getText().toString());
            // builder.append(mBinding.editText6.getText().toString());
            //  DataManager.getINSTANCE().showProgressBar(this);
            showLoading();
            if (builder.toString().equals(mBasePreferenceManager.getOneTimeOtp())) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //  DataManager.getINSTANCE().hideProgressBar();
                        hideLoading();
                        nextActivity();
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //  DataManager.getINSTANCE().hideProgressBar();
                        hideLoading();
                        mBinding.editText1.setText("");
                        mBinding.editText2.setText("");
                        mBinding.editText3.setText("");
                        mBinding.editText4.setText("");
                        //  mBinding.editText5.setText("");
                        //  mBinding.editText6.setText("");
                        mBinding.editText1.requestFocus();
                        Toast.makeText(getApplicationContext(), (R.string.invalid_otp), Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        }


    }


    class eventListener implements View.OnKeyListener {
        EditText mCurrentView, mPrev;

        eventListener(EditText currentView, EditText previousView) {
            this.mCurrentView = currentView;
            this.mPrev = previousView;
        }

        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_DEL &&
                    mCurrentView.getId() != R.id.editText1 && mCurrentView.getText().toString().isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                if (mPrev.getText() != null) {
                    mPrev.requestFocus();
                    return true;
                }

            }
            return false;
        }
    }


    void countdownStart() {
        mBinding.resendOtpTxt.setEnabled(false);
        mBinding.resendOtpTxt.setAlpha((float) .3);
        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                if (millisUntilFinished/1000 < 10) {
                    mBinding.countDownTxt.setText("00 : " + "0" + millisUntilFinished / 1000);
                } else {
                    mBinding.countDownTxt.setText("00 : " + millisUntilFinished / 1000);
                }
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                mBinding.countDownTxt.setText("retry!");
                mBinding.resendOtpTxt.setEnabled(true);
                mBinding.resendOtpTxt.setAlpha((float) 1);
            }

        }.start();
    }


    void nextActivity() {
        if (intentType == Constant.FORGOT) {
            Intent intent = new Intent(OTPVerificationActivity.this, ResetPasswordActivity.class);
            intent.putExtra("from", Constant.FORGOT);
            startActivity(intent);
            finish();
        } else if (intentType == 3) {
            riderUpdateStatus(String.valueOf(mData.getId()), mStatus);
        } else {
            if (mBasePreferenceManager.getIsRider()) {
                Intent intent = new Intent(OTPVerificationActivity.this, SignUpActivity.class);
                startActivity(intent);
                finishAffinity();
            } else {
                Intent intent = new Intent(OTPVerificationActivity.this, SignUpActivityMerchant.class);
                startActivity(intent);
                finishAffinity();
            }

        }

    }

    void hideAndGone() {
        if (intentType == 3) {
            mBinding.toolbar.setVisibility(View.VISIBLE);
            mBinding.matchOtpBtn.setVisibility(View.VISIBLE);
            mBinding.OTPTopImage.setVisibility(View.GONE);
            if (mSendTo.equals(Constant.MERCHANT))
                mBinding.varificationTxt.setText(R.string.pickupOtp);
            else mBinding.varificationTxt.setText(getString(R.string.deliverOtp));

            mBinding.notGetYetTxt.setText(R.string.not_get_yet);
            mBinding.varificationTxtHint.setText(msg);

            mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            mBinding.matchOtpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        } else {

            mBinding.toolbar.setVisibility(View.GONE);
            mBinding.matchOtpBtn.setVisibility(View.GONE);
            mBinding.OTPTopImage.setVisibility(View.VISIBLE);
            mBinding.varificationTxt.setText(R.string.verification);
            mBinding.notGetYetTxt.setText(R.string.din_t_get_otp);
            mBinding.mobileTxtOTP.setText(mBasePreferenceManager.getMobileNum());
        }

        mBinding.resendOtpTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataManager.getINSTANCE().isNetworkConnected(getApplicationContext()))
                    if (intentType == 3) {
                        senOtpForUpdateStatus(String.valueOf(mData.getId()), mSendTo);
                    } else
                       // sendOTP_API(mBasePreferenceManager.getMobileNum(), "2");
                        sendOTP_API(mBasePreferenceManager.getMobileNum());

            }
        });
    }

    public void showAlertLogout(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_congrets_layout);


        TextView pickBtn = (TextView) dialog.findViewById(R.id.pickUpBtn);
        TextView tckParcel = (TextView) dialog.findViewById(R.id.tckParcel);

        if (mSendTo.equals(Constant.MERCHANT)) {
            pickBtn.setText(R.string.picked_up);
            tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());
        } else {
            pickBtn.setText(getString(R.string.delivered));
            tckParcel.setText("Take COD Payments Tk. ".concat(mData.getCodAmount() + " \nand Handover the parcel to " + mData.getReceiverName()));
        }

        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OTPVerificationActivity.this, DashboardActivity.class);
                startActivity(intent);
                finishAffinity();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    void sendOTP_API(String mobile) {
        //  DataManager.getINSTANCE().showProgressBar(SendOTPActivity.this);
        showLoading();
        Call<JsonObject> call;
           if(mBasePreferenceManager.getIsRider()){
              call = new ApiManagerImp().rideSendOtp(mobile, "1");
           }else {
               call = new ApiManagerImp().sendOtpMerchant(mobile, "1");
           }
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
                       /* tvotp.setText(mBasePreferenceManager.getOneTimeOtp());
                        Toast.makeText(getApplicationContext(),
                                object.getString("message"),
                                Toast.LENGTH_SHORT).show();*/
                        countdownStart();
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
                                Toast.makeText(getApplicationContext(),
                                        object.getJSONArray("error").toString(),
                                        Toast.LENGTH_SHORT).show();
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
                //Toast.makeText(getApplicationContext(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });

    }


    void senOtpForUpdateStatus(String shipID, String sendTo) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShipmentSendOTP(mBasePreferenceManager.getUserToken()
                , shipID, sendTo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        mBasePreferenceManager.setOneTimeOtp(object.getString("otp"));
                        Log.d("res", String.valueOf(object));
                        Toast.makeText(getApplicationContext(),
                                object.getString("message"),
                                Toast.LENGTH_SHORT).show();
                        countdownStart();
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

    void riderUpdateStatus(String shipID, String status) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShipmentUpdate(mBasePreferenceManager.getUserToken(),
                shipID, status, mBasePreferenceManager.getOneTimeOtp());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("res", String.valueOf(object));
                        showAlertLogout(OTPVerificationActivity.this);
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