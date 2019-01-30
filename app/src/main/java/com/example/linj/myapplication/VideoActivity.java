package com.example.linj.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author JLin
 */
public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.video_view)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        videoView.setVideoURI(Uri.parse("https://t.alipayobjects.com/images/T1T78eXapfXXXXXXXX.mp4"));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }
}
