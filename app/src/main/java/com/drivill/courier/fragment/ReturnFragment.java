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
import com.drivill.courier.adapter.ReturnAdapter;
import com.drivill.courier.databinding.FragmentReturnBinding;
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
 * Use the {@link ReturnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReturnFragment extends Fragment {

   FragmentReturnBinding mBinding;
   // TODO: Rename parameter arguments, choose names that match
   // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   private static final String ARG_PARAM1 = "param1";
   private static final String ARG_PARAM2 = "param2";

   // TODO: Rename and change types of parameters
   private String mParam1;
   private String mParam2;
   ReturnAdapter mAdapter;


   void initUI() {
      DataManager.getINSTANCE().getDeliveryResponse().observe(getViewLifecycleOwner(), success_observer);
      mAdapter = new ReturnAdapter(this.getContext(), (ReturnAdapter.deliveryCallback) this.getActivity());
      mBinding.deliveryRY.setLayoutManager(new LinearLayoutManager(this.getContext()));
      mBinding.deliveryRY.setAdapter(mAdapter);

   }

   public ReturnFragment() {
      // Required empty public constructor
   }


   public static ReturnFragment newInstance(String param1, String param2) {
      ReturnFragment fragment = new ReturnFragment();
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
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      // Inflate the layout for this fragment
      View view = inflater.inflate(R.layout.fragment_return, container, false);
      mBinding = DataBindingUtil.bind(view);
      initUI();
      //  getList("22.45546516416", "75.54154545545", "deliver");
      return view;

   }

   /* public void showData(RiderPickupListModel model) {
        if (mAdapter != null)
            mAdapter.setData(model);
    }

    @Override
    public void onResume() {
        super.onResume();
        // mAdapter.setData(DataManager.getINSTANCE().getDeliveryResponse().getValue());
    }*/

   void getList(String lat, String lon, String status) {
      mBinding.progrssBar.setVisibility(View.VISIBLE);
      Call<RiderPickupListModel> call = new ApiManagerImp().riderShipmentsList(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), lat, status, lon);
      call.enqueue(new Callback<RiderPickupListModel>() {
         @Override
         public void onResponse(Call<RiderPickupListModel> call, Response<RiderPickupListModel> response) {
            mBinding.progrssBar.setVisibility(View.GONE);
            Log.d("", "");
            if (response.body() != null) {
               RiderPickupListModel model = response.body();
               mAdapter.setData(model);
               mBinding.deliveryRY.setAdapter(mAdapter);
            } else {

               try {
                  JSONObject errObj = new JSONObject(response.errorBody().string());
                  Log.d("", "");
               } catch (JSONException e) {
                  e.printStackTrace();
               } catch (IOException e) {
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

   private final Observer<RiderPickupListModel> success_observer = new Observer<RiderPickupListModel>() {
      @Override
      public void onChanged(RiderPickupListModel riderPickupListModel) {
         if (mAdapter != null){
            mAdapter.setData(DataManager.getINSTANCE().getDeliveryResponse().getValue());
            if(Objects.requireNonNull(DataManager.getINSTANCE().getDeliveryResponse().getValue()).getShipments().size()==0){
               mBinding.noDataTxt.setVisibility(View.VISIBLE);
            }else {
               mBinding.noDataTxt.setVisibility(View.GONE);
            }
         }
      }
   };

}