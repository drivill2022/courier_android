package com.drivill.courier.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.drivill.courier.R;
import com.drivill.courier.utils.MyAnimation;

public class SignUp3Activity extends AppCompatActivity {
    TextView button_continue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyAnimation.noAnimation(this);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_sign_up3);
        button_continue = findViewById(R.id.button_continue);
        button_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp3Activity.this, DashboardActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
        //setSpinner();
    }


    void setSpinner() {
        Spinner Brandspinner = (Spinner) findViewById(R.id.vehicleBrandSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.brand_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        Brandspinner.setAdapter(adapter);


        Spinner Modelspinner = (Spinner) findViewById(R.id.vehicleModelSpinner);
        ArrayAdapter<CharSequence> model_adapter = ArrayAdapter.createFromResource(this,
                R.array.model_array, android.R.layout.simple_spinner_item);
        model_adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        Modelspinner.setAdapter(model_adapter);


        Spinner RegionSpinner = (Spinner) findViewById(R.id.vehicleRegionSpinner);
        ArrayAdapter<CharSequence> region__adapter = ArrayAdapter.createFromResource(this,
                R.array.region_array, android.R.layout.simple_spinner_item);
        region__adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        RegionSpinner.setAdapter(region__adapter);


    /*    Spinner categorySpinner = (Spinner) findViewById(R.id.vehicleCategorySpinner);
        ArrayAdapter<CharSequence> category__adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        category__adapter.setDropDownViewResource(R.layout.spinner_item_layout);
        categorySpinner.setAdapter(category__adapter);
*/

        Brandspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.gary));
                    ((TextView) adapterView.getChildAt(0)).setText("Brand Name");
                    ((TextView) adapterView.getChildAt(0)).setTextSize(12);
                    Log.d("index", String.valueOf(i));
                }

                //  ((TextView) adapterView.getChildAt(i)).setTextSize(getResources().getDimension(R.dimen._10sdp));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Modelspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.gary));
                    ((TextView) adapterView.getChildAt(0)).setText("Model Name");
                    ((TextView) adapterView.getChildAt(0)).setTextSize(12);
                    Log.d("index", String.valueOf(i));
                }

                //  ((TextView) adapterView.getChildAt(i)).setTextSize(getResources().getDimension(R.dimen._10sdp));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        RegionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.gary));
                    ((TextView) adapterView.getChildAt(0)).setText("Select Region");
                    ((TextView) adapterView.getChildAt(0)).setTextSize(12);
                    Log.d("index", String.valueOf(i));
                }

                //  ((TextView) adapterView.getChildAt(i)).setTextSize(getResources().getDimension(R.dimen._10sdp));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


     /*   categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {
                    ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.gary));
                    ((TextView) adapterView.getChildAt(0)).setText("Select Category");
                    ((TextView) adapterView.getChildAt(0)).setTextSize(12);
                    Log.d("index", String.valueOf(i));
                }

                //  ((TextView) adapterView.getChildAt(i)).setTextSize(getResources().getDimension(R.dimen._10sdp));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

*/
    }

}