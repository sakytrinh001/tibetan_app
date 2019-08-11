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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by MinhThanh on 11/19/17.
 */

public class HistoryActivity extends Activity implements View.OnClickListener {

    private ArrayList<String> arrHistory = new ArrayList<String>();
    private ImageView imgView1, imgView2;
    private LinearLayout ln;
    private GridView gv;
    private boolean ascending = true;
    private boolean hideShow = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_history);
        setUp();
    }

    private void setUp(){

        imgView1 = (ImageView) findViewById(R.id.checkSXhs1);
        imgView1.setOnClickListener(this);
        imgView1.setVisibility(View.GONE);

        imgView2 = (ImageView) findViewById(R.id.checkSXhs2);
        imgView2.setOnClickListener(this);
        imgView2.setVisibility(View.GONE);

        ImageView imgBack = (ImageView) findViewById(R.id.back_note_history);
        imgBack.setOnClickListener(this);

        Button btnSort = (Button) findViewById(R.id.btnshort_history);
        btnSort.setOnClickListener(this);

        Button btnEdit = (Button) findViewById(R.id.btnedit_history);
        btnEdit.setOnClickListener(this);

        TextView tvDone, tv1, tv2;
        tvDone = (TextView) findViewById(R.id.tv_done_history);
        tvDone.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.tvSXhs1);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tvSXhs2);
        tv2.setOnClickListener(this);


        ln = (LinearLayout) findViewById(R.id.lnSXhs);
        ln.setVisibility(View.GONE);

        gv =(GridView) findViewById(R.id.grView_his);
        if (arrHistory !=  null){
            //Gán DataSource vào ArrayAdapter
            ArrayAdapter<String> da=new ArrayAdapter<String>
                    (
                            this,
                            android.R.layout.simple_list_item_1, arrHistory
                    );
            gv.setAdapter(da);
        }
        gv.setOnItemClickListener(new AdapterView
                .OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int arg2,
                                    long arg3) {
                //Hiển thị phần tử được chọn trong GridView lên TextView
                Intent intent=new Intent();
                intent.putExtra("MESSAGEHL", arrHistory.get(arg2));
                setResult(1,intent);
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_note_history:
                finish();
                break;

            case R.id.tv_done_history:
                finish();
                break;
            case R.id.tvSXhs1:
                imgView1.setVisibility(View.VISIBLE);
                imgView2.setVisibility(View.GONE);
                ascending = true;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;

            case R.id.tvSXhs2:
                imgView2.setVisibility(View.VISIBLE);
                imgView1.setVisibility(View.GONE);
                ascending = false;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;
            case R.id.btnshort_history:
                hideShow = !hideShow;
                if (hideShow){
                    ln.setVisibility(View.VISIBLE);
                }else{
                    ln.setVisibility(View.GONE);
                }

                break;
        }

    }
    private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(arrHistory);
        }
        else
        {
            Collections.reverse(arrHistory);
        }
        gv.setAdapter(new ArrayAdapter(HistoryActivity.this,android.R.layout.simple_list_item_1,arrHistory));
    }
}
