package com.drivill.courier.model.model;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.drivill.courier.R;

import java.io.File;

public class SignUpModel {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("hub_id")
    @Expose
    private String hubId;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("referral_by")
    @Expose
    private String referral_by;


    @SerializedName("picture")
    @Expose
    private File picture;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("nid_number")
    @Expose
    private String nidNumber;

    @SerializedName("nid_picture")
    @Expose
    private File nidPicture;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("father_nid")
    @Expose
    private String fatherNid;
    @SerializedName("father_nid_pic")
    @Expose
    private File fatherNidPic;
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

    @SerializedName("dl_number")
    @Expose
    private String dl_number;

    @SerializedName("dl_photo")
    @Expose
    private File dl_photo;
    @SerializedName("rc_photo")
    @Expose
    private File rc_photo;
    @SerializedName("manufracture")
    @Expose
    private String manufracture;

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
    private String plat_number;

    @SerializedName("token_number")
    @Expose
    private String token_number;

    @SerializedName("password_confirmation")
    @Expose
    private String password_confirmation;

    @SerializedName("latitude")
    @Expose
    private String latitude="";

    @SerializedName("longitude")
    @Expose
    private String longitude="";


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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReferral_by() {
        return referral_by;
    }

    public void setReferral_by(String referral_by) {
        this.referral_by = referral_by;
    }

    public File getFatherNidPic() {
        return fatherNidPic;
    }

    public void setFatherNidPic(File fatherNidPic) {
        this.fatherNidPic = fatherNidPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHubId() {
        return hubId;
    }

    public void setHubId(String hubId) {
        this.hubId = hubId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNidNumber() {
        return nidNumber;
    }

    public void setNidNumber(String nidNumber) {
        this.nidNumber = nidNumber;
    }

    public File getNidPicture() {
        return nidPicture;
    }

    public void setNidPicture(File nidPicture) {
        this.nidPicture = nidPicture;
    }

    public String getFatherNid() {
        return fatherNid;
    }

    public void setFatherNid(String fatherNid) {
        this.fatherNid = fatherNid;
    }

    public Integer getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(Integer vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public File getDl_photo() {
        return dl_photo;
    }

    public void setDl_photo(File dl_photo) {
        this.dl_photo = dl_photo;
    }

    public String getDl_number() {
        return dl_number;
    }

    public void setDl_number(String dl_number) {
        this.dl_number = dl_number;
    }

    public File getRc_photo() {
        return rc_photo;
    }

    public void setRc_photo(File rc_photo) {
        this.rc_photo = rc_photo;
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

    public String getPlat_number() {
        return plat_number;
    }

    public void setPlat_number(String plat_number) {
        this.plat_number = plat_number;
    }

    public String getToken_number() {
        return token_number;
    }

    public void setToken_number(String token_number) {
        this.token_number = token_number;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

    public boolean validate(Context context) {
        if (getVehicleTypeId() == null) {
            Toast.makeText(context, "please select vehicle type", Toast.LENGTH_SHORT).show();
            return false;
        } else if (getVehicleTypeId() == 1) {
            return true;
        } else {
            if (getDl_number() == null || getDl_number().isEmpty()) {
                Toast.makeText(context, "Please Enter " + context.getResources().getString(R.string.driving_license_number), Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (getDl_photo() == null || getDl_photo().getName().isEmpty()) {
                    Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.driving_license_photo), Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    if (getPlat_number() == null || getPlat_number().isEmpty()) {
                        Toast.makeText(context, "Please Enter " + context.getResources().getString(R.string.plate_number), Toast.LENGTH_SHORT).show();
                        return false;
                    } else {
                        if (getRc_photo() == null || getRc_photo().getName().isEmpty()) {
                            Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.registration_photo), Toast.LENGTH_SHORT).show();
                            return false;
                        } else {
                            if (getBrand() == null || getBrand().isEmpty()) {
                                Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.motorcycle_s_brand), Toast.LENGTH_SHORT).show();
                                return false;
                            } else {
                                if (getModel() == null || getModel().isEmpty()) {
                                    Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.motorcycle_s_model), Toast.LENGTH_SHORT).show();
                                    return false;
                                } else {
                                    if (getRegion() == null || getRegion().isEmpty()) {
                                        Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.region), Toast.LENGTH_SHORT).show();
                                        return false;
                                    } else {
                                        if (getCategory() == null || getCategory().isEmpty()) {
                                            Toast.makeText(context, "Please Select " + context.getResources().getString(R.string.category), Toast.LENGTH_SHORT).show();
                                            return false;
                                        } else {
                                            if (getToken_number() == null || getToken_number().isEmpty()) {
                                                Toast.makeText(context, "Please Enter " + context.getResources().getString(R.string.tokenNum), Toast.LENGTH_SHORT).show();
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
                }
            }
        }
    }
}
