package com.example.linj.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.linj.myapplication.view.dialog.BaseDialog;
import com.example.linj.myapplication.view.dialog.MyDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 * @date 2019/12/2
 * @describe dialog实例，包含两种类型
 */
public class DialogDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                MyDialog.newInstance()
                        .setWidthAndHeight(BaseDialog.LAYOUT_PARAM_MATCH_PARENT, BaseDialog.LAYOUT_PARAM_WRAP_CONTENT)
                        .setCanceledOnTouchOutside(false)
                        .isBottom(true)
                        .bindViewListener((viewHolder, dialog) -> ((MyDialog) dialog).showMessage(viewHolder))
                        .show(getSupportFragmentManager());

                break;
            case R.id.button1:
                AlertDialog alert = new AlertDialog.Builder(this).create();
                alert.setIcon(R.drawable.img_seizuretype_overall);
                alert.setTitle("退出？");
                alert.setMessage("真的要退出泡泡龙游戏吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "不", (dialog, which) -> {

                });

                alert.setButton(DialogInterface.BUTTON_POSITIVE, "是的", (dialog, which) -> {
                    finish();
                });
                alert.show();
                break;
            default:
                break;
        }
    }
}
