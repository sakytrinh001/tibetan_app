package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by MinhThanh on 6/14/18.
 */

public class GlossaryActivity extends Activity {

    private TextView tvDetail, tvTitle;
    private ImageView imgBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro);
        setup();

    }

    protected void setup(){
        tvTitle = (TextView) findViewById(R.id.tvTitleIntro);
        tvTitle.setText(getString(R.string.glossary));
        tvDetail = (TextView)findViewById(R.id.tvDetail1);
        tvDetail.setLineSpacing(1,2);

        readFile("glossary.txt");

        imgBtn = (ImageView) findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    protected void readFile(String intro){
        tvDetail.setText("");
        String fileContents = ReadFile(intro);
        tvDetail.setText(fileContents.toString());

        Log.e("readFile: ", fileContents.toString());
    }

    public String ReadFile(String fileName) {
        BufferedReader br = null;
        String fileText = "";
        try {
            String str = "";
            StringBuffer buffer = new StringBuffer();
            AssetManager assetManager = getAssets();
            InputStream stream = assetManager.open(fileName);

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream));
            if (stream != null) {
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                    buffer.append('\n');
                }
            }
            fileText = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return fileText;
    }
}
