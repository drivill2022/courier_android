package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TrackingModel implements Serializable {

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
        @SerializedName("created_date")
        @Expose
        private String created_date;

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
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

    public class Dthana  implements Serializable{

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
        private String reason;
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
        @SerializedName("created_date")
        @Expose
        private String created_date;

        public String getCreated_date() {
            return created_date;
        }

        public void setCreated_date(String created_date) {
            this.created_date = created_date;
        }

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

    public class Sthana  implements Serializable{

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
}
