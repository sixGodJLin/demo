package com.example.linj.myapplication.recycler;

import android.app.Activity;
import android.os.Bundle;
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
public class RecyclerActivity extends Activity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ButterKnife.bind(this);

        List<String> string = new ArrayList<>();
        string.add("1");
        string.add("1");
        string.add("1");
        string.add("2");
        string.add("2");
        string.add("3");
        string.add("4");
        string.add("4");

        String value = "0";

        List<String> list = new ArrayList<>();
        for (String s : string) {
            if (!value.equals(s)) {
                list.add("0");
            }
            list.add(s);
            value = s;
        }

        System.out.println("RecyclerActivity:" + "onCreate" + "----" + list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}
