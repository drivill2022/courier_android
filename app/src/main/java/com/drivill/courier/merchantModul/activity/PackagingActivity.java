package com.drivill.courier.merchantModul.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityPackagingBinding;
import com.drivill.courier.merchantModul.adapter.spinnerAdapter.CustomDistrictSpinnerAdapter;
import com.drivill.courier.merchantModul.adapter.spinnerAdapter.CustomSpinnerAdapter;
import com.drivill.courier.merchantModul.adapter.spinnerAdapter.CustomThanaSpinnerAdapter;
import com.drivill.courier.merchantModul.fragment.PackagingFragment;
import com.drivill.courier.merchantModul.model.DistrictModel;
import com.drivill.courier.merchantModul.model.DivisionModel;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.ThanaModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.MyUtil;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackagingActivity extends BaseActivity {

    ActivityPackagingBinding mBinding;
    ArrayList<DivisionModel> sDivision;
    ArrayList<ThanaModel> sThana;
    ArrayList<DistrictModel> sDistrict;
    ApiManagerImp mApiManager;
    List<String> addressesStr = new ArrayList<>();
    public static ShipmentCreateModel.Data mShipmentModel;

    private PrefsManager pf ;

    String mDivPickup, mDisPickup, mThanaPickup;
    String mDivDeliver, mDisDeliver, mThanaDeliver;

    void initUI() {
        pf =  new PrefsManager(PackagingActivity.this);
        mShipmentModel = new ShipmentCreateModel().new Data();

        mBinding.pickupAddressET.setText(pf.getAddress());

        mBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mShipmentModel.setPickupDate(mBinding.selectDateText.getText().toString());
                mShipmentModel.setsAddress(mBinding.pickupAddressET.getText().toString());
                mShipmentModel.setdAddress(mBinding.deliveryAddressET.getText().toString());

            //    Log.i("pkg**=","pf*= "+pf.get_division()+"," +pf.get_district()+", "+pf.get_thana());

                if(mShipmentModel.getsDistrict().equals("")){
                    mShipmentModel.setsDistrict(String.valueOf(pf.get_district()));
                }

                if(mShipmentModel.getsDivision().equals("")){
                    mShipmentModel.setsDivision(String.valueOf(pf.get_division()));
                }

                if(mShipmentModel.getsThana().equals("")){
                    mShipmentModel.setsThana(String.valueOf(pf.get_thana()));
                }


                if (mShipmentModel.isValidData1(PackagingActivity.this)) {

                    mBinding.nextBtn.setEnabled(false);
                  Log.i("nextBtn","btn Disable");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBinding.nextBtn.setEnabled(true);
                            Log.i("nextBtn","btn Enable");
                        }
                    }, 2000);

                    try {
                        List<Address> addresses = AppUtil.getAddressList(PackagingActivity.this,
                                mThanaPickup + "," + mDisPickup + "," + mDivPickup);
                        if (addresses.size() != 0) {
                            mShipmentModel.setS_latitude(String.valueOf(addresses.get(0).getLatitude()));
                            mShipmentModel.setS_longitude(String.valueOf(addresses.get(0).getLongitude()));
                        } else {
                        /*Toast.makeText(PackagingActivity.this, "Please Select Correct Pickup Address", Toast.LENGTH_SHORT).show();
                        return;*/

                            mShipmentModel.setS_latitude("");
                            mShipmentModel.setS_longitude("");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        List<Address> addresses = AppUtil.getAddressList(PackagingActivity.this,
                                mThanaDeliver + "," + mDisDeliver + "," + mDivDeliver);
                        if (addresses.size() != 0) {
                            mShipmentModel.setD_latitude(String.valueOf(addresses.get(0).getLatitude()));
                            mShipmentModel.setD_longitude(String.valueOf(addresses.get(0).getLongitude()));
                        } else {
                            mShipmentModel.setD_latitude("");
                            mShipmentModel.setD_longitude("");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    switchFragment(new PackagingFragment());
                 //   Log.i("pkg*=", "nextbtnlist= "+new Gson().toJson(mShipmentModel));

                }
            }
        });

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.pickupAddressET.addTextChangedListener(new TextWatcher() {
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

        mBinding.deliveryAddressET.addTextChangedListener(new TextWatcher() {
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

/*        sDivision = new ArrayList<>();
        DivisionModel divisionModel = new DivisionModel();
        divisionModel.setName(getString(R.string.select_divi));
        sDivision.add(divisionModel);
        settingDiviSpinner();
        settingDiviSpinnerLocation();*/

        getDivisionApi_auto();

   /*     initialSpinner(true);
        initialSpinner(false);*/

    }

    @Override
    protected void setUp() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_packaging);
        mApiManager = new ApiManagerImp();
        initUI();

        MyUtil.ChangeStatusBarColor(PackagingActivity.this,R.color.theme_color);

        UpdatedHeadeerText("Act");
    }


    public void UpdatedHeadeerText(String from){
        if(from.equalsIgnoreCase("Act")){
            mBinding.title.setText(getString(R.string.step_1));
        }else {
            mBinding.title.setText(getString(R.string.step_2));
        }
    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void initialSpinner(boolean typ) {

        sThana = new ArrayList<>();
        sDistrict = new ArrayList<>();
        ThanaModel model = new ThanaModel();
        model.setName(getString(R.string.select_thana));
        sThana.add(model);

        DistrictModel model2 = new DistrictModel();
        model2.setName(getString(R.string.select_district));
        sDistrict.add(model2);
        if (typ) {
            settingDistSpinner();
            settingThanaSpinner();
        } else {
            settingDistSpinnerLocation();
            settingThanaSpinnerLocation();
        }

    }

    void settingDiviSpinner(){
        CustomSpinnerAdapter spinnerArrayAdapter = new CustomSpinnerAdapter(PackagingActivity.this, R.layout.spinner_item_layout, sDivision);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectDivisionSpinner.setAdapter(spinnerArrayAdapter);
        mBinding.selectDivisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setsDivision("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        String id = ((TextView) view).getTag().toString();
                        mShipmentModel.setsDivision(id);
                        mDivPickup = ((TextView) view).getText().toString();
                        getDistrictApi(((TextView) view).getTag().toString(), true);
                    }

                initialSpinner(true);
                 //   Log.i("pkg*=", "Divlist= "+new Gson().toJson(mShipmentModel));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void settingDistSpinner() {
        CustomDistrictSpinnerAdapter spinnerArrayAdapter = new CustomDistrictSpinnerAdapter
                (PackagingActivity.this, R.layout.spinner_item_layout, sDistrict);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectDisSpinner.setAdapter(spinnerArrayAdapter);


        mBinding.selectDisSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setsDistrict("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        String id = ((TextView) view).getTag().toString();
                        mShipmentModel.setsDistrict(id);
                        mDisPickup = ((TextView) view).getText().toString();
                        getThanaApi(id, true);
                    }
                settingThanaSpinner();
             //   Log.i("pkg*=", "Distlist= "+new Gson().toJson(mShipmentModel));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void settingThanaSpinner() {
        CustomThanaSpinnerAdapter spinnerArrayAdapter = new CustomThanaSpinnerAdapter
                (PackagingActivity.this, R.layout.spinner_item_layout, sThana);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectThanaSpinner.setAdapter(spinnerArrayAdapter);

        mBinding.selectThanaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        String id = ((TextView) view).getTag().toString();
                        mShipmentModel.setsThana(id);
                        mThanaPickup = ((TextView) view).getText().toString();
                        //   getThanaApi(((TextView) view).getTag().toString());
                    }

             //   Log.i("pkg*=", "Thanalist= "+new Gson().toJson(mShipmentModel));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void settingDiviSpinnerLocation() {
        CustomSpinnerAdapter spinnerArrayAdapter = new CustomSpinnerAdapter
                (PackagingActivity.this, R.layout.spinner_item_layout, sDivision);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectDivisionSpinnerLocation.setAdapter(spinnerArrayAdapter);
        mBinding.selectDivisionSpinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setdDivision("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        String id = ((TextView) view).getTag().toString();
                        mShipmentModel.setdDivision(id);
                        mDivDeliver = ((TextView) view).getText().toString();
                        getDistrictApi(id, false);
                    }
                initialSpinner(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    void settingDistSpinnerLocation() {
        CustomDistrictSpinnerAdapter spinnerArrayAdapter = new CustomDistrictSpinnerAdapter
                (PackagingActivity.this, R.layout.spinner_item_layout, sDistrict);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectDisSpinnerLocation.setAdapter(spinnerArrayAdapter);


        mBinding.selectDisSpinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setdDistrict("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        String id = ((TextView) view).getTag().toString();
                        mShipmentModel.setdDistrict(id);
                        mDisDeliver = ((TextView) view).getText().toString();
                        getThanaApi(id, false);
                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void settingThanaSpinnerLocation() {
        CustomThanaSpinnerAdapter spinnerArrayAdapter = new CustomThanaSpinnerAdapter
                (PackagingActivity.this, R.layout.spinner_item_layout, sThana);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectThanaSpinnerLocation.setAdapter(spinnerArrayAdapter);


        mBinding.selectThanaSpinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setdThana("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        String id = ((TextView) view).getTag().toString();
                        mThanaDeliver = ((TextView) view).getText().toString();
                        mShipmentModel.setdThana(id);

                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    void getDivisionApi() {
        showLoading();
        Call<ArrayList<DivisionModel>> call = mApiManager.getDivisionMerchant();
        call.enqueue(new Callback<ArrayList<DivisionModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DivisionModel>> call, Response<ArrayList<DivisionModel>> response) {
                Log.d("", "");
                hideLoading();
                if (response.body() != null) {
                    // response.body().g
                    /// sDivision.add(getApplicationContext().getString(R.string.select_divi))
                    /// for (JsonElement obj : response.body()) {
                    sDivision = new ArrayList<>();
                    DivisionModel model = new DivisionModel();
                    model.setName(getResources().getString(R.string.select_divi));

                    sDivision.add(model);
                    sDivision.addAll(response.body());

                  //  settingDiviSpinner();
                    settingDiviSpinnerLocation();

                    Log.d("", "");

                }
            }

            @Override
            public void onFailure(Call<ArrayList<DivisionModel>> call, Throwable t) {
                Log.d("", String.valueOf(t));
                hideLoading();
                showMessage(getResources().getString(R.string.try_again));

            }
        });
    }

    void getDistrictApi(String divId, boolean typ) {
        showLoading();
        Call<ArrayList<DistrictModel>> call = mApiManager.getDistrictMerchant(divId);
        call.enqueue(new Callback<ArrayList<DistrictModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DistrictModel>> call, Response<ArrayList<DistrictModel>> response) {
                hideLoading();
                if (response.body() != null) {
                    sDistrict = new ArrayList<>();
                    DistrictModel model = new DistrictModel();
                    model.setName(getString(R.string.select_district));
                    sDistrict.add(model);
                    sDistrict.addAll(response.body());

                    if (typ)
                        settingDistSpinner();
                    else settingDistSpinnerLocation();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DistrictModel>> call, Throwable t) {
                Log.d("", String.valueOf(t));
                hideLoading();
                showMessage(getResources().getString(R.string.try_again));
            }
        });
    }

    void getThanaApi(String distId, boolean typ) {
        showLoading();
        Call<ArrayList<ThanaModel>> call = mApiManager.getThanaMerchant(distId);
        call.enqueue(new Callback<ArrayList<ThanaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ThanaModel>> call, Response<ArrayList<ThanaModel>> response) {
                hideLoading();
                if (response.body() != null) {
                    sThana = new ArrayList<>();

                    ThanaModel model = new ThanaModel();
                    model.setName(getString(R.string.select_thana));
                    sThana.add(model);
                    sThana.addAll(response.body());

                    if (typ)
                        settingThanaSpinner();
                    else settingThanaSpinnerLocation();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ThanaModel>> call, Throwable t) {
                hideLoading();
                showMessage(getResources().getString(R.string.try_again));
            }
        });
    }

    ///  Calls for auto fill data----

    void getDivisionApi_auto() {
        Call<ArrayList<DivisionModel>> call = mApiManager.getDivisionMerchant();
        call.enqueue(new Callback<ArrayList<DivisionModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DivisionModel>> call, Response<ArrayList<DivisionModel>> response) {
                if (response.body() != null) {
                    sDivision = new ArrayList<>();
                    sDivision.addAll(response.body());

                    for(int i=0; i<sDivision.size(); i++){
                     /*   Log.i("pkg**=","division= "+sDivision.get(i).getName());
                        Log.i("pkg**=","pfdivision= "+pf.get_division());*/
                        if(sDivision.get(i).getId()==pf.get_division()){
                            Collections.swap(sDivision,i,0);
                            getDistrictApi_auto(String.valueOf(pf.get_division()));
                        }
                    }

                    settingDiviSpinner();


                }
            }

            @Override
            public void onFailure(Call<ArrayList<DivisionModel>> call, Throwable t) {
                Log.d("", String.valueOf(t));
                showMessage(getResources().getString(R.string.try_again));

            }
        });
    }

    void getDistrictApi_auto(String divId) {
        Call<ArrayList<DistrictModel>> call = mApiManager.getDistrictMerchant(divId);
        call.enqueue(new Callback<ArrayList<DistrictModel>>() {
            @Override
            public void onResponse(Call<ArrayList<DistrictModel>> call, Response<ArrayList<DistrictModel>> response) {
                if (response.body() != null) {
                    sDistrict = new ArrayList<>();
                    sDistrict.addAll(response.body());
                    for(int i=0; i<sDistrict.size(); i++){
                   /*     Log.i("pkg**=","sDistrict= "+sDistrict.get(i).getName());
                        Log.i("pkg**=","pfdistrict= "+pf.get_district());*/
                        if(sDistrict.get(i).getId()==pf.get_district()){
                            Collections.swap(sDistrict,i,0);
                            getThanaApi_auto(String.valueOf(pf.get_district()));
                        }
                    }

                     settingDistSpinner();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<DistrictModel>> call, Throwable t) {
                Log.d("", String.valueOf(t));
                showMessage(getResources().getString(R.string.try_again));
            }
        });
    }

    void getThanaApi_auto(String distId) {
        Call<ArrayList<ThanaModel>> call = mApiManager.getThanaMerchant(distId);
        call.enqueue(new Callback<ArrayList<ThanaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ThanaModel>> call, Response<ArrayList<ThanaModel>> response) {
                if (response.body() != null) {
                    sThana = new ArrayList<>();
                    sThana.addAll(response.body());

                    for(int i=0; i<sThana.size(); i++){
                      /*  Log.i("pkg**=","sThana= "+sThana.get(i).getName());
                        Log.i("pkg**=","pfsThana= "+pf.get_thana());*/
                        if(sThana.get(i).getId()==pf.get_thana()){
                            mShipmentModel.setsThana(String.valueOf(pf.get_thana()));
                            Collections.swap(sThana,i,0);
                        }
                    }

                    settingThanaSpinner();
                    initialSpinner(false);
                    getDivisionApi();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ThanaModel>> call, Throwable t) {
                showMessage(getResources().getString(R.string.try_again));
            }
        });
    }

}