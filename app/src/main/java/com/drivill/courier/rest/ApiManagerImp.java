package com.drivill.courier.rest;

import android.content.Context;

import com.drivill.courier.merchantModul.model.PaymentDetailslist;
import com.drivill.courier.model.model.SplashModelItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.drivill.courier.merchantModul.model.BreakDownModel;
import com.drivill.courier.merchantModul.model.DistrictModel;
import com.drivill.courier.merchantModul.model.DivisionModel;
import com.drivill.courier.merchantModul.model.EarnAndPayModel;
import com.drivill.courier.merchantModul.model.MerchantNotificationModel;
import com.drivill.courier.merchantModul.model.MerchantProfileModel;
import com.drivill.courier.merchantModul.model.NearByHubModel;
import com.drivill.courier.merchantModul.model.ShipmentCreateModel;
import com.drivill.courier.merchantModul.model.ShipmentDetailModel;
import com.drivill.courier.merchantModul.model.ShipmentModel;
import com.drivill.courier.merchantModul.model.ShippingDataBaseModel;
import com.drivill.courier.merchantModul.model.SignUpDataModel;
import com.drivill.courier.merchantModul.model.ThanaModel;
import com.drivill.courier.merchantModul.model.TrackingModel;
import com.drivill.courier.merchantModul.model.WeightAndProductTypeModel;
import com.drivill.courier.model.model.BannerResponseModel;
import com.drivill.courier.model.model.CODStatementModel;
import com.drivill.courier.model.model.HubModel;
import com.drivill.courier.model.model.LoginModel;
import com.drivill.courier.model.model.ProfileModel;
import com.drivill.courier.model.model.RiderPickupListModel;
import com.drivill.courier.model.model.StatementModel;
import com.drivill.courier.model.model.SupportModel;
import com.drivill.courier.model.model.VehicleModel;
import com.drivill.courier.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManagerImp implements ApiManager {

    ApiServices mApiService;
    Context mContext;

    public ApiManagerImp() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        String baseUrl = Constant.BASE_URL;
        mApiService = getmApiService(baseUrl, gson);
    }

    OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();

    private ApiServices getmApiService(final String baseUrl, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ApiServices.class);
    }

    @Override
    public Call<LoginModel> riderLogin(String mobile, String password,String device_token) {
        return mApiService.riderLogin(mobile, password,device_token);
    }

    @Override
    public Call<JsonObject> riderSignUp(RequestBody name, RequestBody hub_id, RequestBody address, RequestBody email, RequestBody gender, RequestBody password, RequestBody password_confirmation, RequestBody mobile, MultipartBody.Part picture, RequestBody nid_number, MultipartBody.Part nid_picture, MultipartBody.Part father_nid_pic, RequestBody father_nid, RequestBody vehicle_type_id, RequestBody dl_number, MultipartBody.Part dl_photo, MultipartBody.Part rc_photo, RequestBody brand, RequestBody model, RequestBody region, RequestBody category, RequestBody plat_number, RequestBody token_number, RequestBody longitude,
                                        RequestBody latitude) {
        return mApiService.riderSignUp(name, hub_id, address, email, gender, password, password_confirmation, mobile, picture, nid_number, nid_picture, father_nid_pic, father_nid, vehicle_type_id, dl_number, dl_photo, rc_photo, brand, model, region, category, plat_number, token_number, longitude,
                latitude);
    }

    @Override
    public Call<JsonObject> riderSignUp(RequestBody name, RequestBody hub_id, RequestBody address, RequestBody email, RequestBody gender, RequestBody password, RequestBody password_confirmation, RequestBody mobile, MultipartBody.Part picture, RequestBody nid_number, MultipartBody.Part nid_picture, MultipartBody.Part father_nid_pic, RequestBody father_nid, RequestBody vehicle_type_id, RequestBody longitude,
                                        RequestBody latitude) {
        return mApiService.riderSignUp(name, hub_id, address, email, gender, password, password_confirmation, mobile, picture, nid_number, nid_picture, father_nid_pic, father_nid, vehicle_type_id, longitude,
                latitude);
    }

    @Override
    public Call<JsonObject> riderUpdateProfile(String token, Map<String, RequestBody> partMap, MultipartBody.Part file) {
        return mApiService.riderUpdateProfile(token, partMap, file);
    }


    @Override
    public Call<JsonObject> riderMobileChecking(String check_mobile) {
        return mApiService.riderMobileChecking(check_mobile);
    }

    @Override
    public Call<JsonObject> rideSendOtp(String mobile, String otp_for) {
        return mApiService.rideSendOtp(mobile, otp_for);
    }

    @Override
    public Call<JsonObject> rideSendForgotOtp(String mobile) {
        return mApiService.rideSendForgotOtp(mobile);
    }

    @Override
    public Call<JsonObject> resetPasswordRider(String id, String password, String password_confirmation, String otp) {
        return mApiService.resetPasswordRider(id, password, password_confirmation, otp);
    }

    @Override
    public Call<JsonObject> refreshToken(String refresh_token) {
        return mApiService.refreshToken(refresh_token);
    }

    @Override
    public Call<JsonObject> riderShipmentUpdate(String token, String id, String status, String otp) {
        return mApiService.riderShipmentUpdate(token, id, status, otp);
    }

    @Override
    public Call<JsonObject> riderShipmentSendOTP(String token, String shipID, String otp_to) {
        return mApiService.riderShipmentSendOTP(token, shipID, otp_to);
    }

    @Override
    public Call<JsonObject> riderShippingCancelReasonList(String token, String reason_for) {
        return mApiService.riderShippingCancelReasonList(token, reason_for);
    }

    @Override
    public Call<ShipmentCreateModel> riderShipmentCancel(String token, String id, String reason) {
        return mApiService.riderShipmentCancel(token, id, reason);
    }

    @Override
    public Call<StatementModel> riderStatement(String token, String filter, String from_date, String to_date) {
        return mApiService.riderStatement(token, filter, from_date, to_date);
    }

    @Override
    public Call<JsonObject> riderDepositAmount(String token, String amount) {
        return mApiService.riderDepositAmount(token, amount);
    }

    @Override
    public Call<NearByHubModel> riderNearestHub(String token, String latitude, String longitude) {
        return mApiService.riderNearestHub(token, latitude, longitude);
    }

    @Override
    public Call<JsonObject> riderNearestRider(String token, String latitude, String longitude) {
        return mApiService.riderNearestRider(token, latitude, longitude);
    }

    @Override
    public Call<JsonObject> riderSendCurrentLocation(String token, String address, String latitude, String longitude) {
        return mApiService.riderSendCurrentLocation(token, address, latitude, longitude);
    }

    @Override
    public Call<JsonObject> riderTransferShipHub(String token, String shipment_id, String hub_id) {
        return mApiService.riderTransferShipHub(token, shipment_id, hub_id);
    }

    @Override
    public Call<JsonObject> riderTransferShipToRider(String token, String shipment_id, String rider_id) {
        return mApiService.riderTransferShipToRider(token, shipment_id, rider_id);
    }


    @Override
    public Call<ProfileModel> riderProfile(String token) {
        return mApiService.riderProfile(token);
    }

    @Override
    public Call<HubModel> getHubRider() {
        return mApiService.getHubRider();
    }

    @Override
    public Call<VehicleModel> getVehicleData() {
        return mApiService.getVehicleData();
    }

    @Override
    public Call<JsonObject> logoutRider(String token) {
        return mApiService.logoutRider(token);
    }

    @Override
    public Call<RiderPickupListModel> riderShipmentsList(String token, String latitude, String status, String longitude) {
        return mApiService.riderShipmentsList(token, latitude, status, longitude);
    }


    @Override
    public Call<JsonObject> riderPaymentHistory(String token) {
        return mApiService.riderPaymentHistory(token);
    }

    @Override
    public Call<CODStatementModel> getRider_COD_statement(String token, String pageNum) {
        return mApiService.getRider_COD_statement(token, pageNum);
    }

    @Override
    public Call<SupportModel> getRideSupport() {
        return mApiService.getRideSupport();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Merchant Module Api
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public Call<LoginModel> merchantLogin(String mobile, String password,String device_token) {
        return mApiService.merchantLogin(mobile, password,device_token);
    }

    @Override
    public Call<JsonObject> merchantSignUp(RequestBody name,
                                           RequestBody address,
                                           RequestBody email,
                                           RequestBody mobile,
                                           RequestBody password,
                                           RequestBody password_confirmation,
                                           RequestBody fb_page,
                                           MultipartBody.Part picture,
                                           RequestBody nid_number,
                                           RequestBody buss_name,
                                           RequestBody buss_phone,
                                           RequestBody buss_address,
                                           RequestBody latitude, RequestBody longitude, MultipartBody.Part business_logo, RequestBody payment_method, HashMap<String, RequestBody> product_type, MultipartBody.Part trade_lic_no) {
        return mApiService.merchantSignUp(name, address, email, mobile, password, password_confirmation, fb_page, picture, nid_number, buss_name, buss_phone, buss_address, latitude, longitude, business_logo, payment_method, product_type, trade_lic_no);
    }

    @Override
    public Call<JsonObject> merchantUpdateProfile(String token, Map<String, RequestBody> partMap, MultipartBody.Part file) {
        return mApiService.merchantUpdateProfile(token, partMap, file);
    }


    @Override
    public Call<JsonObject> MobileCheckingMerchant(String check_mobile) {
        return mApiService.MobileCheckingMerchant(check_mobile);
    }

    @Override
    public Call<JsonObject> sendOtpMerchant(String mobile, String otp_for) {
        return mApiService.sendOtpMerchant(mobile, otp_for);
    }

    @Override
    public Call<JsonObject> forgotPasswordMerchant(String mobile) {
        return mApiService.forgotPasswordMerchant(mobile);
    }

    @Override
    public Call<JsonObject> resetPasswordMerchant(String id, String password, String password_confirmation, String otp) {
        return mApiService.resetPasswordMerchant(id, password, password_confirmation, otp);
    }

    @Override
    public Call<JsonObject> refreshTokenMerchant(String refresh_token) {
        return mApiService.refreshTokenMerchant(refresh_token);
    }

    @Override
    public Call<ShipmentCreateModel> createShipment(String token, String receiver_name, String contact_no, String product_detail, String product_type, String product_weight, String note, String s_thana, String s_district, String s_division, String d_thana, String d_district, String d_division, String s_address, String d_address, String shipment_type, String s_latitude,
                                                    String s_longitude,
                                                    String d_latitude,
                                                    String d_longitude,
                                                    String cod_amount,
                                                    String pickup_date) {
        return mApiService.createShipment(token, receiver_name, contact_no, product_detail, product_type, product_weight, note, s_thana, s_district, s_division, d_thana, d_district, d_division, s_address, d_address, shipment_type, s_latitude,
                s_longitude,
                d_latitude,
                d_longitude, cod_amount, pickup_date);
    }

    @Override
    public Call<ShipmentCreateModel> shipmentCancel(String token, String id, String reason) {
        return mApiService.shipmentCancel(token, id, reason);
    }

    @Override
    public Call<JsonObject> send_to_pickup(String token, Map<String, String> ship_id) {
        return mApiService.send_to_pickup(token, ship_id);
    }

    @Override
    public Call<JsonObject> merchantShippingCancelReasonList(String token, String reason_for) {
        return mApiService.merchantShippingCancelReasonList(token, reason_for);
    }

    @Override
    public Call<ShippingDataBaseModel> merchantShippingDatabase(String token, String filter) {
        return mApiService.merchantShippingDatabase(token, filter);
    }

    @Override
    public Call<BreakDownModel> merchantPayBreakDown(String token, String filter,
                                                     String from_date,
                                                     String to_date) {
        return mApiService.merchantPayBreakDown(token, filter, from_date, to_date);
    }

    @Override
    public Call<JsonObject> merchantShipComplain(String token, String shipment_id, String complain) {
        return mApiService.merchantShipComplain(token, shipment_id, complain);
    }


    @Override
    public Call<MerchantProfileModel> getProfileMerchant(String token) {
        return mApiService.getProfileMerchant(token);
    }

    @Override
    public Call<SignUpDataModel> getSignUpDataMerchant() {
        return mApiService.getSignUpDataMerchant();
    }

    @Override
    public Call<ArrayList<DivisionModel>> getDivisionMerchant() {
        return mApiService.getDivisionMerchant();
    }

    @Override
    public Call<ArrayList<DistrictModel>> getDistrictMerchant(String divId) {
        return mApiService.getDistrictMerchant(divId);
    }

    @Override
    public Call<ArrayList<ThanaModel>> getThanaMerchant(String disId) {
        return mApiService.getThanaMerchant(disId);
    }

    @Override
    public Call<ArrayList<ShipmentModel>> getShipmentMerchant(String token, String time) {
        return mApiService.getShipmentMerchant(token, time);
    }

    @Override
    public Call<TrackingModel> getShipmentTracking(String token, String shipment_no) {
        return mApiService.getShipmentTracking(token, shipment_no);
    }

    @Override
    public Call<NearByHubModel> getNearByHub(String token, String latitude, String longitude) {
        return mApiService.getNearByHub(token, latitude, longitude);
    }

    @Override
    public Call<JsonObject> logoutMerchant(String token) {
        return mApiService.logoutMerchant(token);
    }

    @Override
    public Call<JsonObject> merchantPaymentHistory(String token, String pageNum) {
        return mApiService.merchantPaymentHistory(token, pageNum);
    }

    @Override
    public Call<EarnAndPayModel> merchantEarnAndPay(String token, String pageNum, String dateFrom,String dateto) {
        return mApiService.merchantEarnAndPay(token, pageNum,dateFrom,dateto);
    }

    @Override
    public Call<JsonObject> withdrawRequest(String token) {
        return mApiService.withdrawRequest(token);
    }

    @Override
    public Call<ShipmentDetailModel> merchantShipmentsDetailById(String token, String shipId) {
        return mApiService.merchantShipmentsDetailById(token, shipId);
    }

    @Override
    public Call<ArrayList<SplashModelItem>> getSplashItem() {
        return mApiService.getSplashItems();
    }

    @Override
    public Call<JsonObject> getMerchantSupport() {
        return mApiService.getMerchantSupport();
    }

    @Override
    public Call<WeightAndProductTypeModel> getWeightAndProductList(String token) {
        return mApiService.getWeightAndProductList(token);
    }

    @Override
    public Call<MerchantNotificationModel> getMerchantNotificationList(String token) {
        return mApiService.getMerchantNotificationList(token);
    }

    @Override
    public Call<MerchantNotificationModel> getRiderNotificationList(String token) {
        return mApiService.getRiderNotificationList(token);
    }


    @Override
    public Call<ArrayList<BannerResponseModel>> getMerchantBanner(String token) {
        return mApiService.getMerchantBanner(token);
    }

    @Override
    public Call<JsonObject> notificationUpdateMerchant(String token, String id) {
        return mApiService.notificationUpdateMerchant(token, id);
    }

    @Override
    public Call<JsonObject> notificationUpdateRider(String token, String id) {
        return mApiService.notificationUpdateRider(token, id);
    }

    @Override
    public Call<PaymentDetailslist> PaymentDetails(String token, String Txt_Id) {
        return mApiService.PaymentDetails(token, Txt_Id);
    }

    @Override
    public Call<PaymentDetailslist> PaymentDetails_viaID(String token, String Id) {
        return mApiService.PaymentDetails_ViaID(token, Id);
    }

    @Override
    public Call<JsonObject> AddMerchantBankDetails(String token, String acc_holder_name, String bankname, String branch_name, String account_number) {
        return mApiService.AddMerchantBankDetails(token, acc_holder_name,bankname,branch_name,account_number);
    }

    @Override
    public Call<JsonObject> riderShipmentUpdate_WithReason(String token, String id, String status, String otp, String reason) {
        return mApiService.riderShipmentUpdate_WithReason(token, id, status, otp, reason);
    }

}
