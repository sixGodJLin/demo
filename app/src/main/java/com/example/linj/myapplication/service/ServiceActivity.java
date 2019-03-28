package com.example.linj.myapplication.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.linj.myapplication.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 */
public class ServiceActivity extends Activity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        ButterKnife.bind(this);

        intent = new Intent(this, ForegroundService.class);
    }

//    /**
//     * service 1
//     * @param view
//     */
//    @OnClick({R.id.btn_start, R.id.btn_stop})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_start:
//                startService(intent);
//                break;
//            case R.id.btn_stop:
//                stopService(intent);
//                break;
//            default:
//                break;
//        }
//    }

//    /**
//     * service 2
//     *
//     * @param view
//     */
//    @OnClick({R.id.btn_start, R.id.btn_stop})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.btn_start:
//                bindService(intent, connection, BIND_AUTO_CREATE);
//                break;
//            case R.id.btn_stop:
//                unbindService(connection);
//                break;
//            default:
//                break;
//        }
//    }
//
//    ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            MyService2.MyBinder myService = (MyService2.MyBinder) service;
//            myService.show();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            System.out.println("ServiceActivity " + "onServiceDisconnected " + "----");
//        }
//    };

    /**
     * foreground service
     *
     * @param view
     */
    @OnClick({R.id.btn_start, R.id.btn_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                startService(intent);
                break;
            case R.id.btn_stop:
                stopService(intent);
                break;
            default:
                break;
        }
    }
}
