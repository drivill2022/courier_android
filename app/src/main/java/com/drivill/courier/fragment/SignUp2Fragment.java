package com.drivill.courier.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drivill.courier.R;
import com.drivill.courier.activity.SignUpActivity;
import com.drivill.courier.databinding.FragmentSignUp2Binding;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.interfaces.SignUpListener;
import com.drivill.courier.model.model.VehicleModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drivill.courier.activity.SignUpActivity.mModel;
import static com.drivill.courier.utils.Constant.DL_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.DL_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.RC_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.RC_IMG_PERMISSION;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp2Fragment extends Fragment {

    boolean hasDLPic = false, hasRCPic = false;

    FragmentSignUp2Binding mBinding;
    SignUpListener mListener;
    VehicleModel mVehicleModel;
    ArrayList<VehicleModel.Model> mModelAry;
    ArrayList<VehicleModel.Brand> mBrandAry;
    ArrayList<VehicleModel.Category> mCatAry;
    ArrayList<VehicleModel.Region> mRegionAry;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUp2Fragment() {
        // Required empty public constructor
    }

    void initUI() {

        mModelAry = new ArrayList<>();
        mBrandAry = new ArrayList<>();
        mCatAry = new ArrayList<>();
        mRegionAry = new ArrayList<>();
        mBrandAry.add(new VehicleModel.Brand("Brand Name"));
        mModelAry.add(new VehicleModel.Model("Model Name"));
        mRegionAry.add(new VehicleModel.Region("Select Region"));
        mCatAry.add(new VehicleModel.Category("Select Category"));
        mListener = (SignUpListener) this.getActivity();
        getVehicleData();


/////////////////////////////////Click Events/////////////////////////////////////////////////////////


        mBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mModel.validate(getContext())) {
                    if (mModel.getVehicleTypeId() == 1) {
                        mListener.nextScreen(new SetPasswordFragment(), 2);

                    } else if (hasDLPic && hasRCPic) {
                        mListener.nextScreen(new SetPasswordFragment(), 2);

                    } else
                        Toast.makeText(getContext(), (R.string.select_photo), Toast.LENGTH_SHORT).show();

                }
            }
        });
        mBinding.dlPhotoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.DL_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.DL_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.DL_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.DL_IMG_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE, Constant.DL_IMG_PERMISSION);
                }


            }
        });
        mBinding.dlPhotoAddCamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.DL_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.DL_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.DL_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.DL_CAMERA_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.CAMERA, Constant.DL_CAMERA_PERMISSION);
                }


            }
        });

        mBinding.rcPhotoAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.RC_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.RC_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.RC_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.RC_IMG_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE, Constant.RC_IMG_PERMISSION);
                }

            }
        });
        mBinding.rcPhptoCamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.RC_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.RC_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.RC_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.RC_CAMERA_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.CAMERA, Constant.RC_CAMERA_PERMISSION);
                }

            }
        });

        mBinding.radioMotorBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //  mGender = compoundButton.getText().toString();
                    mModel.setVehicleTypeId(2);
                    mBinding.radioMotorBtn.setChecked(true);
                    mBinding.radioCycleBtn.setChecked(false);
                } else {
                    mBinding.radioMotorBtn.setChecked(false);
                }


            }
        });
        mBinding.radioCycleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
                    //  mGender = compoundButton.getText().toString();
                    mModel.setVehicleTypeId(1);
                    mBinding.radioCycleBtn.setChecked(true);
                    mBinding.radioMotorBtn.setChecked(false);
                    enableDisableViews(false, (float) .3);
                } else {
                    mBinding.radioCycleBtn.setChecked(false);
                    enableDisableViews(true, (float) 1);
                }

            }
        });

        mBinding.dlNumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                mModel.setDl_number(text);
            }
        });

        mBinding.plateNumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                mModel.setPlat_number(text);
            }
        });

        mBinding.tokenNumET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                mModel.setToken_number(text);
            }
        });
    }

    public static SignUp2Fragment newInstance(String param1, String param2) {
        SignUp2Fragment fragment = new SignUp2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up2, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        ((SignUpActivity) getActivity()).passVal(new FragmentCommunicator() {
            @Override
            public void passData(String name, int type) {
                mBinding.setData(mModel);
                switch (type) {
                    case DL_CAMERA_PERMISSION:
                    case DL_IMG_PERMISSION:
                        if (name != null) {
                            hasDLPic = true;

                            Glide.with(getContext())
                                    .load(mModel.getDl_photo())
                                    .into(mBinding.dlPhotoAddBtn);
                            Glide.with(getContext())
                                    .load(mModel.getDl_photo())
                                    .into(mBinding.dlPhotoAddCamBtn);

                        }

                        break;
                    case RC_CAMERA_PERMISSION:
                    case RC_IMG_PERMISSION:
                        if (name != null) {
                            hasRCPic = true;
                            Glide.with(getContext())
                                    .load(mModel.getRc_photo())
                                    .into(mBinding.rcPhotoAddBtn);
                            Glide.with(getContext())
                                    .load(mModel.getRc_photo())
                                    .into(mBinding.rcPhptoCamBtn);


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
        mBinding.setData(mModel);
        mListener.nextScreen(null, 1);
    }


    void setSpinner() {

/*        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.brand_array, R.layout.spinner_item_layout);*/
        ArrayAdapter<VehicleModel.Brand> adapter = new ArrayAdapter<VehicleModel.Brand>
                (getContext(), R.layout.spinner_item_layout, mBrandAry);
       // adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.vehicleBrandSpinner.setAdapter(adapter);


 /*       ArrayAdapter<CharSequence> model_adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.model_array, R.layout.spinner_item_layout);*/
        ArrayAdapter<VehicleModel.Model> model_adapter = new ArrayAdapter<VehicleModel.Model>
                (getContext(), R.layout.spinner_item_layout, mModelAry);
        // model_adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.vehicleModelSpinner.setAdapter(model_adapter);


      /*  ArrayAdapter<CharSequence> region__adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.region_array, R.layout.spinner_item_layout);*/
        ArrayAdapter<VehicleModel.Region> region__adapter = new ArrayAdapter<VehicleModel.Region>
                (getContext(), R.layout.spinner_item_layout, mRegionAry);
        // region__adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.vehicleRegionSpinner.setAdapter(region__adapter);


/*        ArrayAdapter<CharSequence> category__adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.category_array, R.layout.spinner_item_layout);*/
        ArrayAdapter<VehicleModel.Category> category__adapter = new ArrayAdapter<VehicleModel.Category>
                (getContext(), R.layout.spinner_item_layout, mCatAry);
        //   category__adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.vehicleCategorySpinner.setAdapter(category__adapter);


        mBinding.vehicleBrandSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.gary));
                        mModel.setBrand("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                      //  mModel.setBrand(mBrandAry.get(i).getId().toString());
                        mModel.setBrand(mBrandAry.get(i).getName());
                    /*    for (VehicleModel.Model m : mVehicleModel.getModels()) {
                            mModelAry.add(m.getName());
                        }*/
                    }

                //  ((TextView) adapterView.getChildAt(i)).setTextSize(getResources().getDimension(R.dimen._10sdp));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mBinding.vehicleModelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.gary));
                        mModel.setModel("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        //mModel.setModel(mModelAry.get(i).getId().toString());
                        mModel.setModel(mModelAry.get(i).getName());
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mBinding.vehicleRegionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.gary));
                        mModel.setRegion("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                     //   mModel.setRegion(mRegionAry.get(i).getId().toString());
                        mModel.setRegion(mRegionAry.get(i).getName());
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        mBinding.vehicleCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.gary));
                        mModel.setCategory("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                       // mModel.setCategory(mCatAry.get(i).getId().toString());
                        mModel.setCategory(mCatAry.get(i).getName());
                    }
                //  ((TextView) adapterView.getChildAt(i)).setTextSize(getResources().getDimension(R.dimen._10sdp));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    void enableDisableViews(boolean type, float alpha) {

        mBinding.driPhotoLayout.setAlpha(alpha);
        mBinding.registrationLayout.setAlpha(alpha);
        mBinding.linearLayout2.setAlpha(alpha);


        mBinding.dlNumET.setEnabled(type);
        mBinding.dlPhotoAddCamBtn.setEnabled(type);
        mBinding.dlPhotoAddBtn.setEnabled(type);

        mBinding.rcPhptoCamBtn.setEnabled(type);
        mBinding.rcPhotoAddBtn.setEnabled(type);
        mBinding.plateNumET.setEnabled(type);

        mBinding.vehicleBrandSpinner.setEnabled(type);
        mBinding.vehicleModelSpinner.setEnabled(type);
        mBinding.vehicleRegionSpinner.setEnabled(type);
        mBinding.vehicleCategorySpinner.setEnabled(type);
        mBinding.tokenNumET.setEnabled(type);

    }


    void getVehicleData() {
        ((SignUpActivity) requireActivity()).showLoading();
        Call<VehicleModel> call = new ApiManagerImp().getVehicleData();
        call.enqueue(new Callback<VehicleModel>() {
            @Override
            public void onResponse(Call<VehicleModel> call, Response<VehicleModel> response) {
                ((SignUpActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                    mVehicleModel = response.body();
                    Log.d("res", String.valueOf(response.body()));
                    if (mVehicleModel.getModels() != null)
                        mModelAry.addAll(mVehicleModel.getModels());
                    if (mVehicleModel.getBrands() != null)
                        mBrandAry.addAll(mVehicleModel.getBrands());

                    if (mVehicleModel.getCategories() != null)
                        mCatAry.addAll(mVehicleModel.getCategories());

                    if (mVehicleModel.getRegions() != null)
                        mRegionAry.addAll(mVehicleModel.getRegions());


                    setSpinner();

                }
            }

            @Override
            public void onFailure(Call<VehicleModel> call, Throwable t) {
                ((SignUpActivity) requireActivity()).hideLoading();
                Toast.makeText(getContext(), (R.string.try_again), Toast.LENGTH_SHORT).show();

            }
        });
    }


}