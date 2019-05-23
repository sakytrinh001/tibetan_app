package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class HighlighActivity extends Activity implements View.OnClickListener{
    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};

    private ArrayList<String> arrHiglig = new ArrayList<String>();
    private ArrayList<String> arrHigligShow = new ArrayList<String>();

    private boolean ascending = true;
    private ImageView imgView1, imgView2;
    private GridView gv;
    private boolean hideShow = false;
    private LinearLayout ln;
    private boolean isShowDelete = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highligh);
        setValueArr();
        setUp();

    }

    private void setValueArr(){

        SharedPreferences preShare = getSharedPreferences("SAVEHIGLIG", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preShare.getString("HIGLIG", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        arrHiglig = gson.fromJson(json, type);

        if (arrHiglig != null){

            for (int i = 0; i< arrHiglig.size(); i++){
                if (!arrHiglig.get(i).isEmpty()){
                    String a = arrHiglig.get(i).replace("u003Cspan style=", "")
                        .replace("background:#ffc570;", "")
                        .replace(">", "").replace("/", "")
                        .replace("\\", "").replace("span", "")
                        .replace("u003C", "")
                        .replace("\"", "");
                    arrHigligShow.add(changeTextID(a));
                }

            }

        }

    }

    private void setUp(){
        imgView1 = (ImageView) findViewById(R.id.checkSXh1);
        imgView1.setOnClickListener(this);
        imgView1.setVisibility(View.GONE);

        imgView2 = (ImageView) findViewById(R.id.checkSXh2);
        imgView2.setOnClickListener(this);
        imgView2.setVisibility(View.GONE);

        ImageView imgBack = (ImageView) findViewById(R.id.back_note_highligh);
        imgBack.setOnClickListener(this);

        Button btnSort = (Button) findViewById(R.id.btnshort_highligh);
        btnSort.setOnClickListener(this);

        Button btnEdit = (Button) findViewById(R.id.btnedit_highligh);
        btnEdit.setOnClickListener(this);

        TextView tvDone, tv1, tv2;
        tvDone = (TextView) findViewById(R.id.tv_done_highligh);
        tvDone.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.tvSXh1);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tvSXh2);
        tv2.setOnClickListener(this);


        ln = (LinearLayout) findViewById(R.id.lnSXh);
        ln.setVisibility(View.GONE);

        gv =(GridView) findViewById(R.id.grView_highligh);
        gv.setAdapter(new AdapterPS(HighlighActivity.this, arrHigligShow, true, 3));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_note_highligh:
                finish();
                break;

            case R.id.tv_done_highligh:
                finish();
                break;
            case R.id.tvSXh1:
                imgView1.setVisibility(View.VISIBLE);
                imgView2.setVisibility(View.GONE);
                ascending = true;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;

            case R.id.tvSXh2:
                imgView2.setVisibility(View.VISIBLE);
                imgView1.setVisibility(View.GONE);
                ascending = false;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;
            case R.id.btnshort_highligh:
                hideShow = !hideShow;
                if (hideShow){
                    ln.setVisibility(View.VISIBLE);
                }else{
                    ln.setVisibility(View.GONE);
                }
                break;
            case R.id.btnedit_highligh:
                if (isShowDelete){
                    isShowDelete = false;
                }else{
                    isShowDelete = true;
                }
                gv.setAdapter(new AdapterPS(HighlighActivity.this, arrHigligShow, isShowDelete, 3));
                break;
        }
    }


    private String changeTextID(String chap){
        //verse-0-5-36
        ArrayList<String> arrayVerse = new ArrayList<String>();

        for ( int i = 0; i < 27; i++ ){
            arrayVerse.add("verse-" + (i + 36) + "-");
        }
        for (int i = 0; i < arrayVerse.size(); i++) {
            if (chap.contains(arrayVerse.get(i))){
                return chap.replace(arrayVerse.get(i), dataIdBook[i] + ":").replaceFirst("-", ":").replaceFirst(":", " ");
            }
        }

        return "མད་ཐཱའི-";

    }

    private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(arrHigligShow);
        }
        else
        {
            Collections.reverse(arrHigligShow);
        }
        gv.setAdapter(new AdapterPS(HighlighActivity.this, arrHigligShow, true, 3));
    }

}
