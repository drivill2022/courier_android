package com.drivill.courier.merchantModul.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.adapter.SliderAdapter;
import com.drivill.courier.databinding.FragmentMechantHomeBinding;
import com.drivill.courier.databinding.LayoutDashboardBinding;
import com.drivill.courier.merchantModul.activity.DashboardActivityMerchant;
import com.drivill.courier.merchantModul.activity.PackagingActivity;
import com.drivill.courier.merchantModul.activity.SeeAllItemActivity;
import com.drivill.courier.merchantModul.activity.TrackingActivity;
import com.drivill.courier.merchantModul.adapter.AdvertisedAdapter;
import com.drivill.courier.merchantModul.adapter.RecentDeliveryAdapter;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.merchantModul.model.TrackingModel;
import com.drivill.courier.model.model.BannerResponseModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.Gson;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MerchantHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MerchantHomeFragment extends Fragment implements View.OnClickListener {
    RecentDeliveryAdapter mAdapter;
    LayoutDashboardBinding binding;
    AdvertisedAdapter mAdvertiseAdapter;
    FragmentMechantHomeBinding mBinding;
    public PrefsManager mBasePreferenceManager;

    PrefsManager manager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    void initUI() {
        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            binding.tvWish.setText("Good Morning");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            binding.tvWish.setText("Good Afternoon");

        }else if(timeOfDay >= 16 && timeOfDay < 21){
            binding.tvWish.setText("Good Evening");

        }else if(timeOfDay >= 21 && timeOfDay < 24){
            binding.tvWish.setText("Good Night");
        }
        SliderAdapter adapter = new SliderAdapter();

        binding.tvName.setText("Hi, "+mBasePreferenceManager.get_businessName());
        binding.tvCity.setText(mBasePreferenceManager.getAddress());


        binding.ivDash.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        binding.ivDash.setSliderAdapter(adapter);
        binding.ivDash.setScrollTimeInSec(3);
        binding.ivDash.setAutoCycle(true);
        mBinding.seeMoreTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SeeAllItemActivity.class);
                DataManager.from = "";
                startActivity(intent);
            }
        });
        mBinding.totalList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SeeAllItemActivity.class);
                DataManager.from = "All";
                startActivity(intent);
            }
        });
        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.etTrck.getText().toString().isEmpty()) {
                    fetchTruckLocation(binding.etTrck.getText().toString());
                } else {
                    ((BaseActivity) requireActivity()).showMessage("Enter Tracking ID");
                }
            }
        });

        binding.btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), PackagingActivity.class);
                startActivity(intent);
            }
        });
        mBinding.currentTxtBtn.setOnClickListener(this);
        mBinding.shippingTxtBtn.setOnClickListener(this);
     /* mBinding.scheduleTxtBtn.setOnClickListener(this);*/
        mBinding.deliverTxtBtn.setOnClickListener(this);
        mBinding.cancelTxtBtn.setOnClickListener(this);
    }

    public MerchantHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MechantHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MerchantHomeFragment newInstance(String param1, String param2) {
        MerchantHomeFragment fragment = new MerchantHomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_mechant_home, container, false);
        mBinding = FragmentMechantHomeBinding.bind(view);

        View view1 = inflater.inflate(R.layout.layout_dashboard, container, false);
        binding = LayoutDashboardBinding.bind(view1);
        mBasePreferenceManager = new PrefsManager(requireContext());
        Log.e("dscds",mBasePreferenceManager.get_businessName());


        initUI();
        new getBagCount().execute();
        return view1;
    }

    void settingAdapter(ArrayList<ShipmentModel> arrayList) {
        mAdapter = new RecentDeliveryAdapter(getContext(), "home", (RecentDeliveryAdapter.RecentAdapterClick) requireContext());
        mBinding.recentRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setData(arrayList);
        mBinding.recentRV.setAdapter(mAdapter);
    }

    void settingAddAdapter() {
        SnapHelper snapHelper = new PagerSnapHelper();
        mAdvertiseAdapter = new AdvertisedAdapter(getContext());
        mBinding.recyclerAdvtizement.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(mBinding.recyclerAdvtizement);
        mBinding.recyclerAdvtizement.setAdapter(mAdvertiseAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.rl_track:
                requireContext().startActivity(new Intent(requireActivity(), TrackingActivity.class));
                break;

            case R.id.cancelTxtBtn:
                bundle.putString("data", view.getTag().toString());
                bundle.putString("status", Constant.CANCELLED);
                Navigation.findNavController(view).navigate(R.id.action_mechantHomeFragment_to_scheduledFragment, bundle);
                break;
            case R.id.deliverTxtBtn:
                bundle.putString("data", view.getTag().toString());
                bundle.putString("status", Constant.DELIVERED);
                Navigation.findNavController(view).navigate(R.id.action_mechantHomeFragment_to_scheduledFragment, bundle);
                break;
            case R.id.shippingTxtBtn:
                bundle.putString("data", view.getTag().toString());
                bundle.putString("status", Constant.SHIPPED);
                Navigation.findNavController(view).navigate(R.id.action_mechantHomeFragment_to_scheduledFragment, bundle);
                break;
          /*  case R.id.scheduleTxtBtn:  // line no- 75 Click Event is Commented ; Not used as of now --
                bundle.putString("data", view.getTag().toString());
                bundle.putString("status", Constant.SCHEDULED);
                Navigation.findNavController(view).navigate(R.id.action_mechantHomeFragment_to_scheduledFragment, bundle);
                break;*/
            case R.id.currentTxtBtn:
                bundle.putString("data", view.getTag().toString());
                bundle.putString("status", Constant.CURRENT);
                Navigation.findNavController(view).navigate(R.id.action_mechantHomeFragment_to_scheduledFragment, bundle);
                break;
        }
    }

    private final class getBagCount extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            Call<ArrayList<ShipmentModel>> call = new ApiManagerImp().getShipmentMerchant(manager.getUserToken(), Constant.CURRENT);
            call.enqueue(new Callback<ArrayList<ShipmentModel>>() {
                @Override
                public void onResponse(Call<ArrayList<ShipmentModel>> call, Response<ArrayList<ShipmentModel>> response) {
                    if (response.body() != null) {
                        if (response.body().size() == 0) {
                            mBinding.bagCount.setVisibility(View.GONE);
                        } else {
                            mBinding.bagCount.setVisibility(View.VISIBLE);
                            mBinding.bagCount.setText(String.valueOf(response.body().size()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ShipmentModel>> call, Throwable t) {

                }
            });


            Call<ArrayList<ShipmentModel>> callDelivered = new ApiManagerImp().getShipmentMerchant(manager.getUserToken(), Constant.DELIVERED);
            callDelivered.enqueue(new Callback<ArrayList<ShipmentModel>>() {
                @Override
                public void onResponse(Call<ArrayList<ShipmentModel>> call, Response<ArrayList<ShipmentModel>> response) {
                    if (response.body() != null && response.body().size() != 0) {
                        mBinding.noDataTxt.setVisibility(View.GONE);
                        settingAdapter(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<ShipmentModel>> call, Throwable t) {

                }
            });


            Call<ArrayList<BannerResponseModel>> call1 = new ApiManagerImp().getMerchantBanner(manager.getUserToken());
            call1.enqueue(new Callback<ArrayList<BannerResponseModel>>() {
                @Override
                public void onResponse(Call<ArrayList<BannerResponseModel>> call, Response<ArrayList<BannerResponseModel>> response) {
                    if (response.body() != null) {
                        //settingAdapter(response.body());
                        settingAddAdapter();
                        mAdvertiseAdapter.setData(response.body());
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<BannerResponseModel>> call, Throwable t) {

                }
            });
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {

            // You might want to change "executed" for the returned string
            // passed into onPostExecute(), but that is up to you
        }
    }

    private void fetchTruckLocation(String num) {
        if(!num.startsWith("#")){
            num =  "#"+num;
        }
        Log.i("arp","TrackingID= "+num);
        ((BaseActivity) requireActivity()).showLoading();
        Call<TrackingModel> call = new ApiManagerImp().getShipmentTracking(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), num);
        call.enqueue(new Callback<TrackingModel>() {
            @Override
            public void onResponse(Call<TrackingModel> call, Response<TrackingModel> response) {
                ((BaseActivity) requireActivity()).hideLoading();
                TrackingModel res =  response.body();
                if (res != null && res.getData().getStatusLogs()!=null) {
                    Log.i("res=", new Gson().toJson(res));
                    TrackingModel model = response.body();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", (Serializable) model);
                    TrackingFragment fragment = new TrackingFragment();
                    fragment.setArguments(bundle);
                    switchFragment(fragment);

                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        ((BaseActivity) requireActivity()).onError(object.getString("error"));
                        Log.i("res=", "error=>  "+object.toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrackingModel> call, Throwable t) {
                Log.i("res", String.valueOf(t));
                ((BaseActivity) requireActivity()).hideLoading();
                ((BaseActivity) requireActivity()).onError(getString(R.string.try_again));
            }
        });

    }

    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragmentMechant, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }



}