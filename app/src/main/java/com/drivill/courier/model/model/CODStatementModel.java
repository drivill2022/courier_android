package com.drivill.courier.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CODStatementModel {
    @SerializedName("history")
    @Expose
    private History history;
    @SerializedName("cod_to_deposit")
    @Expose
    private Integer codToDeposit;
    @SerializedName("drivills_commission")
    @Expose
    private Integer drivillsCommission;
    @SerializedName("message")
    @Expose
    private String message;

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public Integer getCodToDeposit() {
        return codToDeposit;
    }

    public void setCodToDeposit(Integer codToDeposit) {
        this.codToDeposit = codToDeposit;
    }

    public Integer getDrivillsCommission() {
        return drivillsCommission;
    }

    public void setDrivillsCommission(Integer drivillsCommission) {
        this.drivillsCommission = drivillsCommission;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Datum {

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
        @SerializedName("delivered_to")
        @Expose
        private String deliveredTo;
        @SerializedName("date")
        @Expose
        private String date;
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

        public String getDeliveredTo() {
            return deliveredTo;
        }

        public void setDeliveredTo(String deliveredTo) {
            this.deliveredTo = deliveredTo;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
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

    public class Ddistrict {

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
    public class Ddivision {

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

    public class Dthana {

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


    public class Merchant {

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

    public class Sdistrict {

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

    public class Sdivision {

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

    public class Sthana {

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

    public class History {

        @SerializedName("current_page")
        @Expose
        private Integer currentPage;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("first_page_url")
        @Expose
        private String firstPageUrl;
        @SerializedName("from")
        @Expose
        private Integer from;
        @SerializedName("last_page")
        @Expose
        private Integer lastPage;
        @SerializedName("last_page_url")
        @Expose
        private String lastPageUrl;
        @SerializedName("next_page_url")
        @Expose
        private String nextPageUrl;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("prev_page_url")
        @Expose
        private Object prevPageUrl;
        @SerializedName("to")
        @Expose
        private Integer to;
        @SerializedName("total")
        @Expose
        private Integer total;

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getLastPage() {
            return lastPage;
        }

        public void setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public String getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(String nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Object getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(Object prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

    }
}
