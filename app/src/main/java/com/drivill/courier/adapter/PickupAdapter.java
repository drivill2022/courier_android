package com.drivill.courier.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.model.model.RiderPickupListModel;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

public class PickupAdapter extends RecyclerView.Adapter<PickupAdapter.MyPickupHolder> {
    Context mContext;
    pickupCallback mListener;
    RiderPickupListModel model;

    public PickupAdapter(Context context, pickupCallback callback) {
        mContext = context;
        this.mListener = callback;
    }

    @NonNull
    @Override
    public MyPickupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_pickup_item, parent, false);
        return new MyPickupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPickupHolder holder, int position) {
        ShipmentModel detail = model.getShipments().get(position);
        if (detail.getStatus() == 3) {
            holder.mapLLPick.setVisibility(View.GONE);
            holder.changeLLPick.setVisibility(View.VISIBLE);
        } else {
            holder.changeLLPick.setVisibility(View.GONE);
            holder.mapLLPick.setVisibility(View.VISIBLE);
        }

        holder.tv_PkgID.setText(detail.getShipmentNo());

        holder.changeLLPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAdapterClick(detail);
            }
        });
        holder.detailLLPick.setOnClickListener(new View.OnClickListener() {
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
                mListener.onAdapterClick(detail);
            }
        });

        holder.phoneRV_PICKUP.setText(detail.getMerchant().getMobile());
        holder.nameRV_PICKUP.setText(detail.getMerchant().getName());
        holder.bussNameRV_PICKUP.setText(detail.getMerchant().getBuss_name());
        holder.addressRV_PICKUP.setText(detail.getsAddress());
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
                if (AppUtil.checkPermissionForCall(mContext)) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + detail.getMerchant().getMobile()));
                    mContext.startActivity(intent);
                } else {
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, 0);

                    AppUtil.requestPermission((Activity) mContext,
                            Manifest.permission.CALL_PHONE, Constant.CALL_PERMISSION);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (model != null && model.getShipments() != null)
            return model.getShipments().size();
        else return 0;
        /*if (DataManager.getINSTANCE().getDeliveryResponse().getValue() != null
                && DataManager.getINSTANCE().getDeliveryResponse().getValue().getShipments() != null)
            return DataManager.getINSTANCE().getDeliveryResponse().getValue().getShipments().size();
        else return 0;*/
    }

    class MyPickupHolder extends RecyclerView.ViewHolder {
        LinearLayout changeLLPick, detailLLPick, mapLLPick;
        TextView nameRV_PICKUP, addressRV_PICKUP, phoneRV_PICKUP,bussNameRV_PICKUP,tv_PkgID;
        ImageView call;
        RelativeLayout mainRL;

        public MyPickupHolder(@NonNull View itemView) {
            super(itemView);
            changeLLPick = itemView.findViewById(R.id.changeLLPick);
            detailLLPick = itemView.findViewById(R.id.detailLLPick);
            mapLLPick = itemView.findViewById(R.id.mapLLPick);
            nameRV_PICKUP = itemView.findViewById(R.id.nameRV_PICKUP);
            addressRV_PICKUP = itemView.findViewById(R.id.addressRV_PICKUP);
            phoneRV_PICKUP = itemView.findViewById(R.id.phoneRV_PICKUP);
            call = itemView.findViewById(R.id.call);
            mainRL = itemView.findViewById(R.id.pickMainRL);
            bussNameRV_PICKUP = itemView.findViewById(R.id.bussNameRV_PICKUP);
            tv_PkgID = itemView.findViewById(R.id.tv_PkgID);
        }
    }

    public interface pickupCallback {
        void onAdapterClick(ShipmentModel shipmentModel);

        void onDeliveryDetail(ShipmentModel bundle);
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
