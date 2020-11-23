package com.example.titaijiaozheng.Ui.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Base.BaseFragment;
import com.example.titaijiaozheng.Model.mainBean.DataErrorBean;
import com.example.titaijiaozheng.Model.mainBean.DataRatingBean;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Adapter.DataErrorRecyclerViewAdapter;
import com.example.titaijiaozheng.Ui.Adapter.DataRatingRecyclerViewAdapter;
import com.example.titaijiaozheng.Ui.Adapter.RecyclerViewDivider;
import com.example.titaijiaozheng.Ui.ViewDIY.GradeProgressBar;
import com.example.titaijiaozheng.Utils.ToastUtils;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.scwang.smart.refresh.header.MaterialHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.xuexiang.xui.widget.progress.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DataFragment extends BaseFragment {

    private final String[] parties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };
    @BindView(R.id.fragment_data_progressBar)
    GradeProgressBar mGradeProgressBar;
    @BindView(R.id.fragment_data_ratingBar)
    ScaleRatingBar mScaleRatingBar;
    @BindView(R.id.fragment_data_rating_recyclerView)
    RecyclerView ratingRecyclerView;
    @BindView(R.id.fragment_data_error_recyclerView)
    RecyclerView errorRecyclerView;
    @BindView(R.id.fragment_data_PieChart)
    PieChart chart;
    @BindView(R.id.fragment_data_refreshLayout)
    SmartRefreshLayout mSmartRefreshLayout;
    private DataErrorRecyclerViewAdapter mDataErrorRecyclerViewAdapter;
    private DataRatingRecyclerViewAdapter mDataRatingRecyclerViewAdapter;

    @Override
    protected int setFragmentLayout() {
        return R.layout.main_fragment_data;
    }

    @Override
    protected void initData() {
        //progressBar数值
        mGradeProgressBar.update(252, String.valueOf(252 * 100 / 360));
        //星级ratingBar数值
        mScaleRatingBar.setRating(4);

        //模拟星级数据
        List<DataRatingBean> dataRatingBeanList = new ArrayList<>();
        dataRatingBeanList.add(new DataRatingBean("步态评分指标", 10, 1, "xx数值", 0));
        dataRatingBeanList.add(new DataRatingBean("体重评分指标", 10, 2, "xx数值", 1));
        dataRatingBeanList.add(new DataRatingBean("身高评分指标", 10, 1, "xx数值", 2));
        //初始化adapter
        mDataRatingRecyclerViewAdapter = new DataRatingRecyclerViewAdapter(dataRatingBeanList, this.mActivity);
        ratingRecyclerView.setAdapter(mDataRatingRecyclerViewAdapter);
        ratingRecyclerView.setLayoutManager(new LinearLayoutManager(this.mActivity));

        //饼状图配置
        initChartStyle();
        initChartLabel();
        setChartData(4, 10);
        //饼图动画  时间加缓冲函数
        chart.animateY(1400, Easing.EaseInOutBack);

        //refreshLayout配置
        //刷新头
        mSmartRefreshLayout.setRefreshHeader(new MaterialHeader(this.getActivity()));

        //错误列表数据
        List<DataErrorBean> dataErrorBeanList = new ArrayList<>();
        dataErrorBeanList.add(new DataErrorBean("骨盘前倾", "描述信息...."));
        dataErrorBeanList.add(new DataErrorBean("脊柱左侧弯", "描述信息...."));
        //初始化adapter
        mDataErrorRecyclerViewAdapter = new DataErrorRecyclerViewAdapter(dataErrorBeanList, this.mActivity);
        errorRecyclerView.setAdapter(mDataErrorRecyclerViewAdapter);
        errorRecyclerView.setLayoutManager(new LinearLayoutManager(this.mActivity));
        errorRecyclerView.addItemDecoration(new RecyclerViewDivider(30));
    }

    /**
     * 设置图表数据
     *
     * @param count 柱状图中柱的数量
     * @param range
     */
    private void setChartData(int count, float range) {
        List<PieEntry> entries = new ArrayList<>();
        //The order of the entries when being added to the entries array determines their position around the center of the chart
        //将条目添加到条目数组时，条目的顺序决定了它们在图表中心的位置
        for (int i = 0; i < count; i++) {
            //设置数据源
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), parties[i % parties.length]));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        //add a lot of colors
        //添加一些颜色
        List<Integer> colors = new ArrayList<>();
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter(chart));
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);

        // undo all highlights
        //撤销所有的亮点
        chart.highlightValues(null);
        chart.invalidate();
    }

    /**
     * 初始化图表的 标签
     */
    private void initChartLabel() {
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        //输入标签样式
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(12f);
    }

    /**
     * 初始化图表的样式
     */
    private void initChartStyle() {
        //使用百分百显示
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        //设置拖拽的阻尼，0为立即停止
        chart.setDragDecelerationFrictionCoef(0.95f);

        //设置图标中心文字
        chart.setDrawCenterText(false);
        //设置图标中心空白，空心
        chart.setDrawHoleEnabled(false);
        //设置空心圆的弧度百分比，最大100
        chart.setHoleRadius(58f);
        chart.setHoleColor(Color.WHITE);
        //设置透明弧的样式
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setTransparentCircleRadius(61f);

        //设置可以旋转
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);


    }

    @Override
    protected void initListener() {
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                if (e != null) {
                    ToastUtils.showShortToast(getActivity(),
                            "Value: " + e.getY() + ", index: " + h.getX()
                                    + ", DataSet index: " + h.getDataSetIndex());
                }
            }

            @Override
            public void onNothingSelected() {

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

        //item—enter点击事件
        mDataErrorRecyclerViewAdapter.setOnItemListener(new DataErrorRecyclerViewAdapter.setOnItemListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.showShortToast(getActivity(), "你点击了第" + position + "个item");
            }
        });

    }
}
