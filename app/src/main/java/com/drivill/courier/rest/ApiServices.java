package com.drivill.courier.rest;

import com.drivill.courier.merchantModul.model.PaymentDetailslist;
import com.google.gson.JsonArray;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *
 * <p>
 * <p>
 * Copy and Search below
 * Rider- Post_method,Get_method
 * Merch- post_Method_For_Merchant,get_Method_For_Merchant
 */

public interface ApiServices {

    ////////////////Post_method////////////////////////////////
    @FormUrlEncoded
    @POST("rider/login")
    Call<LoginModel> riderLogin(@Field("mobile") String mobile,
                                @Field("password") String password,
                                @Field("device_token") String device_token);


    @Multipart
    @POST("rider/register")
    Call<JsonObject> riderSignUp(@Part("name") RequestBody name,
                                 @Part("hub_id") RequestBody hub_id,
                                 @Part("address") RequestBody address,
                                 @Part("email") RequestBody email,
                                 @Part("gender") RequestBody gender,
                                 @Part("password") RequestBody password,
                                 @Part("password_confirmation") RequestBody password_confirmation,
                                 @Part("mobile") RequestBody mobile,
                                 // @Field("picture") String picture,
                                 @Part MultipartBody.Part picture,
                                 @Part("nid_number") RequestBody nid_number,
                                 //  @Field("nid_picture") String nid_picture,
                                 @Part MultipartBody.Part nid_picture,
                                 @Part MultipartBody.Part father_nid_pic,
                                 @Part("father_nid") RequestBody father_nid,
                                 //  @Part("thana") RequestBody thana,
                                 //  @Part("district") RequestBody district,
                                 //   @Part("division") RequestBody division,
                                 @Part("vehicle_type_id") RequestBody vehicle_type_id,
                                 @Part("dl_number") RequestBody dl_number,
                                 // @Part("dl_photo") String dl_photo,
                                 @Part MultipartBody.Part dl_photo,
                                 // @Part("rc_photo") String rc_photo,
                                 @Part MultipartBody.Part rc_photo,
                                 @Part("brand") RequestBody brand,
                                 @Part("model") RequestBody model,
                                 @Part("region") RequestBody region,
                                 @Part("category") RequestBody category,
                                 @Part("plat_number") RequestBody plat_number,
                                 @Part("token_number") RequestBody token_number,
                                 @Part("longitude") RequestBody longitude,
                                 @Part("latitude") RequestBody latitude
    );

    @Multipart
    @POST("rider/register")
    Call<JsonObject> riderSignUp(@Part("name") RequestBody name,
                                 @Part("hub_id") RequestBody hub_id,
                                 @Part("address") RequestBody address,
                                 @Part("email") RequestBody email,
                                 @Part("gender") RequestBody gender,
                                 @Part("password") RequestBody password,
                                 @Part("password_confirmation") RequestBody password_confirmation,
                                 @Part("mobile") RequestBody mobile,
                                 @Part MultipartBody.Part picture,
                                 @Part("nid_number") RequestBody nid_number,
                                 @Part MultipartBody.Part nid_picture,
                                 @Part MultipartBody.Part father_nid_pic,
                                 @Part("father_nid") RequestBody father_nid,
                                 @Part("vehicle_type_id") RequestBody vehicle_type_id,
                                 @Part("longitude") RequestBody longitude,
                                 @Part("latitude") RequestBody latitude
    );


    /*  @Headers({
              "Accept: application/json",
              "Content-Type: application/json"})*/
    @Multipart
    @POST("rider/profile/update")
    Call<JsonObject> riderUpdateProfile(
            @Header("Authorization") String token,
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("rider/check-mobile")
    Call<JsonObject> riderMobileChecking(@Field("mobile") String check_mobile);

    @FormUrlEncoded
    @POST("rider/send-otp")
    Call<JsonObject> rideSendOtp(@Field("mobile") String mobile,
                                 @Field("otp_for") String otp_for);

    @FormUrlEncoded
    @POST("rider/forgot/password")
    Call<JsonObject> rideSendForgotOtp(@Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("rider/reset/password")
    Call<JsonObject> resetPasswordRider(
            @Field("id") String id,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("rider/refresh-token")
    Call<JsonObject> refreshToken(@Field("refresh_token") String refresh_token);


    @FormUrlEncoded
    @POST("rider/shipments/status-update")
    Call<JsonObject> riderShipmentUpdate(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("status") String status,
            @Field("otp") String otp);

    @FormUrlEncoded
    @POST("rider/shipments/status-update")
    Call<JsonObject> riderShipmentUpdate_WithReason(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("status") String status,
            @Field("otp") String otp,
            @Field("reason") String reason);

    @FormUrlEncoded
    @POST("rider/shipments/send-otp")
    Call<JsonObject> riderShipmentSendOTP(
            @Header("Authorization") String token,
            @Field("id") String shipID,
            @Field("otp_to") String otp_to);

    @FormUrlEncoded
    @POST("rider/shipments/cancel-reason-list")
    Call<JsonObject> riderShippingCancelReasonList(
            @Header("Authorization") String token,
            @Field("reason_for") String reason_for
    );

    @FormUrlEncoded
    @POST("rider/shipments/cancel")
    Call<ShipmentCreateModel> riderShipmentCancel(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("reason") String reason

    );

    @FormUrlEncoded
    @POST("rider/shipments/riding-statement")
    Call<StatementModel> riderStatement(
            @Header("Authorization") String token,
            @Field("filter") String filter,
            @Field("from_date") String from_date,
            @Field("to_date") String to_date
    );

    @FormUrlEncoded
    @POST("rider/shipments/deposit-amount")
    Call<JsonObject> riderDepositAmount(
            @Header("Authorization") String token,
            @Field("amount") String amount);

    @FormUrlEncoded
    @POST("rider/shipments/find-nearest-hub")
    Call<NearByHubModel> riderNearestHub(
            @Header("Authorization") String token,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("rider/shipments/find-nearest-rider")
    Call<JsonObject> riderNearestRider(
            @Header("Authorization") String token,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude);

    @FormUrlEncoded
    @POST("rider/shipments/transfer-to-hub")
    Call<JsonObject> riderTransferShipHub(
            @Header("Authorization") String token,
            @Field("shipment_id") String shipment_id,
            @Field("hub_id") String hub_id);

    @FormUrlEncoded
    @POST("rider/shipments/transfer-to-rider")
    Call<JsonObject> riderTransferShipToRider(
            @Header("Authorization") String token,
            @Field("shipment_id") String shipment_id,
            @Field("rider_id") String rider_id);

    @FormUrlEncoded
    @POST("rider/shipments/add-current-location")
    Call<JsonObject> riderSendCurrentLocation(
            @Header("Authorization") String token,
            @Field("address") String address,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude);

    ////////////////////Get_method/////////////////////////////

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("rider/profile")
    Call<ProfileModel> riderProfile(
            @Header("Authorization") String token
    );

    @GET("rider/hubs")
    Call<HubModel> getHubRider();

    @GET("rider/vehicle-data")
    Call<VehicleModel> getVehicleData();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("rider/logout")
    Call<JsonObject> logoutRider(
            @Header("Authorization") String token
    );


    @GET("rider/shipments")
    Call<RiderPickupListModel> riderShipmentsList(
            @Header("Authorization") String token,
            @Query("latitude") String latitude,
            @Query("status") String status,
            @Query("longitude") String longitude
    );


    @GET("rider/shipments/payment-history")
    Call<JsonObject> riderPaymentHistory(
            @Header("Authorization") String token
    );

    @GET("rider/shipments/cod-statement?")
    Call<CODStatementModel> getRider_COD_statement(
            @Header("Authorization") String token,
            @Query("page") String pageNum
    );

    @GET("rider/supports")
    Call<SupportModel> getRideSupport();


    ////////////////post_Method_For_Merchant///////////////////////////////
    @FormUrlEncoded
    @POST("merchant/login")
    Call<LoginModel> merchantLogin(@Field("mobile") String mobile,
                                   @Field("password") String password,
                                   @Field("device_token") String device_token);


    @Multipart
    @POST("merchant/signup")
    Call<JsonObject> merchantSignUp(@Part("name") RequestBody name,
                                    @Part("address") RequestBody address,
                                    @Part("email") RequestBody email,
                                    @Part("mobile") RequestBody mobile,
                                    @Part("password") RequestBody password,
                                    @Part("password_confirmation") RequestBody password_confirmation,
                                    @Part("fb_page") RequestBody fb_page,
                                    //  @Part("thana") RequestBody thana,
                                    //  @Part("district") RequestBody district,
                                    //   @Part("division") RequestBody division,
                                    // @Field("picture") String picture,
                                    @Part MultipartBody.Part picture,
                                    @Part("nid_number") RequestBody nid_number,
                                    @Part("buss_name") RequestBody buss_name,
                                    @Part("buss_phone") RequestBody buss_phone,
                                    @Part("buss_address") RequestBody buss_address,
                                    @Part("latitude") RequestBody latitude,
                                    @Part("longitude") RequestBody longitude,
                                    @Part MultipartBody.Part business_logo,
                                    @Part("payment_method") RequestBody payment_method,
                                    @PartMap() HashMap<String, RequestBody> product_type,
                                    //  @Field("trade_lic_no") String trade_lic_no,
                                    @Part MultipartBody.Part trade_lic_no

    );


    @Multipart
    @POST("merchant/profile/update")
    Call<JsonObject> merchantUpdateProfile(
            @Header("Authorization") String token,
            @PartMap() Map<String, RequestBody> partMap,
            @Part MultipartBody.Part file);


    @FormUrlEncoded
    @POST("merchant/check-mobile")
    Call<JsonObject> MobileCheckingMerchant(@Field("mobile") String check_mobile);

    /* @Headers({
             "Accept: application/json",
             "Content-Type: application/json"})*/

    @FormUrlEncoded
    @POST("merchant/send-otp")
    Call<JsonObject> sendOtpMerchant(@Field("mobile") String mobile,
                                     @Field("otp_for") String otp_for);


    @FormUrlEncoded
    @POST("merchant/forgot/password")
    Call<JsonObject> forgotPasswordMerchant(@Field("mobile") String mobile);


    @FormUrlEncoded
    @POST("merchant/reset/password")
    Call<JsonObject> resetPasswordMerchant(
            @Field("id") String id,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("otp") String otp
    );

    @FormUrlEncoded
    @POST("merchant/refresh-token")
    Call<JsonObject> refreshTokenMerchant(@Field("refresh_token") String refresh_token);

    @FormUrlEncoded
    @POST("merchant/shipments/create")
    Call<ShipmentCreateModel> createShipment(
            @Header("Authorization") String token,
            @Field("receiver_name") String receiver_name,
            @Field("contact_no") String contact_no,
            @Field("product_detail") String product_detail,
            @Field("product_type") String product_type,
            @Field("product_weight") String product_weight,
            @Field("note") String note,
            @Field("s_thana") String s_thana,
            @Field("s_district") String s_district,
            @Field("s_division") String s_division,
            @Field("d_thana") String d_thana,
            @Field("d_district") String d_district,
            @Field("d_division") String d_division,
            @Field("s_address") String s_address,
            @Field("d_address") String d_address,
            @Field("shipment_type") String shipment_type,
            @Field("s_latitude") String s_latitude,
            @Field("s_longitude") String s_longitude,
            @Field("d_latitude") String d_latitude,
            @Field("d_longitude") String d_longitude,
            @Field("cod_amount") String cod_amount,
            @Field("pickup_date") String pickup_date
    ); //date format must  "2021-07-31 13:30:00",


    @FormUrlEncoded
    @POST("merchant/shipments/cancel")
    Call<ShipmentCreateModel> shipmentCancel(
            @Header("Authorization") String token,
            @Field("id") String id,
            @Field("reason") String reason

    );

    @FormUrlEncoded
    @POST("merchant/shipments/send-to-pickup")
    Call<JsonObject> send_to_pickup(@Header("Authorization") String token, @FieldMap Map<String, String> ship_id
        /*    @Field("hub_id") String hub_id,
            @Field("note_for_hub") String note_for_hub*/
    );

    @FormUrlEncoded
    @POST("merchant/shipments/tracking")
    Call<TrackingModel> getShipmentTracking(
            @Header("Authorization") String token,
            @Field("shipment_no") String shipment_no);

    @FormUrlEncoded
    @POST("merchant/shipments/cancel-reason-list")
    Call<JsonObject> merchantShippingCancelReasonList(
            @Header("Authorization") String token,
            @Field("reason_for") String reason_for
    );

    @FormUrlEncoded
    @POST("merchant/shipments/shipping-database")
    Call<ShippingDataBaseModel> merchantShippingDatabase(
            @Header("Authorization") String token,
            @Field("filter") String filter
    );

    @FormUrlEncoded
    @POST("merchant/shipments/payment-breakdown")
    Call<BreakDownModel> merchantPayBreakDown(
            @Header("Authorization") String token,
            @Field("filter") String filter,
            @Field("from_date") String from_date,
            @Field("to_date") String to_date
    );

    @FormUrlEncoded
    @POST("merchant/shipments/complain")
    Call<JsonObject> merchantShipComplain(
            @Header("Authorization") String token,
            @Field("shipment_id") String shipment_id,
            @Field("complain") String complain
    );

//////////////////////////////////////get_Method_For_Merchant/////////////////////////////


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("merchant/profile")
    Call<MerchantProfileModel> getProfileMerchant(
            @Header("Authorization") String token
    );

    @GET("merchant/signup-data")
    Call<SignUpDataModel> getSignUpDataMerchant();

    @GET("merchant/divisions")
    Call<ArrayList<DivisionModel>> getDivisionMerchant();

    @GET("merchant/district/{id}")
    Call<ArrayList<DistrictModel>> getDistrictMerchant(@Path("id") String divId);


    @GET("merchant/thana/{id}")
    Call<ArrayList<ThanaModel>> getThanaMerchant(@Path("id") String disId);


    @GET("merchant/shipments")
    Call<ArrayList<ShipmentModel>> getShipmentMerchant(
            @Header("Authorization") String token,
            @Query("status") String time);


    @GET("merchant/shipments/nearest-hubs")
    Call<NearByHubModel> getNearByHub(
            @Header("Authorization") String token,
            @Query("latitude") String latitude,
            @Query("longitude") String longitude);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"})
    @GET("merchant/logout")
    Call<JsonObject> logoutMerchant(
            @Header("Authorization") String token
    );


    @GET("merchant/shipments/payment-history?")
    Call<JsonObject> merchantPaymentHistory(
            @Header("Authorization") String token,
            @Query("page") String pageNum
    );


    @GET("merchant/shipments/earn-pay?")
    Call<EarnAndPayModel> merchantEarnAndPay(
            @Header("Authorization") String token,
            @Query("page") String pageNum
    );

    @GET("merchant/shipments/withdraw-request")
    Call<JsonObject> withdrawRequest(
            @Header("Authorization") String token
    );

    @GET("merchant/shipments/detail/{id}")
    Call<ShipmentDetailModel> merchantShipmentsDetailById(
            @Header("Authorization") String token,
            @Path("id") String shipId
    );

    @GET("merchant/shipments/payment-view-detail/{txn_id}")
    Call<PaymentDetailslist> PaymentDetails(@Header("Authorization") String token, @Path("txn_id") String shipId);

    @GET("merchant/shipments/view-invoice/{txn_id}")
    Call<PaymentDetailslist> PaymentDetails_ViaID(@Header("Authorization") String token, @Path("txn_id") String mId);

    @GET("merchant/supports")
    Call<JsonObject> getMerchantSupport();


    @GET("merchant/shipments/weight-product-list")
    Call<WeightAndProductTypeModel> getWeightAndProductList(
            @Header("Authorization") String token
    );

    @GET("merchant/shipments/notification-list")
    Call<MerchantNotificationModel> getMerchantNotificationList(
            @Header("Authorization") String token
    );

    @GET("rider/shipments/notification-list")
    Call<MerchantNotificationModel> getRiderNotificationList(@Header("Authorization") String token);

    @GET("merchant/banner-list")
    Call<ArrayList<BannerResponseModel>> getMerchantBanner(
            @Header("Authorization") String token
    );

    @GET("merchant/shipments/notification-read/{Notification_id}")
    Call<JsonObject> notificationUpdateMerchant(
            @Header("Authorization") String token,
            @Path("Notification_id") String id
    );

    @GET("rider/shipments/notification-read/{id}")
    Call<JsonObject> notificationUpdateRider(@Header("Authorization") String token, @Path("id") String id);


    @FormUrlEncoded
    @POST("merchant/shipments/account")
    Call<JsonObject> AddMerchantBankDetails(@Header("Authorization") String token,
                                            @Field("acc_holder_name") String acc_holder_name,
                                            @Field("bank_name") String bank_name,
                                            @Field("branch_name") String branch_name,
                                            @Field("account_number") String account_number);

}
