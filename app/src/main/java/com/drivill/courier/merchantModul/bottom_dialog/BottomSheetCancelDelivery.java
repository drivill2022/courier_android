package com.drivill.courier.merchantModul.bottom_dialog;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.drivill.courier.R;

public class BottomSheetCancelDelivery extends BottomSheetDialog {

    Activity mContext;
    BottomSheetCancelDelivery thisIs;
    BottomSheetEventListener mListener;
    String mShippingID;

    public BottomSheetCancelDelivery(@NonNull Activity context, String shipID) {
        super(context);
        this.mContext = context;
        mShippingID = shipID;
        mListener = (BottomSheetEventListener) context;
        thisIs = this;
        View view = View.inflate(context, R.layout.dialog_cancel_delivery, null);
        this.setContentView(view);
        ((ViewGroup) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        ((ViewGroup) view.getParent()).setPadding(30, 0, 30, 0);

        TextView yescancelBtn = view.findViewById(R.id.yescancelBtn);
        TextView noBtn = view.findViewById(R.id.noBtn);
        TextView itemId = view.findViewById(R.id.itemIdTxt);
        itemId.setText(mShippingID);


        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIs.dismiss();
            }
        });

        yescancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.bottomSheetClick(0);
                thisIs.dismiss();


            }
        });

    }


    public interface BottomSheetEventListener {
        void bottomSheetClick(int pos);
    }


}
