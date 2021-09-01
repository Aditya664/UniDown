package com.dds.unidown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.SpinKitView;

public class homeActivity extends AppCompatActivity {
    private SpinKitView pgbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pgbar = findViewById(R.id.spin_kit);
        pgbar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(homeActivity.this, MainActivity.class); startActivity(i);
                finish(); } }, 3000);
    }
}

