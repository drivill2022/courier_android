package com.drivill.courier.activity.supportActivity;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.drivill.courier.model.model.SupportModel;
import com.drivill.courier.rest.ApiManagerImp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportViewModel extends ViewModel {
    public MutableLiveData<SupportModel> responseObserver = new MutableLiveData<>();

    public SupportViewModel() {
        getSupport();
    }

    private void getSupport() {
        Call<SupportModel> call = new ApiManagerImp().getRideSupport();
        call.enqueue(new Callback<SupportModel>() {
            @Override
            public void onResponse(Call<SupportModel> call, Response<SupportModel> response) {
                if (response.body() != null) {
                    SupportModel model = response.body();
                    responseObserver.setValue(model);
                    Log.d("res", String.valueOf(response.body()));

                }
            }

            @Override
            public void onFailure(Call<SupportModel> call, Throwable t) {

            }
        });
    }
}
