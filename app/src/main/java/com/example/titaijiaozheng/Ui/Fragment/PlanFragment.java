package com.example.titaijiaozheng.Ui.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Base.BaseFragment;
import com.example.titaijiaozheng.Model.mainBean.PlanTrainProjectBean;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Adapter.PlanRecyclerViewPlanAdapter;
import com.example.titaijiaozheng.Ui.Adapter.PlanTrainProjectRecyAdapter;
import com.example.titaijiaozheng.Ui.Adapter.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PlanFragment extends BaseFragment {
    @BindView(R.id.fragment_plan_recyclerView_plan)
    RecyclerView fragmentPlanRecyclerViewPlan;
    @BindView(R.id.fragment_plan_recyclerView_trainProject)
    RecyclerView fragmentPlanRecyclerViewTrainProject;
    private PlanTrainProjectRecyAdapter mPlanTrainProjectRecyAdapter;
    private PlanRecyclerViewPlanAdapter mPlanRecyclerViewPlanAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.main_fragment_plan;
    }

    @Override
    protected void initData() {

        //模拟数据
        List<PlanTrainProjectBean> list = new ArrayList<>();
        list.add(new PlanTrainProjectBean("贴墙站","我是我是我是....",0));
        list.add(new PlanTrainProjectBean("静态飞鸟","1111111..............................................................................",1));
        list.add(new PlanTrainProjectBean("健身球左侧弯","1111111------------------------------------------",2));
        mPlanTrainProjectRecyAdapter = new PlanTrainProjectRecyAdapter(this.getContext(),list);
        fragmentPlanRecyclerViewTrainProject.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentPlanRecyclerViewTrainProject.addItemDecoration(new RecyclerViewDivider(30));
        fragmentPlanRecyclerViewTrainProject.setAdapter(mPlanTrainProjectRecyAdapter);

        //模拟数据
        List<String> stringList = new ArrayList<>();
        stringList.add("贴墙站");
        stringList.add("静态飞鸟");
        stringList.add("健身球左侧弯");
        mPlanRecyclerViewPlanAdapter = new PlanRecyclerViewPlanAdapter(stringList);
        fragmentPlanRecyclerViewPlan.setLayoutManager(new LinearLayoutManager(getContext()));
        fragmentPlanRecyclerViewPlan.setAdapter(mPlanRecyclerViewPlanAdapter);

    }

    @Override
    protected void initListener() {

    }
}
