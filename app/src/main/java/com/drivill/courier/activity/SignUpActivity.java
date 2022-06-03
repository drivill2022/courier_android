package com.drivill.courier.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.merchantModul.activity.SignUpActivityMerchant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivitySignUpBinding;
import com.drivill.courier.fragment.SignUp1Fragment;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.interfaces.SignUpListener;
import com.drivill.courier.model.model.HubModel;
import com.drivill.courier.model.model.SignUpModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.MyAnimation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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

public class SignUpActivity extends BaseActivity implements SignUpListener {
    FragmentCommunicator fragmentCommunicator;
    ActivitySignUpBinding mBinding;
    public static SignUpModel mModel;
    ArrayList<HubModel.Hub> mHubListAllData = new ArrayList<>();
    boolean isSignUpComplete = false;

    File mFile;
    String mGender;


    void initUI() {
        mModel = new SignUpModel();
        mModel.setMobile(mBasePreferenceManager.getMobileNum());
        mBinding.emailETSignUp.setText(mBasePreferenceManager.getMobileNum());
        locationLive.observe(this, success_observer);
        MyAnimation.startAnimBottomToTop(this, mBinding.fullNameLayout);
        mBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(SignUpActivity.this, SignUpActivity2.class);
                startActivity(intent);*/
                validation();
            }
        });

        mBinding.addressETSignUp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        HubModel.Hub da = new HubModel.Hub();
        da.setName("Select Hub ID");
        mHubListAllData.add(da);
        radioEvent();

    }

    @Override
    protected void setUp() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("token", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

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
        MyAnimation.noAnimation(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_MASK_ADJUST);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
     /*   mSignUpViewModel = new ViewModelProvider(this).get(SignUpActivityViewModel.class);
        mSignUpViewModel.success_validator.observe(this, success_observer);
        mSignUpViewModel.validator.observe(this, validation_observer);*/

        initUI();
        getHubList();

    }


    private Observer<Location> success_observer = new Observer<Location>() {
        @Override
        public void onChanged(Location location) {
            Geocoder geocoder = new Geocoder(SignUpActivity.this, Locale.getDefault());

            try {
                List<Address> address = geocoder.getFromLocation(
                        Double.parseDouble(String.valueOf(location.getLatitude())),
                        Double.parseDouble(String.valueOf(location.getLongitude())), 5);
                mBinding.addressETSignUp.setText(address.get(0).getAddressLine(0));
                mModel.setLatitude(String.valueOf(location.getLatitude()));
                mModel.setLongitude(String.valueOf(location.getLongitude()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
    private Observer<Integer> validation_observer = new Observer<Integer>() {
        @Override
        public void onChanged(Integer integer) {

        }
    };

    @Override
    public void finish() {
        super.finish();
        //  MyAnimation.noAnimation(SignUpActivity.this);
    }

    @Override
    public void onBackPressed() {
        if (isSignUpComplete) {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finishAffinity();
        } else super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("requestCode", String.valueOf(requestCode));
        Log.i("data-", String.valueOf(data));

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
//fOR Second SignUpScreen
                case NID_CAMERA_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,NID_CAMERA_PERMISSION);
                        }

                    }else {
                        isDataFrom("camera", data, NID_CAMERA_PERMISSION);
                    }

                    break;
                case NID_IMG_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,NID_IMG_PERMISSION);
                        }

                    }else {
                        isDataFrom("storage", data, NID_IMG_PERMISSION);
                    }

                    break;
                case FATHER_NID_CAMERA_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,FATHER_NID_CAMERA_PERMISSION);
                        }

                    }else {
                        isDataFrom("camera", data, FATHER_NID_CAMERA_PERMISSION);
                    }

                    break;
                case FATHER_NID_IMG_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,FATHER_NID_IMG_PERMISSION);
                        }

                    }else {
                        isDataFrom("storage", data, FATHER_NID_IMG_PERMISSION);
                    }

                    break;
                case PROFILE_CAMERA_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,PROFILE_CAMERA_PERMISSION);
                        }

                    }else {
                        isDataFrom("camera", data, PROFILE_CAMERA_PERMISSION);
                    }

                    break;
                case PROFILE_IMG_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,PROFILE_IMG_PERMISSION);
                        }

                    }else {
                        isDataFrom("storage", data, PROFILE_IMG_PERMISSION);
                    }

                    break;


//Next Signup Screen number 3

                case DL_CAMERA_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,DL_CAMERA_PERMISSION);
                        }

                    }else {
                        isDataFrom("camera", data, DL_CAMERA_PERMISSION);
                    }

                    break;
                case DL_IMG_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,DL_IMG_PERMISSION);
                        }

                    }else {
                        isDataFrom("storage", data, DL_IMG_PERMISSION);
                    }

                    break;
                case RC_CAMERA_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,RC_CAMERA_PERMISSION);
                        }

                    }else {
                        isDataFrom("camera", data, RC_CAMERA_PERMISSION);
                    }

                    break;
                case RC_IMG_PERMISSION:
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        List<ResultItem> selectedItems = MXMediaPicker.getInstance().getSelectedItems(resultCode, data);
                        if (selectedItems != null) {
                            loadData(selectedItems,RC_IMG_PERMISSION);
                        }

                    }else {
                        isDataFrom("storage", data, RC_IMG_PERMISSION);
                    }

                    break;
            }


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            switch (requestCode) {
                case NID_CAMERA_PERMISSION:
                case PROFILE_CAMERA_PERMISSION:
                case FATHER_NID_CAMERA_PERMISSION:
                    AppUtil.callCameraPermission(SignUpActivity.this, requestCode);
                    break;

                case NID_IMG_PERMISSION:
                case PROFILE_IMG_PERMISSION:
                case FATHER_NID_IMG_PERMISSION:
                    AppUtil.callGalleryPermission(SignUpActivity.this, requestCode);
                    break;


            }
        } else {
            Toast.makeText(SignUpActivity.this, "Permission Required", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void nextScreen(Fragment fragment, int type) {
        if (type == 2) {
            mBinding.headingText.setText(getResources().getString(R.string.set_password));
            mBinding.headingTextHint.setText(getResources().getString(R.string.enter_8_character_password));
        } else if (type == 1) {
            mBinding.headingText.setText(getResources().getString(R.string.signup));
            mBinding.headingTextHint.setText(getResources().getString(R.string.create_rider_account));

        } else if (type == 3) {
            isSignUpComplete = true;
            mBinding.headingTextHint.setText("");
            mBinding.headingText.setText("");
        }
        if (fragment != null)
            switchFragment(fragment);
    }

    void isDataFrom(String Tag, Intent data, int permission) {
        String picturePath;
        if (Tag != null) {
            if (Tag.equals("camera")) {
                    mFile = new File(Environment.getExternalStorageDirectory(), getString(R.string.app_name) + " Images/" + AppUtil.fileName);
                    if (mFile.exists()) {
                        String s = AppUtil.compressImage(mFile);
                        mFile = new File(s);
                    }

            } else if (Tag.equals("storage")) {
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
                mFile = new File(picturePath);

                String s = AppUtil.compressImage(mFile);
                mFile = new File(s);
                c.close();
            }
            switch (permission) {
                case NID_CAMERA_PERMISSION:
                case NID_IMG_PERMISSION:
                    mModel.setNidPicture(mFile);
                    fragmentCommunicator.passData(mFile.getName(), NID_CAMERA_PERMISSION);
                    break;

                case FATHER_NID_CAMERA_PERMISSION:
                case FATHER_NID_IMG_PERMISSION:
                    mModel.setFatherNidPic(mFile);
                    fragmentCommunicator.passData(mFile.getName(), FATHER_NID_CAMERA_PERMISSION);
                    break;

                case PROFILE_IMG_PERMISSION:
                case PROFILE_CAMERA_PERMISSION:
                    mModel.setPicture(mFile);
                    fragmentCommunicator.passData(mFile.getName(), PROFILE_CAMERA_PERMISSION);
                    break;

//Next Signup Screen number 3

                case DL_CAMERA_PERMISSION:
                case DL_IMG_PERMISSION:
                    mModel.setDl_photo(mFile);
                    fragmentCommunicator.passData(mFile.getName(), DL_CAMERA_PERMISSION);
                    break;

                case RC_CAMERA_PERMISSION:
                case RC_IMG_PERMISSION:
                    mModel.setRc_photo(mFile);
                    fragmentCommunicator.passData(mFile.getName(), RC_CAMERA_PERMISSION);
                    break;

            }
        }
    }

    public void passVal(FragmentCommunicator fragmentCommunicator) {
        this.fragmentCommunicator = fragmentCommunicator;
    }


    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void addData() {

        mModel.setName(mBinding.nameET.getText().toString());
        mModel.setEmail("");
        mModel.setAddress(mBinding.addressETSignUp.getText().toString());
        mModel.setGender(mGender);
        switchFragment(new SignUp1Fragment());
    }

    void validation() {
        String name, email, address, hub_id;
        name = mBinding.nameET.getText().toString();
        address = mBinding.addressETSignUp.getText().toString();
        hub_id = mModel.getHubId();

        if (!name.isEmpty() && !address.isEmpty()) {

            if (mGender == null || mGender.isEmpty()) {
                Toast.makeText(this, (R.string.pls_select_gender), Toast.LENGTH_SHORT).show();
            } else {
                if (hub_id == null || hub_id.isEmpty()) {
                    Toast.makeText(this, "Please Select" + getResources().getString(R.string.hub_id), Toast.LENGTH_SHORT).show();
                } else {
                    if (!mBinding.agreeCheckBox.isChecked()) {
                        Toast.makeText(this, (R.string.pls_accept_term), Toast.LENGTH_SHORT).show();
                    } else {
                        addData();
                    }
                }

            }


        } else {
            Toast.makeText(this, (R.string.all_field_required), Toast.LENGTH_SHORT).show();
        }
    }

    void radioEvent() {
        mBinding.maleCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mGender = compoundButton.getText().toString();
                    mBinding.maleCheckbox.setChecked(true);
                    mBinding.femaleCheckbox.setChecked(false);
                    mBinding.otherCheckbox.setChecked(false);
                } else {
                    mBinding.maleCheckbox.setChecked(false);
                }


            }
        });
        mBinding.femaleCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    mGender = compoundButton.getText().toString();
                    mBinding.femaleCheckbox.setChecked(true);
                    mBinding.maleCheckbox.setChecked(false);
                    mBinding.otherCheckbox.setChecked(false);
                } else {
                    mBinding.femaleCheckbox.setChecked(false);
                }
            }
        });
        mBinding.otherCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    mGender = compoundButton.getText().toString();
                    mBinding.otherCheckbox.setChecked(true);
                    mBinding.maleCheckbox.setChecked(false);
                    mBinding.femaleCheckbox.setChecked(false);
                } else {
                    mBinding.otherCheckbox.setChecked(false);
                }
            }
        });

        ArrayAdapter<HubModel.Hub> spinnerArrayAdapter = new ArrayAdapter<HubModel.Hub>
                (SignUpActivity.this, R.layout.spinner_item_layout, mHubListAllData);
        mBinding.hubSpinner.setAdapter(spinnerArrayAdapter);
        mBinding.hubSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.gary));
                        mModel.setHubId("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mModel.setHubId(mHubListAllData.get(i).getId().toString());
                     /*   for (HubModel.Hub hub : mHubListAllData) {
                            if (hub.getName().equals(adapterView.getSelectedItem().toString())) {
                                mModel.setHubId(String.valueOf(hub.getId()));
                            }

                        }*/
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void getHubList() {
        showLoading();
        Call<HubModel> call = new ApiManagerImp().getHubRider();
        call.enqueue(new Callback<HubModel>() {
            @Override
            public void onResponse(Call<HubModel> call, Response<HubModel> response) {
                hideLoading();
                if (response.body() != null) {
                    HubModel model = response.body();

                    // mHubListAllData = new ArrayList<>();
                   /* mHubList.add("Select Hub ID");

                    for (HubModel.Hub hubId : model.getHubs()) {
                        mHubList.add(hubId.getName());
                    }*/

                    mHubListAllData.addAll(model.getHubs());
               /*     ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                            (SignUpActivity.this, R.layout.spinner_item_layout, mHubList);
                    spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
                    mBinding.hubSpinner.setAdapter(spinnerArrayAdapter);*/

                    location();
                }
            }

            @Override
            public void onFailure(Call<HubModel> call, Throwable t) {

            }
        });
    }

    public void InitPicker(int permission) {
        MXMediaPicker.init(getApplicationContext());
        MXMediaPicker picker = MXMediaPicker.getInstance();

        PickerConfig pickerConfig = new PickerConfig();
        pickerConfig.setAllowCamera(true);
        pickerConfig.setMultiSelect(false);
        pickerConfig.setFolderMode(MXMediaPicker.FOLDER_MODE_ONLY_PARENT);
        picker.setPickerConfig(pickerConfig);
        picker.chooseImage(SignUpActivity.this, permission);
    }


    public void loadData(List<ResultItem> selectedItems, int permission){
        String uri ;
        String path ="";
        for (ResultItem item : selectedItems) {
            uri = item.getUri();
            path = item.getPath();
        }
        mFile = new File(path);
        Log.i("arp", "Cpath= " + path + "\n" + "mfile= " + mFile +"permission="+permission);

        switch (permission) {
            case NID_CAMERA_PERMISSION:
            case NID_IMG_PERMISSION:
                mModel.setNidPicture(mFile);
                fragmentCommunicator.passData(mFile.getName(), NID_CAMERA_PERMISSION);
                break;

            case FATHER_NID_CAMERA_PERMISSION:
            case FATHER_NID_IMG_PERMISSION:
                mModel.setFatherNidPic(mFile);
                fragmentCommunicator.passData(mFile.getName(), FATHER_NID_CAMERA_PERMISSION);
                break;

            case PROFILE_IMG_PERMISSION:
            case PROFILE_CAMERA_PERMISSION:
                mModel.setPicture(mFile);
                fragmentCommunicator.passData(mFile.getName(), PROFILE_CAMERA_PERMISSION);
                break;

//Next Signup Screen number 3

            case DL_CAMERA_PERMISSION:
            case DL_IMG_PERMISSION:
                mModel.setDl_photo(mFile);
                fragmentCommunicator.passData(mFile.getName(), DL_CAMERA_PERMISSION);
                break;

            case RC_CAMERA_PERMISSION:
            case RC_IMG_PERMISSION:
                mModel.setRc_photo(mFile);
                fragmentCommunicator.passData(mFile.getName(), RC_CAMERA_PERMISSION);
                break;

        }
    }

}