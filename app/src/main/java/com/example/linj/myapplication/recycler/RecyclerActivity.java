package com.example.linj.myapplication.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.linj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author JLin
 * @date 2019/3/8
 */
public class RecyclerActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        List<String> strings = new ArrayList<>();
        strings.add("徐泽小哥哥");
        strings.add("去死吧");
        strings.add("不要嘛！！");
        strings.add("去死吧");
        strings.add("亲亲亲呢！");
        strings.add("嗨呀！");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter(this, strings);
        recyclerView.setAdapter(adapter);
    }
}
