package com.example.titaijiaozheng.Ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.titaijiaozheng.Model.mainBean.PlanTrainProjectBean;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Activity.PlanVideoActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * 日常页面训练项目列表适配器
 */
public class PlanTrainProjectRecyAdapter extends RecyclerView.Adapter<PlanTrainProjectRecyAdapter.InnerHolder> {

    private Context mContext;
    private setOnItemListener mSetOnItemListener;
    private List<PlanTrainProjectBean> mPlanTrainProjectBeans = new ArrayList<>();

    public PlanTrainProjectRecyAdapter(Context context, List<PlanTrainProjectBean> planTrainProjectBeans) {
        this.mPlanTrainProjectBeans = planTrainProjectBeans;
        this.mContext = context;
    }

    @NonNull
    @Override
    public PlanTrainProjectRecyAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanTrainProjectRecyAdapter.InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_plan_trainproject_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        holder.mTitle.setText(mPlanTrainProjectBeans.get(position).getTrainName());
        //使用ClickableSpan
        String more = "查看更多";
        String video = "观看视频";
        String text = mPlanTrainProjectBeans.get(position).getTrainDesc();
        int flag = mPlanTrainProjectBeans.get(position).getTrainFlag();
        SpannableString newText;
        if (text.length() >= 60) {
            //如果描述超过特定长度，删减
            text = mPlanTrainProjectBeans.get(position).getTrainDesc().substring(0, 60);
            newText = addTextClickableSpan(more, video, text, flag);
        } else {
            //没有超出长度
            newText = addTextClickableSpan(more, video, text, flag);
        }
        holder.mSubtitle.setText(newText);
        holder.mSubtitle.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private SpannableString addTextClickableSpan(String more, String video, String desc, int flag) {
        //处理text
        SpannableString spannableString = new SpannableString(desc + more + "    " + video);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //TODO:查看更多activity跳转
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //跳转到视频界面
                Intent intent = new Intent();
                intent.setClass(mContext, PlanVideoActivity.class);
                intent.putExtra("flag", flag);
                mContext.startActivity(intent);
            }
        };
        //设置监听
        spannableString.setSpan(clickableSpan1, desc.length(), desc.length() + more.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(clickableSpan2, desc.length() + more.length() + 4, desc.length() + more.length() + video.length() + 4, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public int getItemCount() {
        return mPlanTrainProjectBeans.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final TextView mTitle;
        private final TextView mSubtitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.fragment_plan_recyclerView_trainProject_item_title);
            mSubtitle = itemView.findViewById(R.id.fragment_plan_recyclerView_trainProject_item_subtitle);
        }
    }

    //回调接口
    public void setOnItemListener(setOnItemListener onItemListener) {
        mSetOnItemListener = onItemListener;
    }
}
