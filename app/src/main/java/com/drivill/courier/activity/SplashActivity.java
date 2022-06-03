package com.drivill.courier.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.drivill.courier.R;
import com.drivill.courier.utils.MyUtil;

public class SplashActivity extends AppCompatActivity {

    private TextView mNextText, mSkipTxt;
    private int mPos = 0;
    MotionLayout motion_layout;

    void initUI() {
        motion_layout = findViewById(R.id.motion_layout);
        mNextText = findViewById(R.id.nextText);
        mSkipTxt = findViewById(R.id.skipTxt);
        mNextText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPos == 3) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            goNextActivity();
                        }
                    }, 1000);

                    endTransition("skip");


                } else if (mPos <= 3) {
                    mPos++;
                    Log.d("pos", String.valueOf(mPos));
                    switchLayout(mPos);
                }
            }
        });

        mSkipTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goNextActivity();
                    }
                }, 1000);

                endTransition("skip");
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyUtil.getFullScreen(SplashActivity.this);
        setContentView(R.layout.activity_splash_init);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switchLayout(mPos);
            }
        }, 2000);

    }

    @Override
    public void onBackPressed() {
        if (mPos == 0) {
            super.onBackPressed();
        } else if (mPos <= 3) {
            mPos--;
            switchLayout(mPos);
        }

    }

    void switchLayout(int pos) {
        switch (pos) {
            case 0:
                initSplash(R.layout.activity_splash, "n");
                break;
            case 1:
                initSplash(R.layout.activity_splash1, "n");
                break;
            case 2:
                initSplash(R.layout.activity_splash2, "n");
                break;
            case 3:
                initSplash(R.layout.activity_splash3, "n");
                break;
        }
    }

    void initSplash(int lay, String tag) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(lay);
                initUI();
            }
        }, 1000);
        if (mPos != 0)
            endTransition(tag);
    }


    private void endTransition(String skip) {
        if (skip.equals("skip")) {
            motion_layout.setTransition(R.id.skipTrans);
        } else {
            motion_layout.setTransition(R.id.finishTrans);
        }
        motion_layout.setTransitionDuration(1000);
        motion_layout.transitionToEnd();

    }

    void goNextActivity() {
        Intent intent = new Intent(SplashActivity.this, LetsStartActivity.class);
        startActivity(intent);
        finish();
    }


}