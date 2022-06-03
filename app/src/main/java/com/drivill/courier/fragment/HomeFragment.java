package com.drivill.courier.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.drivill.courier.R;
import com.drivill.courier.activity.DashboardActivity;
import com.drivill.courier.adapter.MyPagerAdapter;
import com.drivill.courier.databinding.FragmentHomeBinding;
import com.drivill.courier.interfaces.FragmentCommunicator;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.model.activityModel.RiderDashboardViewModel;
import com.drivill.courier.model.model.RiderPickupListModel;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.DataManager;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    FragmentHomeBinding mHomeBinding;
    MyPagerAdapter myPagerAdapter;
    PrefsManager manager;
    TabLayout mTabLayout;
    ViewPager2 mPager;
    LinearLayoutManager mLayoutManager;
    public RiderDashboardViewModel viewModel;


    void initUI(View view) {
        manager = ((DashboardActivity) requireActivity()).mBasePreferenceManager;
        viewModel = new ViewModelProvider(this).get(RiderDashboardViewModel.class);
        viewModel.modelMutableLiveData.observe(getViewLifecycleOwner(), success_observer);
        viewModel.getList(requireContext(), "", "", "", mHomeBinding);

        mHomeBinding.userNameTxt.setText("Hi, ".concat(manager.getUserName()));
     /*   mHomeBinding.pickupCount.setText(viewModel.totalcmpPick.concat("/").concat(viewModel.totalShipmentSize));
        mHomeBinding.deliveryCount.setText(viewModel.totalcmpDelivery.concat("/").concat(viewModel.totalShipmentSize));
*/
        mTabLayout = view.findViewById(R.id.tabLayout);
        mPager = view.findViewById(R.id.viewpager2);

        // mLayoutManager = new LinearLayoutManager(getActivity());
        // mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // mTabLayout.addTab(mTabLayout.newTab().setText("Pickup"));
        //  mTabLayout.addTab(mTabLayout.newTab().setText("Delivery"));


        myPagerAdapter = new MyPagerAdapter(this, 2);
        mPager.setAdapter(myPagerAdapter);
        new TabLayoutMediator(mTabLayout, mPager, ((tab, position) -> {
            tab.setCustomView(R.layout.tab_item_layout);
            TextView textView = tab.getCustomView().findViewById(R.id.textTab);

            if (position == 0) {
                textView.setText(getString(R.string.pickup));
                textView.setTextColor(getResources().getColor(R.color.theme_color));
            } else {
                textView.setText(getString(R.string.delivery));
            }
            tab.setText(textView.getText());

        })).attach();


        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    TextView selectedText = view.findViewById(R.id.textTab);
                    selectedText.setTextColor(getResources().getColor(R.color.theme_color));
                /*    //ImageView selectedImage = view.findViewById(R.id.tab_icon);
                    if (id == 3) {
                        selectedImage.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.live_s1));
                    } else {
                        selectedImage.setColorFilter(null);
                    }
*/
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                if (view != null) {
                    TextView selectedText = view.findViewById(R.id.textTab);
                    selectedText.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        mHomeBinding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getList(requireContext(), "", "", "", mHomeBinding);
            }
        });

    }


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        //     args.putString(ARG_PARAM1, param1);
        //   args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //   mParam1 = getArguments().getString(ARG_PARAM1);
            //   mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mHomeBinding = DataBindingUtil.bind(view);
        initUI(view);
        ((DashboardActivity) requireActivity()).passVal(new FragmentCommunicator() {
            @Override
            public void passData(String name, int type) {
                viewModel.getList(requireContext(), "", "", "", mHomeBinding);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private final Observer<RiderPickupListModel> success_observer = new Observer<RiderPickupListModel>() {
        @Override
        public void onChanged(RiderPickupListModel riderPickupListModel) {
            if (riderPickupListModel != null) {
                Log.i("arp", "mlist= "+new Gson().toJson(riderPickupListModel.getShipments()));
                RiderPickupListModel pickData = new RiderPickupListModel();
                RiderPickupListModel deliverData = new RiderPickupListModel();
                RiderPickupListModel deliverCompleteData = new RiderPickupListModel();
                int deliver = 0, totalDelivery = 0, totalPickup = 0, pickup = 0;
                for (ShipmentModel status : riderPickupListModel.getShipments()) {
                    if (status.getStatus() == Integer.parseInt(Constant.PICKUP) || status.getStatus() == Integer.parseInt(Constant.ASSIGN)) {
                        //  pickData.setShipments(Collections.singletonList(status));
                        pickData.getShipments().add(status);
                        totalPickup = totalPickup + 1;
                        if (status.getStatus() == Integer.parseInt(Constant.PICKUP)) {
                            pickup = pickup + 1;
                        }
                    } else if (     status.getStatus() == Integer.parseInt(Constant.RETURN)
                               || status.getStatus() == Integer.parseInt(Constant.ON_GOING)
                               ||  status.getStatus() == Integer.parseInt(Constant.CANCEL)) {
                        // deliverData.setShipments(Collections.singletonList(status));
                        deliverData.getShipments().add(status);
                    } else if (status.getStatus() == Integer.parseInt(Constant.DELIVER) || status.getStatus() == Integer.parseInt(Constant.COMPLETE)) {
                        deliver = deliver + 1;
                        deliverCompleteData.getShipments().add(status);
                    }
                }
                // viewModel.totalcmpPick = String.valueOf(deliverData.getShipments().size() + deliver);
                viewModel.totalcmpPick = String.valueOf(pickup);
                viewModel.totalDelivery = String.valueOf(deliverData.getShipments().size() + deliver);
                viewModel.totalcmpDelivery = String.valueOf(deliver);
                DataManager.getINSTANCE().getPickupResponse().setValue(pickData);
                DataManager.getINSTANCE().getDeliveryResponse().setValue(deliverData);
                DataManager.getINSTANCE().getDeliverCompleteResponse().setValue(deliverCompleteData);
                mHomeBinding.pickupCount.setText(viewModel.totalcmpPick.concat("/").concat(String.valueOf(totalPickup)));
                mHomeBinding.deliveryCount.setText(viewModel.totalcmpDelivery.concat("/").concat(viewModel.totalDelivery));

            }
        }
    };

    @Override
    public void onResume() {

        // instant update the TV for Rider name ===
        if(manager!=null){
            mHomeBinding.userNameTxt.setText("Hi, ".concat(manager.getUserName()));
        }
        super.onResume();
    }

    ///////////////////ViewPagerProperty///////

    //mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    //mTabLayout.setupWithViewPager(mPager);
    //mTabLayout.setTabTextColors(R.color.black, R.color.blue);
}