package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by MinhThanh on 10/24/17.
 */

public class FontActivity extends Activity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

    private String fontStype = "home1";
    int checkFont = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);

        setup();
        setUpImgCheck();
        getLocal();
        changedFont();
    }

    private void setup(){

        RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.rlly_f1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontStype = "home1";
                checkFont = 1;
                changedFont();
                saveLocal();
            }
        });
        RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.rlly_f2);
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontStype = "home2";
                checkFont = 2;
                changedFont();
                saveLocal();
            }
        });
        RelativeLayout rl3 = (RelativeLayout) findViewById(R.id.rlly_f3);
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontStype = "home3";
                checkFont = 3;
                saveLocal();
                changedFont();
            }
        });

        TextView tvDone = (TextView) findViewById(R.id.tvDoneFont);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("", true);
                finish();
            }
        });

    }

    private void saveLocal(){
        SharedPreferences preShare = getSharedPreferences("FONTCHANGED", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();
        edit.putString("FONT", fontStype);
        edit.commit();

        SharedPreferences preShareCheck = getSharedPreferences("CHECKFONT", MODE_PRIVATE);
        SharedPreferences.Editor editCheck = preShareCheck.edit();
        editCheck.putInt("CHECK", checkFont);
        editCheck.commit();

    }

    private void getLocal(){
        SharedPreferences preShare = getSharedPreferences("FONTCHANGED", MODE_PRIVATE);
        fontStype = preShare.getString("FONT", "home1");

        SharedPreferences preShareCheck = getSharedPreferences("CHECKFONT", MODE_PRIVATE);
        checkFont = preShareCheck.getInt("CHECK", 1);


        Log.e("FontActivity",fontStype);
        Log.e("FontActivityINT", String.valueOf(checkFont));
    }

    private void setUpImgCheck(){

        img1 = (ImageView) findViewById(R.id.imgCheck1);
        img2 = (ImageView) findViewById(R.id.imgCheck2);
        img3 = (ImageView) findViewById(R.id.imgCheck3);
    }

    private void changedFont(){

        Log.e("FontActivityINTCHANGE", String.valueOf(checkFont));
        if ( checkFont == 1){
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
        }
        if ( checkFont == 2){
            img2.setVisibility(View.VISIBLE);
            img1.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);

        }
        if ( checkFont == 3){
            img3.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);

        }
    }

}




