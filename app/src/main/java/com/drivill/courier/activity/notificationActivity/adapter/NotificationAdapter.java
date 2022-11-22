package com.drivill.courier.activity.notificationActivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.drivill.courier.R;
import com.drivill.courier.databinding.ListNotificationItemBinding;
import com.drivill.courier.merchantModul.model.MerchantNotificationModel;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyStatementHolder> {
    Context mContext;
    MerchantNotificationModel mModel;
    NotificationAdapterClick mListener;

    public NotificationAdapter(Context context, MerchantNotificationModel model, NotificationAdapterClick mListener) {
        mContext = context;
        mModel = model;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_notification_item, parent, false);
        ListNotificationItemBinding binding = DataBindingUtil.bind(view);
        return new MyStatementHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, int position) {
        holder.binding.setViewModel(new AdapterViewModel(mContext, mModel.getData().get(position)));
        // holder.binding.


       /* if (mModel.getData().get(position).getIsViewed().equals("0")) {
            holder.binding.llNotification.setBackground(mContext.getResources().getDrawable(R.drawable.backround_blue_transe));
        } else {
            holder.binding.llNotification.setBackground(mContext.getResources().getDrawable(R.drawable.backround_gray));
        }*/
        holder.binding.llNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mModel.getData().get(position).getIsViewed().equals("0")) {
                    mListener.notificationAdapterClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mModel != null && mModel.getData() != null)
            return mModel.getData().size();
        else return 0;
    }

    class MyStatementHolder extends RecyclerView.ViewHolder {
        ListNotificationItemBinding binding;

        public MyStatementHolder(ListNotificationItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

    public interface NotificationAdapterClick {
        void notificationAdapterClick(int id);
    }

    public void statusUpdated(int pos) {
        mModel.getData().get(pos).setIsViewed("1");
       notifyItemChanged(pos);
    }
}
