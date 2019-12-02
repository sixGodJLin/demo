package com.example.linj.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 调用相机功能
 */
public class CameraActivity extends AppCompatActivity {
    private static int TAKE_PHOTO = 0X10;
    @BindView(R.id.image_view)
    ImageView imageView;
    private String path;
    private String picPath;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.take_photo)
    public void onViewClicked() {
        path = Environment.getExternalStorageDirectory().getPath();
        picPath = path + File.separator + getPhotoFileName() + ".jpg";
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Android7.0文件保存方式改变了
        if (Build.VERSION.SDK_INT < 24) {
            picUri = Uri.fromFile(new File(picPath));
            intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);//将原图的uri传入
            startActivityForResult(intent, TAKE_PHOTO);
        } else {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, picPath);
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, TAKE_PHOTO);
        }
    }

    /**
     * 生成照片的名字
     *
     * @return name
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "IMG_" + format.format(date);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                // 生成原图
                Bitmap bitmap = BitmapFactory.decodeFile(picPath);
                // 图片压缩
//                Bitmap compressBitmap = ThumbnailUtils.extractThumbnail(bitmap, 720, 1080);
//                CommonUtils.saveMyBitmap(getApplicationContext(), compressBitmap, CommonUtils.Time());
                Glide.with(getApplicationContext()).load(bitmap).into(imageView);
            }
        }
    }
}
