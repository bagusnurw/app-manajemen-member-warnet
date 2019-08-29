package com.example.kidz.project_uas.DashboardApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.kidz.project_uas.Coupon.Coupon;
import com.example.kidz.project_uas.Fragment.MainFragment;
import com.example.kidz.project_uas.DataMember.LoginAdmin;
import com.example.kidz.project_uas.R;

public class Dashboard extends AppCompatActivity {

    ImageView imgProfile, imgCoupon, imgInfo, imgSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imgProfile = (ImageView) findViewById(R.id.imgProfil);
        imgCoupon = (ImageView) findViewById(R.id.imgCoupon);
        imgInfo = (ImageView) findViewById(R.id.imgInfo);
        imgSetting = (ImageView) findViewById(R.id.imgSetting);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Dashboard.this,DashProfil.class);
                startActivity(a);
            }
        });

        imgCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Dashboard.this,Coupon.class);
                startActivity(a);
            }
        });

        imgInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Dashboard.this,MainFragment.class);
                startActivity(a);
            }
        });

        imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(Dashboard.this,LoginAdmin.class);
                startActivity(a);
            }
        });
    }
}
