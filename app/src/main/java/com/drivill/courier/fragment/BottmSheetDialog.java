package com.drivill.courier.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.drivill.courier.R;
import com.drivill.courier.bottomsheetFragment.BottomSheet;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottmSheetDialog extends BottomSheetDialogFragment {

    ImageView algo_button;
    ImageView dropbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        algo_button = v.findViewById(R.id.all);
        dropbtn = v.findViewById(R.id.dropBtn);
        //Button course_button = v.findViewById(R.id.course_button);

        algo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                                "Algorithm Shared", Toast.LENGTH_SHORT)
                        .show();
                dismiss();
            }
        });

       /* dropbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottmSheetDialog bottomSheet = new BottmSheetDialog();
                //bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
                bottomSheet.dismiss();
            }
        });*/


            /*mBinding.bottomSheetParent.setVisibility(View.GONE);
            mBinding.addNewBtn.setVisibility(View.VISIBLE);
            mBinding.dropBtn.setVisibility(View.GONE);*/

        return v;
    }

    /*@NonNull
    @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override public void onShow(DialogInterface dialogInterface) {
                BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
                setupFullHeight(bottomSheetDialog);
            }
        });
        return  dialog;
    }


    private void setupFullHeight(BottomSheetDialog bottomSheetDialog) {
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();

        int windowHeight = getWindowHeight();
        if (layoutParams != null) {
            layoutParams.height = windowHeight;
        }
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private int getWindowHeight() {
        // Calculate window height for fullscreen use
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }*/
}
