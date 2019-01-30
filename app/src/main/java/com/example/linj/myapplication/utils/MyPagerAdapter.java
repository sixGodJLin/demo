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


/**
 * @author JLin
 * @date 2018/12/14
 */
public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private String[] imageUrls = new String[]{"http://img2.qidian.com/upload/gamesy/2017/12/29/20171229133040vwqwkjpw06.png",
            "http://pic1.nipic.com/2008-12-22/2008122218219520_2.jpg",
    "http://s8.sinaimg.cn/orignal/4aa8ea56fef5b1ee1b597",
    "http://n.sinaimg.cn/front/703/w600h903/20181123/RwOe-hpevhck2518766.jpg"};

    public MyPagerAdapter(Context context) {
        super();
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
        position %= imageUrls.length;
        ImageView imageView = new ImageView(mContext);
        Glide.with(mContext)
                .load(imageUrls[position])
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
                        .transform(new CircleCrop()))
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }
}
