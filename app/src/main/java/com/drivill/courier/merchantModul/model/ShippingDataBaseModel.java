package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShippingDataBaseModel {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("delivery_amount")
    @Expose
    private Integer deliveryAmount;
    @SerializedName("message")
    @Expose
    private String message;

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public Integer getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Result {

        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("total_sales")
        @Expose
        private Integer totalSales;
        @SerializedName("return_sales")
        @Expose
        private Integer returnSales;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getTotalSales() {
            return totalSales;
        }

        public void setTotalSales(Integer totalSales) {
            this.totalSales = totalSales;
        }

        public Integer getReturnSales() {
            return returnSales;
        }

        public void setReturnSales(Integer returnSales) {
            this.returnSales = returnSales;
        }

    }
}




