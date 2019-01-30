package com.example.linj.myapplication.view.dialog;


import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2019/1/11
 */
public class MyDialog extends BaseDialog {

    public static MyDialog newInstance() {
        return new MyDialog();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_my_dialog;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialog dialog) {
        holder.setOnClickListener(R.id.tv_morning, v -> {
            System.out.println("MyDialog " + "convertView " + "----");
        });
    }
}
