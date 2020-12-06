package com.example.titaijiaozheng.Ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.titaijiaozheng.Model.mainBean.PlanTrainDataBean;
import com.example.titaijiaozheng.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class PlanTrainDataRecyclerViewAdapter extends BaseAdapter {

    private List<PlanTrainDataBean> mPlanTrainDataBeans = new ArrayList<>();
    private Context mContext;

    public PlanTrainDataRecyclerViewAdapter(List<PlanTrainDataBean> planTrainDataBeans, Context context) {
        this.mPlanTrainDataBeans = planTrainDataBeans;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mPlanTrainDataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mPlanTrainDataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InnerHolder innerHolder = null;
        if (convertView == null) {
            innerHolder = new InnerHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_plan_traindata_recyclerview_item, parent, false);
            innerHolder.name = convertView.findViewById(R.id.fragment_plan_traindata_title);
            innerHolder.value = convertView.findViewById(R.id.fragment_plan_traindata_value);

            convertView.setTag(innerHolder);
        } else {
            innerHolder = (InnerHolder) convertView.getTag();
        }

        innerHolder.name.setText(mPlanTrainDataBeans.get(position).getName());
        innerHolder.value.setText(mPlanTrainDataBeans.get(position).getValue());
        return convertView;
    }

    class InnerHolder {
        TextView name;
        TextView value;
    }
}
