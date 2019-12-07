package com.example.linj.myapplication.view.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.linj.myapplication.R;

/**
 * @author JLin
 * @date 2019/1/11
 * @describe 自定义dialog基类
 */
public abstract class BaseDialog extends DialogFragment {
    public static final int LAYOUT_PARAM_MATCH_PARENT = WindowManager.LayoutParams.MATCH_PARENT;
    public static final int LAYOUT_PARAM_WRAP_CONTENT = WindowManager.LayoutParams.WRAP_CONTENT;

    /**
     * 是否显示在底部
     */
    private boolean isBottom;

    /**
     * 宽度
     */
    private int mWidth = LAYOUT_PARAM_MATCH_PARENT;

    /**
     * 高度
     */
    private int mHeight = LAYOUT_PARAM_WRAP_CONTENT;

    /**
     * 是否可以在外部取消
     */
    private boolean canceledOnTouchOutside = true;

    /**
     * 绑定视图监听
     */
    private onBindViewListener onBindViewListener;

    /**
     * 设置 layoutId
     *
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * 操作dialog布局
     *
     * @param holder holder
     * @param dialog dialog
     */
    public abstract void convertView(ViewHolder holder, BaseDialog dialog);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        convertView(ViewHolder.create(view), this);
        if (onBindViewListener != null) {
            onBindViewListener.onBindView(ViewHolder.create(view), this);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    /**
     * 初始化参数
     */
    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

            getDialog().setCanceledOnTouchOutside(canceledOnTouchOutside);

            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams lp = window.getAttributes();
            if (isBottom) {
                lp.gravity = Gravity.BOTTOM;
                window.setWindowAnimations(setAnimStyle());
            }
            lp.width = mWidth;
            lp.height = mHeight;
            lp.alpha = 1.0f;
            window.setAttributes(lp);
        }
    }

    public BaseDialog isBottom(boolean isBottom) {
        this.isBottom = isBottom;
        return this;
    }

    public BaseDialog setWidthAndHeight(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    public BaseDialog bindViewListener(onBindViewListener listener) {
        this.onBindViewListener = listener;
        return this;
    }

    public int setAnimStyle() {
        return R.style.FromBottomDialogAnim;
    }

    public void show(FragmentManager manager) {
        super.show(manager, String.valueOf(System.currentTimeMillis()));
    }

    public void dialogDismiss() {
        super.dismiss();
    }
    public BaseDialog setCanceledOnTouchOutside(boolean b) {
        this.canceledOnTouchOutside = b;
        return this;
    }

    public static class ViewHolder {
        private SparseArray<View> views;
        private View convertView;

        private ViewHolder(View view) {
            convertView = view;
            views = new SparseArray<>();
        }

        static ViewHolder create(View view) {
            return new ViewHolder(view);
        }

        /**
         * 获取View
         *
         * @param viewId
         * @param <T>
         * @return
         */
        public <T extends View> T getView(@IdRes int viewId) {
            View view = views.get(viewId);
            if (view == null) {
                view = convertView.findViewById(viewId);
                views.put(viewId, view);
            }
            return (T) view;
        }

        /**
         * 设置文本
         *
         * @param viewId
         * @param text
         */
        public void setText(int viewId, String text) {
            TextView textView = getView(viewId);
            textView.setText(text);
        }

        /**
         * 设置字体颜色
         *
         * @param viewId
         * @param colorId
         */
        public void setTextColor(int viewId, int colorId) {
            TextView textView = getView(viewId);
            textView.setTextColor(colorId);
        }

        /**
         * 设置背景图片
         *
         * @param viewId
         * @param resId
         */
        public void setBackgroundResource(int viewId, int resId) {
            View view = getView(viewId);
            view.setBackgroundResource(resId);
        }

        /**
         * 设置背景颜色
         *
         * @param viewId
         * @param colorId
         */
        public void setBackgroundColor(int viewId, int colorId) {
            View view = getView(viewId);
            view.setBackgroundColor(colorId);
        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param listener
         */
        public void setOnClickListener(int viewId, View.OnClickListener listener) {
            View view = getView(viewId);
            view.setOnClickListener(listener);
        }
    }

    public interface onBindViewListener {
        void onBindView(ViewHolder viewHolder, BaseDialog dialog);
    }
}
