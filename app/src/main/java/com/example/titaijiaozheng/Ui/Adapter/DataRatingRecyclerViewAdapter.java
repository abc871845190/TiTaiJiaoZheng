package com.example.titaijiaozheng.Ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Model.mainBean.DataRatingBean;
import com.example.titaijiaozheng.R;
import com.xuexiang.xui.widget.progress.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataRatingRecyclerViewAdapter extends RecyclerView.Adapter<DataRatingViewHolder> {

    private List<DataRatingBean> mDataRatingBeanList = new ArrayList<>();
    private Context mContext;
    private static final int NORMAL = 1;
    private static final int BOTTOM = 2;
    private static final int TOP = 0;

    public DataRatingRecyclerViewAdapter(List<DataRatingBean> dataRatingBeanList, Context context) {
        this.mDataRatingBeanList = dataRatingBeanList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public DataRatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case NORMAL:
                return new DataRatingRecyclerViewAdapter.InnerHolderNormal(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_data_rating_recyclerview_item, parent, false));
            case BOTTOM:
                return new DataRatingRecyclerViewAdapter.InnerHolderBottom(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_data_rating_recyclerview_item_bottom, parent, false));
            case TOP:
                return new DataRatingRecyclerViewAdapter.InnerHolderTop(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_data_rating_recyclerview_item_top, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DataRatingViewHolder holder, int position) {
        holder.bindData(mDataRatingBeanList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mDataRatingBeanList.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mDataRatingBeanList.size();
    }

    public class InnerHolderNormal extends DataRatingViewHolder {
        @BindView(R.id.fragment_data_rating_recyclerView_item_defaultStandard)
        TextView defaultStandard;
        @BindView(R.id.fragment_data_rating_recyclerView_ratingBar)
        ScaleRatingBar mScaleRatingBar;
        @BindView(R.id.fragment_data_rating_recyclerView_value)
        TextView value;
        @BindView(R.id.fragment_data_rating_recyclerView_standard)
        TextView standard;
        public InnerHolderNormal(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        protected void bindData(DataRatingBean dataBean) {
            bindDataToBean(dataBean);
        }

        private void bindDataToBean(DataRatingBean dataBean) {
            defaultStandard.setText(String.valueOf(dataBean.getDefaultStandard()));
            mScaleRatingBar.setRating(dataBean.getStandardValue());
            value.setText(dataBean.getValue());
            standard.setText(dataBean.getStandard());
        }
    }

    public class InnerHolderBottom extends DataRatingViewHolder {
        @BindView(R.id.fragment_data_rating_recyclerView_item_defaultStandard_btm)
        TextView defaultStandard;
        @BindView(R.id.fragment_data_rating_recyclerView_ratingBar_btm)
        ScaleRatingBar mScaleRatingBar;
        @BindView(R.id.fragment_data_rating_recyclerView_value_btm)
        TextView value;
        @BindView(R.id.fragment_data_rating_recyclerView_standard_btm)
        TextView standard;
        public InnerHolderBottom(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        protected void bindData(DataRatingBean dataBean) {
            defaultStandard.setText(String.valueOf(dataBean.getDefaultStandard()));
            mScaleRatingBar.setRating(dataBean.getStandardValue());
            value.setText(dataBean.getValue());
            standard.setText(dataBean.getStandard());
        }
    }

    public class InnerHolderTop extends DataRatingViewHolder {
        @BindView(R.id.fragment_data_rating_recyclerView_item_defaultStandard_top)
        TextView defaultStandard;
        @BindView(R.id.fragment_data_rating_recyclerView_ratingBar_top)
        ScaleRatingBar mScaleRatingBar;
        @BindView(R.id.fragment_data_rating_recyclerView_value_top)
        TextView value;
        @BindView(R.id.fragment_data_rating_recyclerView_standard_top)
        TextView standard;
        public InnerHolderTop(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }

        @Override
        protected void bindData(DataRatingBean dataBean) {
            defaultStandard.setText(String.valueOf(dataBean.getDefaultStandard()));
            mScaleRatingBar.setRating(dataBean.getStandardValue());
            value.setText(dataBean.getValue());
            standard.setText(dataBean.getStandard());
        }
    }
}
