package com.example.linj.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author JLinX
 */
public class VideoActivity extends Activity {
    private final static String TAG = "MainActivity";
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;

    private ImageView ivPlay;
    private PopupWindow popupWindow;
    private ImageView ivStart;
    private ImageView ivFullScreen;
    private SeekBar seekBar;
    private TextView tvCurrentTime;
    private TextView tvDuration;
    private String filePath = null;

    private Runnable r = this::showOrHiddenController;

    private MyVideoBroadcastReceiver receiver = null;

    // 设置定时器
    private Timer timer = null;
    private final static int WHAT = 0;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case WHAT:
                    if (mediaPlayer != null) {
                        int currentPlayer = mediaPlayer.getCurrentPosition();
                        if (currentPlayer > 0) {
                            mediaPlayer.getCurrentPosition();
                            tvCurrentTime.setText(formatTime(currentPlayer));

                            // 让seekBar也跟随改变
                            int progress = (int) ((currentPlayer / (float) mediaPlayer.getDuration()) * 100);
                            seekBar.setProgress(progress);
                        } else {
                            tvCurrentTime.setText("00:00");
                            seekBar.setProgress(0);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private static final int HIDDEN_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        initView();

        initMediaPlayer();

        initController();

        // 动态注册广播接受者
        receiver = new MyVideoBroadcastReceiver();
        registerReceiver(receiver, new IntentFilter("com.amy.day43_03_SurfaceViewMediaPlayer"));
    }

    private String formatTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }

    private void initController() {
        View controllerView = getLayoutInflater().inflate(R.layout.layout_video_controller, null);

        // 初始化popopWindow
        popupWindow = new PopupWindow(controllerView, LinearLayout.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.px96), true);

        ivStart = controllerView.findViewById(R.id.imageView_play);
        ivFullScreen = controllerView.findViewById(R.id.imageView_fullscreen);
        seekBar = controllerView.findViewById(R.id.seekbar);
        tvCurrentTime = controllerView.findViewById(R.id.textView_playtime);
        tvDuration = controllerView.findViewById(R.id.textView_totaltime);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // 表示手指拖动seekbar完毕，手指离开屏幕会触发以下方法
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 让计时器延时执行
                handler.postDelayed(r, HIDDEN_TIME);
            }

            // 在手指正在拖动seekBar，而手指未离开屏幕触发的方法
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 让计时器取消计时
                handler.removeCallbacks(r);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int playtime = progress * mediaPlayer.getDuration() / 100;
                    mediaPlayer.seekTo(playtime);
                }

            }
        });

        // 点击播放的时候,判断是播放还是暂停
        ivStart.setOnClickListener(v -> {
            if (ivPlay.getVisibility() == View.VISIBLE) {
                ivPlay.setVisibility(View.GONE);
            }

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                ivStart.setImageResource(R.drawable.icon_start);
            } else {
                mediaPlayer.start();
                ivStart.setImageResource(R.drawable.icon_stop);
            }
        });

        // 实现全屏和退出全屏(内容物横竖屏,不是屏幕的横竖屏)
        ivFullScreen.setOnClickListener(v -> {
            System.out.println("VideoActivity " + "initController " + "----111111");
            if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                System.out.println("VideoActivity " + "initController " + "----2222222");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                ivFullScreen.setImageResource(R.drawable.icon_full_screen);

                // 重新设置surfaceView的高度和宽度
                surfaceView.getLayoutParams().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
                surfaceView.getLayoutParams().height = (int) getResources().getDimension(R.dimen.px500);
            } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                System.out.println("VideoActivity " + "initController " + "----333333");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                ivFullScreen.setImageResource(R.drawable.icon_inner_screen);

                surfaceView.getLayoutParams().width = ConstraintLayout.LayoutParams.MATCH_PARENT;
                surfaceView.getLayoutParams().height = ConstraintLayout.LayoutParams.MATCH_PARENT;
            }

            surfaceView.setLayoutParams(surfaceView.getLayoutParams());
        });
    }

    private void showOrHiddenController() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            // 将dp转换为px
            int controllerHeightPixel = (int) getResources().getDimension(R.dimen.px96);
            popupWindow.showAsDropDown(surfaceView, 0, -controllerHeightPixel);
            // 延时执行
            handler.postDelayed(r, HIDDEN_TIME);
        }
    }

    private void initMediaPlayer() {
        filePath = "https://t.alipayobjects.com/images/T1T78eXapfXXXXXXXX.mp4";
        if (mediaPlayer == null) {
            // 1,创建MediaPlay对象
            mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource(filePath);
                mediaPlayer.prepare();
                // mediaPlayer.start();
                mediaPlayer.setLooping(false);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        mediaPlayer.setOnPreparedListener(mp -> {
            // 表示准备完成，设置总的时长，使用时间格式化工具

            // String duration = mediaPlayer.getDuration() ;
            tvDuration.setText(formatTime(mediaPlayer.getDuration()));
            // 初始化定时器
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.sendEmptyMessage(WHAT);
                }
            }, 0, 1000);
        });

        mediaPlayer.setOnErrorListener((mp, what, extra) -> {
            mp.reset();

            return false;
        });

        mediaPlayer.setOnCompletionListener(mp -> {
            // 发送广播，播放下一首歌曲
            Intent intent = new Intent();
            intent.setAction("com.amy.day43_03_SurfaceViewMediaPlayer");
            sendBroadcast(intent);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        ivPlay = findViewById(R.id.imageView_main_play);
        surfaceView = findViewById(R.id.surfaceView_main);
        surfaceHolder = surfaceView.getHolder();

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // TODO Auto-generated method stub
                if (mediaPlayer != null) {
                    mediaPlayer.setDisplay(surfaceHolder);
                    // mediaPlayer.start() ;
                }

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                // TODO Auto-generated method stub
            }
        });

        // 设置屏幕的触摸监听
        surfaceView.setOnTouchListener((v, event) -> {
            // 表示在点击的瞬间就显示控制条
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    showOrHiddenController();
                    break;
                default:
                    break;
            }
            return true;
        });

        ivPlay.setOnClickListener(v -> {
            ivPlay.setVisibility(View.GONE);
            mediaPlayer.start();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        popupWindow.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        timer.cancel();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    class MyVideoBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.amy.day43_03_SurfaceViewMediaPlayer".equals(intent.getAction())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("提示")
                        .setMessage("视屏播放完毕，是否播放")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定",
                                (dialog, which) -> {
                                    mediaPlayer.reset();
                                    try {
                                        mediaPlayer.setDataSource(filePath);
                                        mediaPlayer.prepare();
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                    mediaPlayer.setLooping(false);
                                    mediaPlayer.start();
                                }).show();

            }
        }

    }
}
