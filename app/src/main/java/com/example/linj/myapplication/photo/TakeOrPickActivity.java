package com.example.linj.myapplication.photo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.linj.myapplication.R;
import com.example.linj.myapplication.photo.photoUtils.AlbumActivity;
import com.example.linj.myapplication.photo.photoUtils.PhotoAdapter;
import com.example.linj.myapplication.photo.photoUtils.PhotoConfig;
import com.example.linj.myapplication.photo.photoUtils.PhotoItem;
import com.example.linj.myapplication.recycler.BaseAdapter;
import com.example.linj.myapplication.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class TakeOrPickActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_or_pick);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        photoAdapter = new PhotoAdapter(getApplicationContext(), null);
        recyclerView.setAdapter(photoAdapter);

        List<PhotoItem> photoItemList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            PhotoItem photoItem = new PhotoItem();
            photoItem.setImagePath("");
            photoItem.setThumbPath("");
            photoItem.setSelect(false);
            photoItemList.add(photoItem);
        }
        photoAdapter.addMoreItem(photoItemList);

        photoAdapter.setOnItemClickListener((view, position, data) -> {
            if (position == PhotoConfig.pic_max - 1) {
                ToastUtils.showBottom("请选择");
                startActivity(new Intent(TakeOrPickActivity.this, AlbumActivity.class));
            }
        });
    }
}