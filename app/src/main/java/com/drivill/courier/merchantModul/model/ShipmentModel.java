package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShipmentModel implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("merchant_id")
    @Expose
    private Integer merchantId;
    @SerializedName("shipment_no")
    @Expose
    private String shipmentNo;
    @SerializedName("receiver_name")
    @Expose
    private String receiverName;
    @SerializedName("contact_no")
    @Expose
    private String contactNo;
    @SerializedName("product_detail")
    @Expose
    private Object productDetail;
    @SerializedName("product_weight")
    @Expose
    private String productWeight;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("note")
    @Expose
    private Object note;
    @SerializedName("not_for_hub")
    @Expose
    private Object notForHub;
    @SerializedName("s_address")
    @Expose
    private String sAddress;
    @SerializedName("s_latitude")
    @Expose
    private Object sLatitude;
    @SerializedName("s_longitude")
    @Expose
    private Object sLongitude;
    @SerializedName("d_latitude")
    @Expose
    private Object dLatitude;
    @SerializedName("d_longitude")
    @Expose
    private Object dLongitude;
    @SerializedName("d_address")
    @Expose
    private String dAddress;
    @SerializedName("s_district")
    @Expose
    private Integer sDistrict;
    @SerializedName("s_thana")
    @Expose
    private Integer sThana;
    @SerializedName("s_division")
    @Expose
    private Integer sDivision;
    @SerializedName("d_district")
    @Expose
    private Object dDistrict;
    @SerializedName("d_thana")
    @Expose
    private Object dThana;
    @SerializedName("d_division")
    @Expose
    private Object dDivision;
    @SerializedName("shipment_type")
    @Expose
    private Object shipmentType;
    @SerializedName("cod_amount")
    @Expose
    private Integer codAmount;
    @SerializedName("shipment_cost")
    @Expose
    private Integer shipmentCost;
    @SerializedName("pickup_date")
    @Expose
    private String pickupDate;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("payment_collectedby_rider")
    @Expose
    private String paymentCollectedbyRider;
    @SerializedName("return_status")
    @Expose
    private String returnStatus;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("sdivision")
    @Expose
    private Sdivision sdivision;
    @SerializedName("ddivision")
    @Expose
    private Object ddivision;
    @SerializedName("sdistrict")
    @Expose
    private Sdistrict sdistrict;
    @SerializedName("ddistrict")
    @Expose
    private Object ddistrict;
    @SerializedName("sthana")
    @Expose
    private Sthana sthana;
    @SerializedName("dthana")
    @Expose
    private Object dthana;
    @SerializedName("merchant")
    @Expose
    private Merchant merchant;
    @SerializedName("cancel_by")
    @Expose
    private List<CancelBy> cancelBy;
    @SerializedName("rider_status")
    @Expose
    private RiderStatus riderStatus;
    @SerializedName("rider")
    @Expose
    private Rider rider;
    @SerializedName("status_log")
    @Expose
    private List<StatusLog> statusLog;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Object getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(Object productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Object getNote() {
        return note;
    }

    public void setNote(Object note) {
        this.note = note;
    }

    public Object getNotForHub() {
        return notForHub;
    }

    public void setNotForHub(Object notForHub) {
        this.notForHub = notForHub;
    }

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public Object getsLatitude() {
        return sLatitude;
    }

    public void setsLatitude(Object sLatitude) {
        this.sLatitude = sLatitude;
    }

    public Object getsLongitude() {
        return sLongitude;
    }

    public void setsLongitude(Object sLongitude) {
        this.sLongitude = sLongitude;
    }

    public Object getdLatitude() {
        return dLatitude;
    }

    public void setdLatitude(Object dLatitude) {
        this.dLatitude = dLatitude;
    }

    public Object getdLongitude() {
        return dLongitude;
    }

    public void setdLongitude(Object dLongitude) {
        this.dLongitude = dLongitude;
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

    public Integer getsThana() {
        return sThana;
    }

    public void setsThana(Integer sThana) {
        this.sThana = sThana;
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

    public Object getdThana() {
        return dThana;
    }

    public void setdThana(Object dThana) {
        this.dThana = dThana;
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

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPaymentCollectedbyRider() {
        return paymentCollectedbyRider;
    }

    public void setPaymentCollectedbyRider(String paymentCollectedbyRider) {
        this.paymentCollectedbyRider = paymentCollectedbyRider;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Sdivision getSdivision() {
        return sdivision;
    }

    public void setSdivision(Sdivision sdivision) {
        this.sdivision = sdivision;
    }

    public Object getDdivision() {
        return ddivision;
    }

    public void setDdivision(Object ddivision) {
        this.ddivision = ddivision;
    }

    public Sdistrict getSdistrict() {
        return sdistrict;
    }

    public void setSdistrict(Sdistrict sdistrict) {
        this.sdistrict = sdistrict;
    }

    public Object getDdistrict() {
        return ddistrict;
    }

    public void setDdistrict(Object ddistrict) {
        this.ddistrict = ddistrict;
    }

    public Sthana getSthana() {
        return sthana;
    }

    public void setSthana(Sthana sthana) {
        this.sthana = sthana;
    }

    public Object getDthana() {
        return dthana;
    }

    public void setDthana(Object dthana) {
        this.dthana = dthana;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<CancelBy> getCancelBy() {
        return cancelBy;
    }

    public void setCancelBy(List<CancelBy> cancelBy) {
        this.cancelBy = cancelBy;
    }

    public RiderStatus getRiderStatus() {
        return riderStatus;
    }

    public void setRiderStatus(RiderStatus riderStatus) {
        this.riderStatus = riderStatus;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public List<StatusLog> getStatusLog() {
        return statusLog;
    }

    public void setStatusLog(List<StatusLog> statusLog) {
        this.statusLog = statusLog;
    }


    public class Ddistrict implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("division_id")
        @Expose
        private Integer divisionId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("bn_name")
        @Expose
        private String bnName;
        @SerializedName("lat")
        @Expose
        private Object lat;
        @SerializedName("lon")
        @Expose
        private Object lon;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDivisionId() {
            return divisionId;
        }

        public void setDivisionId(Integer divisionId) {
            this.divisionId = divisionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBnName() {
            return bnName;
        }

        public void setBnName(String bnName) {
            this.bnName = bnName;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLon() {
            return lon;
        }

        public void setLon(Object lon) {
            this.lon = lon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Ddivision implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("bn_name")
        @Expose
        private String bnName;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBnName() {
            return bnName;
        }

        public void setBnName(String bnName) {
            this.bnName = bnName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Dthana implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("district_id")
        @Expose
        private Integer districtId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("bn_name")
        @Expose
        private String bnName;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Integer districtId) {
            this.districtId = districtId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBnName() {
            return bnName;
        }

        public void setBnName(String bnName) {
            this.bnName = bnName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Merchant implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("buss_name")
        @Expose
        private String buss_name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getBuss_name() {
            return buss_name;
        }

        public void setBuss_name(String buss_name) {
            this.buss_name = buss_name;
        }

    }

    public class Sdistrict implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("division_id")
        @Expose
        private Integer divisionId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("bn_name")
        @Expose
        private String bnName;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDivisionId() {
            return divisionId;
        }

        public void setDivisionId(Integer divisionId) {
            this.divisionId = divisionId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBnName() {
            return bnName;
        }

        public void setBnName(String bnName) {
            this.bnName = bnName;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public class Sdivision implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("bn_name")
        @Expose
        private String bnName;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBnName() {
            return bnName;
        }

        public void setBnName(String bnName) {
            this.bnName = bnName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


    }


    public class Sthana implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("district_id")
        @Expose
        private Integer districtId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("bn_name")
        @Expose
        private String bnName;
        @SerializedName("url")
        @Expose
        private String url;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Integer districtId) {
            this.districtId = districtId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBnName() {
            return bnName;
        }

        public void setBnName(String bnName) {
            this.bnName = bnName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    public class CancelBy implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("reason")
        @Expose
        private String reason;
        @SerializedName("cash_status")
        @Expose
        private Object cashStatus;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;
        @SerializedName("updated_by_id")
        @Expose
        private Integer updatedById;
        @SerializedName("updated_ip")
        @Expose
        private String updatedIp;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Object getCashStatus() {
            return cashStatus;
        }

        public void setCashStatus(Object cashStatus) {
            this.cashStatus = cashStatus;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getUpdatedById() {
            return updatedById;
        }

        public void setUpdatedById(Integer updatedById) {
            this.updatedById = updatedById;
        }

        public String getUpdatedIp() {
            return updatedIp;
        }

        public void setUpdatedIp(String updatedIp) {
            this.updatedIp = updatedIp;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }


    public class StatusLog implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("reason")
        @Expose
        private String reason;
        @SerializedName("cash_status")
        @Expose
        private Object cashStatus;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;
        @SerializedName("updated_by_id")
        @Expose
        private Integer updatedById;
        @SerializedName("updated_ip")
        @Expose
        private String updatedIp;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Object getCashStatus() {
            return cashStatus;
        }

        public void setCashStatus(Object cashStatus) {
            this.cashStatus = cashStatus;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getUpdatedById() {
            return updatedById;
        }

        public void setUpdatedById(Integer updatedById) {
            this.updatedById = updatedById;
        }

        public String getUpdatedIp() {
            return updatedIp;
        }

        public void setUpdatedIp(String updatedIp) {
            this.updatedIp = updatedIp;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

    public class Rider implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("hub_id")
        @Expose
        private Integer hubId;
        @SerializedName("rider_id")
        @Expose
        private Integer riderId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("rider")
        @Expose
        private Rider__1 rider;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

        public Integer getHubId() {
            return hubId;
        }

        public void setHubId(Integer hubId) {
            this.hubId = hubId;
        }

        public Integer getRiderId() {
            return riderId;
        }

        public void setRiderId(Integer riderId) {
            this.riderId = riderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Rider__1 getRider() {
            return rider;
        }

        public void setRider(Rider__1 rider) {
            this.rider = rider;
        }

    }

    public class Rider__1 implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("hub_id")
        @Expose
        private Integer hubId;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("otp")
        @Expose
        private Object otp;
        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("referral_code")
        @Expose
        private String referralCode;
        @SerializedName("referral_by")
        @Expose
        private Object referralBy;
        @SerializedName("nid_number")
        @Expose
        private String nidNumber;
        @SerializedName("nid_picture")
        @Expose
        private String nidPicture;
        @SerializedName("father_nid_pic")
        @Expose
        private String fatherNidPic;
        @SerializedName("father_nid")
        @Expose
        private String fatherNid;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("latitude")
        @Expose
        private Float latitude;
        @SerializedName("longitude")
        @Expose
        private Float longitude;
        @SerializedName("thana")
        @Expose
        private Object thana;
        @SerializedName("district")
        @Expose
        private Object district;
        @SerializedName("division")
        @Expose
        private Object division;
        @SerializedName("vehicle_type_id")
        @Expose
        private Integer vehicleTypeId;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("email_verified_at")
        @Expose
        private Object emailVerifiedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("emergency_no")
        @Expose
        private String emergencyNo;
        @SerializedName("father_mobile")
        @Expose
        private String fatherMobile;
        @SerializedName("device_token")
        @Expose
        private String deviceToken;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getHubId() {
            return hubId;
        }

        public void setHubId(Integer hubId) {
            this.hubId = hubId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getOtp() {
            return otp;
        }

        public void setOtp(Object otp) {
            this.otp = otp;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getReferralCode() {
            return referralCode;
        }

        public void setReferralCode(String referralCode) {
            this.referralCode = referralCode;
        }

        public Object getReferralBy() {
            return referralBy;
        }

        public void setReferralBy(Object referralBy) {
            this.referralBy = referralBy;
        }

        public String getNidNumber() {
            return nidNumber;
        }

        public void setNidNumber(String nidNumber) {
            this.nidNumber = nidNumber;
        }

        public String getNidPicture() {
            return nidPicture;
        }

        public void setNidPicture(String nidPicture) {
            this.nidPicture = nidPicture;
        }

        public String getFatherNidPic() {
            return fatherNidPic;
        }

        public void setFatherNidPic(String fatherNidPic) {
            this.fatherNidPic = fatherNidPic;
        }

        public String getFatherNid() {
            return fatherNid;
        }

        public void setFatherNid(String fatherNid) {
            this.fatherNid = fatherNid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Float getLatitude() {
            return latitude;
        }

        public void setLatitude(Float latitude) {
            this.latitude = latitude;
        }

        public Float getLongitude() {
            return longitude;
        }

        public void setLongitude(Float longitude) {
            this.longitude = longitude;
        }

        public Object getThana() {
            return thana;
        }

        public void setThana(Object thana) {
            this.thana = thana;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public Object getDivision() {
            return division;
        }

        public void setDivision(Object division) {
            this.division = division;
        }

        public Integer getVehicleTypeId() {
            return vehicleTypeId;
        }

        public void setVehicleTypeId(Integer vehicleTypeId) {
            this.vehicleTypeId = vehicleTypeId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(Object emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getEmergencyNo() {
            return emergencyNo;
        }

        public void setEmergencyNo(String emergencyNo) {
            this.emergencyNo = emergencyNo;
        }

        public String getFatherMobile() {
            return fatherMobile;
        }

        public void setFatherMobile(String fatherMobile) {
            this.fatherMobile = fatherMobile;
        }

        public String getDeviceToken() {
            return deviceToken;
        }

        public void setDeviceToken(String deviceToken) {
            this.deviceToken = deviceToken;
        }

    }


    public class RiderStatus implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("shipment_id")
        @Expose
        private Integer shipmentId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("reason")
        @Expose
        private Object reason;
        @SerializedName("cash_status")
        @Expose
        private Object cashStatus;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("updated_by")
        @Expose
        private String updatedBy;
        @SerializedName("updated_by_id")
        @Expose
        private Integer updatedById;
        @SerializedName("updated_ip")
        @Expose
        private String updatedIp;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getShipmentId() {
            return shipmentId;
        }

        public void setShipmentId(Integer shipmentId) {
            this.shipmentId = shipmentId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Object getReason() {
            return reason;
        }

        public void setReason(Object reason) {
            this.reason = reason;
        }

        public Object getCashStatus() {
            return cashStatus;
        }

        public void setCashStatus(Object cashStatus) {
            this.cashStatus = cashStatus;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Integer getUpdatedById() {
            return updatedById;
        }

        public void setUpdatedById(Integer updatedById) {
            this.updatedById = updatedById;
        }

        public String getUpdatedIp() {
            return updatedIp;
        }

        public void setUpdatedIp(String updatedIp) {
            this.updatedIp = updatedIp;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}