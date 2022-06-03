package com.drivill.courier.model.activityModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.drivill.courier.activity.LoginActivity;
import com.drivill.courier.databinding.ActivityLoginBinding;
import com.drivill.courier.model.model.LoginModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public final MutableLiveData<LoginModel> success_validator = new MutableLiveData<>();
    // public final MutableLiveData<LoginModel> success_validatorForMerchant = new MutableLiveData<>();
    public final MutableLiveData<Integer> validator = new MutableLiveData<>();


    private boolean checkEmail(String text) {
        if (text.isEmpty()) {
            validator.setValue(Constant.EMPTY_ID);
            return false;
        } else if (!AppUtil.isEmailValid(text)) {
            validator.setValue(Constant.INVALID_MAIL);
            return false;
        }
        return true;
    }

    private boolean checkPassword(String text) {

        if (text.isEmpty()) {
            validator.setValue(Constant.EMPTY_PASSWORD);
            return false;
        }
        return true;
    }

    private boolean isValidMobile(String phone) {
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }


    public void doLogin(ActivityLoginBinding binding, LoginActivity activity,String device_token) {
        String mobile = binding.mobileET.getText().toString().trim();
        String password = binding.passwordET.getText().toString().trim();

        if (!Constant.isConnectingToInternet(activity)) {
            validator.setValue(Constant.NO_INTERNET);
            return;
        }
        if (mobile.isEmpty()) {
            validator.setValue(Constant.EMPTY_NUMBER);
            return;
        }
        if (!AppUtil.isValidPhone(mobile)) {
            validator.setValue(Constant.INVALID_NUMBER);
            return;
        }

        if (!checkPassword(password)) {
            return;
        }
        activity.showLoading();
        // NetworkApi call = RetrofitHolder.getClient().create(NetworkApi.class);
        Call<LoginModel> call = new ApiManagerImp().riderLogin(mobile, password,device_token);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                // success_validator.setValue(response.body());
                Log.d("res", String.valueOf(response));
                if (response.body() != null) {
                    LoginModel model = response.body();
                    success_validator.setValue(model);
                    /* try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("t", String.valueOf(object));
                        if (object != null) {
                            if (object.has("status")) {

                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }*/
                } else {
                    activity.hideLoading();
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object.has("error")) {
                            StringBuilder builder = new StringBuilder();
                            // object.getJSONArray("error").get(0);
                            for (int i = 0; i < object.getJSONArray("error").length(); i++) {
                                builder.append(object.getJSONArray("error").get(i));
                                builder.append("\n");
                            }
                            activity.onError(builder.toString());
                        /*    Toast.makeText(getApplicationContext(),
                                    object.getJSONArray("error").toString(),
                                    Toast.LENGTH_SHORT).show();*/
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                validator.setValue(Constant.SERVER_ERROR);
            }
        });

    }

    public void doMerchantLogin(ActivityLoginBinding binding, LoginActivity activity,String device_token) {

        String mobile = binding.mobileET.getText().toString().trim();
        String password = binding.passwordET.getText().toString().trim();

        if (!Constant.isConnectingToInternet(activity)) {
            validator.setValue(Constant.NO_INTERNET);
            return;
        }
        if (mobile.isEmpty()) {
            validator.setValue(Constant.EMPTY_NUMBER);
            return;
        }

        if (!AppUtil.isValidPhone(mobile)) {
            validator.setValue(Constant.INVALID_NUMBER);
            return;
        }

        if (!checkPassword(password)) {
            return;
        }
        activity.showLoading();
        Call<LoginModel> call = new ApiManagerImp().merchantLogin(mobile, password,device_token);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body() != null)
                    success_validator.setValue(response.body());

                else {
                    activity.hideLoading();
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("t", String.valueOf(object));
                        if (object.has("error")) {
                            StringBuilder builder = new StringBuilder();
                            // object.getJSONArray("error").get(0);
                            for (int i = 0; i < object.getJSONArray("error").length(); i++) {
                                builder.append(object.getJSONArray("error").get(i));
                                builder.append("\n");
                            }
                            activity.onError(builder.toString());
                        /*    Toast.makeText(getApplicationContext(),
                                    object.getJSONArray("error").toString(),
                                    Toast.LENGTH_SHORT).show();*/
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                validator.setValue(Constant.SERVER_ERROR);
            }
        });
    }
}
