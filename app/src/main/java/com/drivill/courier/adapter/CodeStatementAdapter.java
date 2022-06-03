package com.drivill.courier.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.activity.supportActivity.SupportActivity;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.utils.AppUtil;

public class CodeStatementAdapter extends RecyclerView.Adapter<CodeStatementAdapter.MyStatementHolder> {
    Context mContext;
    CODStatementModel model;

    public CodeStatementAdapter(Context context) {
        mContext = context;
    }


    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_code_statement_item, parent, false);
        return new MyStatementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {

        holder.nameCustomer.setText(model.getHistory().getData().get(position).getDeliveredTo());
        holder.deliveryAddAdapterTxt.setText(model.getHistory().getData().get(position).getdAddress());
        holder.priceItem.setText(String.valueOf(model.getHistory().getData().get(position).getCodAmount()));
     //   holder.dateTxt.setText(AppUtil.getDateFormat(model.getHistory().getData().get(position).getDate(), ""));
        holder.dateTxt.setText(model.getHistory().getData().get(position).getDate());
        holder.merchantPlaceTxt.setText(model.getHistory().getData().get(position).getShipmentNo());
    /*  holder.nameCustomer.setText(model.getShipments().get(position).getReceiverName());
        holder.deliveryAddAdapterTxt.setText(model.getShipments().get(position).getdAddress());
        holder.priceItem.setText(String.valueOf(model.getShipments().get(position).getShipmentCost()));
        holder.dateTxt.setText(AppUtil.getDateFormat(model.getShipments().get(position).getCreatedAt(), ""));
        holder.merchantPlaceTxt.setText(model.getShipments().get(position).getMerchant().getName());
    */

        holder.supportIcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SupportActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (model != null && model.getHistory().getData() != null)
            return model.getHistory().getData().size();
        else
            return 0;
    }

    class MyStatementHolder extends RecyclerView.ViewHolder {

        TextView nameCustomer, deliveryAddAdapterTxt, priceItem, dateTxt, merchantPlaceTxt;
        ImageView supportIcn;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            nameCustomer = itemView.findViewById(R.id.nameCustomer);
            deliveryAddAdapterTxt = itemView.findViewById(R.id.deliveryAddAdapterTxt);
            priceItem = itemView.findViewById(R.id.priceItem);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            merchantPlaceTxt = itemView.findViewById(R.id.merchantPlaceTxt);
            supportIcn = itemView.findViewById(R.id.supportIcn);
        }
    }


    /* public void setData(RiderPickupListModel data) {
         this.model = data;
         notifyDataSetChanged();
     } */
    public void setData(CODStatementModel data) {
        this.model = data;
        notifyDataSetChanged();
    }
}
