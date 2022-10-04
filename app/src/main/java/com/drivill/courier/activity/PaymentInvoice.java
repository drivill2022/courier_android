package com.drivill.courier.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.drivill.courier.R;
import com.drivill.courier.adapter.PaymentInvoiceAdapter;
import com.drivill.courier.merchantModul.adapter.PayDetails_popupAdapter;
import com.drivill.courier.merchantModul.model.PaymentDetailslist;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.PrefsManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentInvoice extends AppCompatActivity {
private RecyclerView recyclerView;
    private Dialog dialogbox;
    public PrefsManager mBasePreferenceManager;

    private String TxtId="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_invoice);
        mBasePreferenceManager = new PrefsManager(this);
        TxtId=getIntent().getStringExtra("txtid");
        recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);

        CallPaymentList();

    }

    private void CallPaymentList() {
        // Log.i("arp","token= " + pf.getUserToken() +"\n"+"TxtId= "+ TxtId);

        showLoading();
        //  Call<PaymentDetailslist> call = new ApiManagerImp().PaymentDetails(pf.getUserToken(), TxtId);
        Call<PaymentDetailslist> call = new ApiManagerImp().PaymentDetails_viaID(mBasePreferenceManager.getUserToken(), TxtId);
        call.enqueue(new Callback<PaymentDetailslist>() {
            @Override
            public void onResponse(@NotNull Call<PaymentDetailslist> call, @NotNull Response<PaymentDetailslist> response) {
                hideLoading();
                if (response.body() != null) {
                    PaymentDetailslist model = response.body();
                    //   Log.i("arp", "data= "+ new Gson().toJson(response.body()));

                    PayDetails_popupAdapter adapter = new PayDetails_popupAdapter(PaymentInvoice.this,model);
                    recyclerView.setAdapter(adapter);


                }else {
                   // tv_nodata.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PaymentDetailslist> call, @NotNull Throwable t) {
                Log.i("arp", String.valueOf(t));
                hideLoading();
                //  onError(getString(R.string.try_again));
                Toast.makeText(PaymentInvoice.this, getString(R.string.try_again), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void showLoading() {
        // Log.e("arp","=========showLoading============");
        dialogbox = new Dialog(this);
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
}