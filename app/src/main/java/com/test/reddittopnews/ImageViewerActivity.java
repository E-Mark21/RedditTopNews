package com.test.reddittopnews;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class ImageViewerActivity extends AppCompatActivity {
    public static final String EXTRA_URL = "url_pictures";
    ImageView url_pictures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        url_pictures = (ImageView) findViewById(R.id.url_image);
        int urlID = (Integer) getIntent().getExtras().get(EXTRA_URL);
        String url = CardNewsAdapter.getUrlNews(urlID);
        Picasso.with(this).
                load(url)
                .placeholder(R.drawable.placeholder)
                .into(url_pictures);
    }
}
