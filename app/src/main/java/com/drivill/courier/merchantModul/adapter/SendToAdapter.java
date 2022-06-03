package com.drivill.courier.merchantModul.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.NearByHubModel;
import com.drivill.courier.utils.Constant;

import de.hdodenhof.circleimageview.CircleImageView;

public class SendToAdapter extends RecyclerView.Adapter<SendToAdapter.MyStatementHolder> {
    Context mContext;

    NearByHubModel model;
    int selectPos = 0;
    SelectedHubAdapter mListener;

    public SendToAdapter(Context context, SelectedHubAdapter mListener) {
        mContext = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.send_to_pickup_layout, parent, false);

        return new MyStatementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {
        if (selectPos == position) {
            holder.mainRL.setBackground(mContext.getResources().getDrawable(R.drawable.backround_theme_border));

        } else {
            holder.mainRL.setBackground(mContext.getResources().getDrawable(R.drawable.backround_input));

        }

        NearByHubModel.Datum mo = model.getData().get(position);

        Glide.with(mContext)
                .load(Constant.getImageUrl(mo.getPicture()))
                .into(holder.itemImg);

        holder.itemNameTxt.setText(mo.getName());
        holder.mobileNumTxt.setText(mo.getPhone());
        holder.locationTxt.setText(mo.getAddress());
        holder.kmTxt.setText(mo.getDistance() + " km");

        holder.mainRL.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                selectPos = position;
                mListener.onSelectedHub(mo);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {

        if (model != null && model.getData() != null) {
            return model.getData().size();
        } else return 0;


    }

    class MyStatementHolder extends RecyclerView.ViewHolder {
        CircleImageView itemImg;
        TextView itemNameTxt, locationTxt, mobileNumTxt, kmTxt;
        RelativeLayout mainRL;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            kmTxt = itemView.findViewById(R.id.kmTxt);
            mobileNumTxt = itemView.findViewById(R.id.mobileNumTxt);
            locationTxt = itemView.findViewById(R.id.locationTxt);
            itemNameTxt = itemView.findViewById(R.id.itemNameTxt);
            itemImg = itemView.findViewById(R.id.itemImg);
            mainRL = itemView.findViewById(R.id.mainRLAdapter);
        }
    }

    public void setData(NearByHubModel model) {
        this.model = model;
        notifyDataSetChanged();
    }

   public interface SelectedHubAdapter {
        void onSelectedHub(NearByHubModel.Datum data);
    }
}
