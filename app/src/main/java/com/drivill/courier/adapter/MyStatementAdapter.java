package com.drivill.courier.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.drivill.courier.fragment.ByDateFragment;
import com.drivill.courier.fragment.MonthlyStatementFragment;
import com.drivill.courier.fragment.TodayStatementFragment;
import com.drivill.courier.fragment.WeeklyStatementFragment;

import java.util.ArrayList;
import java.util.List;

public class MyStatementAdapter extends FragmentStateAdapter {
    int count;
    List<String> mFragmentTitleList = new ArrayList<>();

    public MyStatementAdapter(@NonNull Fragment fragment, int count) {
        super(fragment);
        this.count = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TodayStatementFragment();
            case 1:
                return new WeeklyStatementFragment();
            case 2:
                return new MonthlyStatementFragment();
            case 3:
                return new ByDateFragment();
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
