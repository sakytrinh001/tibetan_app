package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;



public class SettingsActivity extends Activity{

    private int backgroundWebview = 1;
    private int fontSize = 2;
    private String font = "home1";

    private TextView tvFontSize;
    private Boolean isJustify;
    private TextView tvFont;
    private Switch swJustify;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        setupAction();
        getSaveLocal();
        changedTextFontSize();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        getSaveLocal();
        changedTextFontSize();

    }

    private void changedTextFontSize(){

        if (fontSize == 0){
            tvFontSize.setText(getResources().getText(R.string.kExtraSmall));
        }
        if (fontSize == 1){
            tvFontSize.setText(getResources().getText(R.string.kSmall));
        }
        if (fontSize == 2){
            tvFontSize.setText(getResources().getText(R.string.kMedium));
        }
        if (fontSize == 3){
            tvFontSize.setText(getResources().getText(R.string.kLarge));
        }
        if (fontSize == 4){
            tvFontSize.setText(getResources().getText(R.string.kExtraLarge));
        }

        if (font == "home1"){
            tvFont.setText(getResources().getText(R.string.kFont1));
        }
        if (font == "home2"){
            tvFont.setText(getResources().getText(R.string.kFont2));
        }
        if (font == "home3"){
            tvFont.setText(getResources().getText(R.string.kFont3));
        }

        if (isJustify){
            swJustify.setChecked(true);
        }else{
            swJustify.setChecked(false);
        }


    }


    private void setupAction(){

        swJustify = (Switch) findViewById(R.id.switchAction);
        swJustify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    isJustify = true;
                    setSaveLocal();
                }else{
                    isJustify = false;
                    setSaveLocal();
                }
            }
        });

        ImageView imgWhite = (ImageView) findViewById(R.id.imgWhite);
        imgWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundWebview = 1;
                setSaveLocal();
            }
        });

        ImageView imgOrigan = (ImageView) findViewById(R.id.imgYellow);
        imgOrigan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundWebview = 2;
                setSaveLocal();
            }
        });

        ImageView imgBlack = (ImageView) findViewById(R.id.imgBlack);
        imgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundWebview = 3;
                setSaveLocal();
            }
        });


//        BTN Font size
        tvFontSize = (TextView) findViewById(R.id.tvFontSize);
        tvFontSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SettingsActivity.this, FontSizeActivity.class);
                startActivityForResult(myIntent, 1);
            }
        });

        tvFont = (TextView) findViewById(R.id.tvFont);
        tvFont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SettingsActivity.this, FontActivity.class);
                startActivityForResult(myIntent, 2);
            }
        });


//        BTN DONE
        TextView tvDone = (TextView) findViewById(R.id.tvDoneSettings);
        tvDone.setOnClickListener(new View.OnClickListener() {
            public static final String PUBLIC_STATIC_STRING_IDENTIFIER = "";

            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER, true);
                setResult(backgroundWebview, resultIntent);
                finish();
            }
        });
    }

    private void setSaveLocal(){
        SharedPreferences preShare = getSharedPreferences("BACKGROUND", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();
        edit.putInt("BGWEBVIEW", backgroundWebview);
        edit.commit();

        SharedPreferences preShareJustify = getSharedPreferences("JUSTIFY", MODE_PRIVATE);
        SharedPreferences.Editor editJustify = preShareJustify.edit();
        editJustify.putBoolean("JUS", isJustify);
        editJustify.commit();

    }

    private void getSaveLocal(){
        SharedPreferences preShare = getSharedPreferences("BACKGROUND", MODE_PRIVATE);
        backgroundWebview = preShare.getInt("BGWEBVIEW", 1);

        SharedPreferences preShareFontSize = getSharedPreferences("FONTSIZE", MODE_PRIVATE);
        fontSize = preShareFontSize.getInt("SIZE", 2);

        SharedPreferences preShareFont = getSharedPreferences("FONTCHANGED", MODE_PRIVATE);
        font = preShareFont.getString("FONT", "home1");

        Log.e("Setting", font);


        SharedPreferences preShareJustify = getSharedPreferences("JUSTIFY", MODE_PRIVATE);
        isJustify = preShareJustify.getBoolean("JUS", true);

    }

}
