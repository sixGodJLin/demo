package com.example.linj.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe 输入编辑框输入文本限制等
 */
public class EditTextDemoActivity extends AppCompatActivity {
    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.tv_button)
    TextView tvButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_button)
    public void onViewClicked() {
        System.out.println("EditTextDemoActivity:" + "onViewClicked" + "====" + Double.valueOf(editText.getText().toString()));
    }
}
