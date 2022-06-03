package com.drivill.courier.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.drivill.courier.fragment.DeliveryFragment;
import com.drivill.courier.fragment.PickupFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStateAdapter {
    int count;
    List<String> mFragmentTitleList = new ArrayList<>();

    PickupFragment mPickupFragment;
    DeliveryFragment mDeliveryFragment;

    public MyPagerAdapter(@NonNull Fragment fragment, int count) {
        super(fragment);
        this.count = count;
        mPickupFragment = new PickupFragment();
        mDeliveryFragment = new DeliveryFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return mPickupFragment;
            case 1:
                return mDeliveryFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public List<String> getmFragmentTitleList() {

        return mFragmentTitleList;
    }


}
