package com.dds.unidown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.dds.unidown.databinding.ActivityInstagramBinding;
import com.dds.unidown.databinding.ActivityWhatsappBinding;

public class instagramActivity extends AppCompatActivity {
    private ActivityInstagramBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_instagram);

        binding.back.setOnClickListener(v ->{
            startActivity(new Intent(this,MainActivity.class));

        });
    }
}