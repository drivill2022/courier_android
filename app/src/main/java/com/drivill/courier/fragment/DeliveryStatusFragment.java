package com.drivill.courier.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.R;
import com.drivill.courier.adapter.DeliveryStatusAdapter;
import com.drivill.courier.databinding.FragmentDeliveryStatusBinding;
import com.drivill.courier.merchantModul.activity.PackagingActivity;
import com.drivill.courier.utils.DataManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeliveryStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeliveryStatusFragment extends Fragment {
    FragmentDeliveryStatusBinding mBinding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DeliveryStatusAdapter mAdapter;

    void initUI() {
        //DataManager.getINSTANCE().getDeliveryResponse().observe(getViewLifecycleOwner(), success_observer);
      //  mAdapter = new DeliveryStatusAdapter(this.getContext());
        mBinding.rvStatus.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.rvStatus.setAdapter(mAdapter);

        mBinding.btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.getINSTANCE().setCurrentItemListSize(0);
                Intent intent = new Intent(requireContext(), PackagingActivity.class);
                startActivity(intent);
            }
        });

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeliveryStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeliveryStatusFragment newInstance(String param1, String param2) {
        DeliveryStatusFragment fragment = new DeliveryStatusFragment();
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
        View view = inflater.inflate(R.layout.fragment_delivery_status, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        return view;
    }

}