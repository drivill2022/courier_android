package com.drivill.courier.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.drivill.courier.model.model.HubModel;

public class HubSpinnerAdapter extends ArrayAdapter<HubModel> {
    public HubSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    private class viewHolder{
        TextView txtTitle;
            }

}
