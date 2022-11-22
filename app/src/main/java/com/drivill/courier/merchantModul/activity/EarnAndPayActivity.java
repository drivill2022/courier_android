package com.drivill.courier.merchantModul.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityEarnAndPayBinding;
import com.drivill.courier.merchantModul.fragment.ChartFragment;
import com.drivill.courier.merchantModul.model.EarnAndPayModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EarnAndPayActivity extends BaseActivity {

    ActivityEarnAndPayBinding mBinding;
    String mCurrentDate;

    String dateFrom;
    String dateTo;
    Date myDate;
    Date newDate;
    void initUI() {
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat showFormat = new SimpleDateFormat("MMM-dd", Locale.getDefault());
        Date c = Calendar.getInstance().getTime();
        mCurrentDate = curFormater.format(c);

        mBinding.montDateTxt.setText(showFormat.format(c));
        mBinding.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EarnAndPayActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);
            }
        });
       mBinding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH,-7);
                dateFrom = sdf.format(c.getTime()).toString();
                try {
                    myDate = sdf.parse(dateFrom);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date newDate = new Date(myDate.getTime() - 604800000L); // 7 * 24 * 60 * 60 * 1000
                dateTo = sdf.format(newDate).toString();
                getErnPayApi("1",dateFrom,dateTo);
            }
        });

        mBinding.rightarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                c.add(Calendar.DAY_OF_MONTH,7);
                dateFrom = sdf.format(c.getTime()).toString();
                try {
                    myDate = sdf.parse(dateFrom);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date newDate = new Date(myDate.getTime() - 604800000L); // 7 * 24 * 60 * 60 * 1000
                dateTo = sdf.format(newDate).toString();
                getErnPayApi("1",dateFrom,dateTo);

            }
        });
        mBinding.breckpayTxt.setOnClickListener(view -> {
            Intent intent = new Intent(EarnAndPayActivity.this, MerchantStatementActivity.class);
            startActivity(intent);
        });
        mBinding.shipingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new ChartFragment());
            }
        });
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_earn_and_pay);
        initUI();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SATURDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        String[] days = new String[7];
        for (int i = 0; i < 7; i++)
        {
            days[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        dateFrom=days[days.length-1];
        dateTo=days[0];
        Log.e("cwcewc", String.valueOf(days.length));




        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,0);
        dateFrom = sdf.format(c.getTime()).toString();
        try {
             myDate = sdf.parse(dateFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date newDate = new Date(myDate.getTime() - 604800000L); // 7 * 24 * 60 * 60 * 1000
        dateTo = sdf.format(newDate).toString();*/
        getErnPayApi("1",dateFrom,dateTo);
    }



    @Override
    protected void onResume() {
        super.onResume();
        //   this.setTitleHeading(getString(R.string.earn_pay));
    }

    public void switchFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    void getErnPayApi(String page, String dateFrom,String dateto) {
        showLoading();
        Call<EarnAndPayModel> call = new ApiManagerImp().merchantEarnAndPay(mBasePreferenceManager.getUserToken(), page,dateFrom,dateto);
        call.enqueue(new Callback<EarnAndPayModel>() {
            @Override
            public void onResponse(Call<EarnAndPayModel> call, Response<EarnAndPayModel> response) {
                hideLoading();
                if (response.body() != null) {
                    EarnAndPayModel model = response.body();
                    Log.e("dhasjhdjsd","dajdajhdjsa"+model.getCompleted()  + model.getTotal());
                    Log.e("pending",model.getPending().toString());
                    mBinding.dateTxt.setText(model.getStartDate().concat(" - ").concat(model.getEndDate()));
                    mBinding.totalCashOutTxt.setText("TK. " + model.getTotalCodCollected());
                    mBinding.completedTxt.setText(model.getCompleted() + "/" + model.getTotal());
                    mBinding.pendingTxt.setText(model.getPending() + "/" + model.getTotal());
                    mBinding.returnTxt.setText(model.getReturn() + "/" + model.getTotal());
                    if (model.getShipments().getData() != null && model.getShipments().getData().size() != 0) {

                        if (model.getShipments().getData().get(0).getDate().equals(mCurrentDate)) {
                            mBinding.todayCmpShipTxt.setText("You have completed " + model.getShipments().getData().get(0).getCount() + " shipping today.");
                        }
                    }
                    Log.d("res", "");

                } else if (response.errorBody() != null) {
                    Log.d("res", "");
                }
            }

            @Override
            public void onFailure(Call<EarnAndPayModel> call, Throwable t) {
                Log.d("res", "onFailure= "+t.getMessage());
                hideLoading();
                onError(getString(R.string.try_again));
            }
        });
    }

    public void setTitleHeading(String title) {
        mBinding.titleTxt.setText(title);
    }
}