package com.example.linj.myapplication.view.dialog;


import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2019/1/11
 * @describe 自定义加载dialog
 */
public class LoadingDialog extends BaseDialog {

    public static LoadingDialog newInstance() {
        return new LoadingDialog();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_loading;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialog dialog) {
        System.out.println("LoadingDialog:" + "convertView" + "====");
    }

    public void change() {
        System.out.println("LoadingDialog:" + "change" + "====");
    }
}
