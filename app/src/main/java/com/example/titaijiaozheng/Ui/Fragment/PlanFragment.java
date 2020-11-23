package com.example.titaijiaozheng.Ui.Fragment;

import android.view.View;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Base.BaseFragment;
import com.example.titaijiaozheng.Model.mainBean.PlanTrainDataBean;
import com.example.titaijiaozheng.Model.mainBean.PlanTrainProjectBean;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Adapter.PlanRecyclerViewPlanAdapter;
import com.example.titaijiaozheng.Ui.Adapter.PlanTrainDataRecyclerViewAdapter;
import com.example.titaijiaozheng.Ui.Adapter.PlanTrainProjectRecyAdapter;
import com.example.titaijiaozheng.Ui.Adapter.RecyclerViewDivider;
import com.example.titaijiaozheng.Utils.ToastUtils;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PlanFragment extends BaseFragment {
    @BindView(R.id.fragment_plan_refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.fragment_plan_recyclerView_planData)
    GridView mGridView;
    @BindView(R.id.fragment_plan_recyclerView_plan)
    RecyclerView fragmentPlanRecyclerViewPlan;
    @BindView(R.id.fragment_plan_recyclerView_trainProject)
    RecyclerView fragmentPlanRecyclerViewTrainProject;
    private PlanTrainProjectRecyAdapter mPlanTrainProjectRecyAdapter;
    private PlanRecyclerViewPlanAdapter mPlanRecyclerViewPlanAdapter;
    private PlanTrainDataRecyclerViewAdapter mPlanTrainDataRecyclerViewAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.main_fragment_plan;
    }

    @Override
    protected void initData() {

        //模拟数据 视频recyclerview
        List<PlanTrainProjectBean> list = new ArrayList<>();
        list.add(new PlanTrainProjectBean("贴墙站", "我是我是我是....", 0));
        list.add(new PlanTrainProjectBean("静态飞鸟", "1111111..............................................................................", 1));
        list.add(new PlanTrainProjectBean("健身球左侧弯", "1111111------------------------------------------", 2));
        mPlanTrainProjectRecyAdapter = new PlanTrainProjectRecyAdapter(this.getContext(), list);
        fragmentPlanRecyclerViewTrainProject.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentPlanRecyclerViewTrainProject.addItemDecoration(new RecyclerViewDivider(30));
        fragmentPlanRecyclerViewTrainProject.setAdapter(mPlanTrainProjectRecyAdapter);

        //模拟数据 方案recyclerview
        List<String> stringList = new ArrayList<>();
        stringList.add("贴墙站");
        stringList.add("静态飞鸟");
        stringList.add("健身球左侧弯");
        mPlanRecyclerViewPlanAdapter = new PlanRecyclerViewPlanAdapter(stringList);
        fragmentPlanRecyclerViewPlan.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentPlanRecyclerViewPlan.setAdapter(mPlanRecyclerViewPlanAdapter);

        //模拟数据 数据项recyclerview
        List<PlanTrainDataBean> planTrainDataBeanList = new ArrayList<>();
        planTrainDataBeanList.add(new PlanTrainDataBean("计划时长", "20分钟"));
        planTrainDataBeanList.add(new PlanTrainDataBean("每周训练", "5日"));
        planTrainDataBeanList.add(new PlanTrainDataBean("每日训练", "2次"));
        mPlanTrainDataRecyclerViewAdapter = new PlanTrainDataRecyclerViewAdapter(planTrainDataBeanList,this.mActivity);
        mGridView.setAdapter(mPlanTrainDataRecyclerViewAdapter);

        //refreshLayout配置
        //刷新头
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(this.getActivity()));
    }

    @Override
    protected void initListener() {
        mPlanRecyclerViewPlanAdapter.setOnItemClickListener(new PlanRecyclerViewPlanAdapter.setOnItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showShortToast(getActivity(), "你现在点击的第" + position + "个方案");
            }
        });

        //刷新监听
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                ToastUtils.showShortToast(getActivity(),"刷新中...");
                refreshLayout.finishRefresh(2000);//传入false表示刷新失败
                ToastUtils.showShortToast(getActivity(),"刷新完毕");
            }
        });
    }
}
