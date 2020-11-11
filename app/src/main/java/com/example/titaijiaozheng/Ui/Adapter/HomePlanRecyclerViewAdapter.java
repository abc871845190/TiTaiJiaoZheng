package com.example.titaijiaozheng.Ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Model.mainBean.HomePlanListBean;
import com.example.titaijiaozheng.Model.mainBean.HomePlanTypeOne;
import com.example.titaijiaozheng.Model.mainBean.HomePlanTypeThree;
import com.example.titaijiaozheng.Model.mainBean.HomePlanTypeTwo;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Activity.MainActivity;
import com.example.titaijiaozheng.Ui.Fragment.HomeFragment;
import com.example.titaijiaozheng.Utils.LogUtils;
import com.example.titaijiaozheng.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 多类型RecyclerView
 */
public class HomePlanRecyclerViewAdapter extends RecyclerView.Adapter<HomePlanViewHolder> {

    /**
     * 三种不同布局类型
     */
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;

    private List<HomePlanListBean> mHomePlanListBeans;
    private Context mContext;

    public HomePlanRecyclerViewAdapter(List<HomePlanListBean> listBeans, Context context) {
        this.mHomePlanListBeans = listBeans;
        this.mContext = context;
    }

    @NonNull
    @Override
    public HomePlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //根据不同viewtype设置不同的布局和内容
        switch (viewType) {
            case TYPE_ONE:
                View view1 = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.fragment_home_recyclerview_item_one, parent, false);
                InnerHolderOne innerHolderOne = new HomePlanRecyclerViewAdapter.InnerHolderOne(view1);
                return innerHolderOne;
            case TYPE_TWO:
                View view2 = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.fragment_home_recyclerview_item_two, parent, false);
                InnerHolderTwo innerHolderTwo = new HomePlanRecyclerViewAdapter.InnerHolderTwo(view2);
                return innerHolderTwo;
            case TYPE_THREE:
                View view3 = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.fragment_home_recyclerview_item_three, parent, false);
                InnerHolderThree innerHolderThree = new HomePlanRecyclerViewAdapter.InnerHolderThree(view3);
                return innerHolderThree;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePlanViewHolder holder, int position) {
        //绑定数据
        holder.bindData(mHomePlanListBeans.get(position));
    }

    @Override
    public int getItemCount() {
        return mHomePlanListBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mHomePlanListBeans.get(position).getType();
    }

    /**
     * 设置并更新数据
     *
     * @param listBeans
     */
    public void setData(List<HomePlanListBean> listBeans) {
        this.mHomePlanListBeans = listBeans;
        notifyDataSetChanged();
    }

    public List<HomePlanListBean> getData() {
        return mHomePlanListBeans;
    }

    /**
     * 第一个类型数据绑定
     */
    public class InnerHolderOne extends HomePlanViewHolder<HomePlanTypeOne> {
        @BindView(R.id.fragment_home_item_one_img)
        public ImageView mImageView;
        @BindView(R.id.fragment_home_recyclerView_item_one_data_name)
        public TextView mDataName;
        @BindView(R.id.fragment_home_recyclerView_item_one_data_value)
        public TextView mDataValue;

        public InnerHolderOne(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void bindData(HomePlanTypeOne dataBean) {
            mImageView.setImageResource(dataBean.getImg());
            mDataName.setText(dataBean.getDataName());
            mDataValue.setText(dataBean.getDataValue());
        }
    }

    /**
     * 第二个类型数据绑定
     */
    public class InnerHolderTwo extends HomePlanViewHolder<HomePlanTypeTwo> {
        @BindView(R.id.fragment_home_item_two_img)
        public ImageView mImageView;
        @BindView(R.id.fragment_home_recyclerView_item_two_data_name)
        public TextView mDataName;
        @BindView(R.id.fragment_home_recyclerView_item_two_data_subTitle)
        public TextView mSubTitle;
        @BindView(R.id.fragment_home_recyclerView_item_two_switch)
        Switch mSwitch;

        public InnerHolderTwo(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void bindData(HomePlanTypeTwo dataBean) {
            mImageView.setImageResource(dataBean.getImg());
            mDataName.setText(dataBean.getDataName());
            mSubTitle.setText(dataBean.getSubTitle());
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        ToastUtils.showShortToast(mContext,"isChecked");
                    }else{
                        ToastUtils.showShortToast(mContext,"unChecked");
                    }
                }
            });
        }
    }

    /**
     * 第三个类型数据绑定
     */
    public class InnerHolderThree extends HomePlanViewHolder<HomePlanTypeThree> {
        @BindView(R.id.fragment_home_item_three_img)
        public ImageView mImageView;
        @BindView(R.id.fragment_home_recyclerView_item_three_data_name)
        public TextView mDataName;
        @BindView(R.id.fragment_home_recyclerView_item_three_recyclerView)
        public RecyclerView mRecyclerView;

        public InnerHolderThree(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        protected void bindData(HomePlanTypeThree dataBean) {
            mImageView.setImageResource(dataBean.getImg());
            mDataName.setText(dataBean.getDataName());

            //嵌套recyclerView 不需要滑动
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            //初始化item RecyclerView
            //adpter
            HomePlanRecyclerViewItemAdapter homePlanRecyclerViewItemAdapter = new HomePlanRecyclerViewItemAdapter(dataBean.getMapList());
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(homePlanRecyclerViewItemAdapter);
            //防止父组件滑动冲突
            mRecyclerView.setFocusableInTouchMode(false);
            mRecyclerView.requestFocus();
            //打卡监听器
            homePlanRecyclerViewItemAdapter.setSetOnItemListener(new setOnItemListener() {
                @Override
                public void onItemClick(int position) {
                    //item被点击
                    LogUtils.i(HomeFragment.class,"item"+position+"被点击");
                }

                @Override
                public void onButtonClick(int position) {
                    //item 的打卡被点击
                    ToastUtils.showShortToast(mContext,"第"+position+"个打卡被点击");
                }
            });
        }
    }
}
