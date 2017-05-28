package com.marvinjason.panelscoringapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private RadioButton mBasic;
    private RadioButton mAdvanced;
    private EditText mServer;
    private EditText mPort;
    private EditText mUrl;
    private Button mSave;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mBasic = (RadioButton) findViewById(R.id.rb_basic);
        mAdvanced = (RadioButton) findViewById(R.id.rb_advanced);
        mServer = (EditText) findViewById(R.id.et_server);
        mPort = (EditText) findViewById(R.id.et_port);
        mUrl = (EditText) findViewById(R.id.et_url);
        mSave = (Button) findViewById(R.id.btn_save);
        sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);

        if (sharedPreferences.getBoolean("advanced", false)) {
            mBasic.setChecked(false);
            mAdvanced.setChecked(true);
            mServer.setEnabled(false);
            mPort.setEnabled(false);
            mUrl.setEnabled(true);
        } else {
            mBasic.setChecked(true);
            mAdvanced.setChecked(false);
            mServer.setEnabled(true);
            mPort.setEnabled(true);
            mUrl.setEnabled(false);
        }

        mServer.setText(sharedPreferences.getString("server", ""));
        mPort.setText(sharedPreferences.getString("port", ""));
        mUrl.setText(sharedPreferences.getString("url", ""));

        mBasic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdvanced.setChecked(false);
                mServer.setEnabled(true);
                mPort.setEnabled(true);
                mUrl.setEnabled(false);
            }
        });

        mAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBasic.setChecked(false);
                mServer.setEnabled(false);
                mPort.setEnabled(false);
                mUrl.setEnabled(true);
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBasic.isChecked()) {
                    sharedPreferences.edit().putBoolean("advanced", false).commit();
                    sharedPreferences.edit().putString("server", mServer.getText().toString()).commit();
                    sharedPreferences.edit().putString("port", mPort.getText().toString()).commit();
                } else {
                    sharedPreferences.edit().putBoolean("advanced", true).commit();
                    sharedPreferences.edit().putString("url", mUrl.getText().toString()).commit();
                }

                Toast.makeText(SettingsActivity.this, "Settings saved!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
