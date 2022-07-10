package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class WebActivity extends AppCompatActivity {

    private WebView myWebView;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        myWebView = (WebView) findViewById(R.id.wellfarWV);
        myWebView.loadUrl("https://clarenes.wixsite.com/welfarecoin");
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
    }

}