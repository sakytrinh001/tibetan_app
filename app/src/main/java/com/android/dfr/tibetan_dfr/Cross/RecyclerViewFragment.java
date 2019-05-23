package com.android.dfr.tibetan_dfr.Cross;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dfr.tibetan_dfr.GlossaryActivity;
import com.android.dfr.tibetan_dfr.IntroActivity;
import com.android.dfr.tibetan_dfr.MainActivity;
import com.android.dfr.tibetan_dfr.R;
import com.android.dfr.tibetan_dfr.adapters.ClickChapter;
import com.android.dfr.tibetan_dfr.adapters.ItemClickListener;
import com.android.dfr.tibetan_dfr.adapters.Section;
import com.android.dfr.tibetan_dfr.adapters.SectionStateChangeListener;
import com.android.dfr.tibetan_dfr.adapters.SectionedExpandableLayoutHelper;
import com.android.dfr.tibetan_dfr.models.Item;

import java.util.ArrayList;

/**
 * Created by MinhThanh on 10/29/17.
 */

public class RecyclerViewFragment extends Activity implements ItemClickListener {

    RecyclerView mRecyclerView;
    SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper;
    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_fragment);


//        //setting the recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setBackgroundColor(Color.WHITE);

        sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(this,
                mRecyclerView, this, 5);

        ArrayList<Item> arrayListIntro = new ArrayList<>();
        sectionedExpandableLayoutHelper.addSection(getString(R.string.intro1), arrayListIntro);

        ArrayList<Item> arrayListIntro2 = new ArrayList<>();
        sectionedExpandableLayoutHelper.addSection(getString(R.string.intro2), arrayListIntro2);

        //new
        ArrayList<Item> arrayList = new ArrayList<>();
//
        ArrayList<Item> arrayListGlossary = new ArrayList<>();

        ArrayList<Item> arrayListNew = new ArrayList<>();
        sectionedExpandableLayoutHelper.addSection(getString(R.string.newBook), arrayListNew);


        arrayList = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            arrayList.add(new Item(String.valueOf(i), 37));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[0], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            arrayList.add(new Item(String.valueOf(i), 38));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[1], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            arrayList.add(new Item(String.valueOf(i), 39));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[2], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 22; i++) {
            arrayList.add(new Item(String.valueOf(i), 40));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[3], arrayList);


        arrayList = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            arrayList.add(new Item(String.valueOf(i), 41));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[4], arrayList);


        arrayList = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            arrayList.add(new Item(String.valueOf(i), 42));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[5], arrayList);


        arrayList = new ArrayList<>();
        for (int i = 1; i < 17; i++) {
            arrayList.add(new Item(String.valueOf(i), 43));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[6], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            arrayList.add(new Item(String.valueOf(i), 44));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[7], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            arrayList.add(new Item(String.valueOf(i), 45));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[8], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            arrayList.add(new Item(String.valueOf(i), 36));
        }sectionedExpandableLayoutHelper.addSection(dataIdBook[9], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            arrayList.add(new Item(String.valueOf(i), 47));
        }sectionedExpandableLayoutHelper.addSection(dataIdBook[10], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            arrayList.add(new Item(String.valueOf(i), 48));
        }sectionedExpandableLayoutHelper.addSection(dataIdBook[11], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            arrayList.add(new Item(String.valueOf(i), 49));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[12], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            arrayList.add(new Item(String.valueOf(i), 50));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[13], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            arrayList.add(new Item(String.valueOf(i), 51));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[14], arrayList);


        arrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            arrayList.add(new Item(String.valueOf(i), 52));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[15], arrayList);


        arrayList = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            arrayList.add(new Item(String.valueOf(i), 53));
        }sectionedExpandableLayoutHelper.addSection(dataIdBook[16], arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Item("1", 54));
        sectionedExpandableLayoutHelper.addSection(dataIdBook[17], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 14; i++) {
            arrayList.add(new Item(String.valueOf(i), 55));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[18], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            arrayList.add(new Item(String.valueOf(i), 56));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[19], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            arrayList.add(new Item(String.valueOf(i), 57));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[20], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            arrayList.add(new Item(String.valueOf(i), 58));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[21], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            arrayList.add(new Item(String.valueOf(i), 59));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[22], arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Item("1", 60));
        sectionedExpandableLayoutHelper.addSection(dataIdBook[23], arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Item("1", 61));
        sectionedExpandableLayoutHelper.addSection(dataIdBook[24], arrayList);


        arrayList = new ArrayList<>();
        arrayList.add(new Item("1", 62));
        sectionedExpandableLayoutHelper.addSection(dataIdBook[25], arrayList);

        arrayList = new ArrayList<>();
        for (int i = 1; i < 23; i++) {
            arrayList.add(new Item(String.valueOf(i), 63));
        }
        sectionedExpandableLayoutHelper.addSection(dataIdBook[26], arrayList);

        sectionedExpandableLayoutHelper.addSection(getString(R.string.glossary), arrayListGlossary);


        sectionedExpandableLayoutHelper.notifyDataSetChanged();



        TextView tvDone = (TextView) findViewById(R.id.tvDoneRecycler);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void itemClicked(Item item) {
        Intent intent=new Intent();
        intent.putExtra("MESSAGE","c-" + String.valueOf(item.getId()-1) + "-" + item.getName());
        intent.putExtra("MESSAGEID",String.valueOf(item.getId()));
        setResult(2,intent);
        finish();
    }

    @Override
    public void itemClicked(Section section) {

    }

    @Override
    public void itemClickedSection(Section section, int position) {
        SharedPreferences.Editor editor;
        SharedPreferences sharedPref;
        sharedPref= getSharedPreferences("INTRO", Context.MODE_PRIVATE);
        if (section.getName().equals(getString(R.string.intro1))){
            Intent i = new Intent(RecyclerViewFragment.this, IntroActivity.class);
            startActivity(i);

            editor=sharedPref.edit();
            // Save your string in SharedPref
            editor.putString("intro", "1");
            editor.commit();

            finish();
        }
        if (section.getName().equals(getString(R.string.intro2))){
            Intent i = new Intent(RecyclerViewFragment.this, IntroActivity.class);
            startActivity(i);

            editor=sharedPref.edit();
            // Save your string in SharedPref
            editor.putString("intro", "2");
            editor.commit();

            finish();
        }

        if (section.getName().equals(getString(R.string.glossary))){
            SharedPreferences.Editor editor1;
            SharedPreferences sharedPref1;
            sharedPref1 = getSharedPreferences("GLOSSARY", Context.MODE_PRIVATE);
            editor1=sharedPref1.edit();
            // Save your string in SharedPref
            editor1.putBoolean("glossary", true);

            Intent i = new Intent(RecyclerViewFragment.this, GlossaryActivity.class);
            startActivity(i);
            if (position == 68){
                editor1.putBoolean("glossary", false);
            }
            editor1.commit();
            finish();
        }
    }
}
