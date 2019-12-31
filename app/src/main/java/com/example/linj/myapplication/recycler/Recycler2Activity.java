package com.example.linj.myapplication.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.linj.myapplication.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Recycler2Activity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> strings;
    private Recycler2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler2);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("" + i);
        }

        adapter = new Recycler2Adapter(getApplicationContext(), strings);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.tv_clear)
    public void onViewClicked() {
        int count = strings.size();
        for (int i = 0; i < count; i++) {
            adapter.deleteItem(0);
        }
    }
}
