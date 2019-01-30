package com.example.linj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.linj.myapplication.view.MyView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author JLin
 */
public class DrawActivity extends AppCompatActivity {
    private MyView myView1;

    ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        myView1 = findViewById(R.id.my_view1);

        myView1.setPaintColor("#fabc6f");

        myView1.start();

        Random rnd = new Random();

        // event 传输的假数据
        double[][] doubles = new double[2][100];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 100; j++) {
                doubles[i][j] = rnd.nextInt(200) - 100;
            }
        }

        double[] doubles1 = new double[100];
        for (int i = 0; i < 100; i++) {
            doubles1[i] = doubles[0][i];
        }

        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {
            myView1.setData(doubles1);
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scheduledThreadPoolExecutor != null) {
            scheduledThreadPoolExecutor.shutdown();
        }
        myView1.onDestroy();
    }
}
