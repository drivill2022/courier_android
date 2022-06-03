package com.drivill.courier.merchantModul.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.BuildConfig;
import com.drivill.courier.R;
import com.drivill.courier.activity.LoginActivity;
import com.drivill.courier.databinding.ActivitySignUpMerchantBinding;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.interfaces.SignUpListener;
import com.drivill.courier.merchantModul.fragment.SignUp1Merchant;
import com.drivill.courier.merchantModul.model.MerchantProfileModel;
import com.drivill.courier.merchantModul.model.SignUpDataModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.IOException;
import java.net.URI;
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


public class SignUpActivityMerchant extends BaseActivity implements SignUpListener {

    boolean isSignUpComplete = false;
    ActivitySignUpMerchantBinding mBinding;
    public static MerchantProfileModel mSignUpModel;
    public static ArrayList<SignUpDataModel.ProductType> product = new ArrayList<>();

    public static ArrayList<SignUpDataModel.PaymentMethod> paymentMethod = new ArrayList<>();
    FragmentCommunicator fragmentCommunicator;
    File mFile;
    List<String> addressesStr = new ArrayList<>();
    Geocoder geocoder;
    ArrayAdapter<String> mLocationApter;


    @Override
    protected void setUp() {
        locationLive.observe(this, success_observer);
        geocoder = new Geocoder(SignUpActivityMerchant.this, Locale.getDefault());
        mBinding.emailETSignUp.setText(mBasePreferenceManager.getMobileNum());

        mBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // shareImage(mFile.getName());

                mSignUpModel.setName(mBinding.nameET.getText().toString());
                //  if (AppUtil.isEmailValid(mBinding.emailETSignUp.getText().toString())) {
                mSignUpModel.setEmail("");
                mSignUpModel.setAddress(mBinding.addressETSignUp.getText().toString());
                mSignUpModel.setNidNumber(mBinding.nidMerchantSignUp.getText().toString());

                if (mSignUpModel.validate1(getApplicationContext()))
                    if (!mBinding.agreeCheckBox.isChecked()) {
                        Toast.makeText(SignUpActivityMerchant.this, (R.string.pls_accept_term), Toast.LENGTH_SHORT).show();
                    } else {
                        switchFragment(new SignUp1Merchant());
                    }

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

        mBinding.profilePicFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             /* if (AppUtil.checkPermissionForStorage(SignUpActivityMerchant.this))
                    AppUtil.callGalleryPermission(SignUpActivityMerchant.this, Constant.PROFILE_IMG_PERMISSION);
                else AppUtil.requestPermission(SignUpActivityMerchant.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE, Constant.PROFILE_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(SignUpActivityMerchant.this)){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        InitPicker(Constant.PROFILE_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(SignUpActivityMerchant.this, Constant.PROFILE_IMG_PERMISSION);
                    }

                }else {
                     AppUtil.requestPermission(SignUpActivityMerchant.this, Manifest.permission.READ_EXTERNAL_STORAGE, Constant.PROFILE_IMG_PERMISSION);
                }
            }
        });

        mBinding.profilePicFromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   /* if (AppUtil.checkPermissionForCamera(SignUpActivityMerchant.this))
                        AppUtil.callCameraPermission(SignUpActivityMerchant.this, Constant.PROFILE_CAMERA_PERMISSION);
                    else AppUtil.requestPermission(SignUpActivityMerchant.this,
                            Manifest.permission.CAMERA, Constant.PROFILE_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(SignUpActivityMerchant.this)){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        InitPicker(Constant.PROFILE_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(SignUpActivityMerchant.this, Constant.PROFILE_CAMERA_PERMISSION);
                    }
                }else {
                AppUtil.requestPermission(SignUpActivityMerchant.this, Manifest.permission.CAMERA, Constant.PROFILE_CAMERA_PERMISSION);
                }

            }
        });

/////////////////////////////getting FCM token////////////////////////////////////////////////////////////////////////////////////////////////
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
        getSignUpData();
        mSignUpModel = new MerchantProfileModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_merchant);
        setUp();
        location();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.setData(mSignUpModel);
    }

    @Override
    public void onBackPressed() {
        if (isSignUpComplete) {
            Intent intent = new Intent(SignUpActivityMerchant.this, LoginActivity.class);
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
                    AppUtil.callCameraPermission(SignUpActivityMerchant.this, requestCode);
                    break;

                case NID_IMG_PERMISSION:
                case PROFILE_IMG_PERMISSION:
                case FATHER_NID_IMG_PERMISSION:
                    AppUtil.callGalleryPermission(SignUpActivityMerchant.this, requestCode);
                    break;

            }
        } else {
            Toast.makeText(SignUpActivityMerchant.this, "Permission Required", Toast.LENGTH_SHORT).show();
        }

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

                case PROFILE_IMG_PERMISSION:
                case PROFILE_CAMERA_PERMISSION:
                    mSignUpModel.setProfileFile(mFile);
                    // fragmentCommunicator.passData(mFile.getName(), PROFILE_CAMERA_PERMISSION);
                    loadImageWithGlide(mBinding.profilePicFromFile, mSignUpModel.getProfileFile());
                    loadImageWithGlide(mBinding.profilePicFromCam, mSignUpModel.getProfileFile());

                    break;

//Next Signup Screen number 3

                case DL_CAMERA_PERMISSION:
                case DL_IMG_PERMISSION:
                    mSignUpModel.setTradeLicense(mFile);
                    fragmentCommunicator.passData(mFile.getName(), DL_CAMERA_PERMISSION);
                    break;

                case RC_CAMERA_PERMISSION:
                case RC_IMG_PERMISSION:
                    mSignUpModel.setBussinessLogoFile(mFile);
                    fragmentCommunicator.passData(mFile.getName(), RC_CAMERA_PERMISSION);
                    break;

            }
        }
    }


    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void nextScreen(Fragment fragment, int type) {
        if (type == 2) {
            mBinding.headingTextHint.setText(getResources().getString(R.string.enter_8_character_password));
            mBinding.headingText.setText(getResources().getString(R.string.set_password));
        } else if (type == 3) {
            isSignUpComplete = true;
            mBinding.headingTextHint.setText("");
            mBinding.headingText.setText("");
        } else if (type == 1) {
            mBinding.headingText.setText(getResources().getString(R.string.signup));
            mBinding.headingTextHint.setText(getResources().getString(R.string.create_account));
        } else {
            mBinding.headingText.setText(getResources().getString(R.string.signup));
            mBinding.headingTextHint.setText(getResources().getString(R.string.create_seller_account));
        }
        if (fragment != null)
            switchFragment(fragment);
    }

    void getSignUpData() {
        showLoading();
        Call<SignUpDataModel> call = new ApiManagerImp().getSignUpDataMerchant();
        call.enqueue(new Callback<SignUpDataModel>() {
            @Override
            public void onResponse(Call<SignUpDataModel> call, Response<SignUpDataModel> response) {
                hideLoading();
                if (response.body() != null) {
                    SignUpDataModel model = response.body();
                    SignUpDataModel.PaymentMethod paymentMath = new SignUpDataModel.PaymentMethod();
                    paymentMath.setName("Select your payment method");
                    paymentMethod.add(paymentMath);
                    //    product.add(getResources().getString(R.string.select_your_product_type_s));
                    product.addAll(model.getProductType());
                    paymentMethod.addAll(model.getPaymentMethod());

                }
            }

            @Override
            public void onFailure(Call<SignUpDataModel> call, Throwable t) {
                hideLoading();
            }
        });
    }

    public void passVal(FragmentCommunicator fragmentCommunicator) {
        this.fragmentCommunicator = fragmentCommunicator;
    }

    public void loadImageWithGlide(ImageView view, File file) {
        Glide.with(this).load(file).into(view);

    }

    private Observer<Location> success_observer = new Observer<Location>() {
        @Override
        public void onChanged(Location location) {
            Geocoder geocoder = new Geocoder(SignUpActivityMerchant.this, Locale.getDefault());
            Log.i("geocoder", "gc==>" + geocoder.toString());
            try {
                List<Address> address = geocoder.getFromLocation(
                        Double.parseDouble(String.valueOf(location.getLatitude())),
                        Double.parseDouble(String.valueOf(location.getLongitude())), 5);
                mBinding.addressETSignUp.setText(address.get(0).getAddressLine(0));
                mSignUpModel.setLatitude(String.valueOf(location.getLatitude()));
                mSignUpModel.setLongitude(String.valueOf(location.getLongitude()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

//// https://stackoverflow.com/questions/7661875/how-to-use-share-image-using-sharing-intent-to-share-images-in-android
    private void shareImage(String pathToImage) {
        Log.i("arp","path= "+ pathToImage);

        try {
            Uri uri = FileProvider.getUriForFile(SignUpActivityMerchant.this, SignUpActivityMerchant.this.getPackageName() + ".provider", mFile);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent .setType("image/*");
            intent .putExtra(Intent.EXTRA_STREAM, uri);
           // startActivity(intent );
            startActivity(Intent.createChooser(intent, "Share via"));
        }catch (Exception e){
            Toast.makeText(SignUpActivityMerchant.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void InitPicker(int permission) {
        MXMediaPicker.init(getApplicationContext());
        MXMediaPicker picker = MXMediaPicker.getInstance();

        PickerConfig pickerConfig = new PickerConfig();
        pickerConfig.setAllowCamera(true);
        pickerConfig.setMultiSelect(false);
        pickerConfig.setFolderMode(MXMediaPicker.FOLDER_MODE_ONLY_PARENT);
        picker.setPickerConfig(pickerConfig);
        picker.chooseImage(SignUpActivityMerchant.this, permission);
    }

    public void  loadData(List<ResultItem> selectedItems, int permission){
        String uri ;
        String path ="";
        for (ResultItem item : selectedItems) {
            uri = item.getUri();
            path = item.getPath();
        }
        mFile = new File(path);
        Log.i("arp", "Cpath= " + path + "\n" + "mfile= " + mFile +"permission="+permission);

        switch (permission) {

            case PROFILE_IMG_PERMISSION:
            case PROFILE_CAMERA_PERMISSION:
                mSignUpModel.setProfileFile(mFile);
                // fragmentCommunicator.passData(mFile.getName(), PROFILE_CAMERA_PERMISSION);
                loadImageWithGlide(mBinding.profilePicFromFile, mSignUpModel.getProfileFile());
                loadImageWithGlide(mBinding.profilePicFromCam, mSignUpModel.getProfileFile());

                break;

//Next Signup Screen number 3

            case DL_CAMERA_PERMISSION:
            case DL_IMG_PERMISSION:
                mSignUpModel.setTradeLicense(mFile);
                fragmentCommunicator.passData(mFile.getName(), DL_CAMERA_PERMISSION);
                break;

            case RC_CAMERA_PERMISSION:
            case RC_IMG_PERMISSION:
                mSignUpModel.setBussinessLogoFile(mFile);
                fragmentCommunicator.passData(mFile.getName(), RC_CAMERA_PERMISSION);
                break;

        }
    }

}