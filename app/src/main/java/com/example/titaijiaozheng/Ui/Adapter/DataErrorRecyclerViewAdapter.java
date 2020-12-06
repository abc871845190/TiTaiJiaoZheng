package com.example.titaijiaozheng.Ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Model.mainBean.DataErrorBean;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataErrorRecyclerViewAdapter extends RecyclerView.Adapter<DataErrorRecyclerViewAdapter.InnerHolder> {

    private List<DataErrorBean> mDataErrorBeanList = new ArrayList<>();
    private Context mContext;
    private setOnItemListener mSetOnItemListener;

    public DataErrorRecyclerViewAdapter(List<DataErrorBean> dataErrorBeans, Context context){
        this.mDataErrorBeanList = dataErrorBeans;
        this.mContext = context;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataErrorRecyclerViewAdapter.InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_data_error_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.name.setText(mDataErrorBeanList.get(position).getName());
        holder.desc.setText(mDataErrorBeanList.get(position).getDesc());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSetOnItemListener!=null){
                    mSetOnItemListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataErrorBeanList.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_data_error_recyclerView_item_name)
        TextView name;
        @BindView(R.id.fragment_data_error_recyclerView_item_desc)
        TextView desc;
        @BindView(R.id.fragment_data_error_recyclerView_item_enter)
        ImageView mImageView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface setOnItemListener{
        void onItemClick(int position);
    }

    public void setOnItemListener(setOnItemListener setOnItemListener){
        this.mSetOnItemListener = setOnItemListener;
    }
}
