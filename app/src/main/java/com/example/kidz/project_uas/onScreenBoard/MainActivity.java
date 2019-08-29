package com.example.kidz.project_uas.onScreenBoard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kidz.project_uas.R;

public class MainActivity extends AppCompatActivity {

    Animation anim1, anim2, anim3;
    ImageView imgBoard;
    TextView textTitle, textDesc;
    Button btnBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onscreen1);

        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1);
        anim2 = AnimationUtils.loadAnimation(this, R.anim.anim2);
        anim3 = AnimationUtils.loadAnimation(this, R.anim.anim3);

        imgBoard = (ImageView) findViewById(R.id.imgBoard);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textDesc = (TextView) findViewById(R.id.textDesc);
        btnBoard = (Button) findViewById(R.id.btnBoard);

        imgBoard.startAnimation(anim1);
        textTitle.startAnimation(anim2);
        textDesc.startAnimation(anim2);
        btnBoard.startAnimation(anim3);

        btnBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,Main2Activity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(a);
        }
        });
    }
}
