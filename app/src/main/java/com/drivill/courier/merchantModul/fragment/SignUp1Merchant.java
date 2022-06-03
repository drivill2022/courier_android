package com.drivill.courier.merchantModul.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentSignUp1MerchantBinding;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.interfaces.SignUpListener;
import com.drivill.courier.merchantModul.activity.SignUpActivityMerchant;
import com.drivill.courier.merchantModul.model.SignUpDataModel;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import static com.drivill.courier.merchantModul.activity.SignUpActivityMerchant.mSignUpModel;
import static com.drivill.courier.merchantModul.activity.SignUpActivityMerchant.paymentMethod;
import static com.drivill.courier.utils.Constant.DL_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.DL_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.RC_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.RC_IMG_PERMISSION;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp1Merchant#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp1Merchant extends Fragment {
    FragmentSignUp1MerchantBinding mBinding;
    SignUpListener mListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    boolean hasBusinessLogo = false;
    List<String> addressesStr = new ArrayList<>();

    void initUI() {
        mListener = (SignUpListener) this.getActivity();
        mBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  mSignUpModel.set
                mSignUpModel.setBussName(mBinding.businessNameET.getText().toString());
                mSignUpModel.setBussPhone(mBinding.businessPhoneET.getText().toString());
                mSignUpModel.setBussAddress(mBinding.businessAddressET.getText().toString());

                if (mSignUpModel.validate2(requireContext())) {
                    if (AppUtil.isValidPhone(mBinding.businessPhoneET.getText().toString())) {
                        mSignUpModel.setBussPhone(mBinding.businessPhoneET.getText().toString());
                    } else {
                        ((BaseActivity) requireActivity()).showMessage(getString(R.string.mobile_num_is_invalid));
                        return;
                    }

                    if (!mBinding.fbET.getText().toString().isEmpty())
                        if (AppUtil.isValidURL(mBinding.fbET.getText().toString()))
                            mSignUpModel.setFbPage(mBinding.fbET.getText().toString());
                        else {
                            ((BaseActivity) requireActivity()).showMessage(getString(R.string.url_is_invalid));
                            return;
                        }

                    mListener.nextScreen(new SignUp2Merchant(), 1);
                }
            }
        });

        mBinding.businessAddressET.addTextChangedListener(new TextWatcher() {
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


        mBinding.addLicenceFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.DL_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.DL_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivityMerchant)getActivity()).InitPicker(DL_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.DL_IMG_PERMISSION);
                    }
                }else {
                    AppUtil.requestPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, Constant.DL_IMG_PERMISSION);
                }

            }
        });

        mBinding.addLicenceFromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.DL_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.DL_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivityMerchant)getActivity()).InitPicker(DL_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.DL_CAMERA_PERMISSION);
                    }
                }else {
                    AppUtil.requestPermission(getActivity(), Manifest.permission.CAMERA, Constant.DL_CAMERA_PERMISSION);
                }
            }
        });


        mBinding.addBusinessLogoFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.RC_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.RC_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivityMerchant)getActivity()).InitPicker(RC_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.RC_IMG_PERMISSION);
                    }
                }else {
                    AppUtil.requestPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE, Constant.RC_IMG_PERMISSION);
                }

            }
        });

        mBinding.addBusinessLogoFromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.RC_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.RC_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivityMerchant)getActivity()).InitPicker(RC_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.RC_CAMERA_PERMISSION);
                    }
                }else {
                    AppUtil.requestPermission(getActivity(), Manifest.permission.CAMERA, Constant.RC_CAMERA_PERMISSION);
                }
            }
        });

        if (paymentMethod != null && paymentMethod.size() != 0) {
            ArrayAdapter<SignUpDataModel.PaymentMethod> spinnerArrayAdapter = new ArrayAdapter<>
                    (requireContext(), R.layout.spinner_item_layout, paymentMethod);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
            mBinding.choosePaySpinner.setAdapter(spinnerArrayAdapter);
        }

        mBinding.choosePaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.gary));
                        mSignUpModel.setPaymentMethod(null);
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mSignUpModel.setPaymentMethod(paymentMethod.get(i).getId());
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public SignUp1Merchant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp1Merchant.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp1Merchant newInstance(String param1, String param2) {
        SignUp1Merchant fragment = new SignUp1Merchant();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up1_merchant, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();

        ((SignUpActivityMerchant) getActivity()).passVal(new FragmentCommunicator() {
            @Override
            public void passData(String name, int type) {
                // mBinding.setData(mSignUpModel);
                switch (type) {
                    case DL_CAMERA_PERMISSION:
                    case DL_IMG_PERMISSION:
                        if (name != null) {
                            //  mBinding.nidPhotoTxt.setText(name);
                            Glide.with(requireActivity())
                                    .load(mSignUpModel.getTradeLicense())
                                    .into(mBinding.addLicenceFromFile);
                            Glide.with(requireActivity())
                                    .load(mSignUpModel.getTradeLicense())
                                    .into(mBinding.addLicenceFromCam);
                        }

                        break;
                    case RC_CAMERA_PERMISSION:
                    case RC_IMG_PERMISSION:
                        if (name != null) {
                            // mBinding.fatherNidPicTxt.setText(name);
                            hasBusinessLogo = true;
                            Glide.with(requireActivity())
                                    .load(mSignUpModel.getBussinessLogoFile())
                                    .into(mBinding.addBusinessLogoFromFile);
                            Glide.with(requireActivity())
                                    .load(mSignUpModel.getBussinessLogoFile())
                                    .into(mBinding.addBusinessLogoFromCam);

                        }

                        break;
                }
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // mBinding.setData(mSignUpModel);
        mListener.nextScreen(null, 0);
    }
}