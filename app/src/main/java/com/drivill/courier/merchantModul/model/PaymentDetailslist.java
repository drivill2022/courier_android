package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaymentDetailslist {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("shipment_no")
        @Expose
        private String shipmentNo;
        @SerializedName("cod_amount")
        @Expose
        private Integer codAmount;
        @SerializedName("shipment_cost")
        @Expose
        private Integer shipmentCost;

        public String getShipmentNo() {
            return shipmentNo;
        }

        public void setShipmentNo(String shipmentNo) {
            this.shipmentNo = shipmentNo;
        }

        public Integer getCodAmount() {
            return codAmount;
        }

        public void setCodAmount(Integer codAmount) {
            this.codAmount = codAmount;
        }

        public Integer getShipmentCost() {
            return shipmentCost;
        }

        public void setShipmentCost(Integer shipmentCost) {
            this.shipmentCost = shipmentCost;
        }

    }

}
