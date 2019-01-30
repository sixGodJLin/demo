package com.example.linj.myapplication.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.linj.myapplication.R;

import java.util.List;


/**
 * @author JLin
 * @date 2018/12/14
 */
public class GuideAdapter extends PagerAdapter {
    private Context mContext;
    private List<Integer> resId;

    public GuideAdapter(Context context, List<Integer> resId) {
        super();
        this.mContext = context;
        this.resId = resId;
    }

    @Override
    public int getCount() {
        return resId.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext)
                .load(resId.get(position))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
