package com.drivill.courier.merchantModul.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.adapter.PayDetails_popupAdapter;
import com.drivill.courier.merchantModul.adapter.PaymentHistoryAdapter;
import com.drivill.courier.merchantModul.model.PaymentDetailslist;
import com.drivill.courier.merchantModul.model.ShipmentDetailModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentDetails extends DialogFragment {

    private String TxtId="";
    private PrefsManager pf ;
    private TextView tv_nodata , Tv_totalAvlblnc;
    private RecyclerView recyclerView ;
    private Dialog dialogbox;

    public PaymentDetails() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.activity_payment_details, container, false);

        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(false);
        pf =  new PrefsManager(requireActivity());

        // bundle get from PaymentHistoryAdapter class --
        if (getArguments() != null) {
            TxtId = getArguments().getString("txtid");
            Log.i("arp","txtid= "+TxtId);
        }

       rootView.findViewById(R.id.Close_Popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        tv_nodata = rootView.findViewById(R.id.tv_nodata);
        Tv_totalAvlblnc = rootView.findViewById(R.id.Tv_totalAvlblnc);

        recyclerView = rootView.findViewById(R.id.Rv_PayDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        CallPaymentList();

        return rootView;
    }

    private void CallPaymentList() {
       // Log.i("arp","token= " + pf.getUserToken() +"\n"+"TxtId= "+ TxtId);

        showLoading();
      //  Call<PaymentDetailslist> call = new ApiManagerImp().PaymentDetails(pf.getUserToken(), TxtId);
        Call<PaymentDetailslist> call = new ApiManagerImp().PaymentDetails_viaID(pf.getUserToken(), TxtId);
        call.enqueue(new Callback<PaymentDetailslist>() {
            @Override
            public void onResponse(@NotNull Call<PaymentDetailslist> call, @NotNull Response<PaymentDetailslist> response) {
                hideLoading();
                if (response.body() != null) {
                    PaymentDetailslist model = response.body();
                 //   Log.i("arp", "data= "+ new Gson().toJson(response.body()));

                    PayDetails_popupAdapter adapter = new PayDetails_popupAdapter(requireActivity(),model);
                    recyclerView.setAdapter(adapter);

                    SetTotal(model);

                }else {
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PaymentDetailslist> call, @NotNull Throwable t) {
                Log.i("arp", String.valueOf(t));
                hideLoading();
              //  onError(getString(R.string.try_again));
                Toast.makeText(requireActivity(), getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void SetTotal(PaymentDetailslist model) {

        try {

            List<Integer> callist = new ArrayList<>();
            int sum = 0;

            for(int i = 0; i < model.getData().size(); i++){
                int cal = model.getData().get(i).getCodAmount() - model.getData().get(i).getShipmentCost();
                callist.add(cal);
            }

            for (int i = 0; i < callist.size(); i++){
                sum += callist.get(i);
            }

            Tv_totalAvlblnc.setText("Total : "+ sum);

        }catch (Exception e){
            Log.i("tag","sum= "+e.toString());
        }

    }

    public void showLoading() {
       // Log.e("arp","=========showLoading============");
        dialogbox = new Dialog(requireActivity());
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.setCancelable(false);
        dialogbox.setContentView(R.layout.dialog_progress_layout);
        dialogbox.show();
    }

    public void hideLoading() {
        if (dialogbox != null && dialogbox.isShowing()) {
            dialogbox.dismiss();
          //   Log.e("arp","=======hideLoading-true========");
        } else {
            Log.e("arp","=======hideLoading-false=======");
        }
    }

    public int getTheme(){
        return R.style.FullScreenDialog;
    }

}