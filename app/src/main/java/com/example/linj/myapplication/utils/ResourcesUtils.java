package com.example.linj.myapplication.utils;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FontRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.support.annotation.StyleableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * @author JLin
 * @date 2019/4/29
 * @describe 全局资源文件工具类
 */
public class ResourcesUtils {

    private static Context appContext;

    /**
     * 初始化全局配置，一般用于applicationContext
     *
     * @param context context
     */
    public static void initAppContext(Context context) {
        ResourcesUtils.appContext = context.getApplicationContext();
    }

    /**
     * 获取动画
     *
     * @param resId resId
     */
    public static Animation getAnimation(@AnimRes int resId) {
        return AnimationUtils.loadAnimation(appContext, resId);
    }

    /**
     * 获取动画set
     *
     * @param resId resId
     */
    public static AnimatorSet getAnimationSet(@AnimatorRes int resId) {
        return (AnimatorSet) AnimatorInflater.loadAnimator(appContext, resId);
    }

    /**
     * 获取指定单位的大小
     *
     * @param unit unit
     * @param size size
     * @see TypedValue#COMPLEX_UNIT_PX
     * @see TypedValue#COMPLEX_UNIT_DIP
     * @see TypedValue#COMPLEX_UNIT_SP
     * @see TypedValue#COMPLEX_UNIT_PT
     * @see TypedValue#COMPLEX_UNIT_IN
     * @see TypedValue#COMPLEX_UNIT_MM
     */
    public static float getRawSize(int unit, float size) {
        try {
            return TypedValue.applyDimension(unit, size, appContext.getResources().getDisplayMetrics());
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    /**
     * 获取字体
     *
     * @param resId resId
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Typeface getId(@FontRes int resId) {
        return appContext.getResources().getFont(resId);
    }

    /**
     * 获取AssetManager
     */
    public static AssetManager getAssetManager() {
        return appContext.getResources().getAssets();
    }

    /**
     * 获取drawable
     *
     * @param resId resId
     */
    public static Drawable getDrawable(@DrawableRes int resId) {
        return ContextCompat.getDrawable(appContext, resId);
    }

    /**
     * 获取ColorStateList
     *
     * @param resId resId
     */
    public static ColorStateList getColorStateList(@ColorRes int resId) {
        return ContextCompat.getColorStateList(appContext, resId);
    }

    /**
     * 获取颜色值
     *
     * @param resId resId
     */
    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(appContext, resId);
    }

    /**
     * 获取drawable资源数组
     *
     * @param resId resId
     */
    public static int[] getDrawables(@ArrayRes int resId) {
        TypedArray ar = appContext.getResources().obtainTypedArray(resId);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++) {
            resIds[i] = ar.getResourceId(i, 0);
        }
        ar.recycle();
        return resIds;
    }

    /**
     * 获取布尔值
     *
     * @param resId resId
     */
    public static boolean getBoolean(@BoolRes int resId) {
        return appContext.getResources().getBoolean(resId);
    }

    /**
     * 获取StyledAttributes
     *
     * @param set   attr
     * @param attrs styleable
     */
    public static TypedArray getStyledAttr(AttributeSet set, @StyleableRes int[] attrs) {
        return appContext.obtainStyledAttributes(set, attrs);
    }

    /**
     * 获取dimen.xml中的尺寸
     *
     * @param dimen dimen
     */
    public static float getDimen(@DimenRes int dimen) {
        return appContext.getResources().getDimension(dimen);
    }

    /**
     * 获取dimen.xml中的尺寸 dp->px
     *
     * @param dimen dimen
     */
    public static float getDimensionPixelSize(@DimenRes int dimen) {
        return appContext.getResources().getDimensionPixelSize(dimen);
    }

    /**
     * 得到String.xml中的字符串数组
     *
     * @param resId resId
     */
    public static int[] getIntArr(@ArrayRes int resId) {
        return appContext.getResources().getIntArray(resId);
    }

    /**
     * 得到String.xml中的字符串数组
     *
     * @param resId resId
     */
    public static String[] getStringArr(@ArrayRes int resId) {
        return appContext.getResources().getStringArray(resId);
    }

    /**
     * 得到String.xml中的字符串
     *
     * @param resId resId
     */
    public static String getString(@StringRes int resId) {
        return appContext.getString(resId);
    }

    /**
     * 得到String.xml中的字符串，带格式化符号
     *
     * @param resId      resId
     * @param formatArgs formatArgs
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        return appContext.getString(resId, formatArgs);
    }

    /**
     * 获取 ?attr/selectableItemBackground
     */
    public static Drawable getSelectableItemBackground() {
        TypedValue typedValue = new TypedValue();
        appContext.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        int[] attribute = new int[]{android.R.attr.selectableItemBackground};
        TypedArray typedArray = appContext.getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
        return typedArray.getDrawable(0);
    }

    /**
     * 获取 ?attr/selectableItemBackgroundBorderless
     */
    public static Drawable getSelectableItemBackgroundBorderless() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TypedValue typedValue = new TypedValue();
            appContext.getTheme().resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, typedValue, true);
            int[] attribute = new int[]{android.R.attr.selectableItemBackgroundBorderless};
            TypedArray typedArray = appContext.getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
            return typedArray.getDrawable(0);
        } else {
            return getSelectableItemBackground();
        }

    }
}
