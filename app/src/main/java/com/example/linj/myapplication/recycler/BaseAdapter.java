package com.example.linj.myapplication.recycler;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author JLin
 * @date 2018/12/17
 * @describe 普通适配器基类
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.BaseRecyclerViewHolder> {

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected OnItemClickListener<T> mOnItemClickListener;
    protected OnItemLongClickListener<T> mOnItemLongClickListener;

    public BaseAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas == null ? new ArrayList<T>() : datas;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        final BaseRecyclerViewHolder holder = new BaseRecyclerViewHolder(mContext, mInflater.inflate(getItemViewId(viewType), parent, false), viewType);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, holder.getLayoutPosition(), mDatas.get(holder.getAdapterPosition())));
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(view -> {
                mOnItemLongClickListener.onItemLongClick(view, holder.getLayoutPosition(), mDatas.get(holder.getAdapterPosition()));
                return true;
            });
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        bindData(holder, position, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void addItem(T itemData) {
        if (mDatas.contains(itemData)) {
            return;
        }
        int position = mDatas.size();
        mDatas.add(position, itemData);
        notifyItemInserted(position);
    }

    public void addItem(int position, T itemData) {
        if (mDatas.contains(itemData)) {
            return;
        }
        mDatas.add(position, itemData);
        notifyItemInserted(position);
    }

    public void deleteItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void deleteItem(T targetItem) {
        Iterator<T> iterator = mDatas.iterator();
        while (iterator.hasNext()) {
            T currentItem = iterator.next();
            if (targetItem == currentItem) {
                iterator.remove();
                notifyDataSetChanged();
            }
        }
    }

    public void addMoreItem(List<T> datas) {
        int startPosition = mDatas.size();
        mDatas.addAll(datas);
        notifyItemRangeChanged(startPosition, mDatas.size());
    }

    public void replaceAll(List<T> datas) {
        if (mDatas == datas) {
            notifyDataSetChanged();
            return;
        }
        if (datas == null || datas.isEmpty()) {
            clear();
            return;
        }
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 引入布局
     *
     * @param viewType viewType
     * @return 布局文件
     */
    public abstract int getItemViewId(int viewType);

    /**
     * 置数据
     *
     * @param holder   holder
     * @param position position
     * @param item     data
     */
    public abstract void bindData(BaseRecyclerViewHolder holder, int position, T item);

    public void setOnItemClickListener(OnItemClickListener<T> clickListener) {
        mOnItemClickListener = clickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> longClickListener) {
        mOnItemLongClickListener = longClickListener;
    }

    public interface OnItemClickListener<T> {
        /**
         * item点击
         *
         * @param view     view
         * @param position position
         * @param data     data
         */
        void onItemClick(View view, int position, T data);
    }

    public interface OnItemLongClickListener<T> {

        /**
         * item长按
         *
         * @param view     view
         * @param position position
         * @param data     data
         * @return true
         */
        boolean onItemLongClick(View view, int position, T data);
    }

    public static class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

        /**
         * 集合类，layout里包含的View,以view的id作为key，value是view对象
         */
        private SparseArray<View> mViews;
        private Context mContext;
        private int viewType;

        public BaseRecyclerViewHolder(Context context, View itemView, int viewType) {
            super(itemView);
            mContext = context;
            mViews = new SparseArray<>();
            this.viewType = viewType;
        }

        @SuppressWarnings("unchecked")
        private <T extends View> T findViewById(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public View getView(int viewId) {
            return findViewById(viewId);
        }

        public int getViewType() {
            return viewType;
        }

        public BaseRecyclerViewHolder setText(int viewId, String value) {
            TextView view = findViewById(viewId);
            view.setText(value);
            return this;
        }

        public BaseRecyclerViewHolder setBackground(int viewId, int resId) {
            View view = findViewById(viewId);
            view.setBackgroundResource(resId);
            return this;
        }

        public BaseRecyclerViewHolder setBackgroundColor(int viewId, int color) {
            View view = findViewById(viewId);
            view.setBackgroundColor(color);
            return this;
        }

        public BaseRecyclerViewHolder setImage(int viewId, int resId) {
            ImageView view = findViewById(viewId);
            return this;
        }

        public BaseRecyclerViewHolder setImage(int viewId, String url) {
            ImageView view = findViewById(viewId);
            Glide.with(mContext).load(url).into(view);
            return this;
        }

        public BaseRecyclerViewHolder setImage(int viewId, Drawable drawable) {
            ImageView view = findViewById(viewId);
            return this;
        }

        public BaseRecyclerViewHolder setOnItemViewClickListener(int viewId, View.OnClickListener listener) {
            View view = findViewById(viewId);
            view.setOnClickListener(listener);
            return this;
        }

    }

}