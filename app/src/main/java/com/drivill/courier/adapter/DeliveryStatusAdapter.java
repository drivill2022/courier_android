package com.drivill.courier.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.MainActivity;
import com.drivill.courier.R;
import com.drivill.courier.databinding.RecyclerDeliveryStatusItemBinding;
import com.drivill.courier.merchantModul.fragment.TrackingFragment;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.merchantModul.model.TrackingModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryStatusAdapter extends RecyclerView.Adapter<DeliveryStatusAdapter.ViewHolder> {
    Context mContext;
    private static final int REQUEST = 112;
    ArrayList<ShipmentModel> data;
    public PrefsManager mBasePreferenceManager;

    public DeliveryStatusAdapter(Context context, ArrayList<ShipmentModel> dataArr) {
        this.mContext=context;
        this.data=dataArr;
        Log.e("shggdfsahgdfhas","fsdfghdgfh"+dataArr);
        mBasePreferenceManager = new PrefsManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerDeliveryStatusItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.recycler_delivery_status_item,parent,false);
        //View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_delivery_status_item, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(data.get(position).getStatusLog().size() == 0){
        } else {


            holder.bind(data.get(position));
            Log.e("dsabdasbds", "dsadbasdj" + data.get(position).getStatus().toString());

            switch (data.get(position).getStatus()) {
                case 1:
                    holder.mBinding.titleTxt.setText("Requested");
                    holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_blue));
                    holder.mBinding.titleTxt.setTextColor(Color.parseColor("#131BFE"));
                    break;
                case 2:
                    holder.mBinding.titleTxt.setText("Assigned");
                    holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_green));
                    holder.mBinding.titleTxt.setTextColor(Color.parseColor("#669E1E"));
                    break;
                case 3:
                    holder.mBinding.titleTxt.setText("Picked Up");
                    holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_orange));
                    holder.mBinding.titleTxt.setTextColor(Color.parseColor("#FE7813"));
                    break;
                case 4:

                case 12:
                    holder.mBinding.titleTxt.setText("In Transit");
                    holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_purple));
                    holder.mBinding.titleTxt.setTextColor(Color.parseColor("#3C42DD"));
                    break;

                case 5:
                    if (Integer.parseInt(data.get(position).getReturnStatus()) == 1) {
                        holder.mBinding.titleTxt.setText("Return Processing");
                        holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_grey));
                        holder.mBinding.titleTxt.setTextColor(Color.parseColor("#242134"));
                        holder.mBinding.titleTxt.setPadding(14, 10, 14, 10);

                    } else {
                        holder.mBinding.titleTxt.setText("On The Way");
                        holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_sky));
                        holder.mBinding.titleTxt.setTextColor(Color.parseColor("#3CA9DE"));

                    }
                    break;

                case 6:
                    if (Integer.parseInt(data.get(position).getReturnStatus()) == 2) {
                        holder.mBinding.titleTxt.setText("Returned");
                        holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_grey));
                        holder.mBinding.titleTxt.setTextColor(Color.parseColor("#242134"));
                    } else {
                        holder.mBinding.titleTxt.setText("Delivered");
                        holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_green_new));
                        holder.mBinding.titleTxt.setTextColor(Color.parseColor("#04A317"));
                        holder.mBinding.titleTxt.setPadding(14, 10, 14, 10);

                    }
                    break;

                case 7:
                    holder.mBinding.titleTxt.setText("Delivered");
                    holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_green_new));
                    holder.mBinding.titleTxt.setTextColor(Color.parseColor("#04A317"));
                    holder.mBinding.titleTxt.setPadding(14, 10, 14, 10);
                    break;

                case 8:
                    holder.mBinding.titleTxt.setText("Return Processing");
                    holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_grey));
                    holder.mBinding.titleTxt.setTextColor(Color.parseColor("#242134"));
                    holder.mBinding.titleTxt.setPadding(14, 10, 14, 10);
                    holder.mBinding.btnDelete.setVisibility(View.GONE);
                    break;

         /*   case 9:
                holder.mBinding.titleTxt.setText("Rejected");
                holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_sky));
                holder.mBinding.titleTxt.setTextColor(Color.parseColor("#F0394A"));
                break;

          */
//            case 10:
//                holder.mBinding.titleTxt.setText("Failed");
//                holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_sky));
//                holder.mBinding.titleTxt.setTextColor(Color.parseColor("#F0394A"));
//                holder.mBinding.titleTxt.setPadding(14, 10, 14, 10);
//                break;
         /*   case 11:
                holder.mBinding.titleTxt.setText("Cancelled");
                holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_blue));
                holder.mBinding.titleTxt.setTextColor(Color.parseColor("#131BFE"));
                break;
            case 12:
                holder.mBinding.titleTxt.setText("Cancelled");
                holder.mBinding.titleTxt.setBackground(mContext.getDrawable(R.drawable.theme_background_button_round_blue));
                holder.mBinding.titleTxt.setTextColor(Color.parseColor("#131BFE"));
                break;*/

            }

       /* if (data.get(position).getStatus().toString().equals("6") || data.get(position).getStatus().toString().equals("7") ){
            holder.mBinding.titleDelivered.setVisibility(View.VISIBLE);
            holder.mBinding.paymentDeposited.setVisibility(View.VISIBLE);
        }
        else if (data.get(position).getStatus().toString().equals("1") ){
            holder.mBinding.titleTxtRequested.setVisibility(View.VISIBLE);
            holder.mBinding.btnEdit.setVisibility(View.VISIBLE);
            holder.mBinding.deelte.setVisibility(View.VISIBLE);
        }
        else if (data.get(position).getStatus().toString().equals("4") || data.get(position).getStatus().toString().equals("12") ){
            holder.mBinding.titletransit.setVisibility(View.VISIBLE);
        }
        else if(data.get(position).getStatus().toString().equals("5") || data.get(position).getStatus().toString().equals("10")){
            holder.mBinding.titleontheway.setVisibility(View.VISIBLE);
            holder.mBinding.titleDelivered.setVisibility(View.GONE);
            holder.mBinding.btnTrack.setVisibility(View.VISIBLE);
            holder.mBinding.btnCall.setVisibility(View.VISIBLE);
            holder.mBinding.failedTC.setVisibility(View.VISIBLE);
            holder.mBinding.view.setVisibility(View.VISIBLE);
            holder.mBinding.btnTrackk.setVisibility(View.GONE);
        }



        else if(data.get(position).getStatus().toString().equals("8")){
            holder.mBinding.titleTxtFailed.setVisibility(View.VISIBLE);
            holder.mBinding.titleDelivered.setVisibility(View.GONE);
            holder.mBinding.btnTrackk.setVisibility(View.GONE);
            holder.mBinding.btnTrack.setVisibility(View.VISIBLE);
            holder.mBinding.btnCall.setVisibility(View.VISIBLE);
            holder.mBinding.failedTC.setVisibility(View.VISIBLE);
            holder.mBinding.view.setVisibility(View.VISIBLE);
            holder.mBinding.btnDelete.setVisibility(View.VISIBLE);
        }
        else if(data.get(position).getStatus().toString().equals("9")){
            holder.mBinding.titlereturnprocessing.setVisibility(View.VISIBLE);
        }
*/

//            holder.mBinding.tvDeliveryCharges.setText(data.get(position).getShipmentCost().toString());
            holder.mBinding.btnTrack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fetchTruckLocation("DC" + data.get(position).getId());
                }
            });

            holder.mBinding.btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE};
                        if (!hasPermissions(mContext, PERMISSIONS)) {
                            ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
                        } else {
                            makeCall();
                        }
                    } else {
                        makeCall();
                    }
                }

                private void makeCall() {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + data.get(position).getContactNo().toString()));
                    mContext.startActivity(intent);

                }
            });

            holder.mBinding.btnTrackk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fetchTruckLocation("DC" + data.get(position).getId());
                }
            });

            holder.mBinding.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "Working On", Toast.LENGTH_SHORT).show();
                }
            });

            holder.mBinding.deelte.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteFun();
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        Log.e("rfdg","sdgdgd"+data.size());
        return data.size();
    }

      class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerDeliveryStatusItemBinding mBinding;

          public void bind(ShipmentModel data){
              mBinding.setData(data);

          }


        public ViewHolder(@NonNull RecyclerDeliveryStatusItemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;


        }

    }



    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private void fetchTruckLocation(String num) {
        if(!num.startsWith("#")){
            num =  "#"+num;
        }
        Log.i("arp","TrackingID= "+num);
        Call<TrackingModel> call = new ApiManagerImp().getShipmentTracking(mBasePreferenceManager.getUserToken(), num);
        call.enqueue(new Callback<TrackingModel>() {
            @Override
            public void onResponse(Call<TrackingModel> call, Response<TrackingModel> response) {
                TrackingModel res =  response.body();
                if (res != null && res.getData().getStatusLogs()!=null) {
                        Log.i("res=", new Gson().toJson(res));
                        TrackingModel model = response.body();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", (Serializable) model);
                        TrackingFragment fragment = new TrackingFragment();
                        fragment.setArguments(bundle);
                        switchFragment(fragment);
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                        //onError(object.getString("error"));
                        Log.i("res=", "error=>  "+object.toString());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<TrackingModel> call, Throwable t) {
                Log.i("res", String.valueOf(t));
                //onError(getString(R.string.try_again));
            }
        });

    }
    public void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager =  ((AppCompatActivity)mContext).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout123, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void deleteFun(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
        builder1.setMessage("Are you sure you want to delete?");
        builder1.setCancelable(true);

        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
