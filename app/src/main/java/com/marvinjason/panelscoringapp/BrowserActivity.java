package com.marvinjason.panelscoringapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BrowserActivity extends Activity {

    private SharedPreferences sharedPreferences;
    private WebView mWebview;
    private boolean doubleBackToExitPressedOnce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        mWebview = (WebView) findViewById(R.id.webview);
        doubleBackToExitPressedOnce = false;

        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setJavaScriptEnabled(true);

        if (sharedPreferences.getBoolean("advanced", false)) {
            String url = sharedPreferences.getString("url", "http://www.google.com/");
            mWebview.loadUrl(url);
        } else {
            String server = sharedPreferences.getString("server", "216.58.220.238");
            String port = sharedPreferences.getString("port", "80");
            mWebview.loadUrl("http://" + server + ":" + port);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(BrowserActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
