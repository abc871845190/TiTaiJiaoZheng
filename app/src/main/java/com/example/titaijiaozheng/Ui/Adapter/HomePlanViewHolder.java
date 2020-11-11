package com.example.titaijiaozheng.Ui.Adapter;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Model.mainBean.HomePlanListBean;

/**
 * recyclerview数据的显示放在ViewHolder中,定义Holder基类
 *
 * @param <T>
 */
public abstract class HomePlanViewHolder<T extends HomePlanListBean> extends RecyclerView.ViewHolder {
    public HomePlanViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void bindData(T dataBean);
}
