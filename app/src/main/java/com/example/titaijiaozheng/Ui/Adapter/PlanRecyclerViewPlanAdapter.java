package com.example.titaijiaozheng.Ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.titaijiaozheng.R;
import java.util.ArrayList;
import java.util.List;

public class PlanRecyclerViewPlanAdapter extends RecyclerView.Adapter<PlanRecyclerViewPlanAdapter.InnerHolder> {

    private List<String> mStringList = new ArrayList<>();

    public PlanRecyclerViewPlanAdapter(List<String> stringList){
        this.mStringList = stringList;
    }

    @NonNull
    @Override
    public PlanRecyclerViewPlanAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlanRecyclerViewPlanAdapter.InnerHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_plan_recyclerview_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlanRecyclerViewPlanAdapter.InnerHolder holder, int position) {
        holder.mTextView.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.fragment_plan_recyclerView_item_text);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public List<String> getStringList() {
        return mStringList;
    }
}
