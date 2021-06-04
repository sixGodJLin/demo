package com.example.linj.myapplication.photo.photoUtils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.example.linj.myapplication.R;
import com.example.linj.myapplication.recycler.BaseAdapter;
import com.example.linj.myapplication.utils.ResourcesUtils;

import java.util.List;

public class PhotoAdapter extends BaseAdapter<PhotoItem> {
    private ImageView imageView;

    public PhotoAdapter(Context context, List<PhotoItem> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.item_image;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, PhotoItem item) {
        imageView = (ImageView) holder.getView(R.id.imageView);

        if (position == PhotoConfig.pic_max - 1) {
            imageView.setImageDrawable(ResourcesUtils.getDrawable(R.drawable.icon_add));
        } else {
            imageView.setBackground(ResourcesUtils.getDrawable(R.drawable.img1));
        }

        holder.setOnItemViewClickListener(R.id.root, v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position, item);
            }
        });
    }
}
