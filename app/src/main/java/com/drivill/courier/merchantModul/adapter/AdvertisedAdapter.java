package com.drivill.courier.merchantModul.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drivill.courier.R;
import com.drivill.courier.model.model.BannerResponseModel;

import java.util.ArrayList;

public class AdvertisedAdapter extends RecyclerView.Adapter<AdvertisedAdapter.MyStatementHolder> {
    Context mContext;
    ArrayList<BannerResponseModel> modelArrayList = new ArrayList<>();

    public AdvertisedAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_add_layout, parent, false);

        return new MyStatementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {

        Glide.with(mContext).load(modelArrayList.get(position).getImage()).into(holder.imageAdv);

      //  Log.i("mydebug","Image=>  "+modelArrayList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        if (modelArrayList != null)
            return modelArrayList.size();
        else return 0;
    }

    class MyStatementHolder extends RecyclerView.ViewHolder {
        ImageView imageAdv;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            imageAdv = itemView.findViewById(R.id.imageAdv);
        }
    }

    public void setData(ArrayList<BannerResponseModel> list) {
        modelArrayList = list;
        notifyDataSetChanged();
    }
}
