package com.example.linj.myapplication.recycler;

import android.content.Context;
import android.widget.TextView;

import com.example.linj.myapplication.R;

import java.util.List;

public class Recycler2Adapter extends BaseAdapter<String> {

    public Recycler2Adapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewId(int viewType) {
        return R.layout.item_receive_msg;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, int position, String item) {
        System.out.println("Recycler2Adapter：" + "bindData" + "==== " + item);
        TextView textView = (TextView) holder.getView(R.id.tv_receive);
        textView.setText(item);
    }
}
