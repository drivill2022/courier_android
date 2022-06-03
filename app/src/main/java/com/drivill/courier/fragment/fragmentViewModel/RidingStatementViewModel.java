package com.drivill.courier.fragment.fragmentViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.drivill.courier.merchantModul.model.BreakDownModel;
import com.drivill.courier.model.model.StatementModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.PrefsManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RidingStatementViewModel extends ViewModel {
    public final MutableLiveData<StatementModel> mTodayData = new MutableLiveData<>();
    public final MutableLiveData<StatementModel> mWeekData = new MutableLiveData<>();
    public final MutableLiveData<StatementModel> mMonthlyData = new MutableLiveData<>();
    public final MutableLiveData<StatementModel> mByDateData = new MutableLiveData<>();


    //For merchant
    public final MutableLiveData<BreakDownModel> mTodayDataMerchant = new MutableLiveData<>();
    public final MutableLiveData<BreakDownModel> mWeekDataMerchant = new MutableLiveData<>();
    public final MutableLiveData<BreakDownModel> mMonthlyDataMerchant = new MutableLiveData<>();
    public final MutableLiveData<BreakDownModel> mByDateDataMerchant = new MutableLiveData<>();


    public void getRiderStatementApi(PrefsManager manager, String filter, String fromDate, String toDate) {
        Call<StatementModel> call = new ApiManagerImp().riderStatement(manager.getUserToken(), filter, fromDate, toDate);
        call.enqueue(new Callback<StatementModel>() {
            @Override
            public void onResponse(Call<StatementModel> call, Response<StatementModel> response) {

                Log.d("", "");
                if (response.body() != null) {
                    StatementModel model = response.body();

                    if (filter.isEmpty()) {
                        mByDateData.setValue(model);
                    } else {
                        switch (filter) {
                            case "today":
                                mTodayData.setValue(model);
                                break;
                            case "weekly":
                                mWeekData.setValue(model);
                                break;
                            case "monthly":
                                mMonthlyData.setValue(model);
                                break;
                        }
                    }

                } else {

                    try {
                        JSONObject errObj = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<StatementModel> call, Throwable t) {

            }
        });
    }


    public void getMerchantBreakdown(PrefsManager manager, String filter, String fromDate, String toDate) {

        Call<BreakDownModel> call = new ApiManagerImp().merchantPayBreakDown(manager.getUserToken(),
                filter, fromDate, toDate);
        call.enqueue(new Callback<BreakDownModel>() {
            @Override
            public void onResponse(Call<BreakDownModel> call, Response<BreakDownModel> response) {


                Log.d("", "");
                if (response.body() != null) {
                    BreakDownModel model = response.body();

                    if (filter.isEmpty()) {
                        mByDateDataMerchant.setValue(model);
                    } else {
                        switch (filter) {
                            case "today":
                                mTodayDataMerchant.setValue(model);
                                break;
                            case "weekly":
                                mWeekDataMerchant.setValue(model);
                                break;
                            case "monthly":
                                mMonthlyDataMerchant.setValue(model);
                                break;
                        }
                    }

                } else {

                    try {
                        JSONObject errObj = new JSONObject(response.errorBody().string());
                        Log.d("", "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<BreakDownModel> call, Throwable t) {

            }
        });
    }
}
