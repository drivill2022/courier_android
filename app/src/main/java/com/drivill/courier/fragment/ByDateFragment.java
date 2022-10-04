package com.drivill.courier.fragment;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.drivill.courier.R;
import com.drivill.courier.activity.StatementActivity;
import com.drivill.courier.databinding.FragmentByDateBinding;
import com.drivill.courier.fragment.fragmentViewModel.RidingStatementViewModel;
import com.drivill.courier.merchantModul.activity.MerchantStatementActivity;
import com.drivill.courier.merchantModul.model.BreakDownModel;
import com.drivill.courier.model.model.StatementModel;
import com.drivill.courier.utils.PrefsManager;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ByDateFragment extends Fragment {
    TextView mByDateTxt, mMonthTxt;
    Button mDoneBtn;
    int count = 1;

    ImageView mPrev, mNext, mClose;
    CalendarView mCalendarView;
    LinearLayout mContainer;
    NestedScrollView mCalView;
    boolean isShow = true;

    int currentYear, currentMonth;

    StringBuilder fromDateToDate;


    RidingStatementViewModel mViewModel;
    PrefsManager manager;
    FragmentByDateBinding mBinding;
    Date fromDate, toDate;

    String apiDate1, apiDate2;


    void initUI(View view) {
        mViewModel = new ViewModelProvider(requireActivity()).get(RidingStatementViewModel.class);
        if (manager.getIsRider())
            mViewModel.mByDateData.observe(requireActivity(), success_observer);
        else {
            mViewModel.mByDateDataMerchant.observe(requireActivity(), success_observerMerchant);
            mBinding.riderScroll.setVisibility(View.GONE);
            mBinding.merchantScollView.setVisibility(View.VISIBLE);
        }

        mByDateTxt = view.findViewById(R.id.selectDate);
        mCalendarView = view.findViewById(R.id.calendarView);
        mContainer = view.findViewById(R.id.layout_container);
        mCalView = view.findViewById(R.id.calViewLL);
        mMonthTxt = view.findViewById(R.id.monthText);
        mPrev = view.findViewById(R.id.prev);
        mNext = view.findViewById(R.id.next);
        mDoneBtn = view.findViewById(R.id.doneBtn);
        mClose = view.findViewById(R.id.close_btn);
        // mMonthTxt = view.findViewById(R.id.monthText);


        mContainer.setVisibility(View.VISIBLE);
        mCalView.setVisibility(View.INVISIBLE);

        initCalender();

        mByDateTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isShow) {
                    mCalView.setVisibility(View.VISIBLE);
                    mContainer.setVisibility(View.INVISIBLE);
                    isShow = false;
                } else {
                    mContainer.setVisibility(View.VISIBLE);
                    mCalView.setVisibility(View.INVISIBLE);
                    isShow = true;
                }
            }
        });

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ByDateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ByDateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ByDateFragment newInstance(String param1, String param2) {
        ByDateFragment fragment = new ByDateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        manager = new PrefsManager(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_by_date, container, false);
        mBinding = FragmentByDateBinding.bind(view);
        initUI(view);
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mViewModel != null)
            if (apiDate1 != null && apiDate2 != null) {

                if (manager.getIsRider())
                    mViewModel.getRiderStatementApi(manager, "",
                            apiDate1, apiDate2);
                else mViewModel.getMerchantBreakdown(manager, "", apiDate1, apiDate2);
            }
    }

    private void updateLabel() {

        Calendar date = mCalendarView.getCurrentPageDate();

        String myFormat = "MMM,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        mMonthTxt.setText(sdf.format(date.getTime()));

    }

    private void changeCalenderMonth(int year, int month, String tag) {
        if (tag.equals("N")) {

            if (month == 12) {
                year++;
                month = 1;
            } else month++;
        } else {

            if (month == 1) {

                year--;
                month = 12;


            } else month--;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        currentYear = year;
        currentMonth = month;
        try {
            mCalendarView.setDate(calendar);
        } catch (OutOfDateRangeException e) {
            e.printStackTrace();
        }
/*
        //setting Date min
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add( Calendar.MONTH, -6 ); // Subtract 6 months
        long minDate = c.getTime().getTime() ;// Twice!
        mCalendarView.setMinimumDate(c);*/

    }

    void initCalender() {
        mCalendarView.setHeaderVisibility(View.GONE);
        Calendar date = mCalendarView.getCurrentPageDate();
        String myFormat = "MMM,yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        String myFormat2 = "MM,yyyy";
        SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.US);

        currentYear = Integer.parseInt(sdf2.format(date.getTime()).substring(3, 7));
        currentMonth = Integer.parseInt(sdf2.format(date.getTime()).substring(0, 2));
        //becouse the month is not number
        currentMonth = currentMonth - 1;

        mMonthTxt.setText(sdf.format(date.getTime()));
        //   mCalendarView.setCalendarDayLayout(R.layout.calender_view_layout);


        mCalendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(@NotNull EventDay eventDay) {

                Calendar clickedDayCalendar = eventDay.getCalendar();
                Log.d("day2", String.valueOf(eventDay));
                String myFormat = "dd MMMM"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                // fromDateToDate.append(sdf.format(clickedDayCalendar.getTime()));

                if (count == 2) {
                    fromDateToDate.append(sdf.format(clickedDayCalendar.getTime()));
                    count = 1;
                    toDate = clickedDayCalendar.getTime();
                } else {
                    fromDateToDate = new StringBuilder();
                    fromDateToDate.append(sdf.format(clickedDayCalendar.getTime()));
                    fromDateToDate.append(" - ");
                    count++;
                    fromDate = clickedDayCalendar.getTime();
                }

            }
        });

        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count == 1 && !(fromDateToDate == null)) {

                    String myFormat = "dd MMMM"; //only showing
                    String apiFormat = "yyyy-MM-dd"; //for passing API param
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                    SimpleDateFormat sdfApi = new SimpleDateFormat(apiFormat, Locale.getDefault());

                    String showDate;
                    if (fromDate.compareTo(toDate) < 0) {
                        showDate = sdf.format(fromDate) + "-" + sdf.format(toDate);
                        apiDate1 = sdfApi.format(fromDate);
                        apiDate2 = sdfApi.format(toDate);
                    } else {
                        showDate = sdf.format(toDate) + "-" + sdf.format(fromDate);
                        apiDate1 = sdfApi.format(toDate);
                        apiDate2 = sdfApi.format(fromDate);
                    }
                    mByDateTxt.setText(showDate);

                    if (manager.getIsRider())
                        mViewModel.getRiderStatementApi(manager, "",
                                apiDate1, apiDate2);
                    else mViewModel.getMerchantBreakdown(manager, "", apiDate1, apiDate2);

                }

                mContainer.setVisibility(View.VISIBLE);
                mCalView.setVisibility(View.INVISIBLE);
                isShow = true;
            }
        });
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContainer.setVisibility(View.VISIBLE);
                mCalView.setVisibility(View.INVISIBLE);
                isShow = true;
            }
        });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   mCalendarView.scrollBy((int)mCalendarView.getScrollX() + 10, (int)mCalendarView.getScrollY());

                changeCalenderMonth(currentYear, currentMonth, "N");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateLabel();
                    }
                }, 300);

            }
        });
        mPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeCalenderMonth(currentYear, currentMonth, "P");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateLabel();
                    }
                }, 300);
            }
        });
    }


    private Observer<StatementModel> success_observer = new Observer<StatementModel>() {
        @Override
        public void onChanged(StatementModel statementModel) {
            Log.d("res", String.valueOf(statementModel));
            mBinding.pickUpTxt.setText(String.valueOf(statementModel.getPickedUp()));
            mBinding.cancelTxt.setText(String.valueOf(statementModel.getCancelled()));
            mBinding.deliveryTxt.setText(String.valueOf(statementModel.getDelivered()));
            mBinding.pendingTxt.setText(String.valueOf(statementModel.getPending()));
            mBinding.totalCodTxt.setText(String.valueOf(statementModel.getTotalCodEarned()));
            StatementActivity.setCollectedCOD(String.valueOf(statementModel.getTotalCodCollected()));

        }
    };


    private Observer<BreakDownModel> success_observerMerchant = new Observer<BreakDownModel>() {
        @Override
        public void onChanged(BreakDownModel statementModel) {
            Log.d("res", String.valueOf(statementModel));
            mBinding.packagesTxt.setText(String.valueOf(statementModel.getShipped()));
            mBinding.packagedeliveryTxt.setText(String.valueOf(statementModel.getDelivered()));
            mBinding.packagesReturnTxt.setText(String.valueOf(statementModel.getCancelled()));
            mBinding.totalPendingTxt.setText(String.valueOf(statementModel.getPending()));

            mBinding.totalCashTxt.setText(String.valueOf(statementModel.getCashCollected()));
            mBinding.totalDrivillReturnTxt.setText(String.valueOf(statementModel.getRefundFromDrivill()));
            mBinding.totalDrivillChargeTxt.setText(String.valueOf(statementModel.getDrivillServiceCharge()));
            mBinding.totalAvailPayTxt.setText(String.valueOf(statementModel.getTotalAvailableForPayout()));

            MerchantStatementActivity.setAvailPay(statementModel.getAvailablePayout().toString());
            MerchantStatementActivity.setCommission("Tk. " + statementModel.getDrivillsCommission());

        }
    };


}