package com.drivill.courier.model.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class HubModel implements Serializable {

    @SerializedName("hubs")
    @Expose
    private List<Hub> hubs = null;

    public List<Hub> getHubs() {
        return hubs;
    }

    public void setHubs(List<Hub> hubs) {
        this.hubs = hubs;
    }


    public static class Hub implements Serializable {

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

        @SerializedName("latitude")
        @Expose
        private Double latitude;

        @SerializedName("longitude")
        @Expose
        private Double longitude;


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

        @NotNull
        @Override
        public String toString() {
            return name;
        }
    }

}
