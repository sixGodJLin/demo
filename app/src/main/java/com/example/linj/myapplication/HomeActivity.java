package com.example.linj.myapplication;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.linj.myapplication.alarm.AlarmActivity;
import com.example.linj.myapplication.baidu.MapActivity;
import com.example.linj.myapplication.bean.GetRandomPictureUrlRequest;
import com.example.linj.myapplication.bean.RandomPicResponse;
import com.example.linj.myapplication.database.DataBaseActivity;
import com.example.linj.myapplication.mail.Mail;
import com.example.linj.myapplication.mail.MailSendUtils;
import com.example.linj.myapplication.recycler.Recycler2Activity;
import com.example.linj.myapplication.retrofit.RetrofitActivity;
import com.example.linj.myapplication.service.ServiceActivity;
import com.example.linj.myapplication.table.SmartTableActivity;
import com.example.linj.myapplication.tcp.TcpDemoActivity;
import com.example.linj.myapplication.view.dialog.LoadingDialog;
import com.google.gson.Gson;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Emitter;
import me.shenfan.updateapp.UpdateService;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * @author JLin
 * @date 2019/12/17
 * @describe master home page
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    static String path = Environment.getExternalStorageDirectory().getPath();
    int mYear;
    int mMonth;
    int mDay;

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

//        Calendar c = Calendar.getInstance();
//        mYear = c.get(Calendar.YEAR);
//        mMonth = c.get(Calendar.MONTH);
//        mDay = c.get(Calendar.DAY_OF_MONTH);
//
//        loadingDialog = new LoadingDialog();
//        loadingDialog.setWidthAndHeight(BaseDialog.LAYOUT_PARAM_MATCH_PARENT, BaseDialog.LAYOUT_PARAM_WRAP_CONTENT);
//        loadingDialog.setCanceledOnTouchOutside(false);

        String fileName = Environment.getExternalStorageDirectory() + "/capture.png";

//        RetrofitApp.inject("http://www.cunkou.co/cunKouApp/cunKouService/");
//        GetRandomPictureUrlRequest request = new GetRandomPictureUrlRequest();
//
//        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//            System.out.println("-------------------------------------------------------");
//            request.setExchangeTime(TimeUtils.format(System.currentTimeMillis(), TimeUtils.Formatter.YYYY_MM_DD_HH_MM_SS_24HOUR));
//            RetrofitApp.with().load(Api.class).getRandomPictureUrl(request).enqueue(new Callback<RandomPicResponse>() {
//                @Override
//                public void onResponse(Call<RandomPicResponse> call, Response<RandomPicResponse> response) {
//                    System.out.println("000000000000000000000000000000000000000000000000000000");
//                    if (response.body() == null) {
//                        return;
//                    }
//                    FileUtils.saveImage(response.body().getImgUrl(), fileName, getApplicationContext());
//                }
//
//                @Override
//                public void onFailure(Call<RandomPicResponse> call, Throwable t) {
//                    System.out.println("111111111111111111111111111111111111111111111");
//                }
//            });
//        }, 0, 7000, TimeUnit.MILLISECONDS);
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.calendar_view, R.id.service_demo, R.id.alarm_demo, R.id.swipe_demo, R.id.schedule_demo,
            R.id.animate_demo, R.id.draw_demo, R.id.calendar_event, R.id.file_select,
            R.id.video_demo, R.id.view_pager_demo, R.id.expand_view, R.id.send_email,
            R.id.dialog_demo, R.id.guide_demo, R.id.tcp_demo, R.id.recycler_demo,
            R.id.camera_demo, R.id.restart, R.id.smart_table, R.id.baidu_map_demo,
            R.id.retrofit_demo, R.id.edit_text_demo, R.id.zxing_demo, R.id.video_record_demo,
            R.id.download_app, R.id.gps, R.id.data_base})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.calendar_view:
                startActivity(new Intent(this, MainActivity.class), ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
                break;
            case R.id.service_demo:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.alarm_demo:
                startActivity(new Intent(this, AlarmActivity.class));
                break;
            case R.id.swipe_demo:
                startActivity(new Intent(this, SwipeActivity.class));
                break;
            case R.id.schedule_demo:
                startActivity(new Intent(this, ScheduleActivity.class));
                break;
            case R.id.draw_demo:
                startActivity(new Intent(this, DrawActivity.class));
                break;
            case R.id.animate_demo:
                startActivity(new Intent(this, AnimateActivity.class));
                break;
            case R.id.calendar_event:
                startActivity(new Intent(this, CalendarActivity.class));
                break;
            case R.id.file_select:
                startActivity(new Intent(this, PDFViewActivity.class));
                break;
            case R.id.video_demo:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.id.video_record_demo:
                startActivity(new Intent(this, VideoRecordActivity.class));
                break;
            case R.id.view_pager_demo:
                startActivity(new Intent(this, ViewPagerActivity.class));
                break;
            case R.id.expand_view:
                startActivity(new Intent(this, ExpandActivity.class));
                break;
            case R.id.dialog_demo:
                startActivity(new Intent(this, DialogDemoActivity.class));
                break;
            case R.id.guide_demo:
                startActivity(new Intent(this, GuideActivity.class));
                break;
            case R.id.tcp_demo:
                startActivity(new Intent(this, TcpDemoActivity.class));
                break;
            case R.id.recycler_demo:
                startActivity(new Intent(this, Recycler2Activity.class));
                break;
            case R.id.camera_demo:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.restart:
                final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.smart_table:
                startActivity(new Intent(this, SmartTableActivity.class));
                break;
            case R.id.baidu_map_demo:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.retrofit_demo:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;
            case R.id.edit_text_demo:
                startActivity(new Intent(this, EditTextDemoActivity.class));
                break;
            case R.id.zxing_demo:
                startActivityForResult(new Intent(this, CaptureActivity.class), 0x00);
                /*
                 * contentEtString：字符串内容
                 * w：图片的宽
                 * h：图片的高
                 * logo：不需要logo的话直接传null
                 */
//                Bitmap logo = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//                Bitmap bitmap = CodeCreator.createQRCode("123456789", 400, 400, logo);
                break;
            case R.id.send_email:
                loadingDialog.show(getSupportFragmentManager());

                String[] toAddr = {"775729609@qq.com"};
                new Thread(() -> {
                    Mail mail = new Mail();
                    mail.setMailServerHost("smtp.qq.com");
                    mail.setMailServerPort("25");
                    mail.setValidate(true);
                    mail.setUserName("775729609"); // 你的邮箱地址前一半
                    mail.setPassword("hbmvsgyfpapubcge");// 您的邮箱密码
                    mail.setFromAddress("775729609@qq.com"); // 发送的邮箱
                    mail.setToAddress(toAddr); // 发到哪个邮件去
                    mail.setSubject("测试结果"); // 邮件主题
                    mail.setContent("嗨 all,"); // 邮件文本
                    MailSendUtils mailSender = new MailSendUtils();
                    mailSender.sendTextMail(mail, new MailSendUtils.sendCallback() {
                        @Override
                        public void success() {
                            runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                            });
                        }

                        @Override
                        public void fail(String message) {
                            runOnUiThread(() -> {
                                Toast.makeText(getApplicationContext(), "发送失败", Toast.LENGTH_SHORT).show();
                                loadingDialog.dismiss();
                            });
                        }
                    });// 记得放子线程
                }).start();
                break;
            case R.id.download_app:
                Log.d(TAG, "onViewClicked: ");
                download();
                break;
            case R.id.gps:
                startActivity(new Intent(this, GPSActivity.class));
                break;
            case R.id.data_base:
                startActivity(new Intent(this, DataBaseActivity.class));
                break;
            default:
                break;
        }
    }

    private void readFile() throws IOException {
        InputStream is = new FileInputStream(new File(Environment.getExternalStorageDirectory() + "/search.html"));
        byte buffer[] = new byte[1024];
        is.read(buffer, 0, 1024);
    }

    public interface Api {
        /**
         * 上传接口，兼容分片上传 （单/多张）
         *
         * @param params params
         *               param userId   用户id
         *               param source   文件来源：2 - 脑电云，3 - 培训
         *               param chunk    （可空）第几片，从 0 开始，非分片上传时不传
         *               param chunks   （可空）总片数，非分片上传时不传
         *               param fileName （可空）文件名，非必传，当前端无法控制分片上传的文件名时，通过此参数传递正确的文件名
         *               param files    文件
         *               return BaseResponse
         */
        @Multipart
        @POST("neuroCloud/unify/upload")
        Call<String> upload(@PartMap Map<String, RequestBody> params);

        @POST("getRandomPictureUrl")
        Call<RandomPicResponse> getRandomPictureUrl(@Body GetRandomPictureUrlRequest request);
    }

    class ProgressRequestBody extends RequestBody {
        private RequestBody requestBody;
        private Emitter<UploadInfo> emitter;
        private UploadInfo uploadInfo;
        private BufferedSink bufferedSink;

        public ProgressRequestBody(RequestBody requestBody, Emitter<UploadInfo> emitter, UploadInfo uploadInfo) {
            this.requestBody = requestBody;
            this.emitter = emitter;
            this.uploadInfo = uploadInfo;
        }

        @Nullable
        @Override
        public MediaType contentType() {
            return requestBody.contentType();
        }

        @Override
        public long contentLength() throws IOException {
            return requestBody.contentLength();
        }

        @Override
        public void writeTo(@NonNull BufferedSink sink) throws IOException {
            if (bufferedSink == null) {
                bufferedSink = Okio.buffer(wrapSink(sink));
            }
            requestBody.writeTo(bufferedSink);
            bufferedSink.flush();
        }

        private Sink wrapSink(Sink sink) {
            return new ForwardingSink(sink) {
                @Override
                public void write(@NonNull Buffer source, long byteCount) throws IOException {
                    super.write(source, byteCount);
                    if (uploadInfo.total == 0) {
                        uploadInfo.total = contentLength();
                    }
                    uploadInfo.current += byteCount;
                    emitter.onNext(uploadInfo);
                }
            };
        }

        public class UploadInfo {
            private long total;
            private long current;

            public long getTotal() {
                return total;
            }

            public void setTotal(long total) {
                this.total = total;
            }

            public long getCurrent() {
                return current;
            }

            public void setCurrent(long current) {
                this.current = current;
            }
        }
    }

    /**
     * 获取图文上传BodyMap
     * <p>
     * 支持null 不添加到参数列表
     * 支持Integer
     * 支持String
     * 支持File
     * 支持File[]
     * 支持List<File>
     *
     * @param resourceMap resourceMap
     * @return RequestBody
     */
    public static Map<String, RequestBody> buildRequestBodyMap(Map<String, Object> resourceMap) {
        Map<String, RequestBody> params = new HashMap<>(10);
        for (Object o : resourceMap.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            if (entry.getValue() == null) {
                continue;
            }
            if (entry.getValue() instanceof Integer) {
                RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), entry.getValue() + "");
                params.put((String) entry.getKey(), body);
            } else if (entry.getValue() instanceof String) {
                RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) entry.getValue());
                params.put((String) entry.getKey(), body);
            } else if (entry.getValue() instanceof File) {
                RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) entry.getValue());
                params.put(entry.getKey() + "\";filename=\"" + ((File) entry.getValue()).getName() + "", body);
            } else if (entry.getValue() instanceof File[]) {
                File[] files = (File[]) entry.getValue();
                if (files != null && files.length > 0) {
                    for (File f : files) {
                        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), f);
                        params.put(entry.getKey() + "\";filename=\"" + f.getName() + "", body);
                    }
                }
            } else if (entry.getValue() instanceof List) {
                List<File> files = (List<File>) entry.getValue();
                if (files != null && files.size() > 0) {
                    for (File f : files) {
                        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), f);
                        params.put(entry.getKey() + "\";filename=\"" + f.getName() + "", body);
                    }
                }
            }
        }
        return params;
    }

    String mSavePath;

    /*
     * 开启新线程下载apk文件
     */
    private void downloadAPK() {
        new Thread(() -> {
            try {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    String sdPath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdPath + "apk";

                    File dir = new File(mSavePath);
                    if (!dir.exists()) {
                        dir.mkdir();
                    }
                    // 下载文件
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://jlin-demo.oss-cn-hangzhou.aliyuncs.com/apk/caihe.apk").openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    int length = conn.getContentLength();

                    File apkFile = new File(mSavePath, "1234.apk");
                    FileOutputStream fos = new FileOutputStream(apkFile);

                    int count = 0;
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int numread = is.read(buffer);
                        count += numread;
                        // 计算进度条的当前位置
                        int mProgress = (int) (((float) count / length) * 100);
                        Log.d(TAG, "run: " + mProgress);
                        // 下载完成
                        if (numread < 0) {
                            break;
                        }
                        fos.write(buffer, 0, numread);
                    }
                    fos.close();
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private long time = 0L;

    private void download() {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //需添加的代码
        if (manager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "default";
                String channelName = "默认通知";
                manager.createNotificationChannel(new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH));
            }
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");
            builder.setProgress(100, 0, false)
                    .setContentTitle("This is content title")
                    .setContentText("this is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.icon_start)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon_stop));

            manager.notify(1, builder.build());

            OkHttpUtils.get()
                    .tag(this)
                    .url("https://jlin-demo.oss-cn-hangzhou.aliyuncs.com/apk/testDemo.apk")
                    .build()
                    .execute(new FileCallBack(Environment.getExternalStorageDirectory() + "/CunKou", "ljxj.apk") {
                        @Override
                        public void inProgress(float progress, long total, int id) {
                            super.inProgress(progress, total, id);
                            Log.d(TAG, "下载进度 ----> " + 100 * progress);
                            if (System.currentTimeMillis() - time >= 1000) {
                                builder.setProgress(100, (int) (100 * progress), false);
                                manager.notify(1, builder.build());
                                //下载进度提示
                                builder.setContentText("下载" + (int) (100 * progress) + "%");
                            }

                        }

                        @Override
                        public void onError(okhttp3.Call call, Exception e, int id) {
                            Log.d(TAG, "---- onError: ");
                            manager.cancel(1);//设置关闭通知栏
                        }

                        @Override
                        public void onResponse(File response, int id) {
                            Log.d(TAG, "---- onResponse: ");
                            installApk();
                        }
                    });
        }
    }

    /**
     * 安装APK文件
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void installApk() {
        if (isHasInstallPermissionWithO()) {
            install();
        } else {
            startInstallPermissionSettingActivity();
        }
    }

    private void install() {
        File apkFile = new File(Environment.getExternalStorageDirectory() + "/CunKou", "ljxj.apk");
        if (!apkFile.exists()) {
            Log.d(TAG, "file is not exit: ");
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".fileProvider", apkFile);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO() {
        return getPackageManager().canRequestPackageInstalls();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity() {
        Intent intent = new Intent();
        //获取当前apk包URI，并设置到intent中（这一步设置，可让“未知应用权限设置界面”只显示当前应用的设置项）
        Uri packageURI = Uri.parse("package:" + getPackageName());
        intent.setData(packageURI);
        //设置不同版本跳转未知应用的动作
        if (Build.VERSION.SDK_INT >= 26) {
            //intent = new Intent(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
            intent.setAction(android.provider.Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
        } else {
            intent.setAction(android.provider.Settings.ACTION_SECURITY_SETTINGS);
        }
        startActivityForResult(intent, 0x01);
        Toast.makeText(getApplicationContext(), "请打开未知应用安装权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 345) {//345选择文件的请求码
            if (data != null) {
                if (resultCode == -1) {
                    if (data.getData() == null) {
                        return;
                    }
                    final Uri uri = data.getData();
                    String baseUrl = "http://192.168.97.108:80/neuroCloud/neuroCloud/unify/upload/";
                    File file = new File(path + "/reminds.xml");

                    System.out.println("HomeActivity " + "onActivityResult " + "----" + path + "/reminds.xml");

                    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create()).build();


                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    Map<String, RequestBody> params = new HashMap<>();

                    params.put("file\"; filename=\"" + file.getName() + "", requestBody);

                    Call<String> call = retrofit.create(Api.class).upload(params);

                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            System.out.println("HomeActivity " + "onResponse " + "----" + "vivi--" + response.message() + "    " + response.body());
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                            System.out.println("HomeActivity " + "onFailure " + "----" + t.getMessage());
                        }
                    });
                }
            }
        } else if (requestCode == 0x00) {
            if (resultCode == RESULT_OK) { // 垃圾巡检——机器的吗
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Log.d(TAG, "onActivityResult: " + content);
            }
        } else if (requestCode == 0x01) {
            Log.d(TAG, "onActivityResult: ");
            installApk();
        }
    }

}
