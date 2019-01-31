package com.example.linj.myapplication.tcp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linj.myapplication.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author JLin
 */
public class TcpDemoActivity extends AppCompatActivity {
    private String TAG = "FuncTcpClient";
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    private Button btnStartClient, btnCloseClient, btnCleanClientSend, btnCleanClientRcv, btnClientSend, btnClientRandom;
    private TextView txtRcv, txtSend;
    private EditText editClientSend, editClientID, editClientPort, editClientIp;
    private static TcpClient tcpClient = null;
    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    private final MyHandler myHandler = new MyHandler(this);
    private MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    ExecutorService exec = Executors.newCachedThreadPool();

    private class MyBtnClicker implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_tcpClientConn:
                    Log.i(TAG, "onClick: 开始");
                    btnStartClient.setEnabled(false);
                    btnCloseClient.setEnabled(true);
                    btnClientSend.setEnabled(true);
                    tcpClient = new TcpClient("192.168.97.116", 8080);
                    exec.execute(tcpClient);
                    break;
                case R.id.btn_tcpClientClose:
                    tcpClient.closeSelf();
                    btnStartClient.setEnabled(true);
                    btnCloseClient.setEnabled(false);
                    btnClientSend.setEnabled(false);
                    break;
                case R.id.btn_tcpCleanClientRecv:
                    txtRcv.setText("");
                    break;
                case R.id.btn_tcpCleanClientSend:
                    txtSend.setText("");
                    break;
                case R.id.btn_tcpClientRandomID:
                    break;
                case R.id.btn_tcpClientSend:
                    Message message = Message.obtain();
                    message.what = 2;
                    message.obj = editClientSend.getText().toString();
                    myHandler.sendMessage(message);
                    exec.execute(() -> tcpClient.send(editClientSend.getText().toString()));
                    break;
                default:
                    break;
            }
        }
    }

    int i = 0;

    private class MyHandler extends android.os.Handler {
        private WeakReference<TcpDemoActivity> mActivity;

        MyHandler(TcpDemoActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity != null) {
                switch (msg.what) {
                    case 1:
                        i++;
                        txtRcv.append(i + ": " + msg.obj.toString() + "\n");
                        if (i % 10 == 0) {
                            txtRcv.setText("");
                        }
                        break;
                    case 2:
                        txtSend.append(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String mAction = intent.getAction();
            switch (mAction) {
                case "tcpClientReceiver":
                    String msg = intent.getStringExtra("tcpClientReceiver");
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = msg;
                    myHandler.sendMessage(message);
                    break;
                default:
                    break;
            }
        }
    }


    private int getPort(String msg) {
        if (msg.equals("")) {
            msg = "1234";
        }
        return Integer.parseInt(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp_demo);
        context = this;
        bindID();
        bindListener();
        bindReceiver();
        Ini();
    }


    private void bindID() {
        btnStartClient = (Button) findViewById(R.id.btn_tcpClientConn);
        btnCloseClient = (Button) findViewById(R.id.btn_tcpClientClose);
        btnCleanClientRcv = (Button) findViewById(R.id.btn_tcpCleanClientRecv);
        btnCleanClientSend = (Button) findViewById(R.id.btn_tcpCleanClientSend);
        btnClientRandom = (Button) findViewById(R.id.btn_tcpClientRandomID);
        btnClientSend = (Button) findViewById(R.id.btn_tcpClientSend);
        editClientPort = (EditText) findViewById(R.id.edit_tcpClientPort);
        editClientIp = (EditText) findViewById(R.id.edit_tcpClientIp);
        editClientSend = (EditText) findViewById(R.id.edit_tcpClientSend);
        txtRcv = (TextView) findViewById(R.id.txt_ClientRcv);
        txtSend = (TextView) findViewById(R.id.txt_ClientSend);
    }

    private void bindListener() {
        btnStartClient.setOnClickListener(myBtnClicker);
        btnCloseClient.setOnClickListener(myBtnClicker);
        btnCleanClientRcv.setOnClickListener(myBtnClicker);
        btnCleanClientSend.setOnClickListener(myBtnClicker);
        btnClientRandom.setOnClickListener(myBtnClicker);
        btnClientSend.setOnClickListener(myBtnClicker);
    }

    private void bindReceiver() {
        IntentFilter intentFilter = new IntentFilter("tcpClientReceiver");
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    private void Ini() {
        btnCloseClient.setEnabled(false);
        btnClientSend.setEnabled(false);

    }
}
