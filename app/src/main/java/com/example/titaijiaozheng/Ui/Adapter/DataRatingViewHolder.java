package com.example.titaijiaozheng.Ui.Adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Model.mainBean.DataRatingBean;

public abstract class DataRatingViewHolder<T extends DataRatingBean> extends RecyclerView.ViewHolder {

    public DataRatingViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    protected abstract void bindData(T dataBean);
}
