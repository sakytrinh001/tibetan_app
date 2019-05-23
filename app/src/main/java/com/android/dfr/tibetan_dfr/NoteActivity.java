package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dfr.tibetan_dfr.adapters.AdapterPS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by MinhThanh on 11/19/17.
 */

public class NoteActivity extends Activity implements View.OnClickListener {


    private ArrayList<String> arrNote = new ArrayList<String>();
    private GridView gv;
    private boolean isTest = true;

    private LinearLayout ln;
    private ImageView imgView1, imgView2;

    private boolean ascending = true;
    private boolean hideShow = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        setUp();
    }

    private void setUp(){

        SharedPreferences preShare = getSharedPreferences("SAVEALLNOTE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preShare.getString("NOTE", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if (json != null){
            arrNote = gson.fromJson(json, type);
        }

        ImageView imgBack = (ImageView) findViewById(R.id.back_note);
        imgBack.setOnClickListener(this);

        TextView tvDone = (TextView) findViewById(R.id.tvDone_note);
        tvDone.setOnClickListener(this);

        Button bt = (Button) findViewById(R.id.btnshort_note);
        bt.setOnClickListener(this);

        Button bt2 = (Button) findViewById(R.id.btnedit_note);
        bt2.setOnClickListener(this);

        TextView tv1 = (TextView) findViewById(R.id.tvSXhn1);
        tv1.setOnClickListener(this);

        TextView tv2 = (TextView) findViewById(R.id.tvSXhn2);
        tv2.setOnClickListener(this);

        imgView1 = (ImageView) findViewById(R.id.checkSXhn1);
        imgView1.setOnClickListener(this);
        imgView1.setVisibility(View.GONE);

        imgView2 = (ImageView) findViewById(R.id.checkSXhn2);
        imgView2.setOnClickListener(this);
        imgView2.setVisibility(View.GONE);

        ln = (LinearLayout) findViewById(R.id.lnSXhn);
        ln.setVisibility(View.GONE);


        gv=(GridView) findViewById(R.id.grView);
        gv.setAdapter(new AdapterPS(NoteActivity.this, arrNote, isTest, 1));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_note:
                finish();
                break;

            case R.id.tvDone_note:
                finish();
                break;
            case R.id.tvSXhn1:
                imgView1.setVisibility(View.VISIBLE);
                imgView2.setVisibility(View.GONE);
                ascending = true;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;

            case R.id.tvSXhn2:
                imgView2.setVisibility(View.VISIBLE);
                imgView1.setVisibility(View.GONE);
                ascending = false;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;
            case R.id.btnshort_note:
                hideShow = !hideShow;
                if (hideShow){
                    ln.setVisibility(View.VISIBLE);
                }else{
                    ln.setVisibility(View.GONE);
                }
                break;

            case R.id.btnedit_note:
                if (isTest){
                    isTest = false;
                }else{
                    isTest = true;
                }
                gv.setAdapter(new AdapterPS(NoteActivity.this, arrNote, isTest, 1));
                break;
        }

    }
    private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(arrNote);
        }
        else
        {
            Collections.reverse(arrNote);
        }

        gv.setAdapter(new AdapterPS(NoteActivity.this, arrNote, true, 1));
    }
}
