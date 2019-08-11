package com.android.dfr.tibetan_dfr.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dfr.tibetan_dfr.R;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by MinhThanh on 12/5/17.
 */

public class AdapterPS extends BaseAdapter {
    private ArrayList<String> result = new ArrayList<String>();
    Context context;
    private static LayoutInflater inflater=null;
    Holder holder=new Holder();
    View rowView;
    boolean isTest = true;
    int indexPS;

    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};

    private ArrayList<String> resultsSave = new ArrayList<String>();
    public AdapterPS(Context mContext, ArrayList<String> osNameList, boolean ismTest, int index) {
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=mContext;
        isTest = ismTest;
        indexPS = index;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_text;
        ImageView os_img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        rowView = inflater.inflate(R.layout.item_grd, null);
        holder.os_text =(TextView) rowView.findViewById(R.id.tvAdapter);
        holder.os_img =(ImageView) rowView.findViewById(R.id.imgDelete);

        holder.os_text.setText(result.get(position));

        if (isTest){
            hideShow(true);
        }else{
            hideShow(false);
        }

        holder.os_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result.remove(position);
                if (result != null){
                    for (int i = 0; i < result.size(); i++){
                        resultsSave.add(changeTextID(result.get(i)));
                    }
                }
                if (indexPS == 2){
                    Gson gson = new Gson();
                    String json = gson.toJson(resultsSave);
                    SharedPreferences preShare = ((Activity)context).getSharedPreferences("SAVEFAVOURITE", 0);
                    SharedPreferences.Editor edit = preShare.edit();
                    edit.putString("FAVOURITE", json);
                    edit.commit();
                }
                if (indexPS == 3){
                    Gson gson = new Gson();
                    String json = gson.toJson(resultsSave);
                    SharedPreferences preShare = ((Activity)context).getSharedPreferences("SAVEHIGLIG", ((Activity)context).MODE_PRIVATE);
                    SharedPreferences.Editor edit = preShare.edit();
                    edit.putString("HIGLIG", json);
                    edit.commit();
                }
                if (indexPS == 1){
                    Gson gson = new Gson();
                    String json = gson.toJson(resultsSave);
                    SharedPreferences preShare = ((Activity)context).getSharedPreferences("SAVEALLNOTE", ((Activity)context).MODE_PRIVATE);
                    SharedPreferences.Editor edit = preShare.edit();
                    edit.putString("NOTE", json);
                    edit.commit();
                }
                notifyDataSetChanged();
            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent();
                intent.putExtra("MESSAGEHL", result.get(position).replace(" ", ""));
                ((Activity)context).setResult(2,intent);
                ((Activity)context).finish();
            }
        });

        return rowView;
    }

    public void hideShow(boolean isHide){
        if (isHide){
            holder.os_img.setVisibility(View.INVISIBLE);
        }else {
            holder.os_img.setVisibility(View.VISIBLE);
        }
    }


    private String changeTextID(String chap){

        String currentChap = "";
        for (int i = 0; i < dataIdBook.length; i++) {
            if (chap.contains(dataIdBook[i])){
                return chap.replace(dataIdBook[i], "verse-" + (i + 36) + "-");
            }

        }

        return currentChap;

    }

}
