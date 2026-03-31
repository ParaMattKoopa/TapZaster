package com.vukidev.tapzaster;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ScrollView;
import android.os.Handler;
import android.media.MediaPlayer;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.content.Intent;

public class about extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        Button backBtn = (Button) findViewById(R.id.btn_back1);
        backBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}