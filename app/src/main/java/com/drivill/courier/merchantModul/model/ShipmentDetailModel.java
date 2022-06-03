package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ShipmentDetailModel implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable {

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
        private String productDetail;
        @SerializedName("product_weight")
        @Expose
        private String productWeight;
        @SerializedName("product_type")
        @Expose
        private Integer productType;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("not_for_hub")
        @Expose
        private Object notForHub;
        @SerializedName("s_address")
        @Expose
        private String sAddress;
        @SerializedName("s_latitude")
        @Expose
        private Double sLatitude;
        @SerializedName("s_longitude")
        @Expose
        private Double sLongitude;
        @SerializedName("d_latitude")
        @Expose
        private Double dLatitude;
        @SerializedName("d_longitude")
        @Expose
        private Double dLongitude;
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
        private Integer dDistrict;
        @SerializedName("d_thana")
        @Expose
        private Integer dThana;
        @SerializedName("d_division")
        @Expose
        private Integer dDivision;
        @SerializedName("shipment_type")
        @Expose
        private Integer shipmentType;
        @SerializedName("cod_amount")
        @Expose
        private Integer codAmount;
        @SerializedName("shipment_cost")
        @Expose
        private String shipmentCost;
        @SerializedName("pickup_date")
        @Expose
        private String pickupDate;
        @SerializedName("otp")
        @Expose
        private Object otp;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("status_logs")
        @Expose
        private List<StatusLog> statusLogs = null;
        @SerializedName("sdivision")
        @Expose
        private Sdivision sdivision;
        @SerializedName("ddivision")
        @Expose
        private Ddivision ddivision;
        @SerializedName("sdistrict")
        @Expose
        private Sdistrict sdistrict;
        @SerializedName("ddistrict")
        @Expose
        private Ddistrict ddistrict;
        @SerializedName("sthana")
        @Expose
        private Sthana sthana;
        @SerializedName("dthana")
        @Expose
        private Dthana dthana;
        @SerializedName("merchant")
        @Expose
        private Merchant merchant;
        @SerializedName("cancel_by")
        @Expose
        private List<Object> cancelBy = null;
        @SerializedName("rider")
        @Expose
        private Rider__1 rider;

        @SerializedName("product_types")
        @Expose
        private ProductTypes productTypes;

        public ProductTypes getProductTypes() {
            return productTypes;
        }

        public void setProductTypes(ProductTypes productTypes) {
            this.productTypes = productTypes;
        }

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

        public String getProductDetail() {
            return productDetail;
        }

        public void setProductDetail(String productDetail) {
            this.productDetail = productDetail;
        }

        public String getProductWeight() {
            return productWeight;
        }

        public void setProductWeight(String productWeight) {
            this.productWeight = productWeight;
        }

        public Integer getProductType() {
            return productType;
        }

        public void setProductType(Integer productType) {
            this.productType = productType;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
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

        public Double getsLatitude() {
            return sLatitude;
        }

        public void setsLatitude(Double sLatitude) {
            this.sLatitude = sLatitude;
        }

        public Double getsLongitude() {
            return sLongitude;
        }

        public void setsLongitude(Double sLongitude) {
            this.sLongitude = sLongitude;
        }

        public Double getdLatitude() {
            return dLatitude;
        }

        public void setdLatitude(Double dLatitude) {
            this.dLatitude = dLatitude;
        }

        public Double getdLongitude() {
            return dLongitude;
        }

        public void setdLongitude(Double dLongitude) {
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

        public Integer getdDistrict() {
            return dDistrict;
        }

        public void setdDistrict(Integer dDistrict) {
            this.dDistrict = dDistrict;
        }

        public Integer getdThana() {
            return dThana;
        }

        public void setdThana(Integer dThana) {
            this.dThana = dThana;
        }

        public Integer getdDivision() {
            return dDivision;
        }

        public void setdDivision(Integer dDivision) {
            this.dDivision = dDivision;
        }

        public Integer getShipmentType() {
            return shipmentType;
        }

        public void setShipmentType(Integer shipmentType) {
            this.shipmentType = shipmentType;
        }

        public Integer getCodAmount() {
            return codAmount;
        }

        public void setCodAmount(Integer codAmount) {
            this.codAmount = codAmount;
        }

        public String getShipmentCost() {
            return shipmentCost;
        }

        public void setShipmentCost(String shipmentCost) {
            this.shipmentCost = shipmentCost;
        }

        public String getPickupDate() {
            return pickupDate;
        }

        public void setPickupDate(String pickupDate) {
            this.pickupDate = pickupDate;
        }

        public Object getOtp() {
            return otp;
        }

        public void setOtp(Object otp) {
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

        public List<StatusLog> getStatusLogs() {
            return statusLogs;
        }

        public void setStatusLogs(List<StatusLog> statusLogs) {
            this.statusLogs = statusLogs;
        }

        public Sdivision getSdivision() {
            return sdivision;
        }

        public void setSdivision(Sdivision sdivision) {
            this.sdivision = sdivision;
        }

        public Ddivision getDdivision() {
            return ddivision;
        }

        public void setDdivision(Ddivision ddivision) {
            this.ddivision = ddivision;
        }

        public Sdistrict getSdistrict() {
            return sdistrict;
        }

        public void setSdistrict(Sdistrict sdistrict) {
            this.sdistrict = sdistrict;
        }

        public Ddistrict getDdistrict() {
            return ddistrict;
        }

        public void setDdistrict(Ddistrict ddistrict) {
            this.ddistrict = ddistrict;
        }

        public Sthana getSthana() {
            return sthana;
        }

        public void setSthana(Sthana sthana) {
            this.sthana = sthana;
        }

        public Dthana getDthana() {
            return dthana;
        }

        public void setDthana(Dthana dthana) {
            this.dthana = dthana;
        }

        public Merchant getMerchant() {
            return merchant;
        }

        public void setMerchant(Merchant merchant) {
            this.merchant = merchant;
        }

        public List<Object> getCancelBy() {
            return cancelBy;
        }

        public void setCancelBy(List<Object> cancelBy) {
            this.cancelBy = cancelBy;
        }

        public Rider__1 getRider() {
            return rider;
        }

        public void setRider(Rider__1 rider) {
            this.rider = rider;
        }

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

    }

    public class Rider__1 implements Serializable{

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
        private Rider__2 rider;

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

        public Rider__2 getRider() {
            return rider;
        }

        public void setRider(Rider__2 rider) {
            this.rider = rider;
        }

    }

    public class Rider__2  implements Serializable {

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
        private Object email;
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
        private Double latitude;
        @SerializedName("longitude")
        @Expose
        private Double longitude;
        @SerializedName("thana")
        @Expose
        private Integer thana;
        @SerializedName("district")
        @Expose
        private Integer district;
        @SerializedName("division")
        @Expose
        private Integer division;
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
        private Object emergencyNo;

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

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
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

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Integer getThana() {
            return thana;
        }

        public void setThana(Integer thana) {
            this.thana = thana;
        }

        public Integer getDistrict() {
            return district;
        }

        public void setDistrict(Integer district) {
            this.district = district;
        }

        public Integer getDivision() {
            return division;
        }

        public void setDivision(Integer division) {
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

        public Object getEmergencyNo() {
            return emergencyNo;
        }

        public void setEmergencyNo(Object emergencyNo) {
            this.emergencyNo = emergencyNo;
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

    public class StatusLog implements Serializable {

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
        private Object updatedBy;
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

        public Object getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(Object updatedBy) {
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

    public class ProductTypes implements Serializable  {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("status")
        @Expose
        private String status;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    }
}
