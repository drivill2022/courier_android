package com.drivill.courier.merchantModul.activity;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityPaymentHistoryBinding;
import com.drivill.courier.merchantModul.adapter.PaymentHistoryAdapter;
import com.drivill.courier.merchantModul.model.PaymentModel;
import com.drivill.courier.model.activityModel.PaymentViewModel;

import java.util.ArrayList;

public class PaymentHistoryActivity extends BaseActivity {
    ActivityPaymentHistoryBinding mBinding;
    ArrayList<PaymentModel.History> mHistoryList = new ArrayList<>();
    PaymentHistoryAdapter mAdapter;

    PaymentViewModel mViewModel;



    void initUI() {
        mViewModel = new PaymentViewModel();
        mViewModel.paymentHistory.observe(this, successListener);
        mAdapter = new PaymentHistoryAdapter(this);
        mBinding.recyclerPayment.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerPayment.setAdapter(mAdapter);

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        mBinding.recyclerPayment.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_payment_history);
        initUI();
        if (mBasePreferenceManager.getIsRider()) {

            mViewModel.getRiderPayHistory(mBasePreferenceManager);
        } else {
            mViewModel.getMerchantPayHistory(mBasePreferenceManager, "1");
        }
    }


    private Observer<PaymentModel> successListener = new Observer<PaymentModel>() {
        @Override
        public void onChanged(PaymentModel paymentModels) {
            mHistoryList = (ArrayList<PaymentModel.History>) paymentModels.getHistory();
            mAdapter.setData(mHistoryList);
        }
    };



}