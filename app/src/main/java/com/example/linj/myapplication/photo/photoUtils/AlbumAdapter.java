package com.example.linj.myapplication.photo.photoUtils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.linj.myapplication.R;
import com.example.linj.myapplication.recycler.BaseAdapter;
import com.example.linj.myapplication.utils.ResourcesUtils;

import java.io.File;
import java.util.List;

public class AlbumAdapter extends BaseAdapter<PhotoItem> {
    private ImageView imageView;
    private Context context;

    public AlbumAdapter(Context context, List<PhotoItem> datas) {
        super(context, datas);
        this.context = context;
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.item_album;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, PhotoItem item) {
        imageView = (ImageView) holder.getView(R.id.imageView);
        Glide.with(context).load(item.getImagePath()).into(imageView);
        holder.setOnItemViewClickListener(R.id.root, v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position, item);
            }
        });
    }
}
