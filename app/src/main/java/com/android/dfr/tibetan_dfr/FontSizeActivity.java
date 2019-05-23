package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by MinhThanh on 10/24/17.
 */

public class FontSizeActivity extends Activity {

    private int fontSize = 2;

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fontsize);

        setUp();
        setImgView();
        getLocal();
        showHideCheck();
    }

    private void setUp(){

        RelativeLayout rl1 = (RelativeLayout) findViewById(R.id.rlly_fz1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontSize = 0;
                saveLocal();
                showHideCheck();
            }
        });
        RelativeLayout rl2 = (RelativeLayout) findViewById(R.id.rlly_fz2);
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontSize = 1;
                saveLocal();
                showHideCheck();
            }
        });
        RelativeLayout rl3 = (RelativeLayout) findViewById(R.id.rlly_fz3);
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontSize = 2;
                saveLocal();
                showHideCheck();
            }
        });
        RelativeLayout rl4 = (RelativeLayout) findViewById(R.id.rlly_fz4);
        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontSize = 3;
                saveLocal();
                showHideCheck();
            }
        });
        RelativeLayout rl5 = (RelativeLayout) findViewById(R.id.rlly_fz5);
        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fontSize = 4;
                saveLocal();
                showHideCheck();
            }
        });

        TextView tvDone = (TextView) findViewById(R.id.tvDoneFontSize);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("", true);
                finish();
            }
        });

    }


    private void setImgView(){

        img1 = (ImageView) findViewById(R.id.imgCheck14);
        img2 = (ImageView) findViewById(R.id.imgCheck16);
        img3 = (ImageView) findViewById(R.id.imgCheck18);
        img4 = (ImageView) findViewById(R.id.imgCheck20);
        img5 = (ImageView) findViewById(R.id.imgCheck22);

    }

    private void showHideCheck(){
        if (fontSize == 0){
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
            img4.setVisibility(View.GONE);
            img5.setVisibility(View.GONE);
        }
        if (fontSize == 1){
            img2.setVisibility(View.VISIBLE);
            img1.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
            img4.setVisibility(View.GONE);
            img5.setVisibility(View.GONE);
        }
        if (fontSize == 2){
            img3.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);
            img4.setVisibility(View.GONE);
            img5.setVisibility(View.GONE);
        }
        if (fontSize == 3){
            img4.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);
            img5.setVisibility(View.GONE);
        }
        if (fontSize == 4){
            img5.setVisibility(View.VISIBLE);
            img2.setVisibility(View.GONE);
            img3.setVisibility(View.GONE);
            img4.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);
        }

    }

    private void saveLocal(){
        SharedPreferences preShare = getSharedPreferences("FONTSIZE", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();
        edit.putInt("SIZE", fontSize);
        edit.commit();

    }

    private void getLocal(){
        SharedPreferences preShare = getSharedPreferences("FONTSIZE", MODE_PRIVATE);
        fontSize = preShare.getInt("SIZE", 2);
    }

}
