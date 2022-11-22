package com.drivill.courier.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Constant {
    /*// In house URL--
    public static final String BASE_URL = "http://drivill.indianshoppingbasket.com/api/";
    public static final String IMG_BROWSER_URL = "http://drivill.indianshoppingbasket.com/public/storage/";
*/
    // Client URL ---
    //public static final String BASE_URL = "http://drivill.com/api/";
    public static final String BASE_URL = "http://3.7.199.148/api/";
    //public static final String BASE_URL = "https://91ed-180-188-237-118.in.ngrok.io/api/";

    //public static final String IMG_BROWSER_URL = "http://drivill.com/public/storage/";
    public static final String IMG_BROWSER_URL = "http://3.7.199.148/public/storage/";
   // public static final String IMG_BROWSER_URL = "https://91ed-180-188-237-118.in.ngrok.io/public/storage/";



    public static final String TOKEN_TYPE = "Bearer ";

    ///////////////////////////////////////////////////////////////////////////
    // this is the intent request parameter
    ///////////////////////////////////////////////////////////////////////////

    public static final int NID_CAMERA_PERMISSION = 111;
    public static final int FATHER_NID_CAMERA_PERMISSION = 222;
    public static final int NID_IMG_PERMISSION = 333;
    public static final int FATHER_NID_IMG_PERMISSION = 444;

    public static final int DL_IMG_PERMISSION = 555;
    public static final int DL_CAMERA_PERMISSION = 666;
    public static final int RC_IMG_PERMISSION = 777;
    public static final int RC_CAMERA_PERMISSION = 888;

    public static final int PROFILE_CAMERA_PERMISSION = 999;
    public static final int PROFILE_IMG_PERMISSION = 1000;
    public static final int CALL_PERMISSION = 101;
    public static final int LOCATION_PERMISSION = 102;
    public static final int MULTIPLE_PERMISSIONS = 103;

    /***
     * Moving and repeat Open Activity code
     */
    public static final int SIGNUP = 1;
    public static final int FORGOT = 2;

    public static final int EditItem = 1;
    public static final int ViewOnly = 2;
    public static final int CREATING_SHIP = 4;
    public static final int FOR_CHANGE_STATUS = 3;


    /***
     * Request code for validate observer
     */
    public static final int NO_INTERNET = 1201;

    /**
     * login constants
     */
    public static final int EMPTY_ID = 1202;
    public static final int INVALID_MAIL = 1203;
    public static final int EMPTY_PASSWORD = 1204;
    public static final int SERVER_ERROR = 1205;

    /**
     * sign-up constants
     */
    public static final int EMPTY_NAME = 1206;
    public static final int EMPTY_MAIL = 1207;

    /**
     * Profile Update constants
     */

    public static final int INVALID_NUMBER = 1208;
    public static final int EMPTY_NUMBER = 1209;
    public static final int EMPTY_CITY = 1210;
    public static final int EMPTY_BUTTON_NAME = 1211;
    public static final int EMPTY_BUTTON_PORT = 1212;
    public static final int CARD_TYPE = 1006;

    /**
     * change password constants
     */
    public static final int EMPTY_OLD_PASSWORD = 1223;
    public static final int EMPTY_CONFIRM_PASSWORD = 1224;
    public static final int CONFIRM_PASSWORD_NOT_MATCH = 1225;
    public static final int NO_NOTIFICATION = 1226;
    public static final int IS_NOTIFICATION = 1227;
    public static final int IS_SCHEDULE_NOTIFICATION = 1228;

    /**
     * api status code
     */
    public static final int STATUS_SUCCESS = 200;
    public static final int STATUS_NO_DATA_FOUND = 1001;
    public static final int STATUS_FAIL = 401;
    public static final int STATUS_OTP_NOT_VARIFIED = 1002;
    public static final int STATUS_SESSION_EXPIRE = 1005;

    /**
     * Data Fetching status code
     */
    public static final String CURRENT =   "current";
    public static final String SCHEDULED = "scheduled";
    public static final String REQUESTED = "requested";
    public static final String SHIPPED =   "shipped";
    public static final String DELIVERED = "delivered";
    public static final String CANCELLED = "cancelled";
    public static final String TRANSITT = "transit";
    public static final String ONGOING = "on-the-way";

    /**
     * event status code
     */
    public static final String EXTRA_DATA_NAME_IS_CONNECTED = "com.drivill.courier.action.IsConnected";

    /**
     * Rider status API update code
     */
    public static final String PENDING = "0";
    public static final String ASSIGN = "2";

    public static final String PICKUP = "3";
    public static final String TRANSIT = "4";
    public static final String ON_GOING = "5";
    public static final String DELIVER = "6";
    public static final String COMPLETE = "7";
    public static final String RETURN = "10";
    /////////////// Send otp to //////////////////////
    public static final String MERCHANT = "1";
    public static final String RECEIVER = "2";
    public static final String CANCEL = "12";


    public static String getImageUrl(String URL) {
        return IMG_BROWSER_URL + URL;
    }


    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        //Toast.makeText(context, "Oops ! Connect your Internet", Toast.LENGTH_LONG).show();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();

    }


}


