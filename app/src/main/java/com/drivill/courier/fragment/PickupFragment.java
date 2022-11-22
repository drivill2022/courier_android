package com.drivill.courier.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.adapter.PickupAdapter;
import com.drivill.courier.databinding.FragmentPickupBinding;
import com.drivill.courier.model.model.RiderPickupListModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.DataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PickupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PickupFragment extends Fragment {
    PickupAdapter mAdapter;
    FragmentPickupBinding mBinding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //RiderDashboardViewModel viewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    void initUI(View view) {
        DataManager.getINSTANCE().getPickupResponse().observe(getViewLifecycleOwner(), success_observer);
        mAdapter = new PickupAdapter(this.getContext(), (PickupAdapter.pickupCallback) this.getContext());
        mBinding.pickupRY.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //mAdapter.setData(viewModel.getRiderPickUpList());
        mBinding.pickupRY.setAdapter(mAdapter);
    }

    public PickupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PickupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PickupFragment newInstance(String param1, String param2) {
        PickupFragment fragment = new PickupFragment();
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
        View view = inflater.inflate(R.layout.fragment_pickup, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI(view);
        return view;
    }

    void getList(String lat, String lon, String status) {
        mBinding.progressBar.setVisibility(View.VISIBLE);
        Call<RiderPickupListModel> call = new ApiManagerImp().riderShipmentsList(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), lat, status, lon);
        call.enqueue(new Callback<RiderPickupListModel>() {
            @Override
            public void onResponse(Call<RiderPickupListModel> call, Response<RiderPickupListModel> response) {
                mBinding.progressBar.setVisibility(View.GONE);
                Log.d("", "");
                if (response.body() != null) {
                    RiderPickupListModel model = response.body();

                    mAdapter.setData(model);
                    mBinding.pickupRY.setAdapter(mAdapter);
                } else {

                    try {
                        JSONObject errObj = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RiderPickupListModel> call, Throwable t) {
                Log.d("", "");
                ((BaseActivity) requireActivity()).onError(getString(R.string.try_again));
            }
        });
    }


   /* public void showData(RiderPickupListModel model) {
        if (mAdapter != null)
            mAdapter.setData(model);
    }

    @Override
    public void onResume() {
        super.onResume();

    }*/

    private final Observer<RiderPickupListModel> success_observer = new Observer<RiderPickupListModel>() {
        @Override
        public void onChanged(RiderPickupListModel riderPickupListModel) {
            if (mAdapter != null){
                mAdapter.setData(DataManager.getINSTANCE().getPickupResponse().getValue());
                if(Objects.requireNonNull(DataManager.getINSTANCE().getPickupResponse().getValue()).getShipments().size()==0){
                    mBinding.noDataTxt.setVisibility(View.VISIBLE);
                }else {
                    mBinding.noDataTxt.setVisibility(View.GONE);
                }
            }
        }

    };

}