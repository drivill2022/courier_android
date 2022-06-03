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
    private String productDetail;
    @SerializedName("product_weight")
    @Expose
    private String productWeight;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("s_address")
    @Expose
    private String sAddress;
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
    @SerializedName("shipment_cost")
    @Expose
    private String shipmentCost;

    @SerializedName("cod_amount")
    @Expose
    private String cod_amount;

    @SerializedName("pickup_date")
    @Expose
    private String pickupDate;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
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

    @SerializedName("s_latitude")
    @Expose
    private String s_latitude;
    @SerializedName("s_longitude")
    @Expose
    private String s_longitude;

    @SerializedName("d_latitude")
    @Expose
    private String d_latitude;
    @SerializedName("d_longitude")
    @Expose
    private String d_longitude;

    @SerializedName("created_date")
    @Expose
    private String created_date;

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    @SerializedName("cancel_by")
    @Expose


    private List<CancelBy> cancelBy = null;

    public List<CancelBy> getCancelBy() {
        return cancelBy;
    }

    public void setCancelBy(List<CancelBy> cancelBy) {
        this.cancelBy = cancelBy;
    }

    public String getCod_amount() {
        return cod_amount;
    }

    public void setCod_amount(String cod_amount) {
        this.cod_amount = cod_amount;
    }

    public String getS_latitude() {
        return s_latitude;
    }

    public void setS_latitude(String s_latitude) {
        this.s_latitude = s_latitude;
    }

    public String getS_longitude() {
        return s_longitude;
    }

    public void setS_longitude(String s_longitude) {
        this.s_longitude = s_longitude;
    }

    public String getD_latitude() {
        return d_latitude;
    }

    public void setD_latitude(String d_latitude) {
        this.d_latitude = d_latitude;
    }

    public String getD_longitude() {
        return d_longitude;
    }

    public void setD_longitude(String d_longitude) {
        this.d_longitude = d_longitude;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
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
}
