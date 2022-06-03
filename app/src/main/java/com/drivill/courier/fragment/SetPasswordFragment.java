package com.drivill.courier.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.activity.SignUpActivity;
import com.drivill.courier.databinding.FragmentSetPasswordBinding;
import com.drivill.courier.interfaces.SignUpListener;
import com.drivill.courier.merchantModul.activity.SignUpActivityMerchant;
import com.drivill.courier.merchantModul.fragment.SignUp2Merchant;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.DataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drivill.courier.activity.SignUpActivity.mModel;
import static com.drivill.courier.merchantModul.activity.SignUpActivityMerchant.mSignUpModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SetPasswordFragment extends Fragment {

    FragmentSetPasswordBinding mBinding;
    SignUpListener mListener;
    boolean mCurrentAction = true;
    boolean confirmVisibility = true;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER01234567890
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    void initUI() {
        mListener = (SignUpListener) this.getActivity();
        mBinding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (DataManager.getINSTANCE().isNetworkConnected(getContext()))
                    if (validation()) {

                        if (((BaseActivity) requireActivity()).mBasePreferenceManager.getIsRider()) {
                            mModel.setPassword(mBinding.createPasswordET.getText().toString());
                            mModel.setPassword_confirmation(mBinding.createPasswordET.getText().toString());
                            sigUpRider();
                        } else {
                            mSignUpModel.setPassWord(mBinding.createPasswordET.getText().toString());
                            mSignUpModel.setcPassword(mBinding.createPasswordET.getText().toString());
                            singUpMerchant();
                        }

                    }
            }
        });

        handleClickOnEditTextIcon();
    }

    public SetPasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SetPasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SetPasswordFragment newInstance(String param1, String param2) {
        SetPasswordFragment fragment = new SetPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_set_password, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        return view;
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
                mBinding.createPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, requireContext().getDrawable(R.drawable.eye_visible), null);

            } else {
                mCurrentAction = true;
                mBinding.createPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.createPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, requireContext().getDrawable(R.drawable.visibility), null);

            }
            mBinding.createPasswordET.setSelection(mBinding.createPasswordET.getText().length());
        } else {

            if (type) {
                confirmVisibility = false;
                mBinding.confirmPasswordET.setInputType(InputType.TYPE_CLASS_TEXT);
                mBinding.confirmPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, requireContext().getDrawable(R.drawable.eye_visible), null);

            } else {
                confirmVisibility = true;
                mBinding.confirmPasswordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.confirmPasswordET.setCompoundDrawablesWithIntrinsicBounds(null, null, requireContext().getDrawable(R.drawable.visibility), null);

            }
            mBinding.confirmPasswordET.setSelection(mBinding.confirmPasswordET.getText().length());
        }

    }

    boolean validation() {
        String pass, cPass;
        pass = mBinding.createPasswordET.getText().toString();
        cPass = mBinding.confirmPasswordET.getText().toString();

        if (pass.isEmpty() || cPass.isEmpty()) {
            Toast.makeText(getContext(), getResources().getString(R.string.plse_enter_pass), Toast.LENGTH_SHORT).show();
        } else if (pass.length() < 8) {
            Toast.makeText(getContext(), getResources().getString(R.string.enter_8_character_password), Toast.LENGTH_SHORT).show();
        } /*else if (!AppUtil.isValidPassword(pass)) {
            Toast.makeText(getContext(), "password must contain one upper case and number", Toast.LENGTH_LONG).show();
        }*/ else if (!pass.equals(cPass)) {
            Toast.makeText(getContext(), "confirm password is wrong", Toast.LENGTH_SHORT).show();
        } else {

            return true;
        }

        return false;
    }

    private void sigUpRider() {
        DataManager.getINSTANCE().showProgressBar(getActivity());

        // ((SignUpActivity) getActivity()).showLoading();
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), mModel.getNidPicture());
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part nid_photo = MultipartBody.Part.createFormData("nid_picture", mModel.getNidPicture().getName(), requestFile);

        RequestBody requestFileFather = RequestBody.create(MediaType.parse("image/*"), mModel.getFatherNidPic());
        MultipartBody.Part father_photo = MultipartBody.Part.createFormData("father_nid_pic", mModel.getFatherNidPic().getName(), requestFileFather);

        RequestBody requestFileProfile_photo = RequestBody.create(MediaType.parse("image/*"), mModel.getPicture());

        MultipartBody.Part profile_photo = MultipartBody.Part.createFormData("picture", mModel.getPicture().getName(), requestFileProfile_photo);

        MultipartBody.Part dl_photo = null;
        MultipartBody.Part rc_photo = null;
        try {
            RequestBody requestFileDl_photo = RequestBody.create(MediaType.parse("image/*"), mModel.getDl_photo());
            dl_photo = MultipartBody.Part.createFormData("dl_photo", mModel.getDl_photo().getName(), requestFileDl_photo);


            RequestBody requestFileRc_photo = RequestBody.create(MediaType.parse("image/*"), mModel.getRc_photo());
            rc_photo = MultipartBody.Part.createFormData("rc_photo", mModel.getRc_photo().getName(), requestFileRc_photo);


        } catch (Exception e) {
            e.printStackTrace();
        }


        // add another part within the multipart request

        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), mModel.getName());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), mModel.getAddress());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), mModel.getEmail());
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), mModel.getGender());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), mModel.getPassword());
        RequestBody password_conf = RequestBody.create(MediaType.parse("text/plain"), mModel.getPassword_confirmation());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), mModel.getMobile());
        // RequestBody referral_cod = RequestBody.create(MediaType.parse("text/plain"), mModel.getReferral_by());
        RequestBody nid_num = RequestBody.create(MediaType.parse("text/plain"), mModel.getNidNumber());
        RequestBody father_nid_num = RequestBody.create(MediaType.parse("text/plain"), mModel.getFatherNid());
        RequestBody vehicleType = RequestBody.create(MediaType.parse("text/plain"), mModel.getVehicleTypeId().toString());
        RequestBody hub_id = RequestBody.create(MediaType.parse("text/plain"), mModel.getHubId());
        RequestBody latitude = RequestBody.create(MediaType.parse("text/plain"), mModel.getLatitude());
        RequestBody longitude = RequestBody.create(MediaType.parse("text/plain"), mModel.getLongitude());

        RequestBody dl_number = null;
        RequestBody plate_num = null;
        RequestBody brand = null;
        RequestBody model = null;
        RequestBody region = null;
        RequestBody category = null;
        RequestBody tokenNum = null;
        try {
            dl_number = RequestBody.create(MediaType.parse("text/plain"), mModel.getDl_number());
            plate_num = RequestBody.create(MediaType.parse("text/plain"), mModel.getPlat_number());
            brand = RequestBody.create(MediaType.parse("text/plain"), mModel.getBrand());
            model = RequestBody.create(MediaType.parse("text/plain"), mModel.getModel());
            region = RequestBody.create(MediaType.parse("text/plain"), mModel.getRegion());
            category = RequestBody.create(MediaType.parse("text/plain"), mModel.getCategory());
            tokenNum = RequestBody.create(MediaType.parse("text/plain"), mModel.getToken_number());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //This s Not In UI
        // RequestBody profilePic = RequestBody.create(MediaType.parse("text/plain"), mModel.getPicture());
        // RequestBody father_name = RequestBody.create(MediaType.parse("text/plain"), mModel.getfatername);
        //RequestBody thana = RequestBody.create(MediaType.parse("text/plain"), mModel.getThana());


        // finally, execute the request

        Call<JsonObject> call;
        if (mModel.getVehicleTypeId() == 1) {
            call = new ApiManagerImp().riderSignUp(
                    name, hub_id, address, email, gender, password, password_conf,
                    mobile, profile_photo, nid_num, nid_photo, father_photo, father_nid_num, vehicleType, longitude, latitude);
        } else {
            call = new ApiManagerImp().riderSignUp(
                    name, hub_id, address, email, gender, password, password_conf, mobile, profile_photo
                    , nid_num, nid_photo, father_photo, father_nid_num,
                    vehicleType, dl_number, dl_photo, rc_photo,
                    brand, model, region, category, plate_num, tokenNum, longitude, latitude
            );
        }

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                DataManager.getINSTANCE().hideProgressBar();
                if (response.body() != null) {
                    JSONObject object = null;
                    try {
                        object = new JSONObject(String.valueOf(response.body()));
                        Log.d("obj", String.valueOf(object));
                        //   ((SignUpActivity) requireActivity()).mBasePreferenceManager.setIS_LOGIN(true);
                        ((SignUpActivity) requireActivity()).mBasePreferenceManager.setTOKEN_TYPE(object.getString("token_type"));
                        ((SignUpActivity) requireActivity()).mBasePreferenceManager.setREFRESH_TOKEN(object.getString("refresh_token"));
                        ((SignUpActivity) requireActivity()).mBasePreferenceManager.setUserToken(object.getString("token_type") + " " + object.getString("access_token"));
                        Toast.makeText(requireActivity(), object.getString("message"), Toast.LENGTH_SHORT).show();
                        mListener.nextScreen(new ThankYouFragment(), 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else if (response.errorBody() != null) {

                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("err", String.valueOf(object));
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < object.getJSONArray("error").length(); i++) {
                            builder.append(object.getJSONArray("error").get(i));
                            builder.append("\n");
                        }
                        ((SignUpActivity) requireActivity()).onError(builder.toString());  //   AppUtil.showCustomDialog(builder.toString(), getContext());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("err", String.valueOf(t));
                DataManager.getINSTANCE().hideProgressBar();
                Toast.makeText(getActivity(), getResources().getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void singUpMerchant() {
        DataManager.getINSTANCE().showProgressBar(getActivity());

        RequestBody requestFileProfile_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getProfileFile());
        MultipartBody.Part profile_photo = MultipartBody.Part.createFormData("picture", mSignUpModel.getProfileFile().getName(), requestFileProfile_photo);

        RequestBody requestFileRc_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getBussinessLogoFile());
        MultipartBody.Part businessLogoFile = MultipartBody.Part.createFormData("business_logo", mSignUpModel.getBussinessLogoFile().getName(), requestFileRc_photo);

        MultipartBody.Part trad_lic_photo = null;
        try {

            RequestBody requestFileDl_photo = RequestBody.create(MediaType.parse("image/*"), mSignUpModel.getTradeLicense());
            trad_lic_photo = MultipartBody.Part.createFormData("trade_lic_no", mSignUpModel.getTradeLicense().getName(), requestFileDl_photo);

        } catch (Exception e) {
            e.printStackTrace();
        }


        // add another part within the multipart request
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getName());
        RequestBody address = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getAddress());
        RequestBody email = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getEmail());
        //  RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getGender());
        RequestBody password = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getPassWord());
        RequestBody password_conf = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getcPassword());
        RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), ((BaseActivity) getActivity()).mBasePreferenceManager.getMobileNum());
        //RequestBody mobile = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getMobile());
        RequestBody nid_num = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getNidNumber());
        RequestBody bussName = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getBussName());
        RequestBody bussPhone = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getBussPhone());
        RequestBody bussAddress = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getAddress());
        RequestBody bussfb = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getFbPage());
        //  RequestBody paymeth = RequestBody.create(MediaType.parse("text/plain"), mSignUpModel.getPaymentMeth());
        RequestBody paymeth = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mSignUpModel.getPaymentMethod()));
        RequestBody latitude = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mSignUpModel.getLatitude()));
        RequestBody longitude = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(mSignUpModel.getLongitude()));


        // finally, execute the request
        HashMap<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < SignUp2Merchant.selectedProduct.size(); i++) {
            String value = SignUp2Merchant.selectedProduct.get(i);
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
            //   RequestBody valueReq = RequestBody.create(MediaType.parse("text/plain"), value);
            map.put("product_type[" + i + "]", requestBody);


        }

        Call<JsonObject> call = new ApiManagerImp().merchantSignUp(name, address, email, mobile,
                password, password_conf, bussfb, profile_photo, nid_num, bussName, bussPhone,
                bussAddress, latitude,
                longitude, businessLogoFile, paymeth, map, trad_lic_photo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                DataManager.getINSTANCE().hideProgressBar();
                if (response.body() != null) {
                    JSONObject object = null;
                    try {
                        object = new JSONObject(String.valueOf(response.body()));
                        Log.i("signup-res=>", String.valueOf(object));
                        // ((SignUpActivityMerchant) requireActivity()).mBasePreferenceManager.setIS_LOGIN(true);
                        ((SignUpActivityMerchant) requireActivity()).mBasePreferenceManager.setTOKEN_TYPE(object.getString("token_type"));
                        ((SignUpActivityMerchant) requireActivity()).mBasePreferenceManager.setREFRESH_TOKEN(object.getString("refresh_token"));
                        ((SignUpActivityMerchant) requireActivity()).mBasePreferenceManager.setUserToken(object.getString("token_type") + " " + object.getString("access_token"));
                        Toast.makeText(requireActivity(), object.getString("message"), Toast.LENGTH_SHORT).show();
                        mListener.nextScreen(new ThankYouFragment(), 3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else if (response.errorBody() != null) {

                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("err", String.valueOf(object));
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < object.getJSONArray("error").length(); i++) {
                            builder.append(object.getJSONArray("error").get(i));
                            builder.append("\n");
                        }
                        ((SignUpActivityMerchant) requireActivity()).onError(builder.toString());  //   AppUtil.showCustomDialog(builder.toString(), getContext());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("err", String.valueOf(t));
                DataManager.getINSTANCE().hideProgressBar();
                Toast.makeText(getActivity(), getResources().getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            }
        });
    }

}