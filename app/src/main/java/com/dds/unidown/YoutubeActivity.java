package com.dds.unidown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dds.unidown.databinding.ActivityYoutubeBinding;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

public class YoutubeActivity extends AppCompatActivity {
private ActivityYoutubeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_youtube);



        binding.back.setOnClickListener(v ->{
            startActivity(new Intent(this,MainActivity.class));

        });



        binding.downbtn.setOnClickListener(v -> ytvideos());

    }

    private void ytvideos() {
        if(TextUtils.isEmpty(binding.yurl.getText().toString())){
            Toast.makeText(this, "Please provide URL", Toast.LENGTH_SHORT).show();
        }else{
            new YouTubeExtractor(this) {
                @Override
                public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                    if (ytFiles != null) {
                        int itag = 22;
                       String downloadUrl = ytFiles.get(itag).getUrl();
                        Util.download(downloadUrl, Util.RootDirectoryYoutube,YoutubeActivity.this,
                                "Youtube"+System.currentTimeMillis()+".mp4");
                    }
                }
            }.extract(binding.yurl.getText().toString(), true, true);
        }
    }
}