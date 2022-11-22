package com.drivill.courier.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.drivill.courier.R
import com.drivill.courier.databinding.ItemSummaryBinding
import com.drivill.courier.merchantModul.model.ShipmentModel
import retrofit2.Callback

class SummaryAdapter(
    val mContext:Context,
    val dataArr: ArrayList<ShipmentModel>
) : RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSummaryBinding) : RecyclerView.ViewHolder(binding.root) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemSummaryBinding>(LayoutInflater.from(parent.context),
            R.layout.item_summary, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItemName.text=dataArr[position].productType
        holder.binding.txtId.text=dataArr[position].shipmentNo.replace("#","")
        holder.binding.amount.text="৳ "+dataArr[position].codAmount.toString()

    }

    override fun getItemCount(): Int {
        return dataArr.size
    }
}