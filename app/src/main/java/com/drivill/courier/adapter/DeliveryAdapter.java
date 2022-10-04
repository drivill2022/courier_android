package com.drivill.courier.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.model.model.RiderPickupListModel;
import com.drivill.courier.utils.Constant;
import com.google.android.material.snackbar.Snackbar;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.MyPickupHolder> {
    Context mContext;
    deliveryCallback mListener;
    RiderPickupListModel model;

    public DeliveryAdapter(Context context, deliveryCallback mListener) {
        mContext = context;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyPickupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_delivery_item, parent, false);

        return new MyPickupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPickupHolder holder, int position) {

        ShipmentModel detail = model.getShipments().get(position);

        holder.txtRider.setText(model.getShipments().get(position).getRider().getRider().getName());

        if (detail.getStatus() == Integer.parseInt(Constant.RETURN) || detail.getStatus() == Integer.parseInt(Constant.CANCEL)) {
            holder.mapLLPick.setVisibility(View.VISIBLE);
            holder.changeLL.setVisibility(View.GONE);
            if(detail.getStatus() == Integer.parseInt(Constant.CANCEL)){
                // if status is cancel in this list no action will be work --- Hence click is disable ---
                holder.tv_status.setText(mContext.getString(R.string.cancelled));
            }else {
                holder.tv_status.setText(mContext.getString(R.string.returntxt));
            }
        } else {
            holder.changeLL.setVisibility(View.VISIBLE);
            holder.mapLLPick.setVisibility(View.GONE);
        }

        holder.tv_PkgID.setText(detail.getShipmentNo());
        holder.changeLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAdapterClick(detail);
            }
        });
        holder.detailLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDeliveryDetail(detail);
            }
        });
        holder.mapLLPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(mContext, GoogleMapActivity.class);
                intent.putExtra("data", detail);
                mContext.startActivity(intent);*/
                if(detail.getStatus() == Integer.parseInt(Constant.CANCEL)){
                    Toast.makeText(mContext, mContext.getString(R.string.cancelalertmsg), Toast.LENGTH_SHORT).show();
                }else {
                    mListener.onAdapterClick(detail);
                }
            }
        });


        holder.phoneRV_PICKUP.setText(detail.getContactNo());
        holder.nameRV_PICKUP.setText(detail.getReceiverName());
        holder.bussNameRV_PICKUP.setText(detail.getMerchant().getBuss_name());
        holder.addressRV_PICKUP.setText(detail.getdAddress());
        holder.addressRV_PICKUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOnMap(detail);
            }
        });

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + detail.getMerchant().getMobile()));
                mContext.startActivity(intent);*/
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + detail.getContactNo()));
                    mContext.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (model != null && model.getShipments() != null)
            return model.getShipments().size();
        else
            return 0;
        /* return DataManager.getINSTANCE().getDeliveryResponse().getValue().getShipments().size();*/
    }

    class MyPickupHolder extends RecyclerView.ViewHolder {
        LinearLayout changeLL, detailLL, mapLLPick;
        TextView nameRV_PICKUP, addressRV_PICKUP, phoneRV_PICKUP,bussNameRV_PICKUP,tv_PkgID, tv_status, txtRider;
        ImageView call;

        public MyPickupHolder(@NonNull View itemView) {
            super(itemView);
            changeLL = itemView.findViewById(R.id.changeLL);
            detailLL = itemView.findViewById(R.id.detailLL);
            mapLLPick = itemView.findViewById(R.id.mapLL);
            nameRV_PICKUP = itemView.findViewById(R.id.nameRV_PICKUP);
            addressRV_PICKUP = itemView.findViewById(R.id.addressRV_PICKUP);
            phoneRV_PICKUP = itemView.findViewById(R.id.phoneRV_PICKUP);
            call = itemView.findViewById(R.id.call);
            bussNameRV_PICKUP = itemView.findViewById(R.id.bussNameRV_PICKUP);
            tv_PkgID = itemView.findViewById(R.id.tv_PkgID);
            tv_status = itemView.findViewById(R.id.tv_status);
            txtRider = itemView.findViewById(R.id.txt_rider);

        }
    }

    public interface deliveryCallback {
        void onAdapterClick(ShipmentModel value);

        void onDeliveryDetail(ShipmentModel pos);
    }

    public void setData(RiderPickupListModel model) {
        this.model = model;
        notifyDataSetChanged();
    }

    private void OpenOnMap(ShipmentModel detail){
        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("geo:" + detail.getdLatitude()
                            + "," + detail.getdLongitude()
                            + "?q=" + detail.getdLatitude()
                            + "," + detail.getdLongitude()
                            + "(" + "Address" + ")"));
            intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
            mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {

            try {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.apps.maps")));
            } catch (android.content.ActivityNotFoundException anfe) {
                mContext.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps")));
            }

            e.printStackTrace();
        }
    }

}
