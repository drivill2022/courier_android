package com.drivill.courier.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drivill.courier.R;
import com.drivill.courier.activity.ProductDetailActivity;
import com.drivill.courier.merchantModul.model.NearByHubModel;
import com.drivill.courier.model.model.NearestRiderModel;
import com.drivill.courier.utils.Constant;

import java.util.ArrayList;

public class DeliveryDetailsAdapter extends RecyclerView.Adapter<DeliveryDetailsAdapter.MyStatementHolder> {
    Context mContext;
    NearByHubModel model;

    ArrayList<NearestRiderModel> mRiderModel;
    AdapterClickEvent mListener;

    public DeliveryDetailsAdapter(Context context, AdapterClickEvent mListener) {
        mContext = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_delivery_detail_item, parent, false);

        return new MyStatementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {

        if (ProductDetailActivity.mTag.equals("hub")) {
            NearByHubModel.Datum data = model.getData().get(position);

            if (data != null) {
                Glide.with(mContext)
                        .load(Constant.getImageUrl(data.getPicture()))
                        .into(holder.itemImg);
                holder.businessAddressTxt.setText(data.getAddress());
                holder.businessNameTxt.setText(data.getName());
            }
            holder.mainlayoutTransfer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.adapterClick(data.getId().toString(), data.getName());
                }
            });

        } else {
            NearestRiderModel data = mRiderModel.get(position);

            if (data != null) {
                Glide.with(mContext)
                        .load(Constant.getImageUrl(data.getPicture()))
                        .into(holder.itemImg);
                holder.businessAddressTxt.setText(data.getAddress());
                holder.businessNameTxt.setText(data.getName());
            }

            holder.mainlayoutTransfer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.adapterClick(data.getId().toString(), data.getName());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (ProductDetailActivity.mTag.equals("hub")) {
            if (model != null && model.getData() != null)
                return model.getData().size();
            else return 0;

        } else {
            if (mRiderModel != null)
                return mRiderModel.size();
            else return 0;
        }

    }

    class MyStatementHolder extends RecyclerView.ViewHolder {
        ImageView itemImg;
        TextView businessNameTxt, businessAddressTxt;
        RelativeLayout mainlayoutTransfer;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.itemImg);
            businessNameTxt = itemView.findViewById(R.id.businessNameTxt);
            businessAddressTxt = itemView.findViewById(R.id.businessAddressTxt);
            mainlayoutTransfer = itemView.findViewById(R.id.mainlayoutTransfer);
        }
    }

    public void setData(NearByHubModel model) {
        this.model = model;
        notifyDataSetChanged();
    }

    public void setRiderData(ArrayList<NearestRiderModel> riderModel) {
        mRiderModel = riderModel;
        notifyDataSetChanged();
    }

    public interface AdapterClickEvent {
        void adapterClick(String id, String name);
    }
}
