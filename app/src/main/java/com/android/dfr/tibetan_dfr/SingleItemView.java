package com.android.dfr.tibetan_dfr;

import android.app.Activity;

/**
 * Created by MinhThanh on 11/26/17.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItemView extends Activity {
    // Declare Variables
    TextView txtBook;
    String textBook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        textBook = i.getStringExtra("textBook");
        // Get the results of country

        // Locate the TextViews in singleitemview.xml
        txtBook = (TextView) findViewById(R.id.textBook);

        // Load the results into the TextViews
        txtBook.setText(textBook);

    }
}