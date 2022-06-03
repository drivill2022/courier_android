package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BreakDownModel {

    @SerializedName("available_payout")
    @Expose
    private Integer availablePayout;
    @SerializedName("drivills_commission")
    @Expose
    private Integer drivillsCommission;
    @SerializedName("delivered")
    @Expose
    private Integer delivered;
    @SerializedName("cancelled")
    @Expose
    private Integer cancelled;
    @SerializedName("pending")
    @Expose
    private Integer pending;
    @SerializedName("shipped")
    @Expose
    private Integer shipped;
    @SerializedName("total_available_for_payout")
    @Expose
    private Integer totalAvailableForPayout;
    @SerializedName("refund_from_drivill")
    @Expose
    private Integer refundFromDrivill;
    @SerializedName("cash_collected")
    @Expose
    private Integer cashCollected;
    @SerializedName("drivill_service_charge")
    @Expose
    private Integer drivillServiceCharge;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getAvailablePayout() {
        return availablePayout;
    }

    public void setAvailablePayout(Integer availablePayout) {
        this.availablePayout = availablePayout;
    }

    public Integer getDrivillsCommission() {
        return drivillsCommission;
    }

    public void setDrivillsCommission(Integer drivillsCommission) {
        this.drivillsCommission = drivillsCommission;
    }

    public Integer getDelivered() {
        return delivered;
    }

    public void setDelivered(Integer delivered) {
        this.delivered = delivered;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }

    public Integer getShipped() {
        return shipped;
    }

    public void setShipped(Integer shipped) {
        this.shipped = shipped;
    }

    public Integer getTotalAvailableForPayout() {
        return totalAvailableForPayout;
    }

    public void setTotalAvailableForPayout(Integer totalAvailableForPayout) {
        this.totalAvailableForPayout = totalAvailableForPayout;
    }

    public Integer getRefundFromDrivill() {
        return refundFromDrivill;
    }

    public void setRefundFromDrivill(Integer refundFromDrivill) {
        this.refundFromDrivill = refundFromDrivill;
    }

    public Integer getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(Integer cashCollected) {
        this.cashCollected = cashCollected;
    }

    public Integer getDrivillServiceCharge() {
        return drivillServiceCharge;
    }

    public void setDrivillServiceCharge(Integer drivillServiceCharge) {
        this.drivillServiceCharge = drivillServiceCharge;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
