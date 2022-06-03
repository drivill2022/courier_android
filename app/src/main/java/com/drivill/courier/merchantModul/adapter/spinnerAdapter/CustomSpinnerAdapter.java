package com.drivill.courier.merchantModul.adapter.spinnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.DivisionModel;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter {
    ArrayList<DivisionModel> arrayArrayList = new ArrayList<>();
    Context mContext;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, ArrayList<DivisionModel> arrayArrayList) {
        super(context, resource);
        this.mContext = context;
        this.arrayArrayList = arrayArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DivisionModel rowItem = arrayArrayList.get(position);

        View rowview = LayoutInflater.from(mContext).inflate(R.layout.spinner_item_layout, parent, false);

        TextView txtTitle = (TextView) rowview.findViewById(R.id.title);
        txtTitle.setText(rowItem.getName());
        txtTitle.setTag(rowItem.getId());
        return rowview;
    }

    @Override
    public int getCount() {
        return arrayArrayList.size();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.spinner_item_layout, parent, false);
        }
        DivisionModel rowItem = arrayArrayList.get(position);


        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        txtTitle.setText(rowItem.getName());
        // txtTitle.setTag(rowItem.getId());
        return convertView;

    }
}
