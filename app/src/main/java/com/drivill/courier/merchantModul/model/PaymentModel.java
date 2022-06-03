package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PaymentModel implements Serializable {

    @SerializedName("history")
    @Expose
    private List<History> history = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class History implements Serializable {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("paid_by")
        @Expose
        private String paid_by;
        @SerializedName("shipment_no")
        @Expose
        private String shipmentNo;
        @SerializedName("receiver_name")
        @Expose
        private String receiverName;
        @SerializedName("cod_amount")
        @Expose
        private Integer codAmount;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;

        public String getShipmentNo() {
            return shipmentNo;
        }

        public void setShipmentNo(String shipmentNo) {
            this.shipmentNo = shipmentNo;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public Integer getCodAmount() {
            return codAmount;
        }

        public void setCodAmount(Integer codAmount) {
            this.codAmount = codAmount;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }


        //Merchant

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getPaid_by() {
            return paid_by;
        }

        public void setPaid_by(String paid_by) {
            this.paid_by = paid_by;
        }
    }
}
