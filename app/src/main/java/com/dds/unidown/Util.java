package com.dds.unidown;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.databinding.DataBinderMapper;

import java.io.File;

public class Util {
    public static String RootDirectoryYoutube = "/Unidown/Youtube/";
    public static String RootDirectoryFacebook = "/Unidown/FaceBook/";



    public static File RootDirectoryhatsapp=
            new File(Environment.getExternalStorageDirectory()
            +"/Download/Unidown/Whatsapp");

    public static void createfilefolder(){
        if (!RootDirectoryhatsapp.exists())
            RootDirectoryhatsapp.mkdir();
    }


    public static void download(String downloadPath, String destinationpath, Context context , String filename) {
        Toast.makeText(context, "Downloading started", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(filename);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,destinationpath+filename);
        ((DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
    }
}
