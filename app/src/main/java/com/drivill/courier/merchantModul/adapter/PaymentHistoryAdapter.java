package com.drivill.courier.merchantModul.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.drivill.courier.R;
import com.drivill.courier.activity.PaymentInvoice;
import com.drivill.courier.activity.supportActivity.SupportActivity;
import com.drivill.courier.merchantModul.model.PaymentModel;
import com.drivill.courier.utils.PrefsManager;
import java.util.ArrayList;

public class PaymentHistoryAdapter extends RecyclerView.Adapter<PaymentHistoryAdapter.MyStatementHolder> {
    Context mContext;
    ArrayList<PaymentModel.History> mHistoryList = new ArrayList<>();

    public PaymentHistoryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public MyStatementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_payment_history, parent, false);

        return new MyStatementHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStatementHolder holder, @SuppressLint("RecyclerView") int position) {

        PrefsManager pm =  new PrefsManager(mContext);

       // holder.dateTxt.setText(AppUtil.getDateFormat(mHistoryList.get(position).getDate(), ""));
       // holder.dateTxt.setText(AppUtil.parseDateToddMMyyyy(mHistoryList.get(position).getDate()));

        holder.dateTxt.setText(mHistoryList.get(position).getDate());
        Log.i("arp","date= " +mHistoryList.get(position).getDate());

        holder.paidByTxt.setText(mHistoryList.get(position).getReceiverName());
        holder.payment_type.setText(mHistoryList.get(position).getPaymentMethod());
        holder.priceItem.setText(String.valueOf(mHistoryList.get(position).getCodAmount()));
        holder.trx_idTxt.setText(mHistoryList.get(position).getShipmentNo());

        holder.help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SupportActivity.class);
                mContext.startActivity(intent);
            }
        });

        holder.tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PaymentInvoice.class);
                intent.putExtra("txtid", String.valueOf(mHistoryList.get(position).getId()));
                mContext.startActivity(intent);
            }
        });

        if(pm.getIsRider()){
            holder.tv_details.setVisibility(View.GONE);
        }else {
            holder.tv_details.setVisibility(View.VISIBLE);
        }

        /*holder.tv_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              *//*  Intent intent = new Intent(mContext, PaymentDetails.class);
                mContext.startActivity(intent);*//*

                // http://www.codeplayon.com/2020/04/how-to-open-a-dialogfragment-in-activity-android-tutorial/
                Bundle bundle = new Bundle();
                bundle.putString("txtid", String.valueOf(mHistoryList.get(position).getId()));
                FragmentManager fm = ((AppCompatActivity)mContext).getSupportFragmentManager();
                PaymentDetails newFragment = new PaymentDetails();
                newFragment.show(fm, "Payment Details");
                newFragment.setArguments(bundle);

            }
        });*/

    }

    @Override
    public int getItemCount() {

        //return 10;
        if (mHistoryList != null) return mHistoryList.size();
        else
            return 0;
    }

    class MyStatementHolder extends RecyclerView.ViewHolder {
        TextView trx_idTxt, priceItem, payment_type, dateTxt, paidByTxt, tv_details;
        ImageView help;

        public MyStatementHolder(@NonNull View itemView) {
            super(itemView);
            trx_idTxt = itemView.findViewById(R.id.trx_idTxt);
            priceItem = itemView.findViewById(R.id.cod_amountAdapterTxt);
            payment_type = itemView.findViewById(R.id.payment_type);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            paidByTxt = itemView.findViewById(R.id.paidByTxt);
            help = itemView.findViewById(R.id.call);
            tv_details = itemView.findViewById(R.id.tv_details);
        }
    }

    public void setData(ArrayList<PaymentModel.History> historyList) {
        mHistoryList = historyList;
        notifyDataSetChanged();

    }


}
