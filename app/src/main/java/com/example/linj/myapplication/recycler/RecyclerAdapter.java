package com.example.linj.myapplication.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.linj.myapplication.R;

import java.util.List;

/**
 * @author JLin
 * @date 2019/3/8
 */
public class RecyclerAdapter extends RecyclerView.Adapter {
    private static final int TYPE_HEADER = 1;

    private Context context;
    private List<String> list;

    public RecyclerAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == TYPE_HEADER) {
            return new ReceiveMsg(LayoutInflater.from(context).inflate(R.layout.item_receive_msg, viewGroup, false));
        } else {
            return new SendMsg(LayoutInflater.from(context).inflate(R.layout.item_send_msg, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ReceiveMsg) {
            ((ReceiveMsg) viewHolder).bindView(i);
        } else {
            ((SendMsg) viewHolder).bindView(i);
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return "0".equals(list.get(position)) ? TYPE_HEADER : super.getItemViewType(position);
    }

    class ReceiveMsg extends RecyclerView.ViewHolder {
        TextView tvReceive;

        ReceiveMsg(@NonNull View itemView) {
            super(itemView);
            tvReceive = itemView.findViewById(R.id.tv_receive);
        }

        void bindView(int position) {
            tvReceive.setText(list.get(position));
        }
    }

    class SendMsg extends RecyclerView.ViewHolder {
        TextView tvSend;

        SendMsg(@NonNull View itemView) {
            super(itemView);
            tvSend = itemView.findViewById(R.id.tv_send);
        }

        void bindView(int position) {
            tvSend.setText(list.get(position));
        }
    }

    interface OnItemClickListener<T> {
        /**
         * item点击
         *
         * @param view     view
         * @param position position
         * @param data     data
         */
        void onItemClick(View view, int position, T data);
    }
}
