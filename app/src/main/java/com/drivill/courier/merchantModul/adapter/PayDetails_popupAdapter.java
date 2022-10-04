package com.drivill.courier.merchantModul.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.PaymentDetailslist;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PayDetails_popupAdapter extends RecyclerView.Adapter<PayDetails_popupAdapter.MyViewHolder>{

     Context mContext;
     PaymentDetailslist data;

    public PayDetails_popupAdapter(Context mContext, PaymentDetailslist model) {
        this.mContext = mContext;
        this.data = model;
    }


    @NotNull
    @Override
    public PayDetails_popupAdapter.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_paymentdetails, parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull PayDetails_popupAdapter.MyViewHolder holder, int position) {

        holder.shipID.setText(data.getData().get(position).getShipmentNo().replace("#",""));
        holder.cod.setText(data.getData().get(position).getCodAmount()+"");
        holder.cost.setText(data.getData().get(position).getShipmentCost()+"");
        holder.shipName.setText(data.getData().get(position).getReceiver_name()+"");


        // COD - cost = available balance

        try {
            int Avlblnc = data.getData().get(position).getCodAmount()-data.getData().get(position).getShipmentCost();
            holder.Avl_blnc.setText(Avlblnc+"");
        }catch (Exception e){
            Log.i("tag","Avlblnc= "+e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return data.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView shipID,cod,cost,Avl_blnc,shipName;

        public MyViewHolder(View itemView) {
            super(itemView);
            shipName =  itemView.findViewById(R.id.tv1);
            shipID =  itemView.findViewById(R.id.tv8);
            cod =  itemView.findViewById(R.id.tv2);
            cost =  itemView.findViewById(R.id.tv3);
            Avl_blnc =  itemView.findViewById(R.id.tv4);

        }
    }
}
