package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drivill.courier.merchantModul.activity.SignUpActivityMerchant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityProfileBinding;
import com.drivill.courier.merchantModul.model.MerchantProfileModel;
import com.drivill.courier.merchantModul.model.SignUpDataModel;
import com.drivill.courier.model.model.ProfileModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.mingxi.mediapicker.MXMediaPicker;
import tech.mingxi.mediapicker.models.PickerConfig;
import tech.mingxi.mediapicker.models.ResultItem;

import static com.drivill.courier.utils.Constant.DL_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.DL_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.FATHER_NID_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.FATHER_NID_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.NID_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.NID_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.PROFILE_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.PROFILE_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.RC_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.RC_IMG_PERMISSION;

public class ProfileActivity extends BaseActivity {
    ActivityProfileBinding mBinding;
    boolean isEdit = false, mSpinnerClickEvent = true;
    File mProfilePic;
    ArrayList<String> paymentMethod;


    @Override
    protected void setUp() {
        initUI();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        setUp();
    }

    private void initUI() {
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.selectProfileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (AppUtil.checkPermissionForStorage(ProfileActivity.this))
                    AppUtil.callGalleryPermission(ProfileActivity.this, Constant.PROFILE_IMG_PERMISSION);
                else AppUtil.requestPermission(ProfileActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.PROFILE_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(ProfileActivity.this)){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        InitPicker(Constant.PROFILE_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(ProfileActivity.this, Constant.PROFILE_IMG_PERMISSION);
                    }
                }else {
                    AppUtil.requestPermission(ProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE, Constant.PROFILE_IMG_PERMISSION);
                }

            }
        });

        Glide.with(this)
                .load(mBasePreferenceManager.getProfileImg())
                .placeholder(this.getResources().getDrawable(R.drawable.splash_logo))
                .into(mBinding.profileImg);
        //  AppUtil.setImg(this, mBasePreferenceManager.ProfileImg, mBinding.profileImg);

        mBinding.editImgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    isEdit = false;
                    mSpinnerClickEvent = true;
                    mBinding.editImgProfile.setText(getString(R.string.edit_profile));
                    callUpdateApi();
                } else {
                    isEdit = true;
                    mSpinnerClickEvent = false;
                    mBinding.editImgProfile.setText(R.string.done);

                }

                mBinding.addressInput.setEnabled(isEdit);
                mBinding.emailInput.setEnabled(isEdit);
                mBinding.emergencyInput.setEnabled(isEdit);
                mBinding.nameInput.setEnabled(isEdit);
                mBinding.phonInput.setEnabled(false);
                mBinding.choosePaySpinner.setEnabled(isEdit);
            }
        });

        mBinding.nameTxt.setText(mBasePreferenceManager.getUserName());
        mBinding.phoneNum.setText(mBasePreferenceManager.getMobileNum());
        mBinding.mailTxt.setText(mBasePreferenceManager.getEmailAddress());
        mBinding.addressTxt.setText(mBasePreferenceManager.getAddress());
        mBinding.altNum.setText(mBasePreferenceManager.get_emergencyNum());
        mBinding.earnTxt.setText(mBasePreferenceManager.getTotalEarn());
        mBinding.deliveryTxt.setText(mBasePreferenceManager.getTotalDelivery());
        mBinding.yearTxt.setText(mBasePreferenceManager.getTotalYear());

        if (!mBasePreferenceManager.getIsRider()) {
            getSignUpData();
            mBinding.yearHinttxt.setText("DELIVERY");
            mBinding.deliverHintTxt.setText("AWARDS");

            mBinding.payMethodLL.setVisibility(View.VISIBLE);
            mBinding.nameInput.setHint(getString(R.string.business_name));
            mBinding.businessNameTxt.setText(mBasePreferenceManager.get_businessName());
            // mBinding.altNum.setText(mBasePreferenceManager.get_emergencyNum());

            mBinding.choosePaySpinner.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return mSpinnerClickEvent;
                }
            });
            mBinding.choosePaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //    mBinding.choosePaySpinner.setSelection(Integer.parseInt(mBasePreferenceManager.getPaymentMethod()));

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else {
            mBinding.nameInput.setHint("Name");
            mBinding.payMethodLL.setVisibility(View.GONE);
            mBinding.businessNameTxt.setText(mBasePreferenceManager.getUserName());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("requestCode", String.valueOf(requestCode));
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == PROFILE_IMG_PERMISSION) {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                    List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                    if (selectedItems != null) {
                        loadData(selectedItems,PROFILE_IMG_PERMISSION);
                    }
                }else {
                    String picturePath;
                    Uri selectedImage = data.getData();
                    String[] filePath = {MediaStore.Images.Media.DATA};
                    assert selectedImage != null;
                    Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                    assert c != null;
                    c.moveToFirst();
                    int columnIndex = c.getColumnIndex(filePath[0]);
                    picturePath = c.getString(columnIndex);
                    if (picturePath == null) {
                        Toast.makeText(this, getResources().getString(R.string.please_select_pic), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    mProfilePic = new File(picturePath);
                    String s = AppUtil.compressImage(mProfilePic);
                    mProfilePic = new File(s);
                    c.close();

                if (mProfilePic != null) {

                    if (isEdit) {
                        Glide.with(this)
                                .load(mProfilePic)
                                .into(mBinding.profileImg);
                    } else
                        callUpdateApi();
                  }
                }
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (requestCode == PROFILE_IMG_PERMISSION) {
                AppUtil.callGalleryPermission(ProfileActivity.this, requestCode);
            }
        } else {
            Toast.makeText(ProfileActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();
        }

    }

    boolean validate(String email, String name, String num, String address) {
        boolean isValid = true;
      /*  if (!AppUtil.isEmailValid(email)) {
            onError(getString(R.string.email_invalid));
            isValid = false;
        } else*/
        if (name.isEmpty()) {
            onError(getString(R.string.enter_business_name));
            isValid = false;
        } else if (address.isEmpty()) {
            onError(getString(R.string.enter_address));
            isValid = false;
        } else if (!AppUtil.isValidPhone(num)) {
            onError(getString(R.string.mobile_num_is_invalid));
            isValid = false;
        }
        return isValid;
    }

    void callUpdateApi() {
        if (validate(mBinding.mailTxt.getText().toString(), mBinding.businessNameTxt.getText().toString(),
                mBinding.phoneNum.getText().toString(),
                mBinding.addressTxt.getText().toString())) {

            if (mBasePreferenceManager.getIsRider()) {
                updateRider();
            } else {
                updateMerchant();
            }
        }
    }

    private void updateRider() {


        MultipartBody.Part profile_photo = null;
        try {
            RequestBody requestFileProfile_photo = RequestBody.create(MediaType.parse("image/*"), mProfilePic);

            profile_photo = MultipartBody.Part.createFormData("picture", mProfilePic.getName(), requestFileProfile_photo);
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  RequestBody requestFileRc_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getBussinessLogoFile());
        MultipartBody.Part businessLogoFile = MultipartBody.Part.createFormData("business_logo", mSignUpModel.getBussinessLogoFile().getName(), requestFileRc_photo);

        MultipartBody.Part trad_lic_photo = null;
        try {

            RequestBody requestFileDl_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getTradeLicense());
            trad_lic_photo = MultipartBody.Part.createFormData("trade_lic_no", mSignUpModel.getTradeLicense().getName(), requestFileDl_photo);

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        // add another part within the multipart request
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), mBasePreferenceManager.getUserToken());
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), mBinding.businessNameTxt.getText().toString());
        RequestBody mail = RequestBody.create(MediaType.parse("text/plain"), mBinding.mailTxt.getText().toString());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), mBinding.addressTxt.getText().toString());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), mBinding.phoneNum.getText().toString());
        RequestBody emergency_mobile = RequestBody.create(MediaType.parse("text/plain"), mBinding.altNum.getText().toString());
        // RequestBody paymentName = RequestBody.create(MediaType.parse("text/plain"), mBinding.paymentName.getText().toString());
        RequestBody hub_id = RequestBody.create(MediaType.parse("text/plain"), mBasePreferenceManager.getHub_id());
        RequestBody vehicle_type = RequestBody.create(MediaType.parse("text/plain"), mBasePreferenceManager.getVehicle_type_id());
        RequestBody PUT = RequestBody.create(MediaType.parse("text/plain"), "PUT");


        // finally, execute the request
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", name);
        map.put("email", mail);
        map.put("mobile", mobile);
        map.put("address", address);
        map.put("emergency_no", emergency_mobile);
        // map.put("paymentName", paymentName);
        //  map.put("vehicle_type_id", hub_id);
        //  map.put("hub_id", vehicle_type);
        /// map.put("_method", PUT);

        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderUpdateProfile(mBasePreferenceManager.getUserToken(), map, profile_photo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                Log.d("res", String.valueOf(response));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        showMessage(object.getString("message"));
                        Log.d("res", String.valueOf(object));
                        fetchProfileRider(mBasePreferenceManager.getUserToken());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("res", String.valueOf(object));
                        if (object.has("error")) {
                            onError(object.getJSONArray("error").get(0).toString());
                        }
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

    private void updateMerchant() {

        MultipartBody.Part profile_photo = null;
        try {
            RequestBody requestFileProfile_photo = RequestBody.create(MediaType.parse("image/*"), mProfilePic);

            profile_photo = MultipartBody.Part.createFormData("picture", mProfilePic.getName(), requestFileProfile_photo);
        } catch (Exception e) {
            e.printStackTrace();
        }

      /*  RequestBody requestFileRc_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getBussinessLogoFile());
        MultipartBody.Part businessLogoFile = MultipartBody.Part.createFormData("business_logo", mSignUpModel.getBussinessLogoFile().getName(), requestFileRc_photo);

        MultipartBody.Part trad_lic_photo = null;
        try {

            RequestBody requestFileDl_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getTradeLicense());
            trad_lic_photo = MultipartBody.Part.createFormData("trade_lic_no", mSignUpModel.getTradeLicense().getName(), requestFileDl_photo);

        } catch (Exception e) {
            e.printStackTrace();
        }*/


        // add another part within the multipart request
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), mBasePreferenceManager.getUserName());
        RequestBody buss_name = RequestBody.create(MediaType.parse("text/plain"), mBinding.businessNameTxt.getText().toString());
        RequestBody mail = RequestBody.create(MediaType.parse("text/plain"), mBinding.mailTxt.getText().toString());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), mBinding.addressTxt.getText().toString());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), mBinding.phoneNum.getText().toString());
        RequestBody payment_method = RequestBody.create(MediaType.parse("text/plain"), String.valueOf((mBinding.choosePaySpinner.getSelectedItemPosition() + 1)));
         RequestBody emergency_mobile = RequestBody.create(MediaType.parse("text/plain"), mBinding.altNum.getText().toString());
        // RequestBody paymentName = RequestBody.create(MediaType.parse("text/plain"), mBinding.paymentName.getText().toString());
        RequestBody hub_id = RequestBody.create(MediaType.parse("text/plain"), mBasePreferenceManager.getHub_id());
        RequestBody vehicle_type = RequestBody.create(MediaType.parse("text/plain"), mBasePreferenceManager.getVehicle_type_id());

        // finally, execute the request
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("name", name);
        map.put("buss_name", buss_name);
        map.put("email", mail);
        map.put("mobile", mobile);
        map.put("address", address);
        map.put("payment_method", payment_method);
        map.put("emergency_no", emergency_mobile);
        // map.put("buss_phone", emergency_mobile);
        //  map.put("vehicle_type_id", hub_id);
        //  map.put("hub_id", vehicle_type);
        /// map.put("_method", PUT);

        showLoading();
        Call<JsonObject> call = new ApiManagerImp().merchantUpdateProfile(mBasePreferenceManager.getUserToken(), map, profile_photo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                Log.d("res", String.valueOf(response));
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));

                        Log.d("res", String.valueOf(object));
                        showMessage(object.getString("message"));
                        fetchMerchantProfile(mBasePreferenceManager.getUserToken());
                        // mBasePreferenceManager.setPaymentMethod(String.valueOf(mBinding.choosePaySpinner.getSelectedItemPosition() + 1));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("res", String.valueOf(object));
                        if (object.has("error")) {
                            onError(object.getJSONArray("error").get(0).toString());
                        }
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

    void getSignUpData() {
        showLoading();
        Call<SignUpDataModel> call = new ApiManagerImp().getSignUpDataMerchant();
        call.enqueue(new Callback<SignUpDataModel>() {
            @Override
            public void onResponse(Call<SignUpDataModel> call, Response<SignUpDataModel> response) {
                hideLoading();
                if (response.body() != null) {
                    paymentMethod = new ArrayList<>();
                    SignUpDataModel model = response.body();
                    Log.i("arp","res= "+new Gson().toJson(model));
                    for (SignUpDataModel.PaymentMethod pro : model.getPaymentMethod()) {
                        paymentMethod.add(pro.getName());
                    }

                    if (paymentMethod != null && paymentMethod.size() != 0) {
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                (ProfileActivity.this, R.layout.spinner_item_layout, paymentMethod);
                        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                        mBinding.choosePaySpinner.setAdapter(spinnerArrayAdapter);
                        mBinding.choosePaySpinner.setSelection(Integer.parseInt(mBasePreferenceManager.getPaymentMethod()) - 1);
                    }

                }
            }

            @Override
            public void onFailure(Call<SignUpDataModel> call, Throwable t) {
                hideLoading();
            }
        });
    }

    void fetchProfileRider(String tok) {
        Call<ProfileModel> call = new ApiManagerImp().riderProfile(tok);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                if (response.body() != null) {
                    ProfileModel model = response.body();
                   // getTotalYear(model.getCreatedAt());
                    mBasePreferenceManager.setMobileNum(model.getMobile());
                    mBasePreferenceManager.setEmailAddress(model.getEmail());

                    mBasePreferenceManager.setUserName(model.getName());
                    mBinding.nameTxt.setText(model.getName()); // update tv at run time ===

                    mBasePreferenceManager.setProfileImg(Constant.getImageUrl(model.getPicture()));
                    mBasePreferenceManager.setAddress(model.getAddress());
                    mBasePreferenceManager.setGENDER(model.getGender());
                    mBasePreferenceManager.set_emergencyNumMethod(String.valueOf(model.getEmergency_no()));
                    mBasePreferenceManager.setHub_id(model.getHubId().toString());
                    mBasePreferenceManager.setVehicle_type_id(model.getVehicleTypeId().toString());
                    AppUtil.setImg(ProfileActivity.this, mBasePreferenceManager.getProfileImg(),
                            mBinding.profileImg);
                    mBasePreferenceManager.setTotalDelivery(model.getDelivered());
                    mBasePreferenceManager.setTotalEarn(model.getEarned());
                    mBasePreferenceManager.setTotalYear(model.getYears());
                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                        onError(object.getString("error"));
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
                    MerchantProfileModel model = response.body();
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
                    mBasePreferenceManager.set_emergencyNumMethod(String.valueOf(model.getEmergency_no()));
                    mBasePreferenceManager.set_businessName(model.getBussName());

                    mBasePreferenceManager.set_thana(model.getThana());
                    mBasePreferenceManager.set_district(model.getDistrict());
                    mBasePreferenceManager.set_division(model.getDivision());

                    mBasePreferenceManager.setTotalDelivery(model.getAwards());
                    mBasePreferenceManager.setTotalEarn(model.getEarned());
                    mBasePreferenceManager.setTotalYear(model.getDelivered());
                    mBinding.choosePaySpinner.setSelection(Integer.parseInt(mBasePreferenceManager.getPaymentMethod()) - 1);
                    AppUtil.setImg(ProfileActivity.this, mBasePreferenceManager.getProfileImg(),
                            mBinding.profileImg);
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
            public void onFailure(Call<MerchantProfileModel> call, Throwable t) {
                Log.d("t", String.valueOf(t));

            }
        });
    }


    private int getTotalYear(String createdAt) {
        int age = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = dateFormat.parse("2020-07-01");
            Calendar now = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(date1);
            if (dob.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            int year1 = now.get(Calendar.YEAR);
            int year2 = dob.get(Calendar.YEAR);
            age = year1 - year2;
            int month1 = now.get(Calendar.MONTH);
            int month2 = dob.get(Calendar.MONTH);
            if (month2 > month1) {
                age--;
            } else if (month1 == month2) {
                int day1 = now.get(Calendar.DAY_OF_MONTH);
                int day2 = dob.get(Calendar.DAY_OF_MONTH);
                if (day2 > day1) {
                    age--;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return age;
    }

    private void InitPicker(int permission) {
        MXMediaPicker.init(getApplicationContext());
        MXMediaPicker picker = MXMediaPicker.getInstance();

        PickerConfig pickerConfig = new PickerConfig();
        pickerConfig.setAllowCamera(false);
        pickerConfig.setMultiSelect(false);
        pickerConfig.setFolderMode(MXMediaPicker.FOLDER_MODE_ONLY_PARENT);
        picker.setPickerConfig(pickerConfig);
        picker.chooseImage(ProfileActivity.this, permission);
    }

    public void loadData(List<ResultItem> selectedItems, int permission){
        String uri ;
        String path ="";
        for (ResultItem item : selectedItems) {
            uri = item.getUri();
            path = item.getPath();
        }
        mProfilePic = new File(path);
        Log.i("arp", "profile= " + path + "\n" + "mfile= " + mProfilePic +"permission="+permission);

        if (mProfilePic != null) {
            if (isEdit) {
                Glide.with(this)
                        .load(mProfilePic)
                        .into(mBinding.profileImg);
            } else
                callUpdateApi();
        }

    }

}