package com.dds.unidown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.dds.unidown.databinding.ActivityFacebookBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Facebook_activity extends AppCompatActivity {
    private ActivityFacebookBinding binding;
    private Facebook_activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_facebook);
        activity = this;


        binding.downbtn.setOnClickListener(v -> {
            getfbdata();
        });

        binding.back.setOnClickListener(v ->{
            startActivity(new Intent(this,MainActivity.class));

        });
    }

    private void getfbdata() {
        URL url = null;
        try {
            url = new URL(binding.fburl.getText().toString());
            String host = url.getHost();
            if(host.contains("facebook.com")) {
                new CallGetFbdata().execute(binding.fburl.getText().toString());
            }else if(host.contains("fb.watch")) {
                new CallGetFbdata().execute(binding.fburl.getText().toString());
            }else if(host.contains("m.facebook.com")){
                new CallGetFbdata().execute(binding.fburl.getText().toString());
            }else Toast.makeText(activity, "Url is invalide", Toast.LENGTH_SHORT).show();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    class CallGetFbdata extends AsyncTask<String, Void, Document>{

        Document fbdoc;

        @Override
        protected Document doInBackground(String... strings) {
            try {
                fbdoc = Jsoup.connect(strings[0]).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fbdoc;
        }

        @Override
        protected void onPostExecute(Document document) {
            String videourl = document.select("meta[property=\"og:video\"]")
                    .last().attr("content");
            if (!videourl.equals(""))
                Util.download(videourl, Util.RootDirectoryFacebook, activity,"facebook "+System.currentTimeMillis()+".mp4");
        }
    }
}
