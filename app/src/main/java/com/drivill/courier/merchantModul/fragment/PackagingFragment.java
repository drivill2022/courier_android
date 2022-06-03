package com.drivill.courier.merchantModul.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.activity.ItemDetailedView;
import com.drivill.courier.bottomsheetFragment.BottomSheet;
import com.drivill.courier.databinding.FragmentPackagingBinding;
import com.drivill.courier.merchantModul.activity.OneItemDetailActivity;
import com.drivill.courier.merchantModul.activity.PackagingActivity;
import com.drivill.courier.merchantModul.bottom_dialog.BottomSheetPakaging;
import com.drivill.courier.merchantModul.bottom_dialog.BottomSheet_Date;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.WeightAndProductTypeModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.MyUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drivill.courier.merchantModul.activity.PackagingActivity.mShipmentModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PackagingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PackagingFragment extends Fragment {
    FragmentPackagingBinding mBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<WeightAndProductTypeModel.ProductType> mProductTypeArrayList = new ArrayList<>();
    ArrayList<WeightAndProductTypeModel.WeightList> mWeightArrayList = new ArrayList<>();


    public PackagingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PackagingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PackagingFragment newInstance(String param1, String param2) {
        PackagingFragment fragment = new PackagingFragment();
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

        View view = inflater.inflate(R.layout.fragment_packaging, container, false);
        mBinding = FragmentPackagingBinding.bind(view);
        initUI();

        ((PackagingActivity) requireActivity()).UpdatedHeadeerText("Frag");

        MyUtil.ChangeStatusBarColor(getActivity(),R.color.theme_color);

        getWeightListData();

        return view;
    }

    void initUI() {
        WeightAndProductTypeModel.ProductType data = new WeightAndProductTypeModel.ProductType();
        WeightAndProductTypeModel.WeightList wet = new WeightAndProductTypeModel.WeightList();
        data.setName("Select Product");
        wet.setName("Select Weight");
        mProductTypeArrayList.add(data);
        mWeightArrayList.add(wet);

        mBinding.selectDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();

              // new BottomSheet_Date(requireActivity()).show();

            }
        });

        mBinding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new BottomSheetPakaging(requireActivity()).show();
                mShipmentModel.setPickupDate(mBinding.selectDateText.getText().toString());
                mShipmentModel.setReceiverName(mBinding.nameET.getText().toString());
                mShipmentModel.setContactNo(mBinding.receiverPhoneET.getText().toString());
               // mShipmentModel.setProductDetail(mBinding.productDetailET.getText().toString());
                mShipmentModel.setProductDetail("");
                mShipmentModel.setNote(mBinding.noteET.getText().toString());
                mShipmentModel.setCod_amount(mBinding.codAmountET.getText().toString());

              /*
                if(mBinding.noteET.getText().toString().length()==0){
                    mShipmentModel.setNote("");
                }else {
                    mShipmentModel.setNote(mBinding.noteET.getText().toString());
                }

                if(mBinding.codAmountET.getText().toString().length()==0){
                    mShipmentModel.setCod_amount("");
                }else {
                    mShipmentModel.setCod_amount(mBinding.codAmountET.getText().toString());
                }

*/
                if (mShipmentModel.isValidData2(requireContext())) {
                    creatShipment();
                }
            }
        });


  /*      ArrayAdapter<CharSequence> adapterP = ArrayAdapter.createFromResource(getContext(),
                R.array.SelectPType, R.layout.spinner_item_layout);
        adapterP.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectProductSpinner.setAdapter(adapterP);

        ArrayAdapter<CharSequence> adapterW = ArrayAdapter.createFromResource(getContext(),
                R.array.SelectWType, R.layout.spinner_item_layout);
        adapterW.setDropDownViewResource(R.layout.spinner_item_layout);
        mBinding.selectWeightSpinner.setAdapter(adapterW);*/

        ArrayAdapter<WeightAndProductTypeModel.ProductType> arrayAdapter = new ArrayAdapter<WeightAndProductTypeModel.ProductType>(
                requireContext(), R.layout.spinner_item_layout, mProductTypeArrayList
        );
        ArrayAdapter<WeightAndProductTypeModel.WeightList> arrayAdapterWet = new ArrayAdapter<WeightAndProductTypeModel.WeightList>(
                requireContext(), R.layout.spinner_item_layout, mWeightArrayList
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

    private void creatShipment() {
        ((PackagingActivity) requireActivity()).showLoading();
        //mShipmentModel.setProductType("1");
        mShipmentModel.setShipmentType("1");
        //   mShipmentModel.setProductWeight("1kg");
     /*   mShipmentModel.setProductType("1");
        mShipmentModel.setProductWeight("1kg");*/
        Call<ShipmentCreateModel> call = new ApiManagerImp().createShipment(((PackagingActivity) requireActivity()).mBasePreferenceManager.getUserToken()
                , mShipmentModel.getReceiverName(), mShipmentModel.getContactNo(), mShipmentModel.getProductDetail(),
                mShipmentModel.getProductType(), mShipmentModel.getProductWeight(), mShipmentModel.getNote(), mShipmentModel.getsThana(),
                mShipmentModel.getsDistrict(), mShipmentModel.getsDivision(), mShipmentModel.getdThana(), mShipmentModel.getdDistrict(),
                mShipmentModel.getdDivision(), mShipmentModel.getsAddress(), mShipmentModel.getdAddress(), mShipmentModel.getShipmentType(),
                mShipmentModel.getS_latitude(), mShipmentModel.getS_longitude(), mShipmentModel.getD_latitude(), mShipmentModel.getD_longitude(),
                mShipmentModel.getCod_amount(), mShipmentModel.getPickupDate());

        call.enqueue(new Callback<ShipmentCreateModel>() {
            @Override
            public void onResponse(Call<ShipmentCreateModel> call, Response<ShipmentCreateModel> response) {
                ((PackagingActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                  //  Log.i("res=>", "creatShipment=>"+new Gson().toJson(response.body()));
                    ShipmentCreateModel model = response.body();

                  //  ((PackagingActivity) requireActivity()).showMessage(model.getMessage());
                    /*DataManager.getINSTANCE().setCurrentItemListSize(
                            DataManager.getINSTANCE().getCurrentItemListSize() + 1);*/
                   // Intent intent = new Intent(requireActivity(), OneItemDetailActivity.class);

                    Intent intent = new Intent(requireActivity(), ItemDetailedView.class);
                    intent.putExtra("data", (Serializable) model);
                    intent.putExtra("from", Constant.CREATING_SHIP);
                    requireActivity().startActivity(intent);
                    requireActivity().finish();
                } else {
                    try {
                        JSONObject O = new JSONObject(response.errorBody().string());
                        ((PackagingActivity) requireActivity()).onError(O.get("error").toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShipmentCreateModel> call, Throwable t) {
                Log.d("", String.valueOf(t));
                ((PackagingActivity) requireActivity()).hideLoading();
                ((PackagingActivity) requireActivity()).onError(getString(R.string.try_again));
            }
        });

    }

    void getWeightListData() {
        ((BaseActivity) requireActivity()).showLoading();
        Call<WeightAndProductTypeModel> call = new ApiManagerImp().getWeightAndProductList(
                ((PackagingActivity) requireActivity()).mBasePreferenceManager.getUserToken()
        );
        call.enqueue(new Callback<WeightAndProductTypeModel>() {
            @Override
            public void onResponse(Call<WeightAndProductTypeModel> call, Response<WeightAndProductTypeModel> response) {
                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {

                    WeightAndProductTypeModel model = response.body();
                    mProductTypeArrayList.addAll(model.getData().getProductType());
                    if (model.getData().getWeightList() != null) {
                        mWeightArrayList.addAll(model.getData().getWeightList());
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        ((BaseActivity) requireActivity()).onError(object.getString("error"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<WeightAndProductTypeModel> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
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

        DatePickerDialog dpd = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

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
}