package com.android.dfr.tibetan_dfr;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.dfr.tibetan_dfr.Cross.RecyclerViewFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements View.OnClickListener  {

    private String[] dataIdBook = {"མད༌ཐཱ།","མཱཪ་ཀུ","ལོ་ཀུ","ཡོ་ཧ་ནན།","མཛད་འཕྲིན།","རོ་མཱ་པ།","ཀོ་རིན་ཐུ་པ་དང་པོ།","ཀོ་རིན་ཐུ་པ་གཉིས་པ།","ག་ལད་ཡཱ་པ།","ཨེ་ཕེ་སི་པ།","ཕི་ལིབ་པི་པ།","ཀོ་ལོ་སཱ་པ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་དང་པོ།","ཐེ་སཱ་ལོ་ནེ་ཀེ་གཉིས་པ།","ཐི་མོ་ཐེ་དང་པོ།","ཐི་མོ་ཐེ་གཉིས་པ།","ཐེ་ཏུ།","ཕི་ལེ་མོན།","ཨིབ་རི་པ།","ཡ་ཀོབ།","པེ་ཏྲོ་དང་པོ།","པེ་ཏྲོ་གཉིས་པ།","ཡོ་ཧ་ནན་དང་པོ།","ཡོ་ཧ་ནན་གཉིས་པ།","ཡོ་ཧ་ནན་གསུམ་པ།","ཡ་ཧུ་དཱ།","མངོན་པ།"};


    private String[] dataNoteBook37 = {"footer-content-0-58", "footer-content-0-85", "footer-content-0-105", "footer-content-0-334", "footer-content-0-360", "footer-content-0-431", "footer-content-0-444", "footer-content-0-457",
            "footer-content-0-722", "footer-content-0-814", "footer-content-0-815", "footer-content-0-820", "footer-content-0-900", "footer-content-0-1006", "footer-content-0-1013", "footer-content-0-1058", "footer-content-0-1128",
            "footer-content-0-1159", "footer-content-0-1190", "footer-content-0-1202", "footer-content-0-1294", "footer-content-0-1315", "footer-content-0-1333", "footer-content-0-1355", "footer-content-0-1426",
            "footer-content-0-1452", "footer-content-0-1481"};

    private String[] dataNoteBook38 = {"footer-content-1-24", "footer-content-1-104", "footer-content-1-143", "footer-content-1-312", "footer-content-1-319", "footer-content-1-382", "footer-content-1-412",
            "footer-content-1-478", "footer-content-1-503", "footer-content-1-551", "footer-content-1-650", "footer-content-1-703", "footer-content-1-738", "footer-content-1-750", "footer-content-1-855",
            "footer-content-1-865", "footer-content-1-876", "footer-content-1-890"};

    private String[] dataNoteBook39 = {"footer-content-2-40", "footer-content-2-98", "footer-content-2-162", "footer-content-2-292", "footer-content-2-361", "footer-content-2-702", "footer-content-2-789",
            "footer-content-2-1095", "footer-content-2-1117", "footer-content-2-1132", "footer-content-2-1289", "footer-content-2-1518", "footer-content-2-1540"};

    private String[] dataNoteBook40 = {"footer-content-3-22", "footer-content-3-52", "footer-content-3-96", "footer-content-3-110", "footer-content-3-122", "footer-content-3-125", "footer-content-3-177",
            "footer-content-3-223", "footer-content-3-227", "footer-content-3-281", "footer-content-3-372", "footer-content-3-483", "footer-content-3-540", "footer-content-3-667", "footer-content-3-791",
            "footer-content-3-857", "footer-content-3-989", "footer-content-3-1008", "footer-content-3-1040", "footer-content-3-1095", "footer-content-3-1126"};

    private String[] dataNoteBook41 = {"footer-content-4-26", "footer-content-4-37", "footer-content-4-64", "footer-content-4-112", "footer-content-4-171", "footer-content-4-300", "footer-content-4-319",
            "footer-content-4-553", "footer-content-4-569", "footer-content-4-631", "footer-content-4-655", "footer-content-4-744", "footer-content-4-839", "footer-content-4-842", "footer-content-4-911",
            "footer-content-4-939", "footer-content-4-1023", "footer-content-4-1059", "footer-content-4-1384"};

    private String[] dataNoteBook42 = {"footer-content-5-44", "footer-content-5-301", "footer-content-5-575"};

    private String[] dataNoteBook43 = {"footer-content-6-195", "footer-content-6-257", "footer-content-6-376", "footer-content-6-570"};
    private String[] dataNoteBook44 = {"footer-content-7-48", "footer-content-7-185"};
    private String[] dataNoteBook45 = {"footer-content-8-73", "footer-content-8-221"};

    private String[] dataNoteBook48 = {"footer-content-11-41"};
    private String[] dataNoteBook49 = {"footer-content-12-101"};
    private String[] dataNoteBook50 = {"footer-content-13-82"};

    private String[] dataNoteBook54 = {"footer-content-17-30"};
    private String[] dataNoteBook55 = {"footer-content-18-258", "footer-content-18-276", "footer-content-18-285", "footer-content-18-292"};
    private String[] dataNoteBook56 = {"footer-content-19-24"};

    private String[] dataNoteBook59 = {"footer-content-22-21"};
    private String[] dataNoteBook63 = {"footer-content-26-36", "footer-content-26-41", "footer-content-26-576", "footer-content-26-696"};

    protected WebView webView;
    private JsHandler _jsHandler;
    final int[] index = {37};

    private ProgressBar progresBar;

    private String getTV;

    private Button btNext;
    private Button btPrevious;
    private TextView imgLogo;
    private ImageView imgSetting;
    private ImageView imgPersonal;
    private LinearLayout lnFontSize;
    private Button btChangedFont;
    private WebSettings webSettings;
    private View viewAlpha;
    private Button btChangeChapter;
    private ScrollView scrV;

    private int feature = 0;
    private ImageView imgHighligh, imgFavourite, imgShare, imgNote, imgCopy, imgSearch;


    private ArrayList<String> arLocal1 = new ArrayList<String>();
    private ArrayList<String> arrFavourite = new ArrayList<String>();
    private ArrayList<String> arSaveAllNote = new ArrayList<String>();

    private boolean isHiglig;
    private String idText;
    private RelativeLayout rllyMenu, rlViewNote, rlnTop;
    private TextView tvTitleNote, tvDoneNote, tvCancelNote, tvChapterName;
    private EditText noteText;


    private FrameLayout frlyTop, frlyBottom;
    private ImageView imgLG;

    boolean isJustify = true;
    int fontSize = 2;
    int backgroundWebview = 1;
    String fontStype = "home1";
    String idVerseChapter = "";

    String setFontSizeSave = "23";
    boolean isH = false;
    TextView tv1, tv2, tv3, tv4, tv5;

    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            Log.e("SeekBar: ", String.valueOf(progress));
            fontSize = progress;
            setFontSize(false);
//            tvProgressLabel.setText("Progress: " + progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        getSaveLocal();

        initWebView("file:///android_asset/ntb" + String.valueOf(index[0]) + ".html");

        initView();
        initNote();
        changeJustify();

        setOnLongClick();

        initMenu();

        getHiglig();
        getFavourite();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.clearCache(true);
        webView.clearHistory();
    }

    private void setOnLongClick(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webView.setDefaultFocusHighlightEnabled(false);
        }
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                webView.evaluateJavascript("javascript:getTextID()",
                        new ValueCallback<String>() {
                            @SuppressLint("JavascriptInterface")
                            @Override
                            public void onReceiveValue(final String value) {

                                if (!value.replace("\"","").equals("null") && value.replace("\"","").length() > 0
                                        && !value.replace("\"", "").equals("body")){

                                idText = value;
                                webView.loadUrl("javascript:selectText('"+value.replace("\"", "")+"')");
                                        imgHighligh.setImageResource(R.drawable.highligh);
                                        imgNote.setImageResource(R.drawable.note);
                                        imgFavourite.setImageResource(R.drawable.favorites);
                                        imgShare.setImageResource(R.drawable.sharetext);
                                        imgCopy.setImageResource(R.drawable.copy);
                                        webView.evaluateJavascript("getYPositionVerse("+idText+")", new ValueCallback<String>() {
                                            @Override
                                            public void onReceiveValue(String sb) {
                                                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rllyMenu.getLayoutParams();
                                                if (!sb.equals("") && sb.length() > 0 && !sb.equals("null")){
                                                    layoutParams.topMargin = (int) Double.parseDouble(sb);
                                                }
                                                rllyMenu.setLayoutParams(layoutParams);
                                                rllyMenu.setVisibility(View.VISIBLE);

                                            }
                                        });
                                        findSelectHi(idText);
                                        findSelectNote(idText);
                                        findSelectFav(idText);
                            }}
                        });

                return false;
            }
        });
    }


    private void initNote(){
        rlViewNote = (RelativeLayout) findViewById(R.id.rlNote);
        rlViewNote.setVisibility(View.INVISIBLE);

        noteText = (EditText) findViewById(R.id.edtNote);
        noteText.setEnabled(true);
        noteText.setInputType(InputType.TYPE_CLASS_TEXT);
        noteText.setFocusable(true);

        tvTitleNote = (TextView) findViewById(R.id.titlenote);

        tvCancelNote = (TextView) findViewById(R.id.cancelNote);
        tvCancelNote.setOnClickListener(this);

        tvDoneNote = (TextView) findViewById(R.id.donenote);
        tvDoneNote.setOnClickListener(this);

    }

    private void initMenu(){

        tvChapterName = (TextView) findViewById(R.id.txtChapterName);

        frlyTop = (FrameLayout) findViewById(R.id.frly_1);
        frlyTop.setVisibility(View.VISIBLE);

        frlyBottom = (FrameLayout) findViewById(R.id.frly_2);

        imgSearch = (ImageView) findViewById(R.id.search);
        imgSearch.setOnClickListener(this);

        rllyMenu = (RelativeLayout) findViewById(R.id.rlMenu);

        rllyMenu.setVisibility(View.INVISIBLE);

        imgHighligh = (ImageView) findViewById(R.id.id_highligh);
        imgHighligh.setOnClickListener(this);

        imgFavourite = (ImageView) findViewById(R.id.id_favourites);
        imgFavourite.setOnClickListener(this);

        imgShare = (ImageView) findViewById(R.id.id_share);
        imgShare.setOnClickListener(this);

        imgNote = (ImageView) findViewById(R.id.id_note);
        imgNote.setOnClickListener(this);

        imgCopy = (ImageView) findViewById(R.id.id_copy);
        imgCopy.setOnClickListener(this);

        View opacityView;
        opacityView = (View) findViewById(R.id.id_opacityview);
        opacityView.setOnClickListener(this);

    }


    private void getSaveLocal(){

        SharedPreferences preSharee = getSharedPreferences("EXITAPP", MODE_PRIVATE);
        if (preSharee.getInt("EXIT", 1) == 0) {
            System.exit(0);
        }

        SharedPreferences preShare = getSharedPreferences("PAGEBOOK", MODE_PRIVATE);
        index[0] = preShare.getInt("PAGE", 37);

        SharedPreferences preShareFontSize = getSharedPreferences("FONTSIZE", MODE_PRIVATE);
        fontSize = preShareFontSize.getInt("SIZE", 2);

        SharedPreferences preShareBG = getSharedPreferences("BACKGROUND", MODE_PRIVATE);
        backgroundWebview = preShareBG.getInt("BGWEBVIEW", 1);

        SharedPreferences preShareJustify = getSharedPreferences("JUSTIFY", MODE_PRIVATE);
        isJustify = preShareJustify.getBoolean("JUS", true);

        SharedPreferences preShareFont = getSharedPreferences("FONTCHANGED", MODE_PRIVATE);
        fontStype = preShareFont.getString("FONT", "home1");

        SharedPreferences preShareGET = getSharedPreferences("SAVEALLNOTE", MODE_PRIVATE);
        Gson gsonGET = new Gson();
        String jsonGET = preShareGET.getString("NOTE", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if (jsonGET != null){
            arSaveAllNote = gsonGET.fromJson(jsonGET, type);
        }

    }

    private void saveLocal(){

        //PAGE BOOK
        SharedPreferences preShare = getSharedPreferences("PAGEBOOK", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();
        edit.putInt("PAGE", index[0]);
        edit.commit();


        //Font Size
        SharedPreferences preShareFontSize = getSharedPreferences("FONTSIZE", MODE_PRIVATE);
        SharedPreferences.Editor editSize = preShareFontSize.edit();
        editSize.putInt("SIZE", fontSize);
        editSize.commit();


    }

    private void initView(){

        rlnTop = (RelativeLayout) findViewById(R.id.rlTop);
        rlnTop.setVisibility(View.VISIBLE);

        viewAlpha = (View) findViewById(R.id.viewOpacity);
        viewAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideOpacity();
            }
        });
        lnFontSize = (LinearLayout) findViewById(R.id.lnFontSize);
        hideOpacity();

        btChangeChapter = (Button) findViewById(R.id.btChapter);
        btChangeChapter.setOnClickListener(this);

        btChangedFont = (Button) findViewById(R.id.btnChangeFontSize);
        btChangedFont.setVisibility(View.GONE);
        btChangedFont.setOnClickListener(this);

        imgPersonal = (ImageView) findViewById(R.id.btLeft);
        imgPersonal.setOnClickListener(this);

        imgLogo = (TextView) findViewById(R.id.logo1);
        imgLogo.setOnClickListener(this);

        imgLG = (ImageView) findViewById(R.id.imageButton2);
        imgLG.setOnClickListener(this);


        imgSetting = (ImageView) findViewById(R.id.imgSettings);
        imgSetting.setOnClickListener(this);

        btNext = (Button) findViewById(R.id.btNext);
        btNext.setOnClickListener(this);
        btPrevious = (Button) findViewById(R.id.btPrevious);
        btPrevious.setOnClickListener(this);

        if (index[0] == 37){
            btPrevious.setVisibility(View.GONE);
        }else{
            btPrevious.setVisibility(View.VISIBLE);
        }

        if (index[0] == 63){
            btNext.setVisibility(View.GONE);
        }else{
            btNext.setVisibility(View.VISIBLE);
        }

        initFont();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initWebView(String url){
        progresBar = (ProgressBar) findViewById(R.id.marker_progress);
        progresBar.setVisibility(View.VISIBLE);
        webView = (WebView)findViewById(R.id.webView);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);
        }
        _jsHandler = new JsHandler(this, webView);

        webView.addJavascriptInterface(_jsHandler, "JsHandler");
        refreshWebView();
        setHeightWebview();
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                webView.setVisibility(View.INVISIBLE);
                progresBar.setVisibility(View.VISIBLE);
                webView.setEnabled(false);
            }

            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view, url);
                changeBG();
                getIDHighlight();
                changeJustify();
                setFontSize(false);
                if (!idVerseChapter.isEmpty()){ webView.loadUrl("javascript:scrollTo('"+ idVerseChapter + "')");}
                progresBar.setVisibility(View.INVISIBLE);
                webView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            webView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                    frlyTop.setVisibility(View.VISIBLE);
                    rlnTop.setVisibility(View.INVISIBLE);
                    tvChapterName.setVisibility(View.VISIBLE);

                    if (i1 == 0){
                        frlyTop.setVisibility(View.VISIBLE);
                        rlnTop.setVisibility(View.VISIBLE);
                        btChangedFont.setVisibility(View.VISIBLE);
                        tvChapterName.setVisibility(View.INVISIBLE);
                    }else{
                        tvChapterName.setVisibility(View.VISIBLE);
                        frlyTop.setVisibility(View.VISIBLE);
                        btChangedFont.setVisibility(View.VISIBLE);
                        btNext.setVisibility(View.GONE);
                        btPrevious.setVisibility(View.GONE);
                    }
                    getYChapterScroll(i1, 28);

                    if (i1 > 0 && i1 < webView.getContentHeight()) {
                        btChangedFont.setVisibility(View.VISIBLE);
                        btNext.setVisibility(View.GONE);
                        btPrevious.setVisibility(View.GONE);
                    } else {
                        if (index[0] == 37) {
                            btPrevious.setVisibility(View.GONE);
                        } else {
                            btPrevious.setVisibility(View.VISIBLE);
                        }
                        if (index[0] == 63) {
                            btNext.setVisibility(View.GONE);
                        } else {
                            btNext.setVisibility(View.VISIBLE);
                        }
                        btChangedFont.setVisibility(View.GONE);
                    }
                }
            });
        }else {
            webView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    int scrollY = webView.getScrollY(); // For ScrollView

                    frlyTop.setVisibility(View.VISIBLE);
                    rlnTop.setVisibility(View.INVISIBLE);
                    tvChapterName.setVisibility(View.VISIBLE);

                    if (scrollY == 0){
                        frlyTop.setVisibility(View.VISIBLE);
                        rlnTop.setVisibility(View.VISIBLE);
                        btChangedFont.setVisibility(View.VISIBLE);
                        tvChapterName.setVisibility(View.INVISIBLE);
                    }else{
                        tvChapterName.setVisibility(View.VISIBLE);
                        frlyTop.setVisibility(View.VISIBLE);
                        btChangedFont.setVisibility(View.VISIBLE);
                        btNext.setVisibility(View.GONE);
                        btPrevious.setVisibility(View.GONE);
                    }
                    getYChapterScroll(scrollY, 28);

                    if (scrollY > 0 && scrollY < webView.getContentHeight()) {
                        btChangedFont.setVisibility(View.VISIBLE);
                        btNext.setVisibility(View.GONE);
                        btPrevious.setVisibility(View.GONE);
                    } else {
                        if (index[0] == 37) {
                            btPrevious.setVisibility(View.GONE);
                        } else {
                            btPrevious.setVisibility(View.VISIBLE);
                        }
                        if (index[0] == 63) {
                            btNext.setVisibility(View.GONE);
                        } else {
                            btNext.setVisibility(View.VISIBLE);
                        }
                        btChangedFont.setVisibility(View.GONE);
                    }
                }
            });
        }

    }
    private void setFontSizeNote(){

        webView.evaluateJavascript("javascript:document.body.style.setProperty(\"font-size\", " + setFontSizeSave.replace("px", "") + ");", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {

            }
        });


        if (index[0] == 37){
            for (int i = 0; i < dataNoteBook37.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook37[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 38){
            for (int i = 0; i < dataNoteBook38.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook38[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 39){
            for (int i = 0; i < dataNoteBook39.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook39[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 40){
            for (int i = 0; i < dataNoteBook40.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook40[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 41){
            for (int i = 0; i < dataNoteBook41.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook41[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 42){
            for (int i = 0; i < dataNoteBook42.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook42[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 43){
            for (int i = 0; i < dataNoteBook43.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook43[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }

        if (index[0] == 44){
            for (int i = 0; i < dataNoteBook44.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook44[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 45){
            for (int i = 0; i < dataNoteBook45.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook45[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 48){
            for (int i = 0; i < dataNoteBook48.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook48[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 49){
            for (int i = 0; i < dataNoteBook49.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook49[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 50){
            for (int i = 0; i < dataNoteBook50.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook50[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }
        if (index[0] == 54){
            for (int i = 0; i < dataNoteBook54.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook54[i]+"', '"+ setFontSizeSave +"')");
            }
            return;
        }

        if (index[0] == 55){
            for (int i = 0; i < dataNoteBook55.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook55[i]+"', '"+ setFontSizeSave +"')");
            }return;
        }

        if (index[0] == 56){
            for (int i = 0; i < dataNoteBook56.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook56[i]+"', '"+ setFontSizeSave +"')");
            }return;
        }

        if (index[0] == 59){
            for (int i = 0; i < dataNoteBook59.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook59[i]+"', '"+ setFontSizeSave +"')");
            }return;
        }

        if (index[0] == 63){
            for (int i = 0; i < dataNoteBook63.length; i++){
                webView.loadUrl("javascript:setFontSizeNote('"+ dataNoteBook63[i]+"', '"+ setFontSizeSave +"')");
            }return;
        }

    }


    protected void getYChapterScroll(final int i, final int totalChapterBook){
        final boolean[] isGetText = {false};
        for (int a = 1; a < 28; a++){
            final int finalA = a;
                webView.evaluateJavascript("getYPos('"+ "c-"+(index[0]-1)+"-" + a +"')",
                    new ValueCallback<String>() {
                        @SuppressLint("JavascriptInterface")
                        @Override
                        public void onReceiveValue(String value) {
                            if (value != null && !value.isEmpty() && !value.equals("null") && value.length() > 0){
                                if (Float.parseFloat(value) < 0){
                                    isGetText[0] = true;
                                }else{
                                    if (isGetText[0]){
                                        isGetText[0] = false;
                                        tvChapterName.setText(getNameChapter(finalA - 1));
                                    }
                                    return;
                                }
                            }else{
                                if ((tvChapterName.getText().toString().isEmpty())){
                                    tvChapterName.setText(getNameChapter(1));
                                }
                                return;
                            }

                        }
                });
        }

    }



    protected void getIDHighlight(){
        if (arLocal1 == null){
            return;
        }
        for (int i =0; i < arLocal1.size(); i++){
            webView.loadUrl("javascript:addHighlight('"+arLocal1.get(i).split("\n")[0]+"')");
        }

    }

    private String getNameChapter(int chapter){
        String nameChap = "";

        int i = chapter - 1;
        //them
        if (index[0] == 37){
            return dataIdBook[0] + " " + String.valueOf(i+1);
        }
        if (index[0] == 38){
            return dataIdBook[1] + " " + String.valueOf(i+1);
        }
        if (index[0] == 39){
            return dataIdBook[2] + " " + String.valueOf(i+1);
        }
        if (index[0] == 40){
            return dataIdBook[3] + " " + String.valueOf(i+1);
        }
        if (index[0] == 41){
            return dataIdBook[4] + " " + String.valueOf(i+1);
        }
        if (index[0] == 42){
            return dataIdBook[5] + " " + String.valueOf(i+1);
        }
        if (index[0] == 43){
            return dataIdBook[6] + " " + String.valueOf(i+1);
        }
        if (index[0] == 44){
            return dataIdBook[7] + " " + String.valueOf(i+1);
        }
        if (index[0] == 45){
            return dataIdBook[8] + " " + String.valueOf(i+1);
        }
        if (index[0] == 46){
            return dataIdBook[9] + " " + String.valueOf(i+1);
        }
        if (index[0] == 47){
            return dataIdBook[10] + " " + String.valueOf(i+1);
        }
        if (index[0] == 48){
            return dataIdBook[11] + " " + String.valueOf(i+1);
        }
        if (index[0] == 49){
            return dataIdBook[12] + " " + String.valueOf(i+1);
        }
        if (index[0] == 50){
            return dataIdBook[13] + " " + String.valueOf(i+1);
        }
        if (index[0] == 51){
            return dataIdBook[14] + " " + String.valueOf(i+1);
        }
        if (index[0] == 52){
            return dataIdBook[15] + " " + String.valueOf(i+1);
        }
        if (index[0] == 53){
            return dataIdBook[16] + " " + String.valueOf(i+1);
        }
        if (index[0] == 54){
            return dataIdBook[17] + " " + String.valueOf(i+1);
        }
        if (index[0] == 55){
            return dataIdBook[18] + " " + String.valueOf(i+1);
        }
        if (index[0] == 56){
            return dataIdBook[19] + " " + String.valueOf(i+1);
        }
        if (index[0] == 57){
            return dataIdBook[20] + " " + String.valueOf(i+1);
        }
        if (index[0] == 58){
            return dataIdBook[21] + " " + String.valueOf(i+1);
        }
        if (index[0] == 59){
            return dataIdBook[22] + " " + String.valueOf(i+1);
        }
        if (index[0] == 60){
            return dataIdBook[23] + " " + String.valueOf(i+1);
        }
        if (index[0] == 61){
            return dataIdBook[24] + " " + String.valueOf(i+1);
        }
        if (index[0] == 62){
            return dataIdBook[25] + " " + String.valueOf(i+1);
        }
        if (index[0] == 63){
            return dataIdBook[26] + " " + String.valueOf(i+1);
        }
        return nameChap;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            getHiglig();
            String idScroll = idVerseChapter;
            int indexCheck = index[0];
            if (requestCode == 2){
                if (index[0] != Integer.parseInt(data.getStringExtra("MESSAGEID"))){
                    index[0] = Integer.parseInt(data.getStringExtra("MESSAGEID"));
                    btNext.setVisibility(View.VISIBLE);
                    btPrevious.setVisibility(View.VISIBLE);
                    if (index[0] == 1){
                        btPrevious.setVisibility(View.GONE);
                        btNext.setVisibility(View.VISIBLE);
                    }
                    if (index[0] == 63){
                        btNext.setVisibility(View.GONE);
                    }
                    saveLocal();
                }
            }
            SharedPreferences preShare = getSharedPreferences("FONTCHANGED", MODE_PRIVATE);
            if (!preShare.getString("FONT", "home1").equals(fontStype)){
                getSaveLocal();
                changeFont();
            }

            SharedPreferences preShareSize = getSharedPreferences("FONTSIZE", MODE_PRIVATE);
            if (preShareSize.getInt("SIZE", 2) != fontSize){
                getSaveLocal();
                setFontSize(true);
            }

            getSaveLocal();
            if (requestCode == 10){
                idVerseChapter = data.getStringExtra("MESSAGESEAARCH");
            }
            if (requestCode == 101 || requestCode == 3){
                if (data.getStringExtra("MESSAGEPS").split("-").length > 1){
                    index[0] = Integer.parseInt(data.getStringExtra("MESSAGEPS").split("-")[1]) + 1;
                    idVerseChapter = data.getStringExtra("MESSAGEPS");
                }
            }

            if (requestCode == 2){
                idVerseChapter = data.getStringExtra("MESSAGE");
            }

            if (idScroll != idVerseChapter) {
                String[] sep = idVerseChapter.split("-");
                if (sep.length > 2){
                    if (("c-"+String.valueOf(indexCheck-1)).equals(sep[0] + "-" + sep[1])){
                        webView.loadUrl("javascript:scrollTo('"+ idVerseChapter + "')");
                    }else{
                        changeFont();
                    }
                }else{
                    changeFont();
                }
            }

            saveLocal();
            changeBG();
            changeJustify();
        }
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        mode.getMenu().clear();
        mode.getMenu().close();
        super.onActionModeStarted(mode);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
    }

    private void setHeightWebview(){
        DisplayMetrics displayMetrics;
        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        webView.getLayoutParams().height = height;
    }

    private void initFont(){

        tv1 = (TextView) findViewById(R.id.tvExSmall);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tvSmall);
        tv2.setOnClickListener(this);

        tv3 = (TextView) findViewById(R.id.tvMedium);
        tv3.setOnClickListener(this);

        tv4 = (TextView) findViewById(R.id.tvLarge);
        tv4.setOnClickListener(this);

        tv5 = (TextView) findViewById(R.id.tvExLarge);
        tv5.setOnClickListener(this);

        if (fontSize == 0){
            setColorFontSize(tv1);
        }
        if (fontSize == 1){
            setColorFontSize(tv2);
        }
        if (fontSize == 2){
            setColorFontSize(tv3);
        }
        if (fontSize == 3){
            setColorFontSize(tv4);
        }
        if (fontSize == 4){
            setColorFontSize(tv5);
        }
    }

    public void refreshWebView() {
        progresBar.setVisibility(View.VISIBLE);
        changeFont();
        setFontSize(false);
    }

    private void setFontSize(final Boolean isShowProgresBar){

        if (fontSize == 0){
            setFontSizeSave = "15px";
        }
        if (fontSize == 1){
            setFontSizeSave = "19px";
        }
        if (fontSize == 2){
            setFontSizeSave = "23px";
        }
        if (fontSize == 3){
            setFontSizeSave = "27px";
        }
        if (fontSize == 4){
            setFontSizeSave = "32.5px";
        }
        if (isShowProgresBar){
            progresBar.setVisibility(View.VISIBLE);
        }
        webView.evaluateJavascript("javascript:document.body.style.setProperty(\"font-size\", " + setFontSizeSave.replace("px", "") + ");", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String s) {
                if (isShowProgresBar){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progresBar.setVisibility(View.INVISIBLE);
                        }
                    }, 4000);
                }
            }
        });
        setFontSizeNote();
    }

    @Override
    public void onClick(View view) {
        idVerseChapter = "";
        switch (view.getId()) {
            case R.id.btPrevious:
                if (index[0] <= 37 && index[0] >= 9){
                    Intent myIntentGloss = new Intent(MainActivity.this, GlossaryActivity.class);
                    startActivity(myIntentGloss);
                }else if (index[0] == 37){
                    Intent myIntentIntro = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(myIntentIntro);
                }
                else{
                    index[0] = index[0] - 1;
                    if (index[0] >= 1){
                        btPrevious.setVisibility(View.VISIBLE);
                        btNext.setVisibility(View.VISIBLE);
                        saveLocal();

                        refreshWebView();
                        if (index[0] == 37){
                            btPrevious.setVisibility(View.GONE);
                            btNext.setVisibility(View.VISIBLE);
                        }
                    }
                }
                break;
            case R.id.btNext:
                if (index[0] == 63){
                    Intent myIntentGloss = new Intent(MainActivity.this, GlossaryActivity.class);
                    startActivity(myIntentGloss);
                }else{
                    index[0] = index[0] + 1;
                    if (index[0] <= 63){
                        btPrevious.setVisibility(View.VISIBLE);
                        btNext.setVisibility(View.VISIBLE);
                        saveLocal();

                        refreshWebView();
                    }
                }
                break;
            case R.id.logo1:
                Intent myIntentC = new Intent(MainActivity.this, RecyclerViewFragment.class);
                startActivityForResult(myIntentC, 2);
                break;

            case R.id.imageButton2:
                Intent myIntentLG = new Intent(MainActivity.this, CrossIconActivity.class);
                startActivity(myIntentLG);
                break;

            case R.id.imgSettings:
                Intent myIntentSetting = new Intent(MainActivity.this, SettingsActivity.class);
                startActivityForResult(myIntentSetting, 1);
                break;

            case R.id.btLeft:
                Intent myIntent1 = new Intent(MainActivity.this, PersonalActivity.class);
                startActivityForResult(myIntent1, 101);
                break;

            case R.id.btnChangeFontSize:
                showOpacity();
                break;

            case R.id.btChapter:
                Intent myIntentChange = new Intent(MainActivity.this, RecyclerViewFragment.class);
                startActivityForResult(myIntentChange, 2);
                break;

            case R.id.tvExLarge:
                setColorFontSize(tv5);
                setFontSizeOpacity(4);
                break;
            case R.id.tvLarge:
                setColorFontSize(tv4);
                setFontSizeOpacity(3);
                break;
            case R.id.tvMedium:
                setColorFontSize(tv3);
                setFontSizeOpacity(2);
                break;
            case R.id.tvSmall:
                setColorFontSize(tv2);
                setFontSizeOpacity(1);
                break;
            case R.id.tvExSmall:
                setColorFontSize(tv1);
                setFontSizeOpacity(0);
                break;

            case R.id.id_highligh:
                feature = 0;
                getTextWV();
                if (arLocal1 == null){
                    if (idText != "body"){
                        if (idText != ""){
                            isHiglig = true;
                        }
                    }
                }else{
                    boolean isOK = true;
                    for (int i = 0;  i < arLocal1.size(); i++){
                        if (arLocal1.get(i).contains(idText.replace("\"", ""))){
                            isHiglig = false;
                            isH = true;
                            isOK = false;
                        }else{
                            if (isOK){
                                isH = false;
                                isHiglig = true;
                            }
                        }
                    }
                    if (arLocal1.size() == 0){
                        isH = true;
                        isHiglig = true;
                    }
                }
                if (isHiglig){
                    webView.loadUrl("javascript:addHighlight('"+idText.replace("\"","")+"')");
                    imgHighligh.setImageResource(R.drawable.highlight_action);
                    if (idText != null){
                        isH = true;
                        arLocal1.add(idText.replace("\"", "")+ "\n"+ getTV);
                        saveHiglig();
                    }
                }else
                {
                    webView.loadUrl("javascript:removeHighlight('"+idText.replace("\"","")+"')");
                    imgHighligh.setImageResource(R.drawable.highligh);
                    for (int i = 0;  i < arLocal1.size(); i++) {
                        if (arLocal1.get(i).contains(idText.replace("\"",""))){
                            arLocal1.remove(i);
                            saveHiglig();
                        }
                    }
                    webView.reload();
                }


                break;

            case R.id.id_favourites:
                feature = 1;
                getTextWV();
                imgFavourite.setImageResource(R.drawable.favorites_action);

                break;
            case R.id.id_share:
                feature = 2;
                getTextWV();
                imgShare.setImageResource(R.drawable.share_action);

                break;
            case R.id.id_note:
                feature = 3;
                getTextWV();
                imgNote.setImageResource(R.drawable.note_action);
                rllyMenu.setVisibility(View.INVISIBLE);
                break;
            case R.id.id_copy:
                feature = 4;
                getTextWV();
                imgCopy.setImageResource(R.drawable.copy_action);
                break;

            case R.id.id_opacityview:
                View viewKey = this.getCurrentFocus();
                if (viewKey != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                rllyMenu.setVisibility(View.INVISIBLE);
                break;

            case R.id.donenote:

                if (getTV != null ){

                    arSaveAllNote.add(changeTextID(idText.replace("\"","")).replace(":", " ").replace("-", ":") + "\n"+ noteText.getText());
                    Gson gson = new Gson();
                    String json = gson.toJson(arSaveAllNote);
                    SharedPreferences preShare = getSharedPreferences("SAVEALLNOTE", MODE_PRIVATE);
                    SharedPreferences.Editor edit = preShare.edit();
                    edit.putString("NOTE", json);
                    edit.commit();
                }

                View viewKey2 = this.getCurrentFocus();
                if (viewKey2 != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                rlViewNote.setVisibility(View.INVISIBLE);
                break;

            case R.id.cancelNote:
                View viewKey1 = this.getCurrentFocus();
                if (viewKey1 != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                rlViewNote.setVisibility(View.INVISIBLE);
                break;

            case R.id.search:
                Intent myIntentSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivityForResult(myIntentSearch, 10);
                break;
            default:
                break;
        }
    }


    private void setFontSizeOpacity(int fz){
        hideOpacity();
        fontSize = fz;
        setFontSize(true);
        setFontSizeNote();
        saveLocal();
    }

    @SuppressLint("ResourceAsColor")
    protected void setColorFontSize(TextView tvSetBg){
        tv1.setBackgroundColor(0x00000000);
        tv2.setBackgroundColor(0x00000000);
        tv3.setBackgroundColor(0x00000000);
        tv4.setBackgroundColor(0x00000000);
        tv5.setBackgroundColor(0x00000000);

        tvSetBg.setBackgroundColor(R.color.colorMain);
    }


    private void getTextWV(){
        webView.evaluateJavascript("javascript:getabc(" + idText + ")",
                new ValueCallback<String>()
                {
                    @SuppressLint("JavascriptInterface")
                    @Override
                    public void onReceiveValue(String value)
                    {

                        getTV = value.replaceAll("\"", "").replaceAll("[0-9]", "")
                                .replaceAll("n", "").replaceFirst("..","").replaceAll("/*", "").replaceAll(" ", "  ");
                        if (feature == 1){
                            if (idText != null){
                                arrFavourite.add(changeIDFourite() + "\n" + getTV);
                            }
                            saveFavourite();
                        }

                        if (feature == 2){

                            String shareBody = getTV + " ༼"+changeIDFourite()+"༽";
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                            startActivity(Intent.createChooser(sharingIntent, ""));

                        }

                        if (feature == 3){

                            if (!idText.equals("null")){
                                rlViewNote.setVisibility(View.VISIBLE);
                                tvTitleNote.setText(changeTextID(idText.replace("\"", "")).replace(":", " ").replace("-", ":"));
                                noteText.requestFocus();
                            }

                        }

                        if (feature == 4){

                            ClipboardManager clipBoard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                            ClipData item = ClipData.newPlainText("text", getTV + " ༼"+changeIDFourite()+"༽");
                            clipBoard.setPrimaryClip(item);
                            imgCopy.setImageResource(R.drawable.copy_action);

                        }


                    }
                });
    }

    private String changeIDFourite(){
        String[] separated = idText.replace("\"", "").split("-");
        String showText = "";
        if (separated.length > 2){
            String book = separated[1];
            for (int i = 0; i < dataIdBook.length; i++){
                try {
                    if ((Integer.parseInt(book) - 36) == i){
                        showText = dataIdBook[i] + " ";
                        break;
                    }
                } catch(NumberFormatException nfe) {

                }
            }
            return showText + separated[2] +":"+separated[3];
        }
        return "";
    }

    private String changeTextID(String chap){

        ArrayList<String> arrayVerse = new ArrayList<String>();

        for ( int i = 0; i < 27; i++ ){
            arrayVerse.add("verse-" + i + "-");
        }
        for (int i = 0; i < arrayVerse.size(); i++) {
            if (chap.contains(arrayVerse.get(i))){
                return chap.replace(arrayVerse.get(i), dataIdBook[i] + ":");
            }
        }

        return "མད་ཐཱའི:";
    }


    private void showOpacity(){
        lnFontSize.setVisibility(View.VISIBLE);
        viewAlpha.setVisibility(View.VISIBLE);
    }

    private void hideOpacity(){
        lnFontSize.setVisibility(View.INVISIBLE);
        viewAlpha.setVisibility(View.INVISIBLE);
    }


    private void changeFont(){
        if (fontStype.equals("home1")){
            webView.loadUrl("file:///android_asset/ntb" + String.valueOf(index[0]) + ".html");
            return;
        }
        if (fontStype.equals("home2")){
            webView.loadUrl("file:///android_asset/ntb" + String.valueOf(index[0]) + "_home2.html");
            return;
        }
        if (fontStype.equals("home3")){
            webView.loadUrl("file:///android_asset/ntb" + String.valueOf(index[0]) + "_home3.html");
            return;
        }


    }

    private void changeJustify(){
        if (isJustify){
            webView.loadUrl("javascript:(function() {document.getElementsByTagName(\"body\")[0].style.textAlign = \"justify\";})()");
        }else{

            webView.loadUrl("javascript:(function() {document.getElementsByTagName(\"body\")[0].style.textAlign = \"none\";})()");
        }

    }

    private void changeBG(){
        if (backgroundWebview == 1){
            webView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"black\");");
        }
        if (backgroundWebview == 2){
            webView.setBackgroundColor(Color.parseColor("#F9FAE6"));
            webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"black\");");
        }
        if (backgroundWebview == 3){
            webView.setBackgroundColor(Color.parseColor("#000000"));
            webView.loadUrl("javascript:document.body.style.setProperty(\"color\", \"white\");");
        }
    }

    private void saveHiglig(){
        if (arLocal1 != null) {
            Gson gson = new Gson();
            String json = gson.toJson(arLocal1);
            SharedPreferences preShare = getSharedPreferences("SAVEHIGLIG", MODE_PRIVATE);
            SharedPreferences.Editor edit = preShare.edit();
            edit.putString("HIGLIG", json);
            edit.commit();
        }
    }

    private void saveFavourite(){
        Gson gson = new Gson();
        String json = gson.toJson(arrFavourite);
        SharedPreferences preShare = getSharedPreferences("SAVEFAVOURITE", MODE_PRIVATE);
        SharedPreferences.Editor edit = preShare.edit();
        edit.putString("FAVOURITE", json);
        edit.commit();
    }

    private void getFavourite(){
        SharedPreferences preShare = getSharedPreferences("SAVEFAVOURITE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preShare.getString("FAVOURITE", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if (json != null){
            arrFavourite = gson.fromJson(json, type);
        }

    }

    private void getHiglig(){
        SharedPreferences preShare = getSharedPreferences("SAVEHIGLIG", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preShare.getString("HIGLIG", null);
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        if (json != null){
            arLocal1 = gson.fromJson(json, type);
        }

    }

    private void findSelectFav(String idVer){
        String[] arIdVer = null;
        String idV = "";
        if (!idVer.isEmpty() && !idVer.equals("null")){
            arIdVer = idVer.split("-");
            if (arIdVer.length > 2){
                idV = changeTextID(arIdVer[0] + "-" + arIdVer[1] + "-").replace(":","") +" "+ arIdVer[2] + ":" +arIdVer[3];
            }
        }
        if (arrFavourite.size() != 0){
            for (int i = 0; i < arrFavourite.size(); i++){
                if (arrFavourite.get(i).split("\n\n")[0].replace("\"", "").equals(idV)){
                    imgFavourite.setImageResource(R.drawable.favorites_action);
                }
            }
        }

    }

    private void findSelectHi(String idVer){

        String[] arIdVer = null;
        String idV = "";
        if (!idVer.isEmpty()&& !idVer.equals("null")){
            arIdVer = idVer.split("-");
            if (arIdVer.length > 3){
                idV = changeTextID(arIdVer[0] + "-" + arIdVer[1] + "-").replace(":","") +" "+ arIdVer[2] + ":" +arIdVer[3];
            }
        }
        if (arLocal1.size() != 0){
            for (int i = 0; i < arLocal1.size(); i++){
                if (arLocal1.get(i).split("\n")[0].replace("\"", "").equals(idVer.replace("\"", ""))){
                    imgHighligh.setImageResource(R.drawable.highlight_action);
                    isH = true;
                }
            }
        }
        if (!isH){
            if (idVer.replace("\"", "").replace("body", "").length() == 0){
                return;
            }
        }
    }


    private void findSelectNote(String idVer){
        String[] arIdVer = null;
        String idV = "";
        if (!idVer.isEmpty()&& !idVer.equals("null")){
            arIdVer = idVer.split("-");
            if (arIdVer.length > 3){
                idV = changeTextID(arIdVer[0] + "-" + arIdVer[1] + "-").replace(":","") +" "+ arIdVer[2] + ":" +arIdVer[3];
            }
        }
        if (arSaveAllNote.size() != 0){
            for (int i = 0; i < arSaveAllNote.size(); i++){
                if (arSaveAllNote.get(i).split("\n")[0].replace("\"", "").replace(":"," ").equals(idV.replace(":","-"))){
                    imgNote.setImageResource(R.drawable.note_action);
                }
            }
        }
    }


}

