package com.drivill.courier.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.drivill.courier.merchantModul.model.ShipmentModel;

import java.util.ArrayList;
import java.util.List;

public class RiderPickupListModel {


    @SerializedName("shipments")
    @Expose
    private List<ShipmentModel> shipments = new ArrayList<>();
    @SerializedName("message")
    @Expose
    private String message;

    public List<ShipmentModel> getShipments() {
        return shipments;
    }

    public void setShipments(List<ShipmentModel> shipments) {
        this.shipments = shipments;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
