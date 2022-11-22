
package com.drivill.courier.model.model;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("bn_name")
    private String mBnName;
    @SerializedName("cod_amount")
    private Long mCodAmount;
    @SerializedName("contact_no")
    private String mContactNo;
    @SerializedName("d_address")
    private String mDAddress;
    @SerializedName("d_district")
    private Long mDDistrict;
    @SerializedName("d_division")
    private Long mDDivision;
    @SerializedName("d_latitude")
    private Double mDLatitude;
    @SerializedName("d_longitude")
    private Double mDLongitude;
    @SerializedName("ddist_name")
    private String mDdistName;
    @SerializedName("ddv_bn_name")
    private String mDdvBnName;
    @SerializedName("ddvid")
    private Long mDdvid;
    @SerializedName("ddvname")
    private String mDdvname;
    @SerializedName("disID")
    private Long mDisID;
    @SerializedName("distname")
    private Long mDistname;
    @SerializedName("drl_created_at")
    private String mDrlCreatedAt;
    @SerializedName("drl_updated_at")
    private String mDrlUpdatedAt;
    @SerializedName("id")
    private Long mId;
    @SerializedName("merchant_id")
    private Long mMerchantId;
    @SerializedName("not_for_hub")
    private String mNotForHub;
    @SerializedName("note")
    private String mNote;
    @SerializedName("payment_collectedby_rider")
    private String mPaymentCollectedbyRider;
    @SerializedName("pickup_date")
    private String mPickupDate;
    @SerializedName("product_detail")
    private String mProductDetail;
    @SerializedName("product_type")
    private Long mProductType;
    @SerializedName("product_weight")
    private String mProductWeight;
    @SerializedName("receiver_name")
    private String mReceiverName;
    @SerializedName("s_address")
    private String mSAddress;
    @SerializedName("s_district")
    private Long mSDistrict;
    @SerializedName("s_division")
    private Long mSDivision;
    @SerializedName("s_latitude")
    private Double mSLatitude;
    @SerializedName("s_longitude")
    private Double mSLongitude;
    @SerializedName("s_thana")
    private Long mSThana;
    @SerializedName("shipment_cost")
    private Object mShipmentCost;
    @SerializedName("shipment_no")
    private String mShipmentNo;
    @SerializedName("shipment_type")
    private Long mShipmentType;
    @SerializedName("status")
    private Long mStatus;

    public String getBnName() {
        return mBnName;
    }

    public void setBnName(String bnName) {
        mBnName = bnName;
    }

    public Long getCodAmount() {
        return mCodAmount;
    }

    public void setCodAmount(Long codAmount) {
        mCodAmount = codAmount;
    }

    public String getContactNo() {
        return mContactNo;
    }

    public void setContactNo(String contactNo) {
        mContactNo = contactNo;
    }

    public String getDAddress() {
        return mDAddress;
    }

    public void setDAddress(String dAddress) {
        mDAddress = dAddress;
    }

    public Long getDDistrict() {
        return mDDistrict;
    }

    public void setDDistrict(Long dDistrict) {
        mDDistrict = dDistrict;
    }

    public Long getDDivision() {
        return mDDivision;
    }

    public void setDDivision(Long dDivision) {
        mDDivision = dDivision;
    }

    public Double getDLatitude() {
        return mDLatitude;
    }

    public void setDLatitude(Double dLatitude) {
        mDLatitude = dLatitude;
    }

    public Double getDLongitude() {
        return mDLongitude;
    }

    public void setDLongitude(Double dLongitude) {
        mDLongitude = dLongitude;
    }

    public String getDdistName() {
        return mDdistName;
    }

    public void setDdistName(String ddistName) {
        mDdistName = ddistName;
    }

    public String getDdvBnName() {
        return mDdvBnName;
    }

    public void setDdvBnName(String ddvBnName) {
        mDdvBnName = ddvBnName;
    }

    public Long getDdvid() {
        return mDdvid;
    }

    public void setDdvid(Long ddvid) {
        mDdvid = ddvid;
    }

    public String getDdvname() {
        return mDdvname;
    }

    public void setDdvname(String ddvname) {
        mDdvname = ddvname;
    }

    public Long getDisID() {
        return mDisID;
    }

    public void setDisID(Long disID) {
        mDisID = disID;
    }

    public Long getDistname() {
        return mDistname;
    }

    public void setDistname(Long distname) {
        mDistname = distname;
    }

    public String getDrlCreatedAt() {
        return mDrlCreatedAt;
    }

    public void setDrlCreatedAt(String drlCreatedAt) {
        mDrlCreatedAt = drlCreatedAt;
    }

    public String getDrlUpdatedAt() {
        return mDrlUpdatedAt;
    }

    public void setDrlUpdatedAt(String drlUpdatedAt) {
        mDrlUpdatedAt = drlUpdatedAt;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Long getMerchantId() {
        return mMerchantId;
    }

    public void setMerchantId(Long merchantId) {
        mMerchantId = merchantId;
    }

    public String getNotForHub() {
        return mNotForHub;
    }

    public void setNotForHub(String notForHub) {
        mNotForHub = notForHub;
    }

    public String getNote() {
        return mNote;
    }

    public void setNote(String note) {
        mNote = note;
    }

    public String getPaymentCollectedbyRider() {
        return mPaymentCollectedbyRider;
    }

    public void setPaymentCollectedbyRider(String paymentCollectedbyRider) {
        mPaymentCollectedbyRider = paymentCollectedbyRider;
    }

    public String getPickupDate() {
        return mPickupDate;
    }

    public void setPickupDate(String pickupDate) {
        mPickupDate = pickupDate;
    }

    public String getProductDetail() {
        return mProductDetail;
    }

    public void setProductDetail(String productDetail) {
        mProductDetail = productDetail;
    }

    public Long getProductType() {
        return mProductType;
    }

    public void setProductType(Long productType) {
        mProductType = productType;
    }

    public String getProductWeight() {
        return mProductWeight;
    }

    public void setProductWeight(String productWeight) {
        mProductWeight = productWeight;
    }

    public String getReceiverName() {
        return mReceiverName;
    }

    public void setReceiverName(String receiverName) {
        mReceiverName = receiverName;
    }

    public String getSAddress() {
        return mSAddress;
    }

    public void setSAddress(String sAddress) {
        mSAddress = sAddress;
    }

    public Long getSDistrict() {
        return mSDistrict;
    }

    public void setSDistrict(Long sDistrict) {
        mSDistrict = sDistrict;
    }

    public Long getSDivision() {
        return mSDivision;
    }

    public void setSDivision(Long sDivision) {
        mSDivision = sDivision;
    }

    public Double getSLatitude() {
        return mSLatitude;
    }

    public void setSLatitude(Double sLatitude) {
        mSLatitude = sLatitude;
    }

    public Double getSLongitude() {
        return mSLongitude;
    }

    public void setSLongitude(Double sLongitude) {
        mSLongitude = sLongitude;
    }

    public Long getSThana() {
        return mSThana;
    }

    public void setSThana(Long sThana) {
        mSThana = sThana;
    }

    public Object getShipmentCost() {
        return mShipmentCost;
    }

    public void setShipmentCost(Object shipmentCost) {
        mShipmentCost = shipmentCost;
    }

    public String getShipmentNo() {
        return mShipmentNo;
    }

    public void setShipmentNo(String shipmentNo) {
        mShipmentNo = shipmentNo;
    }

    public Long getShipmentType() {
        return mShipmentType;
    }

    public void setShipmentType(Long shipmentType) {
        mShipmentType = shipmentType;
    }

    public Long getStatus() {
        return mStatus;
    }

    public void setStatus(Long status) {
        mStatus = status;
    }

}
