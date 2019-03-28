package com.example.linj.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.linj.myapplication.utils.StringAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 */
public class AnimateActivity extends Activity {
    private int mode;

    @BindView(R.id.rv_list)
    RecyclerView rvList;

    StringAdapter stringAdapter;
    @BindView(R.id.change)
    TextView change;
    @BindView(R.id.text)
    TextView text;
    List<SelectBean> beans = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);
        ButterKnife.bind(this);

        beans.add(new SelectBean("1", false));
        beans.add(new SelectBean("2", false));
        beans.add(new SelectBean("3", false));
        beans.add(new SelectBean("4", false));
        beans.add(new SelectBean("5", false));
        beans.add(new SelectBean("6", false));

        mode = 0;
        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        setData();
    }

    @OnClick({R.id.change, R.id.text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.change:
                mode = 1;
                setData();
                break;
            case R.id.text:
                System.out.println("AnimateActivity " + "onViewClicked " + "----" + new Gson().toJson(stringAdapter.getSelect()));
                break;
            default:
                break;
        }
    }

    public class SelectBean {
        private String content;
        private boolean isSelect;

        public SelectBean(String content, boolean isSelect) {
            this.content = content;
            this.isSelect = isSelect;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }
    }

    @Override
    public void onBackPressed() {
        if (mode == 1) {
            mode = 0;
            setData();
        } else {
            super.onBackPressed();
        }
    }

    private void setData() {
        stringAdapter = new StringAdapter(getApplicationContext(), beans, mode);
        rvList.setAdapter(stringAdapter);
    }
}
