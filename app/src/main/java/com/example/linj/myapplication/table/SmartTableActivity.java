package com.example.linj.myapplication.table;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.example.linj.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JLin
 * @date 2019/11/6
 * @describe 表格界面
 */
public class SmartTableActivity extends AppCompatActivity {
    SmartTable<TableBean> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_table);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = 0;
        if (wm != null) {
            width = wm.getDefaultDisplay().getWidth();
        }

        table = findViewById(R.id.table);
        table.getConfig().setShowXSequence(false);
        table.getConfig().setShowYSequence(false);
        table.getConfig().setShowTableTitle(false);
        table.getConfig().setMinTableWidth(width);
//        table.getConfig().setColumnTitleBackgroundColor(Color.parseColor("#ff0000"));
//        table.getConfig().setContentBackgroundColor(Color.parseColor("#ff0000"));
        FontStyle contentStyle = new FontStyle();
        contentStyle.setTextColor(Color.WHITE);
        contentStyle.setTextSpSize(this, 16);
        table.getConfig().setColumnTitleStyle(contentStyle);

        List<TableBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TableBean tableBean = new TableBean();
            tableBean.setName("1");
            tableBean.setType("2");
            tableBean.setWeight("3");
            tableBean.setPrice("4");
            list.add(tableBean);
        }

        table.setData(list);
    }
}
