package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.merchantModul.activity.DashboardActivityMerchant;
import com.drivill.courier.merchantModul.model.MerchantProfileModel;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityLoginBinding;
import com.drivill.courier.model.activityModel.LoginViewModel;
import com.drivill.courier.model.model.LoginModel;
import com.drivill.courier.model.model.ProfileModel;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    String mUrl = "www.drivill.com", device_token;
    boolean mCurrentAction = true;
    ImageView mImageTop;
    TextView mLinkText, mSignNew, mSignTop, mBtnLogin;
    LinearLayout mDonAccountLayout;
    LoginViewModel mLoginViewModel;
    ActivityLoginBinding mBinding;
    //PrefsManager mPrefsManager;

    @SuppressLint("UseCompatLoadingForDrawables")
    void initUI() {
        mImageTop = findViewById(R.id.imageTop);
        mSignNew = findViewById(R.id.signUpNew);
        mSignTop = findViewById(R.id.signUpTop);
        mBtnLogin = findViewById(R.id.button_login);
        mLinkText = findViewById(R.id.textLink);
        mDonAccountLayout = findViewById(R.id.dontAccountLayout);


        mBinding.signUpNew.setOnClickListener(this);
        mBinding.signUpTop.setOnClickListener(this);
        mBinding.buttonLogin.setOnClickListener(this);
        mBinding.textLink.setOnClickListener(this);
        mBinding.forgotTxt.setOnClickListener(this);


        MyAnimation.startAnimTopToBottom(this, mBinding.mobileLayout);
        MyAnimation.startAnimTopToBottom(this, mDonAccountLayout);
        handleClickOnEditTextIcon();

    }


    @Override
    protected void setUp() {
        initUI();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("token", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        device_token = task.getResult();

                        // Log and toast
                        // String msg = getString("", token);
                        //  Log.d(TAG, msg);
                        //  Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyUtil.getFullScreen(this);
        MyAnimation.runFadeInAnimation(LoginActivity.this);
        //  mPrefsManager = new PrefsManager(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mLoginViewModel.success_validator.observe(this, success_observer);
        mLoginViewModel.validator.observe(this, validation_observer);
        //  initUI();
        setUp();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpNew:
            case R.id.signUpTop:
                if (DataManager.getINSTANCE().isNetworkConnected(getApplicationContext()))
                    intentActivity();
                break;
            case R.id.forgotTxt:
                startForgotActivity();

                break;
            case R.id.textLink:
                MyUtil.browseUrl(this, mUrl);
                break;
            case R.id.button_login:
                //   intentActivity(2);
                if (DataManager.getINSTANCE().isNetworkConnected(getApplicationContext())) {
                    if (device_token == null || device_token.isEmpty()) {
                        device_token = mBasePreferenceManager.get_device_token();
                    }
                    if (mBasePreferenceManager.getIsRider()) {
                        mLoginViewModel.doLogin(mBinding, this, device_token);
                    } else {
                        mLoginViewModel.doMerchantLogin(mBinding, this, device_token);
                    }

                }
                break;
        }
    }


    ///////////////////////Custom Methods///////////////

    //For View Animation


    void startForgotActivity() {
        Intent intent = new Intent(LoginActivity.this, SendOTPActivity.class);
        intent.putExtra("from", Constant.FORGOT);
        startActivity(intent);
    }

    void intentActivity() {
        Intent intent = new Intent(LoginActivity.this, SendOTPActivity.class);
        intent.putExtra("from", Constant.SIGNUP);
        startActivity(intent);
    }

    void validations() {
        String Email = "";// = mBinding.emailET.getText().toString();
        String Password = mBinding.passwordET.getText().toString();
        if (Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(getApplicationContext(), (R.string.all_field_required), Toast.LENGTH_SHORT).show();

        } else if (AppUtil.isEmailValid(Email)) {
            //loginAPI(Email, Password);
        } else {
            Toast.makeText(getApplicationContext(), (R.string.email_invalid), Toast.LENGTH_SHORT).show();

        }
    }


    @SuppressLint("ClickableViewAccessibility")
    void handleClickOnEditTextIcon() {
        mBinding.passwordET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (mBinding.passwordET.getRight() - mBinding.passwordET.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        inputType(mCurrentAction);
                        return false;
                    }
                }
                return false;
            }
        });
    }

    void inputType(boolean type) {
        if (type) {
            mCurrentAction = false;
            mBinding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT);
            mBinding.passwordET.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.eye_visible), null);

        } else {
            mCurrentAction = true;
            mBinding.passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mBinding.passwordET.setCompoundDrawablesWithIntrinsicBounds(null, null, getDrawable(R.drawable.visibility), null);

        }
        mBinding.passwordET.setSelection(mBinding.passwordET.getText().length());
    }

    private final Observer<LoginModel> success_observer = new Observer<LoginModel>() {
        @Override
        public void onChanged(LoginModel response) {
            /*mBasePreferenceManager.setUserName(response.getName());
            mBasePreferenceManager.setUserId(response.getId().toString());
            mBasePreferenceManager.setMobileNum(response.getMobile());
            mBasePreferenceManager.setEmailAddress(response.getEmail());
            mBasePreferenceManager.setProfileImg(Constant.getImageUrl(response.getPicture()));
            mBasePreferenceManager.setUserToken(Constant.TOKEN_TYPE + response.getToken());
            */
            mBasePreferenceManager.setTOKEN_TYPE(response.getTokenType());
            mBasePreferenceManager.setREFRESH_TOKEN(response.getRefreshToken());
            mBasePreferenceManager.setUserToken(response.getTokenType() + " " + response.getAccessToken());

            if (mBasePreferenceManager.getIsRider()) {
                fetchProfileRider(response.getTokenType() + " " + response.getAccessToken());

            } else {
                fetchMerchantProfile(response.getTokenType() + " " + response.getAccessToken());
            }
        }
    };

    private Observer<Integer> validation_observer = (integer) -> {
        hideLoading();
        switch (integer) {
            case Constant.EMPTY_ID:
                onError(getString(R.string.please_enter_mail));
                break;

            case Constant.EMPTY_PASSWORD:
                onError(getString(R.string.plse_enter_pass));
                break;

            case Constant.INVALID_MAIL:
                onError(getString(R.string.email_invalid));
                break;

            case Constant.NO_INTERNET:
                onError(getString(R.string.check_internet));
                break;

            case Constant.SERVER_ERROR:
                onError(getString(R.string.try_again));
                break;
            case Constant.INVALID_NUMBER:
                onError(getString(R.string.mobile_num_is_invalid));
                break;
            case Constant.EMPTY_NUMBER:
                onError(getString(R.string.enter_mobile_num));
                break;
        }
    };

    void fetchProfileRider(String tok) {
        Call<ProfileModel> call = new ApiManagerImp().riderProfile(tok);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                hideLoading();
                if (response.body() != null) {
                    ProfileModel model = response.body();
                    if (!model.getStatus().equals("Active")) {
                        onError(getString(R.string.account_not_active));
                    } else {
                        mBasePreferenceManager.setIS_LOGIN(true);
                        mBasePreferenceManager.setMobileNum(model.getMobile());
                        mBasePreferenceManager.setEmailAddress(model.getEmail());
                        mBasePreferenceManager.setUserName(model.getName());
                        mBasePreferenceManager.setProfileImg(Constant.getImageUrl(model.getPicture()));
                        mBasePreferenceManager.setAddress(model.getAddress());
                        mBasePreferenceManager.setGENDER(model.getGender());
                        mBasePreferenceManager.setHub_id(model.getHubId().toString());
                        mBasePreferenceManager.setVehicle_type_id(model.getVehicleTypeId().toString());
                        mBasePreferenceManager.setTotalDelivery(model.getDelivered());
                        mBasePreferenceManager.setTotalEarn(model.getEarned());
                        mBasePreferenceManager.setTotalYear(model.getYears());
                        if (model.getEmergency_no() != null)
                            mBasePreferenceManager.set_emergencyNumMethod(String.valueOf(model.getEmergency_no()));

                        try {
                            mBasePreferenceManager.setLatitude(model.getLatitude());
                            mBasePreferenceManager.setLongitude(model.getLongitude());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }
                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                hideLoading();
                showMessage(getResources().getString(R.string.try_again));
            }
        });
    }

    void fetchMerchantProfile(String tok) {
        Call<MerchantProfileModel> call = new ApiManagerImp().getProfileMerchant(tok);
        call.enqueue(new Callback<MerchantProfileModel>() {
            @Override
            public void onResponse(Call<MerchantProfileModel> call, Response<MerchantProfileModel> response) {
                hideLoading();
                if (response.body() != null) {
                    MerchantProfileModel model = response.body();
                    if (!model.getStatus().equals("Active")) {
                        onError(getString(R.string.account_not_active));
                    } else {
                        mBasePreferenceManager.setIS_LOGIN(true);
                        mBasePreferenceManager.setMobileNum(model.getMobile());
                        mBasePreferenceManager.setLatitude(model.getLatitude());
                        mBasePreferenceManager.setLongitude(model.getLongitude());
                        mBasePreferenceManager.setUserId(model.getMrid());
                        mBasePreferenceManager.setEmailAddress(model.getEmail());
                        mBasePreferenceManager.setUserName(model.getName());
                        mBasePreferenceManager.setProfileImg(Constant.getImageUrl(model.getPicture()));
                        mBasePreferenceManager.setBusinessLogo(Constant.getImageUrl(model.getBusinessLogo()));
                        mBasePreferenceManager.setAddress(model.getAddress());
                        mBasePreferenceManager.setPaymentMethod(String.valueOf(model.getPaymentMethod()));
                        mBasePreferenceManager.set_emergencyNumMethod(String.valueOf(model.getBussPhone()));
                        mBasePreferenceManager.set_businessName(model.getBussName());
                        mBasePreferenceManager.setTotalDelivery(model.getAwards());
                        mBasePreferenceManager.setTotalEarn(model.getEarned());
                        mBasePreferenceManager.setTotalYear(model.getDelivered());

                        mBasePreferenceManager.set_thana(model.getThana());
                        mBasePreferenceManager.set_district(model.getDistrict());
                        mBasePreferenceManager.set_division(model.getDivision());

                        Intent intent = new Intent(LoginActivity.this, DashboardActivityMerchant.class);
                        startActivity(intent);
                        finishAffinity();
                    }

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
                hideLoading();
                showMessage(getResources().getString(R.string.try_again));
            }
        });
    }
}