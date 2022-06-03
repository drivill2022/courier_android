package com.drivill.courier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.drivill.courier.R;
import com.drivill.courier.utils.MyAnimation;

public class SignUpActivity2 extends AppCompatActivity {
    TextView button_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAnimation.noAnimation(this);
        setContentView(R.layout.activity_sign_up2);
        button_continue = findViewById(R.id.button_continue);

        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity2.this, SignUp3Activity.class);
                startActivity(intent);
            }
        });
    }
}