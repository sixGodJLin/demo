package com.example.linj.myapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 */
public class MusicActivity extends Activity {

    @BindView(R.id.text)
    TextView text;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println("MusicActivity " + "onConfigurationChanged " + "----");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
        System.out.println("MusicActivity " + "onCreate " + "----");
    }

    @OnClick(R.id.text)
    public void onViewClicked() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    protected void onStart() {
        System.out.println("MusicActivity " + "onStart " + "----");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        System.out.println("MusicActivity " + "onRestart " + "----");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("MusicActivity " + "onResume " + "----");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("MusicActivity " + "onPause " + "----");
        super.onPause();
    }

    @Override
    protected void onStop() {
        System.out.println("MusicActivity " + "onStop " + "----");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("MusicActivity " + "onDestroy " + "----");
        super.onDestroy();
    }
}
