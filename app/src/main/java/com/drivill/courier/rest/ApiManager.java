package com.drivill.courier.rest;


import com.drivill.courier.merchantModul.model.PaymentDetailslist;
import com.drivill.courier.model.model.DeleteModel;
import com.drivill.courier.model.model.ShipmentDetailsModel;
import com.drivill.courier.model.model.SplashModelItem;
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


/**
 * Created by Sachin Verma on 06/29/21.
 */

public interface ApiManager {

    ////////////////post Method////////////////////////////////

    Call<LoginModel> riderLogin(String mobile,
                                String password,
                                String device_token);


    Call<JsonObject> riderSignUp(RequestBody name,
                                 RequestBody hub_id,
                                 RequestBody address,
                                 RequestBody email,
                                 RequestBody gender,
                                 RequestBody password,
                                 RequestBody password_confirmation,
                                 RequestBody mobile,
                                 // @Field("picture") String picture,
                                 MultipartBody.Part picture,
                                 RequestBody nid_number,
                                 //  @Field("nid_picture") String nid_picture,
                                 MultipartBody.Part nid_picture,
                                 MultipartBody.Part father_nid_pic,
                                 RequestBody father_nid,
                                 // RequestBody thana,
                                 //   RequestBody district,
                                 //  RequestBody division,
                                 RequestBody vehicle_type_id,
                                 RequestBody dl_number,
                                 // @Part("dl_photo") String dl_photo,
                                 MultipartBody.Part dl_photo,
                                 // @Part("rc_photo") String rc_photo,
                                 MultipartBody.Part rc_photo,
                                 RequestBody brand,
                                 RequestBody model,
                                 RequestBody region,
                                 RequestBody category,
                                 RequestBody plat_number,
                                 RequestBody token_number,
                                 RequestBody longitude,
                                 RequestBody latitude
    );

    Call<JsonObject> riderSignUp(RequestBody name,
                                 RequestBody hub_id,
                                 RequestBody address,
                                 RequestBody email,
                                 RequestBody gender,
                                 RequestBody password,
                                 RequestBody password_confirmation,
                                 RequestBody mobile,
                                 MultipartBody.Part picture,
                                 RequestBody nid_number,
                                 MultipartBody.Part nid_picture,
                                 MultipartBody.Part father_nid_pic,
                                 RequestBody father_nid,
                                 RequestBody vehicle_type_id,
                                 RequestBody longitude,
                                 RequestBody latitude

    );

    Call<JsonObject> riderUpdateProfile(
            String token,
            Map<String, RequestBody> partMap,
            MultipartBody.Part file);


    Call<JsonObject> riderMobileChecking(String check_mobile);


    Call<JsonObject> rideSendOtp(String mobile,
                                 String otp_for);

    Call<JsonObject> rideSendForgotOtp(String mobile);

    Call<JsonObject> resetPasswordRider(
            String id,
            String password,
            String password_confirmation,
            String otp
    );

    Call<JsonObject> refreshToken(String refresh_token);

    Call<JsonObject> riderShipmentUpdate(String token,
                                         String id, String status, String otp);

    Call<JsonObject> riderShipmentSendOTP(
            String token,
            String shipID,
            String otp_to);

    Call<JsonObject> riderShippingCancelReasonList(
            String token,
            String reason_for
    );

    Call<ShipmentCreateModel> riderShipmentCancel(
            String token,
            String id,
            String reason

    );

    Call<StatementModel> riderStatement(
            String token,
            String filter,
            String from_date,
            String to_date
    );

    Call<JsonObject> riderDepositAmount(
            String token,
            String amount);


    Call<NearByHubModel> riderNearestHub(
            String token,
            String latitude,
            String longitude);

    Call<JsonObject> riderNearestRider(
            String token,
            String latitude,
            String longitude);

    Call<ArrayList<SplashModelItem>> getSplashItem();


    Call<JsonObject> riderSendCurrentLocation(
            String token,
            String address,
            String latitude,
            String longitude);


    Call<JsonObject> riderTransferShipHub(
            String token,
            String shipment_id,
            String hub_id);

    Call<JsonObject> riderTransferShipToRider(
            String token,
            String shipment_id,
            String rider_id);
    ////////////////////Get Method/////////////////////////////


    Call<ProfileModel> riderProfile(
            String token
    );

    Call<HubModel> getHubRider();

    Call<VehicleModel> getVehicleData();


    Call<JsonObject> logoutRider(String token);


    Call<RiderPickupListModel> riderShipmentsList(
            String token,
            String latitude,
            String status,
            String longitude
    );


    Call<JsonObject> riderPaymentHistory(
            String token
    );

    Call<CODStatementModel> getRider_COD_statement(
            String token, String pageNum
    );

    Call<SupportModel> getRideSupport();

    ////////////////post Method For Merchant////////////////////////////////

    Call<LoginModel> merchantLogin(String mobile,
                                   String password,
                                   String device_token);


    Call<JsonObject> merchantSignUp(RequestBody name,
                                    RequestBody address,
                                    RequestBody email,
                                    RequestBody mobile,
                                    RequestBody password,
                                    RequestBody password_confirmation,
                                    RequestBody fb_page,
                                    //  @Part("thana") RequestBody thana,
                                    //  @Part("district") RequestBody district,
                                    //   @Part("division") RequestBody division,
                                    // @Field("picture") String picture,
                                    MultipartBody.Part picture,
                                    RequestBody nid_number,
                                    RequestBody buss_name,
                                    RequestBody buss_phone,
                                    RequestBody buss_address,
                                    RequestBody latitude,
                                    RequestBody longitude,
                                    MultipartBody.Part business_logo,
                                    RequestBody payment_method,
                                    HashMap<String, RequestBody> product_type,
                                    //  @Field("trade_lic_no") String trade_lic_no,
                                    MultipartBody.Part trade_lic_no

    );


    Call<JsonObject> merchantUpdateProfile(
            String token,
            Map<String, RequestBody> partMap,
            MultipartBody.Part file);

    Call<JsonObject> MobileCheckingMerchant(String check_mobile);

    Call<JsonObject> sendOtpMerchant(String mobile,
                                     String otp_for);

    Call<JsonObject> forgotPasswordMerchant(String mobile);


    Call<JsonObject> resetPasswordMerchant(String id, String password, String password_confirmation, String otp
    );

    Call<JsonObject> refreshTokenMerchant(String refresh_token);

    Call<ShipmentCreateModel> createShipment(
            String token, String receiver_name, String contact_no, String product_detail,
            String product_type,
            String product_weight,
            String note,
            String s_thana,
            String s_district,
            String s_division,
            String d_thana,
            String d_district,
            String d_division,
            String s_address,
            String d_address, String shipment_type,
            String s_latitude,
            String s_longitude,
            String d_latitude,
            String d_longitude,
            String shipment_cost,
            String pickup_date
    );


    Call<ShipmentCreateModel> updateShipment(String token,
                                             String id,
                                             String receiver_name,
                                             String contact_no,
                                             String product_type,
                                             String product_weight,
                                             String note,
                                             String d_thana,
                                             String d_district,
                                             String d_division,
                                             String d_address,
                                             String cod_amount,
                                             String pickup_date);

    Call<ShipmentCreateModel> shipmentCancel(
            String token,
            String id,
            String reason

    );

    Call<JsonObject> send_to_pickup(
            String token,
            Map<String, String> ship_id
    /*        String hub_id,
            String note_for_hub*/
    );

    Call<JsonObject> merchantShippingCancelReasonList(
            String token,
            String reason_for
    );

    Call<ShippingDataBaseModel> merchantShippingDatabase(
            String token,
            String filter
    );

    Call<BreakDownModel> merchantPayBreakDown(
            String token,
            String filter,
            String from_date,
            String to_date
    );

    Call<JsonObject> merchantShipComplain(
            String token,
            String shipment_id,
            String complain
    );

    ////////////////////Get Method for Merchant/////////////////////////////

    Call<MerchantProfileModel> getProfileMerchant(String token);

    Call<SignUpDataModel> getSignUpDataMerchant();

    Call<ArrayList<DivisionModel>> getDivisionMerchant();

    Call<ArrayList<DistrictModel>> getDistrictMerchant(String divId);


    Call<DeleteModel> deleteShipment(String token, String divId);

    Call<ShipmentDetailsModel> getShipmentDetail(String token, String divId);

    Call<ArrayList<ThanaModel>> getThanaMerchant(String disId);

    Call<ArrayList<ShipmentModel>> getShipmentMerchant(
            String token,
            String time);

    Call<TrackingModel> getShipmentTracking(
            String token,
            String shipment_no);

    Call<NearByHubModel> getNearByHub(
            String token,
            String latitude,
            String longitude);

    Call<JsonObject> logoutMerchant(String token);

    Call<JsonObject> merchantPaymentHistory(
            String token,
            String pageNum
    );

    Call<EarnAndPayModel> merchantEarnAndPay(
            String token,
            String pageNum, String dateFrom,String dateto

    );

    Call<JsonObject> withdrawRequest(
            String token
    );

    Call<ShipmentDetailModel> merchantShipmentsDetailById(
            String token, String shipId
    );

    Call<JsonObject> getMerchantSupport();

    Call<WeightAndProductTypeModel> getWeightAndProductList(
            String token
    );
    Call<MerchantNotificationModel> getMerchantNotificationList(String token);

    Call<MerchantNotificationModel> getRiderNotificationList(String token);

    Call<ArrayList<BannerResponseModel>> getMerchantBanner(
            String token
    );

    Call<JsonObject> notificationUpdateMerchant(String token, String id);

    Call<JsonObject> notificationUpdateRider(String token, String id);

    Call<PaymentDetailslist> PaymentDetails(String token, String Txt_Id);
    Call<PaymentDetailslist> PaymentDetails_viaID(String token, String Id);

    Call<JsonObject> AddMerchantBankDetails(String token, String acc_holder_name, String bank_name, String branch_name, String account_number);

    Call<JsonObject> riderShipmentUpdate_WithReason(String token, String id, String status, String otp, String reason);
}
