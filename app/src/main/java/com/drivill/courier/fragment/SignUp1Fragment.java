package com.drivill.courier.fragment;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.drivill.courier.R;
import com.drivill.courier.activity.SignUpActivity;
import com.drivill.courier.databinding.FragmentSignUp1Binding;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.interfaces.SignUpListener;
import com.drivill.courier.merchantModul.activity.SignUpActivityMerchant;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

import static com.drivill.courier.activity.SignUpActivity.mModel;
import static com.drivill.courier.utils.Constant.FATHER_NID_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.FATHER_NID_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.NID_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.NID_IMG_PERMISSION;
import static com.drivill.courier.utils.Constant.PROFILE_CAMERA_PERMISSION;
import static com.drivill.courier.utils.Constant.PROFILE_IMG_PERMISSION;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUp1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUp1Fragment extends Fragment {
    boolean hasNidPic = false, hasFatherNidPic = false, hasProfile = false;
    FragmentSignUp1Binding mBinding;
    SignUpListener mListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    void initUI() {
        mListener = (SignUpListener) this.getActivity();
        mBinding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validation();
            }
        });


        mBinding.profilePicFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.PROFILE_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.PROFILE_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.PROFILE_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.PROFILE_IMG_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE, Constant.PROFILE_IMG_PERMISSION);
                }

            }
        });

        mBinding.profilePicFromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.PROFILE_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.PROFILE_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.PROFILE_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.PROFILE_CAMERA_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.CAMERA, Constant.PROFILE_CAMERA_PERMISSION);
                }
            }
        });

        mBinding.nidPicAddFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.NID_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.NID_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.NID_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.NID_IMG_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE, Constant.NID_IMG_PERMISSION);
                }
            }
        });

        mBinding.nidPicAddFromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.NID_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.NID_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.NID_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.NID_CAMERA_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                          Manifest.permission.CAMERA, Constant.NID_CAMERA_PERMISSION);
                }

            }
        });
        mBinding.fatherNidPickFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (AppUtil.checkPermissionForStorage(getActivity()))
                    AppUtil.callGalleryPermission(getActivity(), Constant.FATHER_NID_IMG_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE, Constant.FATHER_NID_IMG_PERMISSION);*/

                if(AppUtil.checkPermissionForStorage(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.FATHER_NID_IMG_PERMISSION);
                    }else {
                        AppUtil.callGalleryPermission(getActivity(), Constant.FATHER_NID_IMG_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.READ_EXTERNAL_STORAGE, Constant.FATHER_NID_IMG_PERMISSION);
                }

            }
        });

        mBinding.fatherNidPicFromCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  if (AppUtil.checkPermissionForCamera(getActivity()))
                    AppUtil.callCameraPermission(getActivity(), Constant.FATHER_NID_CAMERA_PERMISSION);
                else AppUtil.requestPermission(getActivity(),
                        Manifest.permission.CAMERA, Constant.FATHER_NID_CAMERA_PERMISSION);*/

                if(AppUtil.checkPermissionForCamera(getActivity())){
                    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q){
                        ((SignUpActivity)getActivity()).InitPicker(Constant.FATHER_NID_CAMERA_PERMISSION);
                    }else {
                        AppUtil.callCameraPermission(getActivity(), Constant.FATHER_NID_CAMERA_PERMISSION);
                    }

                }else {
                    AppUtil.requestPermission(getActivity(),
                            Manifest.permission.CAMERA, Constant.FATHER_NID_CAMERA_PERMISSION);
                }

            }
        });
    }

    public SignUp1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUp1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUp1Fragment newInstance(String param1, String param2) {
        SignUp1Fragment fragment = new SignUp1Fragment();
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
        View view = inflater.inflate(R.layout.fragment_sign_up1, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        ((SignUpActivity) getActivity()).passVal(new FragmentCommunicator() {
            @Override
            public void passData(String name, int type) {
                mBinding.setData(mModel);
                switch (type) {
                    case NID_CAMERA_PERMISSION:
                    case NID_IMG_PERMISSION:
                        if (name != null) {
                            //  mBinding.nidPhotoTxt.setText(name);
                            hasNidPic = true;
                            Glide.with(getContext())
                                    .load(mModel.getNidPicture())
                                    .into(mBinding.nidPicAddFromFile);
                            Glide.with(getContext())
                                    .load(mModel.getNidPicture())
                                    .into(mBinding.nidPicAddFromCam);
                        }

                        break;
                    case FATHER_NID_CAMERA_PERMISSION:
                    case FATHER_NID_IMG_PERMISSION:
                        if (name != null) {
                            // mBinding.fatherNidPicTxt.setText(name);
                            hasFatherNidPic = true;
                            Glide.with(getContext())
                                    .load(mModel.getFatherNidPic())
                                    .into(mBinding.fatherNidPickFromFile);
                            Glide.with(getContext())
                                    .load(mModel.getFatherNidPic())
                                    .into(mBinding.fatherNidPicFromCam);


                        }

                        break;
                    case PROFILE_CAMERA_PERMISSION:
                    case PROFILE_IMG_PERMISSION:
                        if (name != null) {
                            // mBinding.fatherNidPicTxt.setText(name);
                            hasProfile = true;

                            Glide.with(getContext())
                                    .load(mModel.getPicture())
                                    .into(mBinding.profilePicFromFile);
                            Glide.with(getContext())
                                    .load(mModel.getPicture())
                                    .into(mBinding.profilePicFromCam);

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
    }

    void addData(String nidNum, String nidPic, String fnidNum, String fnidPic) {
        mModel.setNidNumber(nidNum);
        // SignUpActivity.mModel.setNidPicture(nidPic);
        mModel.setFatherNid(fnidNum);
        //SignUpActivity.mModel.setFatherNidPhoto();

        mListener.nextScreen(new SignUp2Fragment(), 1);
    }

    void validation() {
        String nidNum, nidPhoto, fatherNidNum, fatherNidPhoto;
        nidNum = mBinding.nidNumET.getText().toString();
        nidPhoto = mBinding.nidPhotoTxt.getText().toString();
        fatherNidNum = mBinding.fatherNidET.getText().toString();
        fatherNidPhoto = mBinding.fatherNidPicTxt.getText().toString();

        if (mModel.getPicture() != null && !mModel.getPicture().getName().isEmpty()) {
            hasProfile = true;
        }
        if (mModel.getNidPicture() != null && !mModel.getNidPicture().getName().isEmpty()) {
            hasNidPic = true;
        }
        if (mModel.getFatherNidPic() != null && !mModel.getFatherNidPic().getName().isEmpty()) {
            hasFatherNidPic = true;
        }


        if (!nidNum.isEmpty() && !fatherNidNum.isEmpty()) {

            if (hasNidPic && hasFatherNidPic && hasProfile) {
                addData(nidNum, nidPhoto, fatherNidNum, fatherNidPhoto);
            } else {
                Toast.makeText(this.requireContext(), (R.string.select_photo), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this.requireContext(), (R.string.all_field_required), Toast.LENGTH_SHORT).show();
        }
    }
}