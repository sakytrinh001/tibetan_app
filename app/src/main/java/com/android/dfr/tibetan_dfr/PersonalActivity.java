package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by MinhThanh on 10/25/17.
 */

public class PersonalActivity extends Activity {

    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_personal);
        setupAction();
    }

    private void setupAction(){

        TextView tvDone = (TextView) findViewById(R.id.tvDonePersonal);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        RelativeLayout rlp1 = (RelativeLayout) findViewById(R.id.rlly_p1);
        rlp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PersonalActivity.this, NoteActivity.class);
                startActivityForResult(myIntent, 1);
            }
        });
        RelativeLayout rlp2 = (RelativeLayout) findViewById(R.id.rlly_p2);
        rlp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PersonalActivity.this, FavouriteActivity.class);
                startActivityForResult(myIntent, 2);
            }
        });
        RelativeLayout rlp3 = (RelativeLayout) findViewById(R.id.rlly_p3);
        rlp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(PersonalActivity.this, HighlighActivity.class);
                startActivityForResult(myIntent, 3);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null){
            Intent intent=new Intent();
            intent.putExtra("MESSAGEPS", changeTextID(data.getStringExtra("MESSAGEHL")).replace(":","-").replace("--","-"));
            setResult(101,intent);
            finish();
        }

    }

    private String changeTextID(String chap){
        //co sua
        String currentChap = "";
        for (int i = 0; i < dataIdBook.length; i++){
            if (chap.contains(dataIdBook[i])){
                return chap.replace(dataIdBook[i], "verse-" + (i + 36) + "-").split("\n")[0];
            }
        }
        return currentChap;

    }

}
