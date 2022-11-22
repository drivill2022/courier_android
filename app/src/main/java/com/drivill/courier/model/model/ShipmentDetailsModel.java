
package com.drivill.courier.model.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ShipmentDetailsModel {

    @SerializedName("data")
    private Datum mData;
    @SerializedName("status")
    private String mStatus;

    public Datum getData() {
        return mData;
    }

    public void setData(Datum data) {
        mData = data;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
