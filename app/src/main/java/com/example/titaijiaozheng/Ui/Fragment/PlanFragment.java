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
import com.example.titaijiaozheng.Ui.Activity.MainActivity;
import com.example.titaijiaozheng.Ui.Adapter.PlanRecyclerViewPlanAdapter;
import com.example.titaijiaozheng.Ui.Adapter.PlanTrainDataRecyclerViewAdapter;
import com.example.titaijiaozheng.Ui.Adapter.PlanTrainProjectRecyAdapter;
import com.example.titaijiaozheng.Ui.Adapter.RecyclerViewDivider;
import com.example.titaijiaozheng.Utils.ToastUtils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

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
    private ClassicsFooter mFooter;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int setFragmentLayout() {
        return R.layout.main_fragment_plan;
    }

    @Override
    protected void initData() {

        //模拟数据 视频recyclerview
        List<PlanTrainProjectBean> list = new ArrayList<>();
        list.add(new PlanTrainProjectBean("贴墙站", "我是我是我是....", "https://3070180753@qq.com:ans5kgf23i9b4s3h@dav.jianguoyun.com/dav/%E6%88%91%E7%9A%84%E5%9D%9A%E6%9E%9C%E4%BA%91/%E5%BA%B7%E5%A4%8D%E8%A7%86%E9%A2%912.mp4", 0));
        list.add(new PlanTrainProjectBean("静态飞鸟", "1111111..............................................................................", "https://3070180753@qq.com:ans5kgf23i9b4s3h@dav.jianguoyun.com/dav/%E6%88%91%E7%9A%84%E5%9D%9A%E6%9E%9C%E4%BA%91/%E5%BA%B7%E5%A4%8D%E8%A7%86%E9%A2%911.mp4", 1));
        list.add(new PlanTrainProjectBean("健身球左侧弯", "1111111------------------------------------------", "https://3070180753@qq.com:ans5kgf23i9b4s3h@dav.jianguoyun.com/dav/%E6%88%91%E7%9A%84%E5%9D%9A%E6%9E%9C%E4%BA%91/%E5%BA%B7%E5%A4%8D%E8%A7%86%E9%A2%911.mp4", 2));
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
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        fragmentPlanRecyclerViewPlan.setLayoutManager(mLinearLayoutManager);
        fragmentPlanRecyclerViewPlan.setAdapter(mPlanRecyclerViewPlanAdapter);

        //模拟数据 数据项recyclerview
        List<PlanTrainDataBean> planTrainDataBeanList = new ArrayList<>();
        planTrainDataBeanList.add(new PlanTrainDataBean("计划时长", "20分钟"));
        planTrainDataBeanList.add(new PlanTrainDataBean("每周训练", "5日"));
        planTrainDataBeanList.add(new PlanTrainDataBean("每日训练", "2次"));
        mPlanTrainDataRecyclerViewAdapter = new PlanTrainDataRecyclerViewAdapter(planTrainDataBeanList, this.mActivity);
        mGridView.setAdapter(mPlanTrainDataRecyclerViewAdapter);

        //refreshLayout配置
        //刷新头
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(this.getActivity()));
        //底部刷新头
        mFooter = new ClassicsFooter(this.getActivity());
        mFooter.setFinishDuration(2000);
        mSmartRefreshLayout.setRefreshFooter(mFooter);
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
                ToastUtils.showShortToast(getActivity(), "刷新中...");
                refreshLayout.finishRefresh(2000);//传入false表示刷新失败
                ToastUtils.showShortToast(getActivity(), "刷新完毕");
            }
        });
        //footer
        mSmartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //ToastUtils.showShortToast(getActivity(), "加载中...");
                refreshLayout.finishLoadMore();//传入false表示加载失败
                mPlanTrainProjectRecyAdapter.loadMoreData(new PlanTrainProjectBean("测试类", "测试描述", "https://3070180753@qq.com:ans5kgf23i9b4s3h@dav.jianguoyun.com/dav/%E6%88%91%E7%9A%84%E5%9D%9A%E6%9E%9C%E4%BA%91/%E5%BA%B7%E5%A4%8D%E8%A7%86%E9%A2%911.mp4", 3));
                //ToastUtils.showShortToast(getActivity(), "加载完毕");
            }
        });

        fragmentPlanRecyclerViewTrainProject.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //第一个可见视图,最后一个可见视图
            int firstVisibleItem, lastVisibleItem;
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals("InnerHolder")
                            && (position < firstVisibleItem || position > lastVisibleItem)) {
                        if(GSYVideoManager.isFullState(getActivity())) {
                            return;
                        }
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        GSYVideoManager.releaseAllVideos();
                        mPlanTrainProjectRecyAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
