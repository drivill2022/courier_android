package com.drivill.courier.merchantModul.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentChartBinding;
import com.drivill.courier.merchantModul.activity.EarnAndPayActivity;
import com.drivill.courier.merchantModul.model.ShippingDataBaseModel;
import com.drivill.courier.rest.ApiManagerImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChartFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    FragmentChartBinding mBinding;

    boolean today = false, week = true, month = true;

    public ChartFragment() {

        // Required empty public constructor
    }


    void initUI() {
        // configureLineChart();
        mBinding.lineChart.setScaleEnabled(false);
        setCharData();
        getDatabaseApi("today");
    }

    public static ChartFragment newInstance(String param1, String param2) {
        ChartFragment fragment = new ChartFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        mBinding = FragmentChartBinding.bind(view);
        initUI();
        try {
            ((EarnAndPayActivity) requireActivity()).setTitleHeading(getString(R.string.shipping_database));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }


    void configureLineChart() {
        Description desc = new Description();
        desc.setText("History");
        desc.setTextSize(28);
        mBinding.lineChart.setDescription(desc);

        XAxis xAxis = mBinding.lineChart.getXAxis();
        xAxis.setValueFormatter(new ValueFormatter() {
            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd MMM", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {
                long millis = (long) value * 1000L;
                return mFormat.format(new Date(millis));
            }
        });


    }

    void setCharData() {
        mBinding.todayTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (today) {
                    mBinding.progrss.setVisibility(View.VISIBLE);
                    mBinding.todayTxt.setBackground(getResources().getDrawable(R.drawable.backround_theme_border));
                    mBinding.weeklyTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    mBinding.monthlyTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    getDatabaseApi("today");
                }
                today = false;
                week = true;
                month = true;
            }
        });
        mBinding.weeklyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (week) {
                    mBinding.progrss.setVisibility(View.VISIBLE);
                    mBinding.todayTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    mBinding.weeklyTxt.setBackground(getResources().getDrawable(R.drawable.backround_theme_border));
                    mBinding.monthlyTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    getDatabaseApi("weekly");
                }

                today = true;
                week = false;
                month = true;
            }
        });
        mBinding.monthlyTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (month) {
                    mBinding.progrss.setVisibility(View.VISIBLE);
                    mBinding.todayTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    mBinding.weeklyTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    mBinding.monthlyTxt.setBackground(getResources().getDrawable(R.drawable.backround_theme_border));
                    getDatabaseApi("monthly");
                }

                today = true;
                week = true;
                month = false;
            }
        });
    }

    void setData(String filter, ShippingDataBaseModel modelData) {
       /* ArrayList<Entry> yValue = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = (float) Math.random() * range + 20;
            yValue.add(new Entry(i, val));
        }*/
        mBinding.lineChart.invalidate();
        mBinding.lineChart.getDescription().setEnabled(false);

        XAxis XAx = mBinding.lineChart.getXAxis();
        XAx.setPosition(XAxis.XAxisPosition.BOTTOM);
        XAx.setDrawAxisLine(true);
        XAx.setDrawGridLines(true);
        ArrayList<String> xAxis = new ArrayList<>();
       /* if (filter.equals("weekly")) {

            xAxis.add("Sat");
            xAxis.add("Sun");
            xAxis.add("Mon");
            xAxis.add("Tue");
            xAxis.add("Wed");
            xAxis.add("Thu");
            xAxis.add("Fri");

        } else if (filter.equals("monthly")) {
            xAxis.add("Jan");
            xAxis.add("feb");
            xAxis.add("Mar");
            xAxis.add("Apr");
            xAxis.add("May");
            xAxis.add("Jun");
            xAxis.add("July");
            xAxis.add("Aug");
            xAxis.add("Sep");
            xAxis.add("Oct");
            xAxis.add("Nov");
            xAxis.add("Dis");
        } else {
            for (int i = 1; i <= 24; i++) {
                xAxis.add(i + ":00");
            }
        }*/
      /*  XAx.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                //  return super.getAxisLabel(value, axis);
                return xAxis.get((int) value);
            }
        });*/


        YAxis YAxL = mBinding.lineChart.getAxisLeft();
        YAxis YAxR = mBinding.lineChart.getAxisRight();
        YAxL.setDrawAxisLine(false);
        YAxL.setDrawGridLines(false);
        YAxR.setEnabled(false);

      /*  YAxL.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                //  return super.getAxisLabel(value, axis);
                return xAxis.get((int) value);
            }
        });*/


        ArrayList<Entry> yAxisSin = new ArrayList<>();
        ArrayList<Entry> yAxisCos = new ArrayList<>();

        int numDataPoint = 500;
        double x = 0;

        /*for (int i = 0; i < xAxis.size(); i++) {
            //   float sinFun = Float.parseFloat(String.valueOf(Math.sin(x)));
            float sinFun = (float) Math.random() * xAxis.size();
            float cosFun = Float.parseFloat(String.valueOf(Math.cos(x)));
            yAxisSin.add(new Entry(i, sinFun));
            yAxisCos.add(new Entry(i, cosFun));

        }*/
        if (modelData.getResult() == null /*&& modelData.getTotalSales() == null*/) {

           /* yAxisSin.add(new Entry(0, (float) 200));
            yAxisSin.add(new Entry(1, (float) 100));
            yAxisSin.add(new Entry(2, (float) 500));


            yAxisSin.add(new Entry(3, (float) 200));
            yAxisSin.add(new Entry(4, (float) 100));
            yAxisSin.add(new Entry(5, (float) 500));
            yAxisSin.add(new Entry(6, (float) 500));


            yAxisCos.add(new Entry(0, (float) 100));
            yAxisCos.add(new Entry(1, (float) 300));
            yAxisCos.add(new Entry(2, (float) 200));


            yAxisCos.add(new Entry(3, (float) 700));
            yAxisCos.add(new Entry(4, (float) 250));
            yAxisCos.add(new Entry(5, (float) 150));
            yAxisCos.add(new Entry(6, (float) 400));





            LineDataSet lineDataSet1 = new LineDataSet(yAxisCos, "Return Sales");
            lineDataSet1.setDrawCircles(false);
            lineDataSet1.setMode(LineDataSet.Mode.LINEAR);
            lineDataSet1.setDrawValues(false);
            try {
                lineDataSet1.setColor(getResources().getColor(R.color.red));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }


            LineDataSet lineDataSet2 = new LineDataSet(yAxisSin, "Sales");
            lineDataSet2.setDrawCircles(false);
            lineDataSet2.setMode(LineDataSet.Mode.LINEAR);
            lineDataSet2.setDrawValues(false);
            try {
                lineDataSet2.setColor(getResources().getColor(R.color.theme_color));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            LineData data = new LineData(lineDataSet1, lineDataSet2);
            mBinding.lineChart.setData(data);
*/
            return;


        } else {
            for (int i = 0; i < modelData.getResult().size(); i++) {
                xAxis.add(modelData.getResult().get(i).getDate());
            }

            XAx.setValueFormatter(new ValueFormatter() {
                @Override
                public String getAxisLabel(float value, AxisBase axis) {
                    //  return super.getAxisLabel(value, axis);
                    return xAxis.get((int) value);
                }
            });
            if (modelData.getResult() != null) {

                for (int i = 0; i < modelData.getResult().size(); i++) {
                    yAxisSin.add(new Entry(i, (float) modelData.getResult().get(i).getTotalSales()));
                }

            }

            if (modelData.getResult() != null)
                for (int i = 0; i < modelData.getResult().size(); i++) {
                    yAxisCos.add(new Entry(i, (float) modelData.getResult().get(i).getReturnSales()));
                }


            LineDataSet lineDataSet1 = new LineDataSet(yAxisCos, "Return Sales");
            lineDataSet1.setDrawCircles(false);
            lineDataSet1.setMode(LineDataSet.Mode.LINEAR);
            lineDataSet1.setDrawValues(false);
            try {
                lineDataSet1.setColor(getResources().getColor(R.color.red));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }


            LineDataSet lineDataSet2 = new LineDataSet(yAxisSin, "Sales");
            lineDataSet2.setDrawCircles(false);
            lineDataSet2.setMode(LineDataSet.Mode.LINEAR);
            lineDataSet2.setDrawValues(false);
            try {
                lineDataSet2.setColor(getResources().getColor(R.color.theme_color));
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            LineData data = new LineData(lineDataSet1, lineDataSet2);
            mBinding.lineChart.setData(data);

// adding some animation that is some good part
            mBinding.lineChart.animateXY(1000, 1000);

        }
    }

    void getDatabaseApi(String filter) {
        Call<ShippingDataBaseModel> call = new ApiManagerImp().merchantShippingDatabase(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(),
                filter);

        call.enqueue(new Callback<ShippingDataBaseModel>() {
            @Override
            public void onResponse(Call<ShippingDataBaseModel> call, Response<ShippingDataBaseModel> response) {
                mBinding.progrss.setVisibility(View.INVISIBLE);
                if (response.body() != null) {
                    ShippingDataBaseModel model = response.body();
                    mBinding.allCashoutTxt.setText("Tk. " + model.getDeliveryAmount());

                    if (filter.equals("today")) {
                        mBinding.lineChart.setVisibility(View.GONE);
                        mBinding.paiChart.setVisibility(View.VISIBLE);
                        setupPaiChart(model);
                    } else {
                        mBinding.tvNodata.setVisibility(View.GONE);
                        mBinding.paiChart.setVisibility(View.GONE);
                        mBinding.lineChart.setVisibility(View.VISIBLE);
                        setData(filter, model);
                    }

                }
            }

            @Override
            public void onFailure(Call<ShippingDataBaseModel> call, Throwable t) {


            }
        });
    }

    void setupPaiChart(ShippingDataBaseModel model) {

        if(model.getDeliveryAmount() == 0)
            mBinding.tvNodata.setVisibility(View.VISIBLE);
            else
            mBinding.tvNodata.setVisibility(View.GONE);

        Map<String, Integer> typeAmountMap = new HashMap<>();
        ArrayList<Integer> colors = new ArrayList<>();
        try {
            colors.add(getResources().getColor(R.color.red));
            colors.add(getResources().getColor(R.color.theme_color));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


        if (model.getResult() != null) {
            typeAmountMap.put("Total Sale", model.getResult().get(0).getTotalSales());
        }
        if (model.getResult() != null) {
            typeAmountMap.put("Return Sales", model.getResult().get(0).getReturnSales());
        }


        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        if (typeAmountMap.size() != 0) {
            for (String type : typeAmountMap.keySet()) {
                pieEntries.add(new PieEntry(typeAmountMap.get(type).floatValue(), type));
            }
            PieDataSet dataSet = new PieDataSet(pieEntries, "");
            dataSet.setColors(colors);


            PieData data = new PieData(dataSet);
            mBinding.paiChart.setData(data);
            mBinding.paiChart.getDescription().setEnabled(false);
            // mBinding.paiChart.setUsePercentValues(true);
            mBinding.paiChart.setRotationAngle(0);
            mBinding.paiChart.animateXY(1000, 1000);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            ((EarnAndPayActivity) requireActivity()).setTitleHeading(getString(R.string.earn_pay));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}