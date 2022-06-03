package com.drivill.courier.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.google.android.material.snackbar.Snackbar;

public class MyUtil {

    public static void getFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void browseUrl(Activity activity, String ulr) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + ulr));
        activity.startActivity(browserIntent);
    }

    public static void showAlertLogout(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.logout_dialog_layout);

        TextView logoutBtn = (TextView) dialog.findViewById(R.id.logoutTxt);
        TextView cancelBtn = (TextView) dialog.findViewById(R.id.cancelTxt);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((BaseActivity) activity).mBasePreferenceManager.getIsRider())
                    ((BaseActivity) activity).logout();
                else ((BaseActivity) activity).logoutMerchant();
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }

    public static void softInputMode(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

    }

    public static void ChangeStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color, activity.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color));
        }
    }

/*    public static void CheckAppVerson(Context context){
        // https://www.codeplayon.com/2021/01/android-in-app-force-update-implementation/
       // AtomicBoolean IsUpdateAvailable = new AtomicBoolean(false);
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(context);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(result -> {

            if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {

              //  IsUpdateAvailable.set(true);
                android.view.ContextThemeWrapper ctw = new android.view.ContextThemeWrapper(context,R.style.customBottomSheetDialogTheme);
                final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(ctw);
                alertDialogBuilder.setTitle(context.getString(R.string.updateapp));
                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setIcon(R.drawable.googleplayiocn);
                alertDialogBuilder.setMessage(context.getString(R.string.updateappmsg));
                alertDialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try{
                            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id="+context.getPackageName())));
                            Log.i("arp","marketuri= "+"market://details?id="+context.getPackageName());
                        }
                        catch (ActivityNotFoundException e){
                            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id="+context.getPackageName())));
                            Log.i("arp","marketuri= "+"https://play.google.com/store/apps/details?id="+context.getPackageName());
                        }
                    }
                });
              *//*  alertDialogBuilder.setNegativeButton("No Thanks",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });*//*

                alertDialogBuilder.show();

            } else {
                Log.i("arp","CheckAppVersonresult= "+result.updateAvailability());
             //   IsUpdateAvailable.set(false);
            }
        });

       // return IsUpdateAvailable.get();
    }*/

    public static String _getStatus(Context mContext,Integer status){
      //  Log.i("arp","_getStatus= "+status);
        String statustype ;
        if(status == 1){
            statustype = mContext.getString(R.string.requested);
        }else if(status == 2){
            statustype = mContext.getString(R.string.assigned);
        } else if(status == 3){
            statustype = mContext.getString(R.string.picked_up);
        } else if(status == 4){
            statustype = mContext.getString(R.string.inTrans);
        } else if(status == 5){
            statustype =  mContext.getString(R.string.On_going_delivery);
        } else if(status == 6){
            statustype =  mContext.getString(R.string.delivered);
        } else if(status == 7){
            statustype =  mContext.getString(R.string.completed);
        }else if(status == 8){
            statustype =  mContext.getString(R.string.cancelled);
        } else if(status == 9){
            statustype =  mContext.getString(R.string.Rejected);
        }else if(status == 10){
            // statustype =  mContext.getString(R.string.returntxt)+"\n"+mContext.getString(R.string.customer_attempt_failed);
            statustype =  mContext.getString(R.string.customer_attempt_failed);
        }else if(status == 11){
            statustype =  mContext.getString(R.string.cancelled)/*+"\n"+mContext.getString(R.string.cancel_bformark)*/;
        }else if(status == 12){
            statustype =  mContext.getString(R.string.cancelled)/*+"\n"+mContext.getString(R.string.cancelled_by_rider)*/;
        }else {
            statustype = mContext.getString(R.string.Onboarding);
        }

        return statustype;
    }
}
