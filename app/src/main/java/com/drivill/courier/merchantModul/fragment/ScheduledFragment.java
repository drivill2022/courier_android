package com.drivill.courier.merchantModul.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.R;
import com.drivill.courier.adapter.DeliveryStatusAdapter;
import com.drivill.courier.databinding.FragmentScheduledBinding;
import com.drivill.courier.merchantModul.activity.PackagingActivity;
import com.drivill.courier.merchantModul.interfases.FragmentListener;
import com.drivill.courier.merchantModul.adapter.RecentDeliveryAdapter;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.PrefsManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScheduledFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduledFragment extends Fragment {
    FragmentListener mListener;
    FragmentScheduledBinding mBinding;
    PrefsManager manager;
    RecentDeliveryAdapter mAdapter;
    DeliveryStatusAdapter adapter;
    ArrayList<ShipmentModel> dataArr;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "data";
    private static final String ARG_PARAM2 = "status";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    void initUI() {
        mListener = (FragmentListener) requireActivity();
        String value = getArguments().getString("status");
        getAllShipItem(value);
    }

    public ScheduledFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduledFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduledFragment newInstance(String param1, String param2) {
        ScheduledFragment fragment = new ScheduledFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new PrefsManager(requireContext());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_scheduled, container, false);
        mBinding = FragmentScheduledBinding.bind(view);
        initUI();
       // mListener.changeToolTitle(mParam1);
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        mBinding.btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), PackagingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


    void getAllShipItem(String type) {
        DataManager.getINSTANCE().showProgressBar(requireActivity());
        Call<ArrayList<ShipmentModel>> call = new ApiManagerImp().getShipmentMerchant(manager.getUserToken(), type);
        call.enqueue(new Callback<ArrayList<ShipmentModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ShipmentModel>> call, Response<ArrayList<ShipmentModel>> response) {
                DataManager.getINSTANCE().hideProgressBar();
                if (response.body() != null) {
                    Log.d("responce", String.valueOf(response.body()));
                    dataArr = response.body();
                   // Log.e("dsadasgdjgasjd","djagsdhgashdgdsa"+dataArr.get(0).getContactNo());
                    settingAdapter();
                    if(response.body().size()==0){
                        mBinding.noDataTxt.setVisibility(View.VISIBLE);
                    }else {
                        mBinding.noDataTxt.setVisibility(View.GONE);
                    }
                } else {

                    mBinding.recyclerView.setVisibility(View.GONE);
                    mBinding.noDataTxt.setVisibility(View.VISIBLE);
                    try {

                        JSONObject object = new JSONObject(response.errorBody().string());
                        Log.d("er", String.valueOf(object));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ShipmentModel>> call, Throwable t) {
                DataManager.getINSTANCE().hideProgressBar();
            }
        });
    }

    void settingAdapter() {
        adapter=new DeliveryStatusAdapter(requireContext(),dataArr);
        Log.e("dshakjdhgjasgdhjgsd","dsdasfasfd"+dataArr);
        mAdapter = new RecentDeliveryAdapter(requireContext(), "home", (RecentDeliveryAdapter.RecentAdapterClick) requireActivity());
        mAdapter.setData(dataArr);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mBinding.recyclerView.setAdapter(adapter);
    }

}