package com.dds.unidown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.dds.unidown.databinding.ActivityMainBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.whatsapp.setOnClickListener(v ->{
            startActivity(new Intent(this,WhatsappActivity.class));

        });
        binding.youtube.setOnClickListener(v ->{
            startActivity(new Intent(this,YoutubeActivity.class));

        });
        binding.insta.setOnClickListener(v ->{
            startActivity(new Intent(this,instagramActivity.class));

        });
binding.facebook.setOnClickListener(v ->{
            startActivity(new Intent(this,Facebook_activity.class));

        });




        checkPermission();
    }
    private void checkPermission(){
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                if(!report.areAllPermissionsGranted());
                checkPermission();
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
        }).check();
    }
}