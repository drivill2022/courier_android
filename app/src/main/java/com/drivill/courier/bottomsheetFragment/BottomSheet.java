package com.drivill.courier.bottomsheetFragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.drivill.courier.R;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.utils.Constant;

public class BottomSheet extends BottomSheetDialog {
    Context mContext;
    BottomSheet thisIs;
    BottomSheetEventListener mListener;

    private ShipmentModel mData;

    public BottomSheet(@NonNull Context context, int typ, ShipmentModel mData) {
        super(context);
        this.mContext = context;
        mListener = (BottomSheetEventListener) context;
        thisIs = this;
        this.mData = mData;
        View view = View.inflate(context, R.layout.transferred_layout, null);
        this.setContentView(view);
        ((View) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        ((View) view.getParent()).setPadding(30, 0, 30, 0);

        TextView home = view.findViewById(R.id.home_dialog);
        TextView rider = view.findViewById(R.id.rider_dialog);
        TextView callTxt = view.findViewById(R.id.callCustomerTxt);
        TextView callMerchantTxt = view.findViewById(R.id.callMerchantTxt);
        TextView button_continue = view.findViewById(R.id.button_continue);

        if (typ == 1) {
            callTxt.setVisibility(View.GONE);
            callMerchantTxt.setVisibility(View.GONE);
            home.setVisibility(View.VISIBLE);
            rider.setVisibility(View.VISIBLE);
        } else if (typ == 2) {
            callTxt.setVisibility(View.VISIBLE);
            home.setVisibility(View.GONE);
            rider.setVisibility(View.GONE);
            callMerchantTxt.setVisibility(View.GONE);
        } else {
            callTxt.setVisibility(View.VISIBLE);
            home.setVisibility(View.GONE);
            rider.setVisibility(View.GONE);
            callMerchantTxt.setVisibility(View.VISIBLE);

            if (typ == 3)
                if (mData.getStatus() == Integer.parseInt(Constant.RETURN)) {
                    button_continue.setAlpha((float) .3);
                }
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.bottomSheetClick(1, mData);
                thisIs.dismiss();
            }
        });

        rider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.bottomSheetClick(2, mData);
                thisIs.dismiss();
            }
        });

        callTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.bottomSheetClick(3, mData);
                thisIs.dismiss();
            }
        });

        callMerchantTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mData.getMerchant() != null) {
                    mListener.bottomSheetClick(4, mData);
                } else {
                    Toast.makeText(mContext, "Merchant not found", Toast.LENGTH_SHORT).show();
                }

                thisIs.dismiss();
            }
        });

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typ == 3)
                    if (mData.getStatus() == Integer.parseInt(Constant.ON_GOING)) {
                        mListener.bottomSheetClick(5, mData);
                        thisIs.dismiss();
                    }
            }
        });
    }


    public interface BottomSheetEventListener {
        void bottomSheetClick(int pos, ShipmentModel Data);
    }

}
