package com.example.linj.myapplication;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linj.myapplication.view.MyView2;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JLin
 */
public class MusicActivity extends AppCompatActivity {
    //    MediaPlayer mediaPlayer;
    int dx;
    private MyView2 myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        myView = findViewById(R.id.my_view2);

//        mediaPlayer = new MediaPlayer();
//
//        try {
//            mediaPlayer.setDataSource(this, Uri.parse("http://sc1.111ttt.cn/2016/1/12/09/205091416301.mp3"));
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                System.out.println("MusicActivity " + "onPrepared " + "----");
//                mp.start();
//            }
//        });

        Random random = new Random();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            dx = random.nextInt(100);
            myView.setDx(dx, dx);
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mediaPlayer.stop();
//        mediaPlayer.release();
    }
}
