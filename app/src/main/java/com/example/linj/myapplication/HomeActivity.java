package com.example.linj.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.linj.myapplication.alarm.AlarmActivity;
import com.example.linj.myapplication.recycler.RecyclerActivity;
import com.example.linj.myapplication.service.ServiceActivity;
import com.example.linj.myapplication.tcp.TcpDemoActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Emitter;
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
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * @author JLin
 */
public class HomeActivity extends Activity {
    static String path = Environment.getExternalStorageDirectory().getPath();
    int mYear;
    int mMonth;
    int mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.calendar_view, R.id.service_demo, R.id.alarm_demo, R.id.swipe_demo, R.id.schedule_demo,
            R.id.animate_demo, R.id.draw_demo, R.id.calendar_event, R.id.file_select,
            R.id.video_demo, R.id.music_demo, R.id.view_pager_demo, R.id.expand_view,
            R.id.dialog_demo, R.id.guide_demo, R.id.tcp_demo, R.id.recycler_demo,
            R.id.camera_demo, R.id.restart})
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
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("*/*");
//                intent.addCategory(Intent.CATEGORY_OPENABLE);
//                try {
//                    startActivityForResult(Intent.createChooser(intent, "选择文件上传"), 345);
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(this, "请安装一个文件管理器.", Toast.LENGTH_SHORT).show();
//                }
//                try {
//                    readFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                startActivity(new Intent(this, PDFViewActivity.class));
                break;
            case R.id.video_demo:
                startActivity(new Intent(this, VideoActivity.class));
                break;
            case R.id.music_demo:
                startActivity(new Intent(this, MusicActivity.class));
                break;
            case R.id.view_pager_demo:
                startActivity(new Intent(this, ViewPagerActivity.class));
//                DatePickerDialog datePickerDialog = new DatePickerDialog(HomeActivity.this, (view1, year, month, dayOfMonth) -> {
//                    Calendar startcal = Calendar.getInstance();
//                    startcal.set(Calendar.YEAR, year);
//                    startcal.set(Calendar.MONTH, month);
//                    startcal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(startcal.getTimeInMillis()));
//                    System.out.println("HomeActivity " + "onViewClicked " + "----" + date);
//                }, mYear, mMonth, mDay);
//
//                datePickerDialog.show();
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
                startActivity(new Intent(this, RecyclerActivity.class));
                break;
            case R.id.camera_demo:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.restart:
                final Intent intent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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
        }
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

}
