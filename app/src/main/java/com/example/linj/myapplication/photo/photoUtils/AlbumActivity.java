package com.example.linj.myapplication.photo.photoUtils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.linj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JLin
 * @date 2021/06/04
 * @describe 相册界面
 */
public class AlbumActivity extends AppCompatActivity {
    private static final String TAG = AlbumActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    public static final int LOADER_ALL = 1;// 获取所有图片
    private List<PhotoItem> mDatas = new ArrayList<>();
    private AlbumAdapter albumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        albumAdapter = new AlbumAdapter(getApplicationContext(), null);
        recyclerView.setAdapter(albumAdapter);
//        initAllPics();
        initAllAlbum();
    }

    /**
     * 拉取所有图片信息
     */
    private void initAllPics() {
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        loaderManager.initLoader(LOADER_ALL, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            private final String[] IMAGE_PROJECTION = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATE_ADDED,
                    MediaStore.Images.Media._ID,
            };

            @NonNull
            @Override
            public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
                if (i == LOADER_ALL) {
                    return new CursorLoader(AlbumActivity.this,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            IMAGE_PROJECTION, null, null, IMAGE_PROJECTION[2] + " DESC");
                }
                return null;
            }

            @Override
            public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
                if (cursor != null) {
                    mDatas.clear();
                    while (cursor.moveToNext()) {
                        PhotoItem item = new PhotoItem();
                        String path = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[0]));
                        String name = cursor.getString(cursor.getColumnIndex(IMAGE_PROJECTION[1]));
                        item.setName(name);
                        item.setImagePath(path);
                        mDatas.add(item);
                    }
                    albumAdapter.replaceAll(mDatas);
                }
            }

            @Override
            public void onLoaderReset(@NonNull Loader<Cursor> loader) {

            }
        });
    }

    /**
     * 拉取所有相册信息
     */
    private void initAllAlbum() {
        ContentResolver cr = getContentResolver();

        // 构造相册索引
        String[] columns = new String[]{
                MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.SIZE
        };
        // 得到一个游标
        @SuppressLint("Recycle")
        Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);
        assert cur != null;
        if (cur.moveToFirst()) {
            // 获取指定列的索引
            int bucketIdIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_ID);
            int bucketNameIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int photoIdIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int photoNameIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);
            int photoPathIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            int photoSizeIndex = cur.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE);

            do {
                String bucketId = cur.getString(bucketIdIndex);
                String bucketName = cur.getString(bucketNameIndex);
                String id = cur.getString(photoIdIndex);
                String name = cur.getString(photoNameIndex);
                String path = cur.getString(photoPathIndex);
                String size = cur.getString(photoSizeIndex);

                Log.i(TAG, "id:" + id
                        + ", bucketId: " + bucketId
                        + " name:" + name
                        + " path:" + path
                        + " size: " + size
                        + " bucket: " + bucketName);
            } while (cur.moveToNext());
        }
    }
}