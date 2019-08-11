package com.android.dfr.tibetan_dfr;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dfr.tibetan_dfr.adapters.ListViewAdapter;
import com.android.dfr.tibetan_dfr.adapters.WorldPopulation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Created by MinhThanh on 11/26/17.
 */

public class SearchActivity extends Activity{

    private Context mContext;
    private String[] separated;

    private WorldPopulation[] separatedAllBook;
    private ListView lv, lvHis;
    private ArrayList<String> arrSeparated = new ArrayList<String>();
    private ArrayList<WorldPopulation> arrListSearch = new ArrayList<WorldPopulation>();
    private EditText edSearch;
    ListViewAdapter adapter;
    ArrayAdapter<String>adapterHis;
    private ProgressBar prBar;
    private ArrayList<String> arrSeparatedHis = new ArrayList<String>();
    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        getHis();
        initView();
        mReadJsonData();

    }

    protected void initView(){

        TextView tvDone = (TextView) findViewById(R.id.tvDoneSearch);
        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edSearch = (EditText) findViewById(R.id.edSearch);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() == 0) {
                    lvHis.setVisibility(View.VISIBLE);
                    lv.setVisibility(View.GONE);
                    prBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String text = edSearch.getText().toString().toLowerCase(Locale.getDefault());
                    if (!checkContains(text)){
                        arrSeparatedHis.add(text);
                        adapterHis.notifyDataSetChanged();
                        saveHis();
                    }
                    lv.setVisibility(View.VISIBLE);
                    lvHis.setVisibility(View.GONE);
                    adapter.filter("དེ");
                    InputMethodManager imm = (InputMethodManager)textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(textView.getWindowToken(), 0);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private boolean checkContains(String txt){
        if (arrSeparatedHis != null){
            for (int i = 0; i < arrSeparatedHis.size(); i++){
                if (arrSeparatedHis.contains(txt)){
                   return true ;
                }
            }
        }
        return false;
    }

    private void saveHis(){
        Gson gson = new Gson();
        String json = gson.toJson(arrSeparatedHis);
        SharedPreferences preShare = getSharedPreferences("SAVEHISSEARCH", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();
        edit.putString("HISSEARCH", json);
        edit.commit();
    }

    private void getHis(){
        SharedPreferences preShare = getSharedPreferences("SAVEHISSEARCH", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preShare.getString("HISSEARCH", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if (json != null){
            arrSeparatedHis = gson.fromJson(json, type);
        }
    }

    public void mReadJsonData() {
        prBar = (ProgressBar) findViewById(R.id.progressSearch);
        prBar.setVisibility(View.GONE);

        lv = (ListView) findViewById(R.id.lvSearch);
        lv.setVisibility(View.GONE);

        lvHis = (ListView) findViewById(R.id.lvSearchHis);
        lvHis.setVisibility(View.VISIBLE);

        adapterHis=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, arrSeparatedHis);
        //4. Đưa Data source vào ListView
        lvHis.setAdapter(adapterHis);


        //5. Thiết lập sự kiện cho Listview, khi chọn phần tử nào thì hiển thị lên TextView
        lvHis.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1,
                                            int arg2,
                                            long arg3) {
                        //đối số arg2 là vị trí phần tử trong Data Source (arr)
                        adapter.filter(arrSeparatedHis.get(arg2));
                        edSearch.setText(arrSeparatedHis.get(arg2));
                        lv.setVisibility(View.VISIBLE);
                        lvHis.setVisibility(View.GONE);
                        InputMethodManager imm = (InputMethodManager)edSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(edSearch.getWindowToken(), 0);
                        }
                    }
                });

        lv.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                lv.removeOnLayoutChangeListener(this);
                prBar.setVisibility(View.GONE);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.putExtra("MESSAGESEAARCH", getChapter(String.valueOf(arrListSearch.get(position).getIdText())));
                setResult(10,intent);
                finish();
            }
        });

        for (int i = 36; i < 64; i++){
            String fileContents = ReadFile("ntb"+i+".txt");
            separated = fileContents.split("\\\\v");
            List<String> newList = Arrays.asList(separated);
            arrSeparated.addAll(newList);
        }

        for (int i = 0; i < arrSeparated.size(); i++)
        {
            WorldPopulation wp = new WorldPopulation(arrSeparated.get(i));
            // Binds all strings into an array
            arrListSearch.add(wp);
        }
        adapter = new ListViewAdapter(this, arrListSearch);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                if (arrListSearch.size() > 0){
                    lv.setVisibility(View.VISIBLE);
                }
                super.onChanged();
            }
        });
        lv.setAdapter(adapter);
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

    protected String getChapter(String chap){
        String currentChap = "";
        //verse-0-5-36
        SharedPreferences preShare = getSharedPreferences("PAGEBOOK", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();

        for (int i = 1; i < dataIdBook.length; i++){
            if (chap.contains(dataIdBook[i])){
                edit.putInt("PAGE", i);
                edit.commit();
                return chap.replace(  dataIdBook[i] + "\n ", "verse-"+(i+36)+"-").replace(":","-");
            }
        }
        return currentChap;
    }
}
