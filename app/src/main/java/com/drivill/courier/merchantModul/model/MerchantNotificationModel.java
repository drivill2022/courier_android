package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MerchantNotificationModel implements Serializable {

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

    public static class Datum implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("rider_id")
        @Expose
        private Integer riderId;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("is_viewed")
        @Expose
        private String isViewed;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("message")
        @Expose
        private String message;

        public String getCreated_at_for_human() {
            return created_at_for_human;
        }

        public void setCreated_at_for_human(String created_at_for_human) {
            this.created_at_for_human = created_at_for_human;
        }

        @SerializedName("created_at_for_human")
        @Expose
        private String created_at_for_human;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getRiderId() {
            return riderId;
        }

        public void setRiderId(Integer riderId) {
            this.riderId = riderId;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

        public String getIsViewed() {
            return isViewed;
        }

        public void setIsViewed(String isViewed) {
            this.isViewed = isViewed;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

}
