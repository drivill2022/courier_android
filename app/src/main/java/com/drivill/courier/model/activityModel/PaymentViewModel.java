package com.drivill.courier.model.activityModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.drivill.courier.merchantModul.model.PaymentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.PrefsManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentViewModel extends ViewModel {
    public MutableLiveData<PaymentModel> paymentHistory = new MutableLiveData<>();

    public void getRiderPayHistory(PrefsManager manager) {
        Call<JsonObject> call = new ApiManagerImp().riderPaymentHistory(manager.getUserToken());
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        JSONArray array = object.getJSONObject("history").getJSONArray("data");
                        Log.d("res", String.valueOf(object));
                        PaymentModel model = new PaymentModel();

                        ArrayList<PaymentModel.History> list1 = new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            PaymentModel.History list = new PaymentModel().new History();
                            list.setShipmentNo(array.getJSONObject(i).getString("shipment_no"));
                            list.setReceiverName(array.getJSONObject(i).getString("paid_by"));
                            list.setCodAmount(array.getJSONObject(i).getInt("cod_amount"));
                            list.setDate(array.getJSONObject(i).getString("date"));
                            list.setPaymentMethod(array.getJSONObject(i).getString("payment_method"));
                            list1.add(list);

                        }
                        model.setMessage(object.getString("message"));
                        model.setHistory(list1);

                        paymentHistory.setValue(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject errorObj = new JSONObject(String.valueOf(response.errorBody()));
                        Log.d("res", String.valueOf(errorObj));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

    public void getMerchantPayHistory(PrefsManager manager, String page) {
        Call<JsonObject> call = new ApiManagerImp().merchantPaymentHistory(manager.getUserToken(), page);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        JSONArray array =  object.getJSONObject("history").getJSONArray("data");
                        PaymentModel model = new PaymentModel();

                        ArrayList<PaymentModel.History> list1 = new ArrayList<>();
                        for (int i = 0; i < array.length(); i++) {
                            PaymentModel.History list = new PaymentModel().new History();
                            list.setShipmentNo(array.getJSONObject(i).getString("shipment_no"));
                            list.setReceiverName(array.getJSONObject(i).getString("paid_by"));
                            list.setCodAmount(array.getJSONObject(i).getInt("cod_amount"));
                            list.setDate(array.getJSONObject(i).getString("date"));
                            list.setId(array.getJSONObject(i).getInt("id"));
                            list.setPaymentMethod(array.getJSONObject(i).getString("payment_method"));
                            list1.add(list);

                        }
                        model.setMessage(object.getString("message"));
                        model.setHistory(list1);
                        Log.i("arp","MerchantPayHistory= "+new Gson().toJson(model));

                        paymentHistory.setValue(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        JSONObject errorObj = new JSONObject(String.valueOf(response.errorBody()));
                        Log.d("res", String.valueOf(errorObj));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                 Log.i("arp",t.toString());
            }
        });
    }

}
