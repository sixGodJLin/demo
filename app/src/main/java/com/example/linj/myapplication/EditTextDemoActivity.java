package com.example.linj.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 输入编辑框输入文本限制等
 */
public class EditTextDemoActivity extends AppCompatActivity {
    @BindView(R.id.edit_text)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_demo);
        ButterKnife.bind(this);
    }
}
