package com.example.linj.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.linj.myapplication.view.dialog.BaseDialog;
import com.example.linj.myapplication.view.dialog.MyDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author JLin
 */
public class DialogDemoActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        MyDialog.newInstance()
                .setWidthAndHeight(BaseDialog.LAYOUT_PARAM_MATCH_PARENT, BaseDialog.LAYOUT_PARAM_WRAP_CONTENT)
                .setCanceledOnTouchOutside(false)
                .isBottom(true)
                .show(getSupportFragmentManager());
    }
}
