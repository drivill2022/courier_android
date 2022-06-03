package com.drivill.courier.model.activityModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.JsonObject;
import com.drivill.courier.model.model.SignUpModel;

public class SignUpActivityViewModel extends ViewModel {
    public final MutableLiveData<JsonObject> success_validator = new MutableLiveData<>();
    public final MutableLiveData<Integer> validator = new MutableLiveData<>();
    public final MutableLiveData<SignUpModel> mDataModel = new MutableLiveData<>();

}
