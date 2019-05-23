package com.android.dfr.tibetan_dfr;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
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
        import android.widget.ListAdapter;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.dfr.tibetan_dfr.adapters.AdapterPS;
        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.Collections;

/**
 * Created by MinhThanh on 11/19/17.
 */

public class FavouriteActivity extends Activity implements View.OnClickListener {

    private ArrayList<String> arrFavouritea = new ArrayList<String>();

    private LinearLayout ln;
    private ImageView imgView1, imgView2;

    private boolean ascending = true;
    private boolean hideShow = false;

    private boolean isTest = true;
    private ArrayAdapter<String> da;

    private GridView gv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_favourites);
        getFavourite();

        setUp();
    }

    private void getFavourite(){
        SharedPreferences preShare = getSharedPreferences("SAVEFAVOURITE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preShare.getString("FAVOURITE", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if (json != null){
            arrFavouritea = gson.fromJson(json, type);
        }
    }

    private void setUp(){

        ImageView imgBack = (ImageView) findViewById(R.id.back_note_favourite);
        imgBack.setOnClickListener(this);

        imgView1 = (ImageView) findViewById(R.id.checkSX1);
        imgView1.setOnClickListener(this);
        imgView1.setVisibility(View.GONE);

        imgView2 = (ImageView) findViewById(R.id.checkSX2);
        imgView2.setOnClickListener(this);
        imgView2.setVisibility(View.GONE);

        ln = (LinearLayout) findViewById(R.id.lnSX);

        ln.setVisibility(View.GONE);

        Button btnSort = (Button) findViewById(R.id.btnshort_favourite);
        btnSort.setOnClickListener(this);

        Button btnEdit = (Button) findViewById(R.id.btnedit_favourite);
        btnEdit.setOnClickListener(this);

        TextView tvDone, tv1, tv2;
        tvDone = (TextView) findViewById(R.id.tv_done_favourite);
        tvDone.setOnClickListener(this);

        tv1 = (TextView) findViewById(R.id.tvSX1);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tvSX2);
        tv2.setOnClickListener(this);

        gv = (GridView) findViewById(R.id.grView_favourite);

        gv.setAdapter(new AdapterPS(FavouriteActivity.this, arrFavouritea, true, 2));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.back_note_favourite:
                finish();
                break;
            case R.id.tv_done_favourite:
                finish();
                break;
            case R.id.btnshort_favourite:
                hideShow = !hideShow;
                if (hideShow){
                    ln.setVisibility(View.VISIBLE);
                }else{
                    ln.setVisibility(View.GONE);
                }

                break;
            case R.id.tvSX1:
                imgView1.setVisibility(View.VISIBLE);
                imgView2.setVisibility(View.GONE);
                ascending = true;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;

            case R.id.tvSX2:
                imgView2.setVisibility(View.VISIBLE);
                imgView1.setVisibility(View.GONE);
                ascending = false;
                sortData(ascending);
                hideShow = !hideShow;
                ln.setVisibility(View.GONE);
                break;
            case R.id.btnedit_favourite:
                if (isTest){
                    isTest = false;
                }else{
                    isTest = true;
                }
                gv.setAdapter(new AdapterPS(FavouriteActivity.this, arrFavouritea, isTest, 2));
                break;
        }

    }
    private void sortData(boolean asc)
    {
        //SORT ARRAY ASCENDING AND DESCENDING
        if (asc)
        {
            Collections.sort(arrFavouritea);
        }
        else
        {
            Collections.reverse(arrFavouritea);
        }

        gv.setAdapter(new AdapterPS(FavouriteActivity.this, arrFavouritea, true, 2));
    }
}
