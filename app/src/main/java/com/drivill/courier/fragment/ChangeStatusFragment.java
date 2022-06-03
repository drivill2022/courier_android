package com.drivill.courier.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.activity.OTPVerificationActivity;
import com.drivill.courier.bottomsheetFragment.BottomSheet;
import com.drivill.courier.databinding.FragmentChangeStatusBinding;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeStatusFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "data";
    //private static final String ARG_PARAM2 = "data";

    private ShipmentModel mData;
    private String mParam2;
    FragmentChangeStatusBinding mbinding;

    void initUI() {
        mbinding.pickupTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         /* Intent intent = new Intent(getActivity(), OTPVerificationActivity.class);
            intent.putExtra("from", 3);
            requireActivity().startActivity(intent);*/
                //senOtp(String.valueOf(mData.getId()), Constant.MERCHANT, Constant.PICKUP);
                if (mData.getStatus() == 2)
                    showAlertForPickup();
                else
                    Toast.makeText(requireContext(), "Already Picked-Up", Toast.LENGTH_SHORT).show();

            }
        });
        mbinding.inTransitTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         /* Intent intent = new Intent(getActivity(), OTPVerificationActivity.class);
                intent.putExtra("from", 3);
                requireActivity().startActivity(intent);*/
                //senOtp(String.valueOf(mData.getId()), Constant.MERCHANT, Constant.PICKUP);
                if (mData.getStatus() == 3)
                    showAlertForTransit();
                else
                    Toast.makeText(requireContext(), "First Pickup Product", Toast.LENGTH_SHORT).show();

            }
        });
        mbinding.orderCancelTxtPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mData);
                Navigation.findNavController(view).navigate(R.id.action_changeStatusFragment_to_cancellationFragment2, bundle);
            }
        });
        mbinding.orderCancelTxtDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", mData);
                Navigation.findNavController(view).navigate(R.id.action_changeStatusFragment_to_cancellationFragment2, bundle);
            }
        });
        mbinding.deliverTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         /*       Intent intent = new Intent(getActivity(), OTPVerificationActivity.class);
                intent.putExtra("from", 3);
                requireActivity().startActivity(intent);*/
                senOtp(String.valueOf(mData.getId()), Constant.RECEIVER, Constant.DELIVER);
            }
        });

        mbinding.transferredPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BottomSheet(requireContext(), 1, mData).show();
            }
        });
        mbinding.transferredDeliverTxtBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BottomSheet(requireContext(), 1, mData).show();
            }
        });
        mbinding.merchantNotTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BottomSheet(requireContext(), 2, mData).show();
            }
        });
        mbinding.customerNotTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BottomSheet(requireContext(), 3, mData).show();
            }
        });

        mbinding.shipmentIdTxt.setText(mData.getShipmentNo());
    }

    public ChangeStatusFragment() {
        // Required empty public constructor
    }


    public static ChangeStatusFragment newInstance(boolean param1, String param2) {
        ChangeStatusFragment fragment = new ChangeStatusFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mData = (ShipmentModel) getArguments().getSerializable(ARG_PARAM1);
            //  mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_change_status, container, false);
        mbinding = DataBindingUtil.bind(view);
        initUI();
        if (mData.getStatus() == Integer.parseInt(Constant.ASSIGN) ||
                mData.getStatus() == Integer.parseInt(Constant.PICKUP)) {
            mbinding.pickLL.setVisibility(View.VISIBLE);
            mbinding.deliLL.setVisibility(View.GONE);

        } else /*if (mData.getStatus() == Integer.parseInt(Constant.PICKUP) ||
                mData.getStatus() == Integer.parseInt(Constant.TRANSIT)) */ {
            mbinding.pickLL.setVisibility(View.GONE);
            mbinding.deliLL.setVisibility(View.VISIBLE);

            if (mData.getStatus() == Integer.parseInt(Constant.RETURN)) {
                mbinding.inTransitDeliveryTxtBtn.setVisibility(View.VISIBLE);
                mbinding.deliverTxtBtn.setEnabled(false);
                mbinding.deliverTxtBtn.setAlpha((float) .5);
                mbinding.orderCancelTxtDeliver.setEnabled(false);
                mbinding.orderCancelTxtDeliver.setAlpha((float) .5);
            /*    mbinding.transferredDeliverTxtBt.setEnabled(false);
                mbinding.transferredDeliverTxtBt.setAlpha((float) .5);
                mbinding.customerNotTxtBtn.setEnabled(false);
                mbinding.customerNotTxtBtn.setAlpha((float) .5);*/

                mbinding.inTransitDeliveryTxtBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showAlertForTransit();
                    }
                });

            } else {
                mbinding.inTransitDeliveryTxtBtn.setVisibility(View.INVISIBLE);
            }
        }
        return view;
    }

    void senOtp(String shipID, String sendTo, String status) {
        ((BaseActivity) requireActivity()).showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShipmentSendOTP(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken()
                , shipID, sendTo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        ((BaseActivity) requireActivity()).mBasePreferenceManager.setOneTimeOtp(object.getString("otp"));
                        Log.d("res", String.valueOf(object));
                        Intent intent = new Intent(getActivity(), OTPVerificationActivity.class);
                        intent.putExtra("from", Constant.FOR_CHANGE_STATUS);
                        intent.putExtra("data", mData);
                        intent.putExtra("msg", object.getString("message"));
                        intent.putExtra("status", status);
                        intent.putExtra("send", sendTo);
                        requireActivity().startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        ((BaseActivity) requireActivity()).onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
                ((BaseActivity) requireActivity()).onError(getString(R.string.try_again));

            }
        });
    }


    void riderUpdateStatus(String shipID, String status) {
        ((BaseActivity) requireActivity()).showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShipmentUpdate(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(),
                shipID, status, ((BaseActivity) requireActivity()).mBasePreferenceManager.getOneTimeOtp());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Log.d("res", String.valueOf(object));
                        Toast.makeText(requireContext(), object.getString("message"), Toast.LENGTH_SHORT).show();

                        Navigation.findNavController(requireActivity(), R.id.inTransitTxtBtn).navigate(R.id.action_changeStatusFragment_to_homeFragment);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        ((BaseActivity) requireActivity()).onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
                ((BaseActivity) requireActivity()).onError(getString(R.string.try_again));
            }
        });
    }


    public void showAlertForPickup() {
     /*   final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_congrets_layout);*/

        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView pickBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView tckParcel = (TextView) dialog.findViewById(R.id.shipIdTxt);
        TextView headingTitleTxt = (TextView) dialog.findViewById(R.id.headingTitleTxt);

        pickBtn.setText(R.string.picked_up);
        headingTitleTxt.setText("Congratulations!");
        tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());


        pickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riderUpdateStatus(String.valueOf(mData.getId()), Constant.PICKUP);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);


    }

    public void showAlertForTransit() {
        final Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.bottom_sheet_dialog_layout);


        TextView okBtn = (TextView) dialog.findViewById(R.id.submitBtn);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelBtn);
        TextView tckParcel = (TextView) dialog.findViewById(R.id.titleTxt);

     /*   if (mSendTo.equals(Constant.MERCHANT)) {
          //  pickBtn.setText(R.string.picked_up);
            tckParcel.setText("Take the parcel from ".concat(mData.getMerchant().getName()) + " and\nsend to " + mData.getReceiverName());
        } else {
           // pickBtn.setText(getString(R.string.delivered));
            tckParcel.setText("Take COD Payments Tk. ".concat(mData.getShipmentCost() + " \nand Handover the parcel to " + mData.getReceiverName()));
        }*/

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riderUpdateStatus(String.valueOf(mData.getId()), Constant.TRANSIT);
                dialog.dismiss();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

}