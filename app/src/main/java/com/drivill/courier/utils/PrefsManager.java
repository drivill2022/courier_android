package com.drivill.courier.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sachin Verma on 06/29/21.
 */

public class PrefsManager {
    private final String pakg = "com.drivill.courier";
    public final String PREFS_NAME = pakg + ".PREFS_NAME";
    public final String UserName = pakg + ".UserName";
    public final String MobileNum = pakg + ".MobileNum";
    public final String Is_First = pakg + ".Is_First";
    public final String User_ID = pakg + ".User_ID";
    public final String EmailAddress = pakg + ".EmailAddress";
    public final String ProfileImg = pakg + ".ProfileImg";
    public final String UserToken = pakg + ".UserToken";
    public final String OneTimeOtp = pakg + ".OneTimeOtp";
    public final String IS_LOGIN = pakg + ".IS_LOGIN";

    public final String TOKEN_TYPE = pakg + ".TOKEN_TYPE";
    public final String REFRESH_TOKEN = pakg + ".REFRESH_TOKEN";

    public final String GENDER = pakg.concat(".GENDER");
    public final String hub_id = pakg.concat(".hub_id");
    public final String Address = pakg.concat(".Address");
    public final String referral_code = pakg.concat(".referral_code");
    public final String vehicle_type_id = pakg.concat(".vehicle_type_id");
    public final String isRider = pakg.concat(".isRider");
    public final String lat = pakg.concat(".lat");
    public final String lon = pakg.concat(".lon");
    public final String division = pakg.concat(".division");
    public final String district = pakg.concat(".district");
    public final String thana = pakg.concat(".thana");

    public final String paymentMethod = pakg.concat(".paymentMethod");
    public final String emergencyNum = pakg.concat(".emergencyNum");
    public final String businessName = pakg.concat(".businessName");
    public final String businessLogo = pakg.concat(".businessLogo");


    public final String enableNotification = pakg.concat(".enableNotification");
    public final String enableCamera = pakg.concat(".enableCamera");
    public final String enableLocation = pakg.concat(".enableLocation");

    public String totalYear = pakg.concat(".totalYear");
    public String totalEarn = pakg.concat(".totalEarn");
    public String totalDelivery = pakg.concat(".totalDelivery");
    public String deviceToken = pakg.concat(".deviceToken");


    SharedPreferences mSharedPreferences;

    public PrefsManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }


    public String get_device_token() {
        return mSharedPreferences.getString(deviceToken, "");
    }

    public void set_device_token(String device_token) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(this.deviceToken, device_token);
        editor.commit();
    }
    public String getBusinessLogo() {
        return mSharedPreferences.getString(businessLogo, "");
    }

    public void setBusinessLogo(String logo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(this.businessLogo, logo);
        editor.commit();
    }

    public String getTotalYear() {
        return mSharedPreferences.getString(totalYear, "");
    }

    public void setTotalYear(String totalYear) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(this.totalYear, totalYear);
        editor.commit();
    }

    public String getTotalEarn() {
        return mSharedPreferences.getString(totalEarn, "");
    }

    public void setTotalEarn(String totalEarn) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(this.totalEarn, totalEarn);
        editor.commit();
    }

    public String getTotalDelivery() {
        return mSharedPreferences.getString(totalDelivery, "");
    }

    public void setTotalDelivery(String totalDelivery) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(this.totalDelivery, totalDelivery);
        editor.commit();
    }

    public String get_businessName() {
        return mSharedPreferences.getString(businessName, "");
    }

    public void set_businessName(String lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(businessName, lo);
        editor.commit();

    }

    public String get_emergencyNum() {
        return mSharedPreferences.getString(emergencyNum, "");
    }

    public void set_emergencyNumMethod(String lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(emergencyNum, lo);
        editor.commit();

    }


    public String getPaymentMethod() {
        return mSharedPreferences.getString(paymentMethod, "");
    }

    public void setPaymentMethod(String lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(paymentMethod, lo);
        editor.commit();

    }

    public String getLatitude() {
        return mSharedPreferences.getString(lat, "");

    }

    public String getLongitude() {
        return mSharedPreferences.getString(lon, "");

    }

    public void setLongitude(String lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(lon, lo);
        editor.commit();

    }

    public void setLatitude(String lat) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(this.lat, lat);
        editor.commit();
    }


    public boolean getEnableNotification() {
        return mSharedPreferences.getBoolean(enableNotification, false);
    }

    public void setEnableNotification(boolean not) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(this.enableNotification, not);
        editor.commit();
    }

    public boolean getEnableCamera() {
        return mSharedPreferences.getBoolean(enableCamera, false);
    }

    public void setEnableCamera(boolean camera) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(this.enableCamera, camera);
        editor.commit();
    }

    public boolean getEnableLocation() {
        return mSharedPreferences.getBoolean(enableLocation, false);
    }

    public void setEnableLocation(boolean location) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(this.enableLocation, location);
        editor.commit();
    }


    public boolean getIsRider() {
        return mSharedPreferences.getBoolean(isRider, false);
    }

    public void setIsRider(boolean isRider) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(this.isRider, isRider);
        editor.commit();
    }

    public String getGENDER() {
        return mSharedPreferences.getString(GENDER, "");

    }

    public void setGENDER(String gender) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(GENDER, gender);
        editor.commit();
    }

    public String getHub_id() {
        return mSharedPreferences.getString(hub_id, "");

    }

    public void setHub_id(String hubid) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(hub_id, hubid);
        editor.commit();
    }

    public String getAddress() {
        return mSharedPreferences.getString(Address, "");

    }

    public void setAddress(String address) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(Address, address);
        editor.commit();
    }

    public String getVehicle_type_id() {
        return mSharedPreferences.getString(vehicle_type_id, "");
    }

    public void setVehicle_type_id(String id) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(vehicle_type_id, id);
        editor.commit();
    }

    public String getTOKEN_TYPE() {
        return mSharedPreferences.getString(TOKEN_TYPE, "");
    }

    public void setTOKEN_TYPE(String token_type) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(TOKEN_TYPE, token_type);
        editor.commit();

    }

    public String getREFRESH_TOKEN() {
        return mSharedPreferences.getString(REFRESH_TOKEN, "");

    }

    public void setREFRESH_TOKEN(String refresh_token) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(REFRESH_TOKEN, refresh_token);
        editor.commit();
    }

    public boolean getIS_LOGIN() {
        return mSharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIS_LOGIN(boolean is_login) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, is_login);
        editor.commit();
    }

    public String getOneTimeOtp() {
        return mSharedPreferences.getString(OneTimeOtp, "");
    }

    public void setOneTimeOtp(String oneTimeOtp) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(OneTimeOtp, oneTimeOtp);
        editor.commit();
    }

    public String getUserToken() {
        return mSharedPreferences.getString(UserToken, "");
    }

    public void setUserToken(String userToken) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(UserToken, userToken);
        editor.commit();
    }

    public String getProfileImg() {
        return mSharedPreferences.getString(ProfileImg, "");
    }

    public void setProfileImg(String profileImg) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ProfileImg, profileImg);
        editor.commit();
    }

    public String getEmailAddress() {
        return mSharedPreferences.getString(EmailAddress, "");
    }

    public void setEmailAddress(String emailAddress) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(EmailAddress, emailAddress);
        editor.commit();
    }

    public String getUserName() {
        return mSharedPreferences.getString(UserName, "");
    }

    public void setUserName(String username) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(UserName, username);
        editor.commit();
    }

    public String getMobileNum() {
        return mSharedPreferences.getString(MobileNum, "");
    }

    public void setMobileNum(String mobileNum) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(MobileNum, mobileNum);
        editor.commit();
    }

    public boolean getIsFirst() {
        return mSharedPreferences.getBoolean(Is_First, true);
    }

    public void setIsFirst(boolean first) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(Is_First, first);
        editor.commit();
    }


    public String getUserId() {
        return mSharedPreferences.getString(User_ID, "");
    }


    public void setUserId(String id) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(User_ID, id); // A Editor always stores data in key value format in a Sharedpreference.
        editor.commit();
    }


    public void clearSharePreference() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.commit();

    }


    public void removeSharePreference(String STRING) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(STRING);
        editor.commit();

    }


    public int get_district() {
        return mSharedPreferences.getInt(district, 0);
    }

    public void set_district(int lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(district, lo);
        editor.commit();

    }

    public int get_division() {
        return mSharedPreferences.getInt(division, 0);
    }

    public void set_division(int lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(division, lo);
        editor.commit();

    }

    public int get_thana() {
        return mSharedPreferences.getInt(thana, 0);
    }

    public void set_thana(int lo) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(thana, lo);
        editor.commit();

    }

}
