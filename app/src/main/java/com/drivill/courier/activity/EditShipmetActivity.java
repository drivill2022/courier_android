package com.drivill.courier.activity;

import static com.drivill.courier.merchantModul.activity.PackagingActivity.mShipmentModel;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityEditShipmetBinding;
import com.drivill.courier.merchantModul.adapter.spinnerAdapter.CustomDistrictSpinnerAdapter;
import com.drivill.courier.merchantModul.adapter.spinnerAdapter.CustomSpinnerAdapter;
import com.drivill.courier.merchantModul.adapter.spinnerAdapter.CustomThanaSpinnerAdapter;
import com.drivill.courier.merchantModul.model.DistrictModel;
import com.drivill.courier.merchantModul.model.DivisionModel;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.ThanaModel;
import com.drivill.courier.merchantModul.model.WeightAndProductTypeModel;
import com.drivill.courier.model.model.ShipmentDetailsModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.PrefsManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditShipmetActivity extends BaseActivity {

    ActivityEditShipmetBinding mBinding;
    ArrayList<DivisionModel> sDivision;
    ArrayList<ThanaModel> sThana;
    ArrayList<DistrictModel> sDistrict;
    ApiManagerImp mApiManager;
    List<String> addressesStr = new ArrayList<>();
    public static ShipmentCreateModel.Data mShipmentModel;

    ArrayList<WeightAndProductTypeModel.ProductType> mProductTypeArrayList = new ArrayList<>();
    ArrayList<WeightAndProductTypeModel.WeightList> mWeightArrayList = new ArrayList<>();

    private PrefsManager pf ;

    String mDivPickup, mDisPickup, mThanaPickup;
    String mDivDeliver, mDisDeliver, mThanaDeliver;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding=ActivityEditShipmetBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mApiManager= new ApiManagerImp();
        pf =  new PrefsManager(EditShipmetActivity.this);
        mShipmentModel = new ShipmentCreateModel().new Data();
        id=getIntent().getStringExtra("id");
        getShipment(getIntent().getStringExtra("id"));
        Log.e("eqwfe",getIntent().getStringExtra("id"));
       // getShipment("1");

        setUp();
        mBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mShipmentModel.setPickupDate(mBinding.selectDateText.getText().toString());
                mShipmentModel.setReceiverName(mBinding.nameET.getText().toString());
                mShipmentModel.setContactNo(mBinding.receiverPhoneET.getText().toString());
                // mShipmentModel.setProductDetail(mBinding.productDetailET.getText().toString());
                mShipmentModel.setProductDetail("");
                mShipmentModel.setNote(mBinding.noteET.getText().toString());
                mShipmentModel.setCod_amount(mBinding.codAmountET.getText().toString());
                mShipmentModel.setdAddress(mBinding.deliveryAddressET.getText().toString());

                if(mShipmentModel.isValidData3(EditShipmetActivity.this)){
                    updateShipment();
                }
            }
        });
    }

    @Override
    protected void setUp() {

        getDivisionApi_auto();
        getWeightListData();
        mBinding.selectDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();

                // new BottomSheet_Date(requireActivity()).show();

            }
        });

        WeightAndProductTypeModel.ProductType data = new WeightAndProductTypeModel.ProductType();
        WeightAndProductTypeModel.WeightList wet = new WeightAndProductTypeModel.WeightList();
        data.setName("Select Product");
        wet.setName("Select Weight");
        mProductTypeArrayList.add(data);
        mWeightArrayList.add(wet);


        ArrayAdapter<WeightAndProductTypeModel.ProductType> arrayAdapter = new ArrayAdapter<WeightAndProductTypeModel.ProductType>(
                this, R.layout.spinner_item_layout, mProductTypeArrayList
        );

        ArrayAdapter<WeightAndProductTypeModel.WeightList> arrayAdapterWet = new ArrayAdapter<WeightAndProductTypeModel.WeightList>(
                this, R.layout.spinner_item_layout, mWeightArrayList
        );

        mBinding.selectProductSpinner.setAdapter(arrayAdapter);
        mBinding.selectWeightSpinner.setAdapter(arrayAdapterWet);
        mBinding.selectProductSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setProductType("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setProductType(mProductTypeArrayList.get(i).getId().toString());
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mBinding.selectWeightSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (view != null)
                    if (i == 0) {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setProductWeight("");
                    } else {
                        ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                        mShipmentModel.setProductWeight(mWeightArrayList.get(i).getId());
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
        CustomSpinnerAdapter spinnerArrayAdapter = new CustomSpinnerAdapter(EditShipmetActivity.this, R.layout.spinner_item_layout, sDivision);
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
                (EditShipmetActivity.this, R.layout.spinner_item_layout, sDistrict);
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
                (EditShipmetActivity.this, R.layout.spinner_item_layout, sThana);
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
                (EditShipmetActivity.this, R.layout.spinner_item_layout, sDivision);
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
                (EditShipmetActivity.this, R.layout.spinner_item_layout, sDistrict);
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
                (EditShipmetActivity.this, R.layout.spinner_item_layout, sThana);
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
    void getWeightListData() {
       showLoading();
        Call<WeightAndProductTypeModel> call = new ApiManagerImp().getWeightAndProductList(
                mBasePreferenceManager.getUserToken()
        );
        call.enqueue(new Callback<WeightAndProductTypeModel>() {
            @Override
            public void onResponse(Call<WeightAndProductTypeModel> call, Response<WeightAndProductTypeModel> response) {
                hideLoading();
                if (response.body() != null) {

                    WeightAndProductTypeModel model = response.body();
                    mProductTypeArrayList.addAll(model.getData().getProductType());
                    if (model.getData().getWeightList() != null) {
                        mWeightArrayList.addAll(model.getData().getWeightList());
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        onError(object.getString("error"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<WeightAndProductTypeModel> call, Throwable t) {
                hideLoading();
            }
        });
    }

    void datePicker() {
        Date EndTime = null;
        Date CurrentTime = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
        try {
            EndTime = dateFormat.parse("08:00 am");
            CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            Log.i("exe-","time-"+e.toString());
        }

        final Calendar c = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        // c.add(Calendar.DATE, 1);
        if (CurrentTime != null && CurrentTime.after(EndTime)) {
            System.out.println("timeeee end ");
            c.add(Calendar.DATE, 1);
        }else {
            c.add(Calendar.DATE, 0);
        }
        Log.i("time-","time-"+CurrentTime +"\n"+ EndTime);

        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                      /*  mBinding.selectDateText.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);*/
                date.set(year, monthOfYear, dayOfMonth);
                mBinding.selectDateText.setText(AppUtil.getDateFormat(date.getTime()));


                    /*    new TimePickerDialog(PackagingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                              *//*  SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault());
                                String time = localDateFormat.format((date.getTime()));*//*
                                mBinding.selectDateText.setText(AppUtil.getDateFormat(date.getTime()));
                                // Log.v("TAG", "The choosen one " + date.getTime());
                            }
                        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
*/
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

        dpd.getDatePicker().setMinDate(c.getTimeInMillis());
        dpd.show();
    }

    void getShipment(String divId) {
        Call<ShipmentDetailsModel> call = new ApiManagerImp().getShipmentDetail(mBasePreferenceManager.getUserToken(),divId);
        call.enqueue(new Callback<ShipmentDetailsModel>() {
            @Override
            public void onResponse(Call<ShipmentDetailsModel> call, Response<ShipmentDetailsModel> response) {
                Log.e("edcsdcs",response.body().getData().toString());
                if (response.body() != null) {
                    if(response.body().getData() !=null) {
                        mBinding.nameET.setText(response.body().getData().getReceiverName());
                        mBinding.receiverPhoneET.setText(response.body().getData().getContactNo());
                        mBinding.noteET.setText(response.body().getData().getNote());
                        mBinding.codAmountET.setText(response.body().getData().getCodAmount().toString());
                        mBinding.selectDateText.setText(response.body().getData().getPickupDate());
                    }


                }
                else{
                    Toast.makeText(EditShipmetActivity.this,response.message(),Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<ShipmentDetailsModel> call, Throwable t) {
                Log.d("fsdfsdfs", String.valueOf(t));
                Toast.makeText(EditShipmetActivity.this,"Server error try after some time",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateShipment() {
       showLoading();
        Call<ShipmentCreateModel> call = new ApiManagerImp().updateShipment(mBasePreferenceManager.getUserToken(),id
                , mShipmentModel.getReceiverName(), mShipmentModel.getContactNo(),mShipmentModel.getProductType(),
                mShipmentModel.getProductWeight(), mShipmentModel.getNote(), mShipmentModel.getdThana(), mShipmentModel.getdDistrict(),
                mShipmentModel.getdDivision(), mShipmentModel.getdAddress(),
                mShipmentModel.getCod_amount(), mShipmentModel.getPickupDate());

        call.enqueue(new Callback<ShipmentCreateModel>() {
            @Override
            public void onResponse(Call<ShipmentCreateModel> call, Response<ShipmentCreateModel> response) {
                hideLoading();
                if (response.body() != null) {
                    showMessage(response.body().getMessage());
                    finish();
                }
                else{
                   // showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ShipmentCreateModel> call, Throwable t) {
                Log.d("cssssa", String.valueOf(t));
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });

    }

}