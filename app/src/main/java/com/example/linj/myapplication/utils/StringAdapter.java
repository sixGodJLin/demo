package com.example.linj.myapplication.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.linj.myapplication.AnimateActivity;
import com.example.linj.myapplication.MyRadioButton;
import com.example.linj.myapplication.R;

import java.util.List;

/**
 * @author JLin
 * @date 2018/12/12
 */
public class StringAdapter extends RecyclerView.Adapter<StringAdapter.ViewHolder> {
    private Context mContext;
    private List<AnimateActivity.SelectBean> beans;
    private int mode;

    public StringAdapter(Context mContext, List<AnimateActivity.SelectBean> beans, int mode) {
        this.mContext = mContext;
        this.beans = beans;
        this.mode = mode;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_report, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myRadioButton.setVisibility(mode == 1 ? View.VISIBLE : View.GONE);

        holder.textView.setText(beans.get(position).getContent());

        holder.myRadioButton.setChecked(beans.get(position).isSelect());
        holder.linearLayout.setOnClickListener(v -> {
            holder.myRadioButton.setChecked(!holder.myRadioButton.isChecked());
            beans.get(position).setSelect(holder.myRadioButton.isChecked());
        });
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private RadioButton myRadioButton;
        private LinearLayout linearLayout;

        ViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            textView = itemView.findViewById(R.id.textView);
            myRadioButton = itemView.findViewById(R.id.radioButton);
        }
    }

    public List<AnimateActivity.SelectBean> getSelect() {
        return beans;
    }
}
