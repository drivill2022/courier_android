package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SummaryModel {
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

class Datum {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("shipment_no")
    @Expose
    private String shipmentNo;
    @SerializedName("shipment_cost")
    @Expose
    private Integer shipmentCost;
    @SerializedName("cod_amount")
    @Expose
    private Integer codAmount;
    @SerializedName("pickup_date")
    @Expose
    private String pickupDate;
    @SerializedName("merchant_id")
    @Expose
    private Integer merchantId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("receiver_name")
    @Expose
    private String receiverName;
    @SerializedName("product_detail")
    @Expose
    private Object productDetail;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("s_address")
    @Expose
    private String sAddress;
    @SerializedName("d_address")
    @Expose
    private String dAddress;
    @SerializedName("s_district")
    @Expose
    private Integer sDistrict;
    @SerializedName("s_division")
    @Expose
    private Integer sDivision;
    @SerializedName("d_district")
    @Expose
    private Object dDistrict;
    @SerializedName("d_division")
    @Expose
    private Object dDivision;
    @SerializedName("shipment_type")
    @Expose
    private Object shipmentType;
    @SerializedName("s_thana")
    @Expose
    private Integer sThana;
    @SerializedName("distname")
    @Expose
    private Object distname;
    @SerializedName("ddrmrctID")
    @Expose
    private Integer ddrmrctID;
    @SerializedName("mrcht_name")
    @Expose
    private String mrchtName;
    @SerializedName("mrct_number")
    @Expose
    private String mrctNumber;
    @SerializedName("mrct_email")
    @Expose
    private String mrctEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShipmentNo() {
        return shipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        this.shipmentNo = shipmentNo;
    }

    public Integer getShipmentCost() {
        return shipmentCost;
    }

    public void setShipmentCost(Integer shipmentCost) {
        this.shipmentCost = shipmentCost;
    }

    public Integer getCodAmount() {
        return codAmount;
    }

    public void setCodAmount(Integer codAmount) {
        this.codAmount = codAmount;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Object getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(Object productDetail) {
        this.productDetail = productDetail;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public Integer getsDistrict() {
        return sDistrict;
    }

    public void setsDistrict(Integer sDistrict) {
        this.sDistrict = sDistrict;
    }

    public Integer getsDivision() {
        return sDivision;
    }

    public void setsDivision(Integer sDivision) {
        this.sDivision = sDivision;
    }

    public Object getdDistrict() {
        return dDistrict;
    }

    public void setdDistrict(Object dDistrict) {
        this.dDistrict = dDistrict;
    }

    public Object getdDivision() {
        return dDivision;
    }

    public void setdDivision(Object dDivision) {
        this.dDivision = dDivision;
    }

    public Object getShipmentType() {
        return shipmentType;
    }

    public void setShipmentType(Object shipmentType) {
        this.shipmentType = shipmentType;
    }

    public Integer getsThana() {
        return sThana;
    }

    public void setsThana(Integer sThana) {
        this.sThana = sThana;
    }

    public Object getDistname() {
        return distname;
    }

    public void setDistname(Object distname) {
        this.distname = distname;
    }

    public Integer getDdrmrctID() {
        return ddrmrctID;
    }

    public void setDdrmrctID(Integer ddrmrctID) {
        this.ddrmrctID = ddrmrctID;
    }

    public String getMrchtName() {
        return mrchtName;
    }

    public void setMrchtName(String mrchtName) {
        this.mrchtName = mrchtName;
    }

    public String getMrctNumber() {
        return mrctNumber;
    }

    public void setMrctNumber(String mrctNumber) {
        this.mrctNumber = mrctNumber;
    }

    public String getMrctEmail() {
        return mrctEmail;
    }

    public void setMrctEmail(String mrctEmail) {
        this.mrctEmail = mrctEmail;
    }

}