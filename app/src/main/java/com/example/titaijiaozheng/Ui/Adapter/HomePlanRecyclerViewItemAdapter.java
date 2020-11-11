package com.example.titaijiaozheng.Ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 嵌套recyclerView 适配器
 */
public class HomePlanRecyclerViewItemAdapter extends RecyclerView.Adapter<HomePlanRecyclerViewItemAdapter.InnerHolder> {

    private List<String> mStringList;
    private setOnItemListener mSetOnItemListener;

    public HomePlanRecyclerViewItemAdapter(List<String> stringList) {
        this.mStringList = stringList;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_home_recyclerview_item_recyclerview, parent, false);
        InnerHolder innerHolder = new HomePlanRecyclerViewItemAdapter.InnerHolder(view);
        return innerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.mDataName.setText(mStringList.get(position));
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSetOnItemListener != null) {
                    mSetOnItemListener.onButtonClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }


    public class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.fragment_home_recyclerView_item_three_recyclerView_dataName)
        TextView mDataName;
        @BindView(R.id.fragment_home_recyclerView_item_three_recyclerView_button)
        Button mButton;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSetOnItemListener != null) {
                        mSetOnItemListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    /**
     * 暴露给外面添加点击事件
     *
     * @param setOnItemListener
     */
    public void setSetOnItemListener(setOnItemListener setOnItemListener) {
        this.mSetOnItemListener = setOnItemListener;
    }

}
