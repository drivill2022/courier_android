package com.drivill.courier.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.R;
import com.drivill.courier.activity.StatementActivity;
import com.drivill.courier.databinding.FragmentMonthlyStatementBinding;
import com.drivill.courier.fragment.fragmentViewModel.RidingStatementViewModel;
import com.drivill.courier.merchantModul.activity.MerchantStatementActivity;
import com.drivill.courier.merchantModul.model.BreakDownModel;
import com.drivill.courier.model.model.StatementModel;
import com.drivill.courier.utils.PrefsManager;

public class MonthlyStatementFragment extends Fragment {
    RidingStatementViewModel mViewModel;
    PrefsManager manager;
    FragmentMonthlyStatementBinding mBinding;

    void initUI() {
        mViewModel = new ViewModelProvider(requireActivity()).get(RidingStatementViewModel.class);


        if (manager.getIsRider()) {
            mViewModel.mMonthlyData.observe(requireActivity(), success_observer);

        } else {
            mViewModel.mMonthlyDataMerchant.observe(requireActivity(), success_observerMerchant);
            mBinding.riderScroll.setVisibility(View.GONE);
            mBinding.merchantScollView.setVisibility(View.VISIBLE);
        }
    }

    public MonthlyStatementFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //    mParam1 = getArguments().getString(ARG_PARAM1);
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
        manager = new PrefsManager(requireContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel != null) {
            if (manager.getIsRider())
                mViewModel.getRiderStatementApi(manager, "monthly",
                        "", "");
            else mViewModel.getMerchantBreakdown(manager, "monthly",
                    "", "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_statement, container, false);
        mBinding = FragmentMonthlyStatementBinding.bind(view);
        initUI();
        return view;
    }


    private Observer<StatementModel> success_observer = new Observer<StatementModel>() {
        @Override
        public void onChanged(StatementModel statementModel) {
            Log.d("res", String.valueOf(statementModel));
            mBinding.pickUpTxt.setText(String.valueOf(statementModel.getPickedUp()));
            mBinding.cancelTxt.setText(String.valueOf(statementModel.getCancelled()));
            mBinding.deliveryTxt.setText(String.valueOf(statementModel.getDelivered()));
            mBinding.pendingTxt.setText(String.valueOf(statementModel.getPending()));
            mBinding.totalCodTxt.setText(String.valueOf(statementModel.getTotalCodEarned()));

            StatementActivity.setCollectedCOD(String.valueOf(statementModel.getTotalCodCollected()));

        }
    };


    private Observer<BreakDownModel> success_observerMerchant = new Observer<BreakDownModel>() {
        @Override
        public void onChanged(BreakDownModel statementModel) {
            Log.d("res", String.valueOf(statementModel));
            mBinding.packagesTxt.setText(String.valueOf(statementModel.getShipped()));
            mBinding.packagedeliveryTxt.setText(String.valueOf(statementModel.getDelivered()));
            mBinding.packagesReturnTxt.setText(String.valueOf(statementModel.getCancelled()));
            mBinding.totalPendingTxt.setText(String.valueOf(statementModel.getPending()));

            mBinding.totalCashTxt.setText(String.valueOf(statementModel.getCashCollected()));
            mBinding.totalDrivillReturnTxt.setText(String.valueOf(statementModel.getRefundFromDrivill()));
            mBinding.totalDrivillChargeTxt.setText(String.valueOf(statementModel.getDrivillServiceCharge()));
            mBinding.totalAvailPayTxt.setText(String.valueOf(statementModel.getTotalAvailableForPayout()));

            MerchantStatementActivity.setAvailPay(statementModel.getAvailablePayout().toString());
            MerchantStatementActivity.setCommission("Tk. " + statementModel.getDrivillsCommission());

        }
    };


}