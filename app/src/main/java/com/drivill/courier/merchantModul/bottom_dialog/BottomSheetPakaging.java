package com.drivill.courier.merchantModul.bottom_dialog;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.drivill.courier.utils.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.drivill.courier.R;
import com.drivill.courier.merchantModul.activity.OneItemDetailActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BottomSheetPakaging extends BottomSheetDialog {
    Activity mContext;
    BottomSheetPakaging thisIs;
    BottomSheetEventListener mListener;

    public BottomSheetPakaging(@NonNull Activity context) {
        super(context);
        this.mContext = context;
        //  mListener = (BottomSheetEventListener) context;
        thisIs = this;
        View view = View.inflate(context, R.layout.recycler_costing_item_layout, null);
        this.setContentView(view);
        ((ViewGroup) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        ((ViewGroup) view.getParent()).setPadding(30, 0, 30, 0);

        RelativeLayout deliveryCostRL = view.findViewById(R.id.deliveryCostRL);
        RelativeLayout expressCostRL = view.findViewById(R.id.expressCostRL);
        TextView processPickup = view.findViewById(R.id.processPickup);
        ImageView close_btn = view.findViewById(R.id.close_btn);
        TextView button_backHome = view.findViewById(R.id.button_backHome);

        deliveryCostRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deliveryCostRL.setBackgroundResource(R.drawable.theme_background_button);
                expressCostRL.setBackgroundResource(R.drawable.backround_input);
            }
        });

        expressCostRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expressCostRL.setBackgroundResource(R.drawable.theme_background_button);
                deliveryCostRL.setBackgroundResource(R.drawable.backround_input);
            }
        });

        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIs.dismiss();
            }
        });

        button_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIs.dismiss();
                mContext.finish();

            }
        });
        processPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thisIs.dismiss();
                Intent intent = new Intent(mContext, OneItemDetailActivity.class);
                mContext.startActivity(intent);

            }
        });

        datePicker();
    }


    public interface BottomSheetEventListener {
        void bottomSheetClick(int pos);
    }

    void datePicker() {
        Date EndTime = null;
        Date CurrentTime = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
        try {
            EndTime = dateFormat.parse("08:00 am");
            CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            Log.i("exe-","time-"+e.toString());
        }

        final Calendar c = Calendar.getInstance();
        Calendar date = Calendar.getInstance();
        // c.add(Calendar.DATE, 1);
        if (CurrentTime != null && CurrentTime.after(EndTime)) {
            System.out.println("timeeee end ");
            c.add(Calendar.DATE, 1);
        }else {
            c.add(Calendar.DATE, 0);
        }
        Log.i("time-","time-"+CurrentTime +"\n"+ EndTime);

        DatePickerDialog dpd = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                      /*  mBinding.selectDateText.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);*/
                date.set(year, monthOfYear, dayOfMonth);
              //  mBinding.selectDateText.setText(AppUtil.getDateFormat(date.getTime()));


                    /*    new TimePickerDialog(PackagingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                date.set(Calendar.MINUTE, minute);
                              *//*  SimpleDateFormat localDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a", Locale.getDefault());
                                String time = localDateFormat.format((date.getTime()));*//*
                                mBinding.selectDateText.setText(AppUtil.getDateFormat(date.getTime()));
                                // Log.v("TAG", "The choosen one " + date.getTime());
                            }
                        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false).show();
*/
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

        dpd.getDatePicker().setMinDate(c.getTimeInMillis());
        dpd.show();


    }


}
