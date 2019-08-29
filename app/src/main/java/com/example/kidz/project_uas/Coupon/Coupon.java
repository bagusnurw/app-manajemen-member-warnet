package com.example.kidz.project_uas.Coupon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kidz.project_uas.R;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

public class Coupon extends AppCompatActivity {

    Button buttonCoupon;
    EditText editCodeCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);

        buttonCoupon = (Button) findViewById(R.id.buttonCoupon);
        editCodeCoupon = (EditText) findViewById(R.id.editCodeCoupon);

        final String coupon = "chickendinner";

        buttonCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editCodeCoupon.getText().toString().equals(coupon)){
                    final TickerView tickerView = findViewById(R.id.tickerView);
                    tickerView.setCharacterLists(TickerUtils.provideNumberList());
                    tickerView.setAnimationDuration(6000);
                    tickerView.setAnimationInterpolator(new OvershootInterpolator());
                    tickerView.setText("10 Jam");
                    tickerView.setText("5 Jam");
                    tickerView.setText("3 Jam");
                } else {
                    Toast.makeText(getApplicationContext(), "Coupon is Wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}

