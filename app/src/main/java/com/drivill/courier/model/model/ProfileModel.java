package com.drivill.courier.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileModel implements Serializable {
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

    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;

    @SerializedName("emergency_no")
    @Expose
    private String emergency_no;

    @SerializedName("delivered")
    @Expose
    private String delivered;

    @SerializedName("earned")
    @Expose
    private String earned;

    @SerializedName("years")
    @Expose
    private String years;


    @SerializedName("hub")
    @Expose
    private Hub hub;
    @SerializedName("vtype")
    @Expose
    private Vtype vtype;

    @SerializedName("vehicle")
    @Expose
    private Vehicle vehicle;


    public String getDelivered() {
        return delivered;
    }

    public void setDelivered(String delivered) {
        this.delivered = delivered;
    }

    public String getEarned() {
        return earned;
    }

    public void setEarned(String earned) {
        this.earned = earned;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getEmergency_no() {
        return emergency_no;
    }

    public void setEmergency_no(String emergency_no) {
        this.emergency_no = emergency_no;
    }

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

    public Hub getHub() {
        return hub;
    }

    public void setHub(Hub hub) {
        this.hub = hub;
    }

    public Vtype getVtype() {
        return vtype;
    }

    public void setVtype(Vtype vtype) {
        this.vtype = vtype;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public class Vehicle implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("delivery_rider_id")
        @Expose
        private Integer deliveryRiderId;
        @SerializedName("dl_photo")
        @Expose
        private String dlPhoto;
        @SerializedName("dl_number")
        @Expose
        private String dlNumber;
        @SerializedName("brand")
        @Expose
        private String brand;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("region")
        @Expose
        private String region;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("plat_number")
        @Expose
        private String platNumber;
        @SerializedName("token_number")
        @Expose
        private String tokenNumber;
        @SerializedName("rc_photo")
        @Expose
        private String rcPhoto;
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

        public Integer getDeliveryRiderId() {
            return deliveryRiderId;
        }

        public void setDeliveryRiderId(Integer deliveryRiderId) {
            this.deliveryRiderId = deliveryRiderId;
        }

        public String getDlPhoto() {
            return dlPhoto;
        }

        public void setDlPhoto(String dlPhoto) {
            this.dlPhoto = dlPhoto;
        }

        public String getDlNumber() {
            return dlNumber;
        }

        public void setDlNumber(String dlNumber) {
            this.dlNumber = dlNumber;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getPlatNumber() {
            return platNumber;
        }

        public void setPlatNumber(String platNumber) {
            this.platNumber = platNumber;
        }

        public String getTokenNumber() {
            return tokenNumber;
        }

        public void setTokenNumber(String tokenNumber) {
            this.tokenNumber = tokenNumber;
        }

        public String getRcPhoto() {
            return rcPhoto;
        }

        public void setRcPhoto(String rcPhoto) {
            this.rcPhoto = rcPhoto;
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

    public class Hub implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("latitude")
        @Expose
        private Object latitude;
        @SerializedName("longitude")
        @Expose
        private Object longitude;
        @SerializedName("hbsid")
        @Expose
        private String hbsid;
        @SerializedName("thana")
        @Expose
        private Integer thana;
        @SerializedName("district")
        @Expose
        private Integer district;
        @SerializedName("division")
        @Expose
        private Integer division;
        @SerializedName("picture")
        @Expose
        private String picture;
        @SerializedName("supervisor_name")
        @Expose
        private String supervisorName;
        @SerializedName("sup_phone")
        @Expose
        private String supPhone;
        @SerializedName("sup_picture")
        @Expose
        private String supPicture;
        @SerializedName("sup_tin_no")
        @Expose
        private String supTinNo;
        @SerializedName("sup_nid_no")
        @Expose
        private String supNidNo;
        @SerializedName("sup_nid_pic")
        @Expose
        private String supNidPic;
        @SerializedName("sup_tin_pic")
        @Expose
        private String supTinPic;
        @SerializedName("tl_picture")
        @Expose
        private String tlPicture;
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
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;

        public Integer getId() {
            return id;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHbsid() {
            return hbsid;
        }

        public void setHbsid(String hbsid) {
            this.hbsid = hbsid;
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

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getSupervisorName() {
            return supervisorName;
        }

        public void setSupervisorName(String supervisorName) {
            this.supervisorName = supervisorName;
        }

        public String getSupPhone() {
            return supPhone;
        }

        public void setSupPhone(String supPhone) {
            this.supPhone = supPhone;
        }

        public String getSupPicture() {
            return supPicture;
        }

        public void setSupPicture(String supPicture) {
            this.supPicture = supPicture;
        }

        public String getSupTinNo() {
            return supTinNo;
        }

        public void setSupTinNo(String supTinNo) {
            this.supTinNo = supTinNo;
        }

        public String getSupNidNo() {
            return supNidNo;
        }

        public void setSupNidNo(String supNidNo) {
            this.supNidNo = supNidNo;
        }

        public String getSupNidPic() {
            return supNidPic;
        }

        public void setSupNidPic(String supNidPic) {
            this.supNidPic = supNidPic;
        }

        public String getSupTinPic() {
            return supTinPic;
        }

        public void setSupTinPic(String supTinPic) {
            this.supTinPic = supTinPic;
        }

        public String getTlPicture() {
            return tlPicture;
        }

        public void setTlPicture(String tlPicture) {
            this.tlPicture = tlPicture;
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

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

    }

    public class Vtype implements Serializable {

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
        private Object createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;

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

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
