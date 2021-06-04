package com.example.linj.myapplication.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author JLin
 * @date 2019/4/29
 * @describe 吐司类
 */
public class ToastUtils {

    private static Context appContext;
    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    @ColorInt
    private static final int ERROR_COLOR = Color.parseColor("#BFff1744");
    @ColorInt
    private static final int INFO_COLOR = Color.parseColor("#BF000000");
    @ColorInt
    private static final int SUCCESS_COLOR = Color.parseColor("#BF2E7D32");
    @ColorInt
    private static final int WARNING_COLOR = Color.parseColor("#BFE7A455");

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static Toast sToast;
    private static final int X_OFFSET = 0;
    private static final int Y_OFFSET = 80;
    private static int bgColor = COLOR_DEFAULT;
    private static boolean useColorful;

    /**
     * 初始化全局配置，一般用于applicationContext
     *
     * @param context     context
     * @param useColorful 是否使用多色Toast，默认不使用
     */
    public static void initAppContext(Context context, boolean useColorful) {
        ToastUtils.appContext = context.getApplicationContext();
        ToastUtils.useColorful = useColorful;
    }

    public static Context getContext(){
        return ToastUtils.appContext;
    }

    private ToastUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    // -----------------------------------------------------------------------------------for normal

    public static void showNormal(CharSequence msg) {
        setBgColor(COLOR_DEFAULT);
        show(msg);
    }

    public static void showNormal(@StringRes int strRes) {
        setBgColor(COLOR_DEFAULT);
        show(strRes);
    }

    public static void showNormalLong(CharSequence msg) {
        setBgColor(COLOR_DEFAULT);
        show(msg, Toast.LENGTH_LONG);
    }

    public static void showNormalLong(@StringRes int strRes) {
        setBgColor(COLOR_DEFAULT);
        show(strRes, Toast.LENGTH_LONG);
    }

    // ----------------------------------------------------------------------------------for gravity

    public static void showTop(@StringRes int strRes) {
        showTop(appContext.getString(strRes));
    }

    public static void showTop(CharSequence msg) {
        setBgColor(COLOR_DEFAULT);
        HANDLER.post(() -> {
            cancel();
            sToast = Toast.makeText(appContext, msg, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.TOP, X_OFFSET, Y_OFFSET);
            sToast.show();
        });
    }

    public static void showCenter(@StringRes int strRes) {
        showCenter(appContext.getString(strRes));
    }

    public static void showCenter(CharSequence msg) {
        setBgColor(COLOR_DEFAULT);
        HANDLER.post(() -> {
            cancel();
            sToast = Toast.makeText(appContext, msg, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.CENTER, 0, 0);
            sToast.show();
        });
    }

    public static void showBottom(@StringRes int strRes) {
        showBottom(appContext.getString(strRes));
    }

    public static void showBottom(CharSequence msg) {
        setBgColor(COLOR_DEFAULT);
        HANDLER.post(() -> {
            cancel();
            sToast = Toast.makeText(appContext, msg, Toast.LENGTH_SHORT);
            sToast.setGravity(Gravity.BOTTOM, X_OFFSET, Y_OFFSET);
            sToast.show();
        });
    }

    // -----------------------------------------------------------------------for colorful and short

    public static void normal(CharSequence text) {
        setBgColor(COLOR_DEFAULT);
        show(text);
    }

    public static void normal(@StringRes final int resId) {
        normal(appContext.getString(resId));
    }

    /**
     * 警告类信息：一般包括：请，至少，至多等语气类提示语
     *
     * @param text text
     */
    public static void warning(CharSequence text) {
        setBgColor(WARNING_COLOR);
        show(text);
    }

    /**
     * 警告类信息：一般包括：请，至少，至多，不能等语气类提示语
     *
     * @param resId resId
     */
    public static void warning(@StringRes final int resId) {
        warning(appContext.getString(resId));
    }

    /**
     * 成功类信息：一般包括：成功，OK等语气类提示语
     *
     * @param text text
     */
    public static void success(CharSequence text) {
        setBgColor(SUCCESS_COLOR);
        show(text);
    }

    /**
     * 成功类信息：一般包括：成功，OK等语气类提示语
     *
     * @param resId resId
     */
    public static void success(@StringRes final int resId) {
        success(appContext.getString(resId));
    }

    /**
     * 提示类信息：一般包括：温馨提示，完成，不可用，无效等语气类提示语
     *
     * @param text text
     */
    public static void info(CharSequence text) {
        setBgColor(INFO_COLOR);
        show(text);
    }

    /**
     * 提示类信息：一般包括：温馨提示，完成，不可用，无效等语气类提示语
     *
     * @param resId resId
     */
    public static void info(@StringRes final int resId) {
        info(appContext.getString(resId));
    }

    /**
     * 错误类信息：一般包括：失败，error，错误，禁止等语气类提示语
     *
     * @param text text
     */
    public static void error(CharSequence text) {
        setBgColor(ERROR_COLOR);
        show(text);
    }

    /**
     * 错误类信息：一般包括：失败，error，错误，禁止等语气类提示语
     *
     * @param resId resId
     */
    public static void error(@StringRes final int resId) {
        error(appContext.getString(resId));
    }

    // --------------------------------------------------------------------------------------private

    private static void cancel() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    private static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    private static void show(@StringRes final int strRes) {
        show(appContext.getString(strRes), Toast.LENGTH_SHORT);
    }

    private static void show(@StringRes final int strRes, final int duration) {
        show(appContext.getString(strRes), duration);
    }

    /**
     * 显示
     *
     * @param text     text
     * @param duration duration
     */
    private static void show(final CharSequence text, final int duration) {
        HANDLER.post(() -> {
            cancel();
            sToast = Toast.makeText(appContext, text, duration);
            if (useColorful && bgColor != COLOR_DEFAULT) {
                Drawable background = sToast.getView().getBackground();
                background.setColorFilter(new PorterDuffColorFilter(bgColor, PorterDuff.Mode.SRC_IN));
                TextView msgView = sToast.getView().findViewById(android.R.id.message);
                msgView.setTextColor(Color.WHITE);
            }
            sToast.show();
        });
    }

    /**
     * 设置背景颜色
     *
     * @param backgroundColor 背景色
     */
    private static void setBgColor(@ColorInt final int backgroundColor) {
        ToastUtils.bgColor = backgroundColor;
    }
}
