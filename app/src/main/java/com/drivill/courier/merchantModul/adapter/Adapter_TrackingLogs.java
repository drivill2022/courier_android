package com.drivill.courier.merchantModul.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.PaymentDetailslist;
import com.drivill.courier.merchantModul.model.TrackingModel;

import org.jetbrains.annotations.NotNull;

public class Adapter_TrackingLogs extends RecyclerView.Adapter<Adapter_TrackingLogs.MyViewHolder>{

     Context mContext;
     TrackingModel data;

    public Adapter_TrackingLogs(FragmentActivity mContext, TrackingModel model) {
        this.mContext = mContext;
        this.data = model;
    }

    @NotNull
    @Override
    public Adapter_TrackingLogs.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.adapter_trackinglogs, parent,false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter_TrackingLogs.MyViewHolder holder, int position) {
        int status = data.getData().getStatusLogs().get(position).getStatus();
        String statustype = "" ;

        if(status == 1){
            statustype = mContext.getString(R.string.requested);
        }else if(status == 2){
            statustype = mContext.getString(R.string.assigned);
        } else if(status == 3){
            statustype = mContext.getString(R.string.picked_up);
        } else if(status == 4){
            statustype = mContext.getString(R.string.inTrans);
        } else if(status == 5){
            statustype =  mContext.getString(R.string.On_going_delivery);
        } else if(status == 6){
            statustype =  mContext.getString(R.string.delivered);
           holder.confirmView.setVisibility(View.GONE);
        } else if(status == 7){
            statustype =  mContext.getString(R.string.completed);
        }else if(status == 8){
            statustype =  mContext.getString(R.string.cancelled);
        } else if(status == 9){
            statustype =  mContext.getString(R.string.Rejected);
        }else if(status == 10){
           // statustype =  mContext.getString(R.string.returntxt)+"\n"+mContext.getString(R.string.customer_attempt_failed);
            statustype =  mContext.getString(R.string.customer_attempt_failed);
        }else if(status == 11){
            statustype =  mContext.getString(R.string.cancelled)/*+"\n"+mContext.getString(R.string.cancel_bformark)*/;
        }else if(status == 12){
            statustype =  mContext.getString(R.string.cancelled)/*+"\n"+mContext.getString(R.string.cancelled_by_rider)*/;
        }else {
            statustype = mContext.getString(R.string.Onboarding);
        }
        // Dc3811

        holder.tv_status.setText(statustype);
        holder.tv_date.setText(data.getData().getStatusLogs().get(position).getCreated_date());
        holder.tv_note.setText(data.getData().getStatusLogs().get(position).getNote());

    }

    @Override
    public int getItemCount() {
        return data.getData().getStatusLogs().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_date,tv_status,tv_note;
        View confirmView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_date =  itemView.findViewById(R.id.tv_date);
            tv_status =  itemView.findViewById(R.id.tv_status);
            tv_note =  itemView.findViewById(R.id.tv_note);
            confirmView =  itemView.findViewById(R.id.confirmView);

        }
    }
}
