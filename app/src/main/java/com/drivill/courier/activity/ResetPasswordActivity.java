package com.drivill.courier.activity;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityResetPasswordBinding;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordActivity extends BaseActivity {

    ActivityResetPasswordBinding mBinding;
    int from;

    boolean confirmVisibility = true, mCurrentAction = true;

    void initUI() {
        mBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (DataManager.getINSTANCE().isNetworkConnected(getApplicationContext()))
                    if (validation()) {
                        if (from == Constant.FORGOT) {
                            if (mBasePreferenceManager.getIsRider()) {

                                resetPassword(mBinding.createPasswordET.getText().toString(),
                                        mBinding.confirmPasswordET.getText().toString());
                            } else {

                                resetPasswordMerchant(mBinding.createPasswordET.getText().toString(),
                                        mBinding.confirmPasswordET.getText().toString());
                            }
                        }else
                            Toast.makeText(getApplicationContext(), "Define From", Toast.LENGTH_SHORT).show();

                    }
            }
        });
        handleClickOnEditTextIcon();
    }


    @Override
    protected void setUp() {
        initUI();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        from = getIntent().getIntExtra("from", 0);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        setUp();
    }


    boolean validation() {
        String pass, cPass;
        pass = mBinding.createPasswordET.getText().toString();
        cPass = mBinding.confirmPasswordET.getText().toString();

        if (pass.isEmpty() || cPass.isEmpty()) {
            Toast.makeText(this, getResources().getString(R.string.plse_enter_pass), Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 8) {
            Toast.makeText(this, getResources().getString(R.string.enter_8_character_password), Toast.LENGTH_SHORT).show();
        } /*else if (!AppUtil.isValidPassword(pass)) {
            Toast.makeText(this, "password must contain one upper case and number", Toast.LENGTH_LONG).show();
        }*/ else if (!pass.equals(cPass)) {
            Toast.makeText(this, "confirm password is wrong", Toast.LENGTH_SHORT).show();
        } else {
            return true;
        }

        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    void handleClickOnEditTextIcon() {
        mBinding.createPasswordET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mBinding.createPasswordET.getRight() - mBinding.createPasswordET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        inputType(mCurrentAction, 1);
                        return false;
                    }
                }
                return false;
            }
        });
        mBinding.confirmPasswordET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mBinding.confirmPasswordET.getRight() - mBinding.confirmPasswordET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        inputType(confirmVisibility, 2);
                        return false;
                    }
                }
                return false;
            }
        });
    }

    void inputType(boolean type, int pos) {

        if (pos == 1) {
            if (type) {
                mCurrentAction = false;
                mBinding.createPasswordET.setInputType(InputType.TYPE_CLASS_TEXT);
                mBinding.createPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.eye_visible), null);

            } else {
                mCurrentAction = true;
                mBinding.createPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.createPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.visibility), null);

            }
            mBinding.createPasswordET.setSelection(mBinding.createPasswordET.getText().length());
        } else {

            if (type) {
                confirmVisibility = false;
                mBinding.confirmPasswordET.setInputType(InputType.TYPE_CLASS_TEXT);
                mBinding.confirmPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.eye_visible), null);

            } else {
                confirmVisibility = true;
                mBinding.confirmPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.confirmPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.visibility), null);

            }
            mBinding.confirmPasswordET.setSelection(mBinding.confirmPasswordET.getText().length());
        }

    }


    private void resetPassword(String pass, String cPass) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().resetPasswordRider(mBasePreferenceManager.getUserId(),
                pass, cPass, mBasePreferenceManager.getOneTimeOtp());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    Log.i("res==>", String.valueOf(response.body()));
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
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
                hideLoading();
            }
        });
    }

    private void resetPasswordMerchant(String pass, String cPass) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().resetPasswordMerchant(mBasePreferenceManager.getUserId(), pass, cPass, mBasePreferenceManager.getOneTimeOtp());

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
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
                hideLoading();
            }
        });
    }

}