package com.drivill.courier.merchantModul.model;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.drivill.courier.R;

import java.io.File;

public class MerchantProfileModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("mrid")
    @Expose
    private String mrid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("business_logo")
    @Expose
    private String businessLogo;
    @SerializedName("nid_number")
    @Expose
    private String nidNumber;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("buss_name")
    @Expose
    private String bussName;
    @SerializedName("buss_address")
    @Expose
    private String bussAddress;
    @SerializedName("buss_phone")
    @Expose
    private String bussPhone;
    @SerializedName("trade_lic_no")
    @Expose
    private String tradeLicNo;
    @SerializedName("payment_method")
    @Expose
    private Integer paymentMethod;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("fb_page")
    @Expose
    private String fbPage = "";
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("thana")
    @Expose
    private int thana;
    @SerializedName("district")
    @Expose
    private int district;
    @SerializedName("division")
    @Expose
    private int division;
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

    @SerializedName("picture_file_type")
    @Expose
    private File profileFile;

    @SerializedName("lic_file_type")
    @Expose
    private File tradeLicense;

    @SerializedName("business")
    @Expose
    private File bussinessLogoFile;


    @SerializedName("payment_meth")
    @Expose
    private String paymentMeth;

    @SerializedName("pass")
    @Expose
    private String passWord;

    @SerializedName("cPass")
    @Expose
    private String cPassword;

    @SerializedName("latitude")
    @Expose
    private String latitude = "";

    @SerializedName("longitude")
    @Expose
    private String longitude = "";


    @SerializedName("emergency_no")
    @Expose
    private String emergency_no;

    @SerializedName("delivered")
    @Expose
    private String delivered;

    @SerializedName("earned")
    @Expose
    private String earned;

    @SerializedName("awards")
    @Expose
    private String awards;


    public String getEmergency_no() {
        return emergency_no;
    }

    public void setEmergency_no(String emergency_no) {
        this.emergency_no = emergency_no;
    }

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

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMrid() {
        return mrid;
    }

    public void setMrid(String mrid) {
        this.mrid = mrid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBusinessLogo() {
        return businessLogo;
    }

    public void setBusinessLogo(String businessLogo) {
        this.businessLogo = businessLogo;
    }

    public String getNidNumber() {
        return nidNumber;
    }

    public void setNidNumber(String nidNumber) {
        this.nidNumber = nidNumber;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getBussName() {
        return bussName;
    }

    public void setBussName(String bussName) {
        this.bussName = bussName;
    }

    public String getBussAddress() {
        return bussAddress;
    }

    public void setBussAddress(String bussAddress) {
        this.bussAddress = bussAddress;
    }

    public String getBussPhone() {
        return bussPhone;
    }

    public void setBussPhone(String bussPhone) {
        this.bussPhone = bussPhone;
    }

    public String getTradeLicNo() {
        return tradeLicNo;
    }

    public void setTradeLicNo(String tradeLicNo) {
        this.tradeLicNo = tradeLicNo;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getFbPage() {
        return fbPage;
    }

    public void setFbPage(String fbPage) {
        this.fbPage = fbPage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getThana() {
        return thana;
    }

    public void setThana(int thana) {
        this.thana = thana;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
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

///////////////////////Custom not in Response//////////////////


    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getcPassword() {
        return cPassword;
    }

    public void setcPassword(String cPassword) {
        this.cPassword = cPassword;
    }

    public File getBussinessLogoFile() {
        return bussinessLogoFile;
    }

    public void setBussinessLogoFile(File bussinessLogoFile) {
        this.bussinessLogoFile = bussinessLogoFile;
    }

    public String getPaymentMeth() {
        return paymentMeth;
    }

    public void setPaymentMeth(String paymentMeth) {
        this.paymentMeth = paymentMeth;
    }

    public File getProfileFile() {
        return profileFile;
    }

    public void setProfileFile(File profileFile) {
        this.profileFile = profileFile;
    }

    public File getTradeLicense() {
        return tradeLicense;
    }

    public void setTradeLicense(File tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public boolean validate1(Context context) {
        if (getName() == null || getName().isEmpty()) {
            Toast.makeText(context, "Please " + context.getResources().getString(R.string.enter_your_name), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (getAddress() == null || getAddress().isEmpty()) {
                Toast.makeText(context, "Please " + context.getResources().getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (getNidNumber() == null || getNidNumber().length() < 5) {
                    if (getNidNumber() == null || getNidNumber().isEmpty()) {
                        Toast.makeText(context, "Please Enter " + context.getResources().getString(R.string.nid_number), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, context.getResources().getString(R.string.enter_17_digit_nid_number), Toast.LENGTH_SHORT).show();

                    }
                    return false;
                } else {
                    if (getProfileFile() == null || getProfileFile().getName().isEmpty()) {
                        Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.profile_photo), Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
    }


    public boolean validate2(Context context) {
        if (getBussName() == null || getBussName().isEmpty()) {
            Toast.makeText(context, "Please " + context.getResources().getString(R.string.enter_business_name), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (getBussPhone() == null || getBussPhone().isEmpty()) {
                Toast.makeText(context, "Please " + context.getResources().getString(R.string.enter_business_phone), Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (getBussAddress() == null || getBussAddress().isEmpty()) {
                    Toast.makeText(context, "Please " + context.getResources().getString(R.string.enter_business_address), Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    if (getPaymentMethod() == null) {
                        Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.payment_method), Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        if (getBussinessLogoFile() == null || getBussinessLogoFile().getName().isEmpty()) {
                            Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.business_logo), Toast.LENGTH_SHORT).show();
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
    }
}

