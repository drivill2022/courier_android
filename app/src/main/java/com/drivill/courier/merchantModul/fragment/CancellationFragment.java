package com.drivill.courier.merchantModul.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.drivill.courier.utils.PrefsManager;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.FragmentCancellationBinding;
import com.drivill.courier.interfaces.YesNoInterface;
import com.drivill.courier.merchantModul.interfases.FragmentListener;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CancellationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CancellationFragment extends Fragment implements YesNoInterface {
    FragmentListener mListener;
    FragmentCancellationBinding mBinding;
    YesNoInterface mDialogListener;
    String reason = "";
    PrefsManager manager;
    JSONArray jsonArray;
    private ShipmentModel mData;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "data";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    void initUI() {
        //  mListener = (FragmentListener) requireActivity();
        mDialogListener = this;
        // mListener.changeToolTitle(getString(R.string.cancellation));
        if (((BaseActivity) requireActivity()).mBasePreferenceManager.getIsRider())
            getCancelReasonListRider("2");
        else
            getCancelReasonList("4");
        mBinding.listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("tg", String.valueOf(i));
                try {

                    String from = "Mer" ;
                    if(manager.getIsRider()){
                      from = getContext().getResources().getString(R.string.rider);
                    }
                    reason = jsonArray.getJSONObject(i).getString("title");
                    AppUtil.yesNoDialog(requireActivity(), reason, from ,mDialogListener);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public CancellationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CancellationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CancellationFragment newInstance(String param1, String param2) {
        CancellationFragment fragment = new CancellationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = new PrefsManager(requireContext());
        if (getArguments() != null) {
            mData = (ShipmentModel) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancellation, container, false);
        mBinding = FragmentCancellationBinding.bind(view);
        initUI();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void isNoYes(boolean b) {
        if (b) {
            if (((BaseActivity) requireActivity()).mBasePreferenceManager.getIsRider())
                cancelShipingRider(mData.getId().toString(), reason);
            else
                cancelShiping(mData.getId().toString(), reason);
        }
    }


    // Merchant
    void cancelShiping(String id, String reason) {
        ((BaseActivity) requireActivity()).showLoading();
        Call<ShipmentCreateModel> call = new ApiManagerImp().shipmentCancel(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), id, reason);
        call.enqueue(new Callback<ShipmentCreateModel>() {
            @Override
            public void onResponse(Call<ShipmentCreateModel> call, Response<ShipmentCreateModel> response) {
                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                   // ((BaseActivity) requireActivity()).showMessage(response.body().getMessage());
                    Navigation.findNavController(requireActivity(), R.id.listTxt).navigate(R.id.action_cancellationFragment_to_mechantHomeFragment);

                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        ((BaseActivity) requireActivity()).onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShipmentCreateModel> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
                ((BaseActivity) requireActivity()).onError(getString(R.string.try_again));
            }
        });
    }

    void cancelShipingRider(String id, String reason) {
        ((BaseActivity) requireActivity()).showLoading();
        Call<ShipmentCreateModel> call = new ApiManagerImp().riderShipmentCancel(((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), id, reason);
        call.enqueue(new Callback<ShipmentCreateModel>() {
            @Override
            public void onResponse(Call<ShipmentCreateModel> call, Response<ShipmentCreateModel> response) {
                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                    ((BaseActivity) requireActivity()).showMessage(response.body().getMessage());
                    Navigation.findNavController(requireActivity(), R.id.listTxt).navigate(R.id.action_cancellationFragment2_to_homeFragment);

                } else if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        ((BaseActivity) requireActivity()).onError(object.getString("error"));
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ShipmentCreateModel> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
                ((BaseActivity) requireActivity()).onError(getString(R.string.try_again));
            }
        });
    }

    void getCancelReasonList(String reasonFor) {
        ((BaseActivity) requireActivity()).showLoading();
        Call<JsonObject> call = new ApiManagerImp().merchantShippingCancelReasonList(
                ((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), reasonFor
        );

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("res", String.valueOf(response));
                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        if (object.has("cancel_reasons")) {
                            jsonArray = object.getJSONArray("cancel_reasons");
                            ArrayList<String> reasonList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                String title = jsonArray.getJSONObject(i).getString("title");
                                reasonList.add(title);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),
                                    R.layout.list_item_layout, R.id.listTxt, reasonList);
                            mBinding.listItem.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
            }
        });
    }

    void getCancelReasonListRider(String reasonFor) {
        ((BaseActivity) requireActivity()).showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderShippingCancelReasonList(
                ((BaseActivity) requireActivity()).mBasePreferenceManager.getUserToken(), reasonFor
        );

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("res", String.valueOf(response));
                ((BaseActivity) requireActivity()).hideLoading();
                if (response.body() != null) {
                    try {

                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        if (object.has("cancel_reasons")) {
                            jsonArray = object.getJSONArray("cancel_reasons");
                            ArrayList<String> reasonList = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {

                                String title = jsonArray.getJSONObject(i).getString("title");
                                if (mData.getStatus() == Integer.parseInt(Constant.ON_GOING))
                                    reasonList.add(title);
                                else {
                                    if (!title.equalsIgnoreCase("customer not available")){
                                        reasonList.add(title);
                                    }
                                }
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(requireContext(),
                                    R.layout.list_item_layout, R.id.listTxt, reasonList);
                            mBinding.listItem.setAdapter(adapter);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                ((BaseActivity) requireActivity()).hideLoading();
            }
        });
    }
}