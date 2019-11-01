package com.example.linj.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_splash)
    AppCompatImageView ivSplash;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        RxPermissions permissions = new RxPermissions(this);
        permissions.setLogging(true);
        permissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_CALENDAR)
                .subscribe(aBoolean -> ViewCompat.animate(ivSplash).alpha(1f).setDuration(1600).setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                        System.out.println("SplashActivity:" + "onAnimationStart" + "----");
                    }

                    @Override
                    public void onAnimationEnd(View view) {
                        System.out.println("SplashActivity:" + "onAnimationEnd" + "----");
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onAnimationCancel(View view) {
                    }
                }).start());

    }
}