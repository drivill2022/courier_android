 package com.drivill.courier.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.drivill.courier.fragment.DeliveryFragment;
import com.drivill.courier.fragment.PickupFragment;
import com.drivill.courier.fragment.ReturnFragment;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
    int count;
    List<String> mFragmentTitleList = new ArrayList<>();

    PickupFragment mPickupFragment;
    DeliveryFragment mDeliveryFragment;

    public MyPagerAdapter(
            @NonNull FragmentManager fm)
    {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new PickupFragment();
        else if (position == 1)
            fragment = new DeliveryFragment();
        else if (position == 2)
            fragment = new DeliveryFragment();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Pickup";
        else if (position == 1)
            title = "Delivery";
        else if (position == 2)
            title = "Return";
        return title;
    }

   /* public MyPagerAdapter(@NonNull Fragment fragment, int count) {
        super(fragment);
        this.count = count;
        mPickupFragment = new PickupFragment();
        mDeliveryFragment = new DeliveryFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment=new PickupFragment();
            case 1:
                fragment=new DeliveryFragment();

            case 2:
                fragment=new DeliveryFragment();

            default:
                fragment= null;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return count;
    }*/

    public List<String> getmFragmentTitleList() {

        return mFragmentTitleList;
    }


}
