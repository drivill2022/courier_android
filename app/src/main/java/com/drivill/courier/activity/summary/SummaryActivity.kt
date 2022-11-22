package com.drivill.courier.activity.summary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.drivill.courier.BaseActivity
import com.drivill.courier.R
import com.drivill.courier.adapter.SummaryAdapter
import com.drivill.courier.databinding.ActivitySummaryBinding
import com.drivill.courier.merchantModul.activity.DashboardActivityMerchant
import com.drivill.courier.merchantModul.activity.PackagingActivity
import com.drivill.courier.merchantModul.model.ShipmentModel
import com.drivill.courier.rest.ApiManagerImp
import com.drivill.courier.utils.Constant
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SummaryActivity : BaseActivity() {
    lateinit var activitySummaryBinding: ActivitySummaryBinding
    lateinit var summaryAdapter: SummaryAdapter
    var dataArr: ArrayList<ShipmentModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySummaryBinding=ActivitySummaryBinding.inflate(layoutInflater)
        setContentView(activitySummaryBinding.root)
        dataArr= intent.getSerializableExtra("dataarray") as ArrayList<ShipmentModel>?
        summaryAdapter= SummaryAdapter(this@SummaryActivity, dataArr!!)
        activitySummaryBinding.recyclerSummary.adapter=summaryAdapter
        //getAllShipDemmItem()

        activitySummaryBinding.backBtn.setOnClickListener {
            val intent = Intent(this@SummaryActivity, DashboardActivityMerchant::class.java)
            startActivity(intent)
            finishAffinity()
        }
        activitySummaryBinding.btnAddNew.setOnClickListener {
            val intent = Intent(this, PackagingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun setUp() {
    }


    fun getAllShipDemmItem() {
        // ((PackagingActivity) requireActivity()).showLoading();
        val call = ApiManagerImp().getShipmentMerchant(mBasePreferenceManager.userToken,
            Constant.CURRENT
        )
        call.enqueue(object : Callback<ArrayList<ShipmentModel>?> {
            override fun onResponse(
                call: Call<ArrayList<ShipmentModel>?>,
                response: Response<ArrayList<ShipmentModel>?>
            ) {
                hideLoading()
                if (response.body() != null) {
                    //  Log.i("arp",new Gson().toJson(response.body()));
                    dataArr = ArrayList<ShipmentModel>()
                    for (i in response.body()!!.indices) {
                        val model = response.body()!![i]
                        if (model.status == 0) {
                            dataArr!!.add(model)
                            Log.i("arp", model.status.toString())
                        }
                    }
                    Log.i("arpwew", dataArr!!.size.toString())

                    summaryAdapter= SummaryAdapter(this@SummaryActivity, dataArr!!)
                    activitySummaryBinding.recyclerSummary.adapter=summaryAdapter
                } else {
                    try {
                        val `object` = JSONObject(response.errorBody()!!.string())
                        onError(`object`["error"].toString())
                    } catch (e: JSONException) {
                        Log.i("arp", e.toString())
                    } catch (e: IOException) {
                        Log.i("arp", e.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<ShipmentModel>?>, t: Throwable) {
                hideLoading()
                onError(getString(R.string.try_again))
                Log.i("arp", t.toString())
            }
        })
    }

}