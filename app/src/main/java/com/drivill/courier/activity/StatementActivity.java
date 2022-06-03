package com.drivill.courier.activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drivill.courier.adapter.CodeStatementAdapter;
import com.google.gson.JsonObject;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityStatementBinding;
import com.drivill.courier.fragment.CodeStatementFragment;
import com.drivill.courier.fragment.RidingFragment;
import com.drivill.courier.merchantModul.activity.PaymentHistoryActivity;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.DataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatementActivity extends BaseActivity {

    ActivityStatementBinding mBinding;
    private boolean isSwitched = true;

    public static TextView amountTxt, amountTxtDeposit;
    public int mTotalPage, currentPage = 1;
    public static int perPageCount = 0;

    void initUI() {
        amountTxt = findViewById(R.id.amountTxt);
        amountTxtDeposit = findViewById(R.id.amountTxtDeposit);
        SimpleDateFormat curFormater = new SimpleDateFormat("dd-MMM-yyyy");
        Date c = Calendar.getInstance().getTime();
        String dateObj = curFormater.format(c);
        mBinding.dateTxt.setText(dateObj);
        mBinding.dateTxtCode.setText(dateObj);

        if (mBasePreferenceManager.getIsRider()) {
            mBinding.payHistTxtBtn.setVisibility(View.GONE);
            mBinding.paymentHistoryBtnTxt.setVisibility(View.GONE);
        }

        mBinding.ridingTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSwitched) {
                    isSwitched = true;
                    switchFragment(new RidingFragment());
                    mBinding.ridingTxt.setBackground(getResources().getDrawable(R.drawable.backround_dark_gray));
                    mBinding.codeTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    mBinding.codeTxt.setTextColor(getResources().getColor(R.color.gary));
                    mBinding.ridingTxt.setTextColor(getResources().getColor(R.color.white));
                    mBinding.constraintLayout2.transitionToStart();

                }
            }
        });
        mBinding.codeTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSwitched) {
                    switchFragment(new CodeStatementFragment());
                    mBinding.codeTxt.setBackground(getResources().getDrawable(R.drawable.backround_dark_gray));
                    mBinding.ridingTxt.setTextColor(getResources().getColor(R.color.gary));
                    mBinding.codeTxt.setTextColor(getResources().getColor(R.color.white));
                    mBinding.ridingTxt.setBackground(getResources().getDrawable(R.drawable.backround_input));
                    isSwitched = false;

                    mBinding.constraintLayout2.transitionToEnd();
                }
            }
        });

        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBinding.paymentHistoryBtnTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatementActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);
            }
        });
        mBinding.payHistTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatementActivity.this, PaymentHistoryActivity.class);
                startActivity(intent);
            }
        });

    /*    mBinding.depositTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });*/
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_statement);
        initUI();
        switchFragment(new RidingFragment());
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                getCoToDeposit(String.valueOf(currentPage), false);
            }
        });
    }

    void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment, fragment.toString());
        //  fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public static void setCollectedCOD(String str) {
        amountTxt.setText(str);
    }

    public static void setDepositCOD(String str) {
        amountTxtDeposit.setText(str);
    }

    void showAlertDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_amount_layout);


        TextView title, okTxtBtn, cancelBtn;
        EditText amountET;
        okTxtBtn = (TextView) dialog.findViewById(R.id.okTxt);
        cancelBtn = (TextView) dialog.findViewById(R.id.cancelTxt);
        title = (TextView) dialog.findViewById(R.id.titleTxt);
        amountET = (EditText) dialog.findViewById(R.id.amountET);

        String riderAmount, merchantAmount;
        if (mBasePreferenceManager.getIsRider()) {
            title.setText("Enter Your Deposit Amount");

        } else {
            title.setText("Enter Your withdrawal Amount");
        }


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amountET.getText().toString().isEmpty()) {
                    Toast.makeText(StatementActivity.this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                } else if (amountET.getText().toString().equals("0")) {
                    Toast.makeText(StatementActivity.this, "Amount not 0", Toast.LENGTH_SHORT).show();

                } else {
                    depositAmountApi(amountET.getText().toString());
                    dialog.dismiss();
                }
            }
        });
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    CodeStatementAdapter mStatementAdapter;
    public void loadMoreData(CodeStatementAdapter mStatementAdapter) {
        if (mTotalPage != 0 && currentPage <= mTotalPage) {
            currentPage++;
            this.getCoToDeposit(String.valueOf(currentPage), true);
        }

        this.mStatementAdapter = mStatementAdapter;
    }


    void getCoToDeposit(String page, boolean isLoading) {
        Call<CODStatementModel> call = new ApiManagerImp().getRider_COD_statement(mBasePreferenceManager.getUserToken(), page);
        call.enqueue(new Callback<CODStatementModel>() {
            @Override
            public void onResponse(Call<CODStatementModel> call, Response<CODStatementModel> response) {
                if (response.body() != null) {
                    CODStatementModel model = response.body();
                    setDepositCOD(String.valueOf(model.getCodToDeposit()));
                    mBinding.drivillCommisionTxt.setText("Tk." + model.getDrivillsCommission());

                    if (isLoading) {
                        CODStatementModel liveData = DataManager.getINSTANCE().getDeliveredData().getValue();
                        if (liveData != null) {
                            liveData.getHistory().getData().addAll(model.getHistory().getData());
                            DataManager.getINSTANCE().getDeliveredData().setValue(liveData);
                        }
                        if (mStatementAdapter != null) {
                            mStatementAdapter.setData(DataManager.getINSTANCE().getDeliveredData().getValue());
                        }

                       // Log.i("mydebug-", "getItemCount=>  "+Objects.requireNonNull(mStatementAdapter).getItemCount()+"");

                    } else {
                        DataManager.getINSTANCE().getDeliveredData().setValue(model);
                        mTotalPage = model.getHistory().getTotal();
                        perPageCount = model.getHistory().getPerPage();
                    }
                  //   Log.i("mydebug-",mTotalPage+"\n"+perPageCount+"\n"+page);


                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CODStatementModel> call, Throwable t) {

            }
        });
    }

    void depositAmountApi(String amount) {
        showLoading();
        Call<JsonObject> call = new ApiManagerImp().riderDepositAmount(mBasePreferenceManager.getUserToken(),
                amount);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                Log.d("res", String.valueOf(response));
                if (response.body() != null)
                    try {
                        JSONObject object = new JSONObject(String.valueOf(response.body()));
                        Toast.makeText(StatementActivity.this, object.getString("message"), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                if (response.errorBody() != null) {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        Toast.makeText(StatementActivity.this, object.getString("error"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                Log.d("res", String.valueOf(t));
            }
        });
    }

}