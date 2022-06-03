package com.drivill.courier.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.drivill.courier.R;

public class MyAnimation {

    public static void runFadeInAnimation(Activity context) {
      /*  Animation a = AnimationUtils.loadAnimation(context, R.anim.fadein);
        a.reset();
        LinearLayout ll = (LinearLayout) findViewById(R.id.yourviewhere);
        ll.clearAnimation();
        ll.startAnimation(a);*/
        context.overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    public static void noAnimation(Activity context) {
      /*  Animation a = AnimationUtils.loadAnimation(context, R.anim.fadein);
        a.reset();
        LinearLayout ll = (LinearLayout) findViewById(R.id.yourviewhere);
        ll.clearAnimation();
        ll.startAnimation(a);*/
        context.overridePendingTransition(0, 0);
    }

    public static void startAnimBottomToTop(Context context, View view) {

        Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.up_anim);
        view.startAnimation(animation2);
    }

    public static void startAnimTopToBottom(Context context, View view) {

        Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.down_anim);
        view.startAnimation(animation2);
    }

    public static void startAnimZoomIn(Context context, View view) {

        Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.zoom_in);
        view.startAnimation(animation2);
    }

    public static void startAnimZoomOut(Context context, View view) {

        Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.zoom_out);
        view.startAnimation(animation2);
    }
}
