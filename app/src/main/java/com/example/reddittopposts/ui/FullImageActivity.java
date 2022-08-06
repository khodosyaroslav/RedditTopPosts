package com.example.reddittopposts.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.reddittopposts.R;

import java.io.File;

public class FullImageActivity extends AppCompatActivity {
    ImageView imageView;
    String urlImage;
    String fileName = "Top_post_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        imageView = findViewById(R.id.full_image);
        Intent i = getIntent();
        urlImage = i.getStringExtra("urlImage");

        Glide.with(this).load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    public void downloadImage(View view){
        try{
            DownloadManager downloadManager = null;
            downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

            Uri downloadUri = Uri.parse(urlImage);

            DownloadManager.Request request = new DownloadManager.Request(downloadUri);

            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                            DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false)
                    .setTitle(fileName)
                    .setMimeType("image/jpeg")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, File.separator+fileName+".jpg");

            downloadManager.enqueue(request);
            Toast.makeText(this, "Image download complete", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, "Image download fail", Toast.LENGTH_SHORT).show();
        }
    }
}