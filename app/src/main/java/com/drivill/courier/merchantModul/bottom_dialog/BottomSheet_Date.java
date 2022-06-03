package com.drivill.courier.merchantModul.bottom_dialog;

import android.app.Activity;
import android.app.DatePickerDialog;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.drivill.courier.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BottomSheet_Date extends BottomSheetDialog {
    Activity mContext;
    BottomSheet_Date thisIs;
    BottomSheetEventListener mListener;
   // private DatePicker picker ;
    private CalendarView picker;

    public BottomSheet_Date(@NonNull Activity context) {
        super(context);
        this.mContext = context;
        //  mListener = (BottomSheetEventListener) context;
        thisIs = this;
        View view = View.inflate(context, R.layout.bottomsheet_date, null);
        this.setContentView(view);
        ((ViewGroup) view.getParent()).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
        ((ViewGroup) view.getParent()).setPadding(10, 0, 10, 0);

        RelativeLayout bottomSheet = findViewById(R.id.ll_BS);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

         picker = findViewById(R.id.datePicker);

        picker.setForwardButtonImage(mContext.getResources().getDrawable(R.drawable.ic_arrow_right_24));
        picker.setPreviousButtonImage(mContext.getResources().getDrawable(R.drawable.ic_arrow_left_24));

        findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 dismiss();
            }
        });

        SetUpDatepicker(picker);
      //  datePicker();
    }

    private void SetUpDatepicker(CalendarView picker){

         picker.setMinimumDate(Calendar.getInstance());

        Date EndTime = null;
        Date CurrentTime = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm aa", Locale.getDefault());
        try {
            EndTime = dateFormat.parse("08:00 am");
            CurrentTime = dateFormat.parse(dateFormat.format(new Date()));
        }catch (Exception e){
            Log.i("cal-","time-"+e.toString());
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
        Log.i("cal-","time-"+CurrentTime +"\n"+ EndTime);

        try {
            picker.setDate(date);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
            Log.i("cal-","setDate- "+e.toString());
        }

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

            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));

        dpd.getDatePicker().setMinDate(c.getTimeInMillis());
        dpd.show();

    }


}
