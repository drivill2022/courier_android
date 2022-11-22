package com.drivill.courier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.drivill.courier.R;
import com.drivill.courier.databinding.ActivityStartNewBinding;
import com.drivill.courier.utils.MyAnimation;

public class StartNewActivity extends AppCompatActivity {

    ActivityStartNewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityStartNewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MyAnimation.startAnimTopToBottom(this, binding.cvCourier);
        MyAnimation.startAnimTopToBottom(this, binding.cvFilms);
        MyAnimation.startAnimTopToBottom(this, binding.ivLogo);

        binding.cvCourier.setOnClickListener(view -> {
            Intent intent =new Intent(this,StartActivity.class);
            startActivity(intent);
            finishAffinity();

        });
    }
}