package com.drivill.courier.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.drivill.courier.R;
import com.drivill.courier.adapter.MyStatementAdapter;
import com.drivill.courier.databinding.FragmentRidingBinding;
import com.drivill.courier.utils.PrefsManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RidingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RidingFragment extends Fragment {
    FragmentRidingBinding mBinding;
    MyStatementAdapter myStatementAdapter;
    private final int mTabCount = 4;

    PrefsManager mPrefManager;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    void initUI() {

     /*

     mBinding.tabLayoutRiding.addTab(mBinding.tabLayoutRiding.newTab().setText("Today"));
     mBinding.tabLayoutRiding.addTab(mBinding.tabLayoutRiding.newTab().setText("Weekly"));
     mBinding.tabLayoutRiding.addTab(mBinding.tabLayoutRiding.newTab().setText("Monthly"));
     mBinding.tabLayoutRiding.addTab(mBinding.tabLayoutRiding.newTab().setText("By Date"));
*/
        if (!mPrefManager.getIsRider()) {
            mBinding.tabLayoutRiding.setBackground(null);
        }

        myStatementAdapter = new MyStatementAdapter(this, mTabCount);
        mBinding.viewpager2Riding.setAdapter(myStatementAdapter);
        new TabLayoutMediator(mBinding.tabLayoutRiding,
                mBinding.viewpager2Riding,
                ((tab, position) -> {
                    tab.setCustomView(R.layout.tab_item_layout);
                    TextView textView = tab.getCustomView().findViewById(R.id.textTab);
                    switch (position) {
                        case 0:
                            textView.setText("Today");
                            textView.setTextColor(getResources().getColor(R.color.theme_color));
                            tab.setText(textView.getText());

                            break;
                        case 1:
                            textView.setText("Weekly");
                            tab.setText(textView.getText());

                            break;
                        case 2:
                            textView.setText("Monthly");
                            tab.setText(textView.getText());

                            break;
                        case 3:
                            textView.setText("By Date");
                            tab.setText(textView.getText());

                            break;
                    }


                })).attach();
        mBinding.tabLayoutRiding.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    }

    public RidingFragment() {
        // Required empty public constructor
    }

    public static RidingFragment newInstance(String param1, String param2) {
        RidingFragment fragment = new RidingFragment();
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

        mPrefManager = new PrefsManager(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_riding, container, false);
        mBinding = DataBindingUtil.bind(view);
        initUI();
        return view;
    }


}