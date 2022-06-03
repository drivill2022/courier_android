package com.drivill.courier.merchantModul.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.ShipmentModel;

import java.util.ArrayList;

public class RequestPickupItemAdapter extends RecyclerView.Adapter<RequestPickupItemAdapter.MyStatementHolder> {
    Context mContext;
    ArrayList<ShipmentModel> model;
    adapterEventListener listener;
    int type;

    public RequestPickupItemAdapter(Context context, ArrayList<ShipmentModel> model, adapterEventListener listener, int type) {
        mContext = context;
        this.model = model;
        this.type = type;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_request_pickup_item, parent, false);
        return new MyStatementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {

   /*     if (type == 1) {
            if (model.get(position).getStatus() == 0) {
                holder.deliverToTxt.setText(mContext.getResources()
                        .getString(R.string.deliver_to)
                        .concat(model.get(position).getReceiverName()));
                holder.deliveryAddressTxt.setText(model.get(position).getdAddress());
                holder.id_txt.setText(model.get(position).getShipmentNo());
                holder.parentViewRL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onAdapterItemClick(position);
                    }
                });
            }

        } else {*/
            holder.deliverToTxt.setText(mContext.getResources()
                    .getString(R.string.deliver_to)
                    .concat(model.get(position).getReceiverName()));
            holder.deliveryAddressTxt.setText(model.get(position).getdAddress());
            holder.id_txt.setText(model.get(position).getShipmentNo());
            holder.parentViewRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAdapterItemClick(position);
                }
            });
      //  }

    }

    @Override
    public int getItemCount() {
        if (model != null)
            return model.size();
        else return 0;
    }

    class MyStatementHolder extends RecyclerView.ViewHolder {
        RelativeLayout parentViewRL;
        TextView id_txt, deliveryAddressTxt, deliverToTxt;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            parentViewRL = itemView.findViewById(R.id.parentViewRL);
            id_txt = itemView.findViewById(R.id.id_txt);
            deliveryAddressTxt = itemView.findViewById(R.id.deliveryAddressTxt);
            deliverToTxt = itemView.findViewById(R.id.deliverToTxt);
        }
    }

    public interface adapterEventListener {
        void onAdapterItemClick(int pos);
    }
}
