package com.drivill.courier.merchantModul.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.activity.supportActivity.SupportActivity;
import com.drivill.courier.merchantModul.activity.CompletedDeliveryActivity;
import com.drivill.courier.merchantModul.activity.OneItemDetailActivity;
import com.drivill.courier.merchantModul.activity.SelectHubActivity;
import com.drivill.courier.merchantModul.activity.SentActivity;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.rest.ApiManagerImp;
import com.drivill.courier.utils.AppUtil;
import com.drivill.courier.utils.Constant;
import com.drivill.courier.utils.PrefsManager;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.LongFunction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.drivill.courier.utils.MyUtil._getStatus;

public class RecentDeliveryAdapter extends RecyclerView.Adapter<RecentDeliveryAdapter.MyStatementHolder> {
    Context mContext;
    ArrayList<ShipmentModel> dataArr = new ArrayList<>();
    String tag;
    PopupWindow myPopupWindow;
    LinearLayout mReadyLL, mCancelLL, mSupportLL;
    RecentAdapterClick mListener;
    private PrefsManager mBasePreferenceManager;
    private Dialog dialogbox;

    public RecentDeliveryAdapter(Context context, String tag, RecentAdapterClick listener) {
        mContext = context;
        this.tag = tag;
        mListener = listener;
        setPopUpWindow();
      //  Log.i("arp","constructor-tag= "+tag);
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (tag.equals("home") || tag.equals("All")) {
            view = LayoutInflater.from(mContext).inflate(R.layout.recycler_recent_item, parent, false);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.recycler_detail_item, parent, false);
        }
        return new MyStatementHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {

        Log.e("dsabhjgashdghasd","dsgahgdhgsad"+tag);
        mBasePreferenceManager = new PrefsManager(mContext);
        if (dataArr != null && dataArr.size() != 0) {
           ShipmentModel  model = dataArr.get(position);
            if (model != null) {
                if (tag.equals("home") || tag.equals("All")) {
                    Glide.with(mContext).load(((BaseActivity) mContext).getBusinessLogo()).into(holder.itemImg);
                     holder.tv_shipmentstatus.setText(_getStatus(mContext,model.getStatus()));

                     if(model.getStatus() == 8 || model.getStatus() == 11 || model.getStatus() == 12){
                            holder.tv_shipmentstatus.setTextColor(mContext.getResources().getColor(R.color.red));
                        }else {
                            holder.tv_shipmentstatus.setTextColor(mContext.getResources().getColor(R.color.theme_color));
                        }
                }

                if (model.getShipmentNo() != null)
                    holder.orderIdTxt.setText(model.getShipmentNo());
                else
                    holder.orderIdTxt.setText("");

                holder.dateTxt.setText(model.getCreatedDate());
                if (dataArr.get(position).getReceiverName() != null)
                    holder.deliverPlaceName.setText(dataArr.get(position).getReceiverName());

                if (model.getCodAmount() != null)
                    holder.priceItem.setText(model.getCodAmount());
                else holder.priceItem.setText("0");
                if (model.getdAddress() != null)
                    holder.businessName.setText(model.getdAddress());

                if (model.getStatus()!=null){
                    if (model.getStatus()==6||model.getStatus()==7){
                        holder.helpImgBtn.setVisibility(View.VISIBLE);
                    }else {
                        holder.helpImgBtn.setVisibility(View.GONE);
                    }

                }else {holder.helpImgBtn.setVisibility(View.GONE);}
                holder.helpImgBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Intent intent = new Intent(mContext, SupportActivity.class);
                        //  mContext.startActivity(intent);
                        mListener.adapterClick(model.getId().toString());
                    }
                });
                holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent;
                        if (tag.equals("home") || tag.equals(Constant.DELIVERED)) {
                            intent = new Intent(mContext, CompletedDeliveryActivity.class);
                            intent.putExtra("pos", model.getId().toString());
                        } else {
                            intent = new Intent(mContext, OneItemDetailActivity.class);
                            intent.putExtra("from", Constant.ViewOnly);
                            intent.putExtra("data", (Serializable) model);
                        }
                        mContext.startActivity(intent);
                    }
                });

                if (tag.equals(Constant.SCHEDULED) || tag.equals(Constant.CURRENT)) {
                   // holder.shaduledDate.setText(model.getPickupDate());
                    holder.drop_scheduleImgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                          myPopupWindow.showAsDropDown(view, -153, 0);
                            popupClickEvent(view, model);

                        }
                    });
                } else if (tag.equals(Constant.CANCELLED)) {

                    if (model.getStatus() == 8) {
                        holder.canceledByTxt.setTextColor(mContext.getResources().getColor(R.color.red));
                        holder.cancelTxt.setTextColor(mContext.getResources().getColor(R.color.red));
                        if (model.getCancelBy() != null && model.getCancelBy().size() != 0)
                            holder.cancelTxt.setText(model.getCancelBy().get(0).getUpdatedBy());
                    } /*else {
                        holder.canceledByTxt.setTextColor(mContext.getResources().getColor(R.color.black));
                        holder.cancelTxt.setTextColor(mContext.getResources().getColor(R.color.black));
                        holder.cancelTxt.setText("Me");
                    }*/
                } else if (tag.equals("All")) {

                }
            }
        }


    }

    @Override
    public int getItemCount() {
   /*     if (dataArr != null)
            return dataArr.size();
        else return 0;*/
        return dataArr.size();
    }


    class MyStatementHolder extends RecyclerView.ViewHolder {
        TextView orderIdTxt, businessName, dateTxt, deliverPlaceName, priceItem, deliveryAddressTxt,
                shaduledDate, deliverdTxt , tv_shipmentstatus,
                cancelTxt, canceledByTxt;
        LinearLayout statusLL, canceledLL;
        RelativeLayout parentLayout;
        ImageView drop_scheduleImgBtn, helpImgBtn, itemImg;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            orderIdTxt = itemView.findViewById(R.id.id_txt);
            businessName = itemView.findViewById(R.id.deliveryAddressTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            deliverPlaceName = itemView.findViewById(R.id.deliverPlaceName);
            parentLayout = itemView.findViewById(R.id.parentLayout);
            priceItem = itemView.findViewById(R.id.priceItem);
            helpImgBtn = itemView.findViewById(R.id.helpImgBtn);

            if (tag.equals("home") || tag.equals("All")) {
                itemImg = itemView.findViewById(R.id.itemImg);

                tv_shipmentstatus = itemView.findViewById(R.id.tv_shipmentstatus);
                tv_shipmentstatus.setVisibility(View.VISIBLE);
            }

            if (!tag.equals("home")) {
                statusLL = itemView.findViewById(R.id.statusLL);
                canceledLL = itemView.findViewById(R.id.cancelLL);
                deliverdTxt = itemView.findViewById(R.id.deliverdTxt);
                shaduledDate = itemView.findViewById(R.id.shaduledDate);
                canceledByTxt = itemView.findViewById(R.id.canceledByTxt);
                cancelTxt = itemView.findViewById(R.id.cancelTxt);
                drop_scheduleImgBtn = itemView.findViewById(R.id.drop_scheduleImgBtn);

                switch (tag) {
                    case Constant.SHIPPED:
                        statusLL.setVisibility(View.GONE);
                        break;
                    case Constant.SCHEDULED:
                    case Constant.CURRENT:
                        statusLL.setVisibility(View.VISIBLE);
                        canceledLL.setVisibility(View.GONE);
                        deliverdTxt.setVisibility(View.GONE);
                        shaduledDate.setVisibility(View.GONE);
                        break;
                    case Constant.DELIVERED:
                        statusLL.setVisibility(View.VISIBLE);
                        canceledLL.setVisibility(View.GONE);
                        drop_scheduleImgBtn.setVisibility(View.GONE);
                        shaduledDate.setVisibility(View.GONE);
                        deliverdTxt.setVisibility(View.VISIBLE);
                        break;
                    case Constant.CANCELLED:
                        statusLL.setVisibility(View.VISIBLE);
                        canceledLL.setVisibility(View.VISIBLE);
                        drop_scheduleImgBtn.setVisibility(View.GONE);
                        shaduledDate.setVisibility(View.GONE);
                        deliverdTxt.setVisibility(View.GONE);
                        break;
                }
            }

        }
    }

    public void setData(ArrayList<ShipmentModel> dataList) {
        this.dataArr = dataList;
        notifyDataSetChanged();
    }


  /*  private void showStatusPopup( Point p) {

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) mContext.findViewById(R.id.llStatusChangePopup);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.status_popup_layout, null);

        // Creating the PopupWindow
        changeStatusPopUp = new PopupWindow(context);
        changeStatusPopUp.setContentView(layout);
        changeStatusPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeStatusPopUp.setFocusable(true);

        // Some offset to align the popup a bit to the left, and a bit down, relative to button's position.
        int OFFSET_X = -20;
        int OFFSET_Y = 50;

        //Clear the default translucent background
        changeStatusPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        changeStatusPopUp.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y + OFFSET_Y);
    }*/

    public interface RecentAdapterClick {
        void adapterClick(String shipID);
    }

    public void updateList(ArrayList<ShipmentModel> list) {
        this.dataArr = list;
        notifyDataSetChanged();
    }

    private void setPopUpWindow() {
    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.popup_menu_layout, null);

    mReadyLL = (LinearLayout) view.findViewById(R.id.readyLL);
    mCancelLL = (LinearLayout) view.findViewById(R.id.cancelLL);
    mSupportLL = (LinearLayout) view.findViewById(R.id.supportLL);
    myPopupWindow = new PopupWindow(view, 400, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
    }

    void popupClickEvent(View view2, ShipmentModel model) {
      //  Log.i("arp","status== "+model.getStatus());
        if(model.getStatus()==0){
            mReadyLL.setVisibility(View.VISIBLE);
        }else {
            mReadyLL.setVisibility(View.GONE);
        }

        mReadyLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(mContext, SelectHubActivity.class);
                intent.putExtra("data", model);
                mContext.startActivity(intent);*/
                sendToPickup(model);
                myPopupWindow.dismiss();

            }
        });
        mCancelLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", model);
                Navigation.findNavController(view2).navigate(R.id.action_scheduledFragment_to_cancellationFragment, bundle);
                myPopupWindow.dismiss();
            }
        });
        mSupportLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SupportActivity.class);
                mContext.startActivity(intent);
                myPopupWindow.dismiss();
            }
        });
    }

    void sendToPickup(ShipmentModel model) {
        showLoading();

        HashMap<String, String> map = new HashMap<>();

        String value = model.getId().toString();
        map.put("id[" + 0 + "]", value);

        Call<JsonObject> call = new ApiManagerImp().send_to_pickup(mBasePreferenceManager.getUserToken(), map);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hideLoading();
                if (response.body() != null) {

                    Intent intent = new Intent(mContext, SentActivity.class);
                    mContext.startActivity(intent);
                } else {
                    try {
                        JSONObject object = new JSONObject(response.errorBody().string());
                       // onError(object.getString("error"));
                        Toast.makeText(mContext, object.getString("error"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hideLoading();
                Log.d("err", String.valueOf(t));
                Toast.makeText(mContext, R.string.try_again, Toast.LENGTH_SHORT).show();
               // onError(getString(R.string.try_again));
            }
        });
    }

    private void showLoading() {
        dialogbox = new Dialog(mContext);
        dialogbox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.setCancelable(false);
        dialogbox.setContentView(R.layout.dialog_progress_layout);
        dialogbox.show();
    }

    public void hideLoading() {
        if (dialogbox != null && dialogbox.isShowing()) {
            dialogbox.dismiss();
            // Log.e("dismiss","true");
        } else {
             Log.e("dismiss","false");
        }
    }
}
