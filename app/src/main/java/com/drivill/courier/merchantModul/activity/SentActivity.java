package com.drivill.courier.merchantModul.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.drivill.courier.R;
import com.drivill.courier.activity.summary.SummaryActivity;
import com.drivill.courier.databinding.ActivitySentBinding;
import com.drivill.courier.utils.CircleAngleAnimation;

public class SentActivity extends AppCompatActivity {

    ActivitySentBinding mBinding;

    void initUI() {
        mBinding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SentActivity.this, DashboardActivityMerchant.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        mBinding.bcktohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SentActivity.this, DashboardActivityMerchant.class);
                startActivity(intent);
                finishAffinity();
            }
        });

        startAnimation();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sent);
        initUI();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(SentActivity.this, DashboardActivityMerchant.class);
        startActivity(intent);
        finishAffinity();
    }

    void startAnimation() {
        CircleAngleAnimation animation = new CircleAngleAnimation(mBinding.cirvle, 360);
        animation.setDuration(1500);
        mBinding.cirvle.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animatable animatable = (Animatable) mBinding.animCheck.getDrawable();
                animatable.start();
                Intent intent = new Intent(SentActivity.this, SummaryActivity.class);
                intent.putExtra("dataarray",getIntent().getSerializableExtra("data"));
                startActivity(intent);
                finishAffinity();
            }
        }, 1500);
    }
}