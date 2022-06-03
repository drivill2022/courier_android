package com.drivill.courier.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementModel {

    @SerializedName("picked_up")
    @Expose
    private Integer pickedUp;
    @SerializedName("delivered")
    @Expose
    private Integer delivered;
    @SerializedName("cancelled")
    @Expose
    private Integer cancelled;
    @SerializedName("pending")
    @Expose
    private Integer pending;
    @SerializedName("total_cod_earned")
    @Expose
    private Integer totalCodEarned;
    @SerializedName("total_cod_collected")
    @Expose
    private Integer totalCodCollected;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(Integer pickedUp) {
        this.pickedUp = pickedUp;
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

    public Integer getTotalCodEarned() {
        return totalCodEarned;
    }

    public void setTotalCodEarned(Integer totalCodEarned) {
        this.totalCodEarned = totalCodEarned;
    }

    public Integer getTotalCodCollected() {
        return totalCodCollected;
    }

    public void setTotalCodCollected(Integer totalCodCollected) {
        this.totalCodCollected = totalCodCollected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
