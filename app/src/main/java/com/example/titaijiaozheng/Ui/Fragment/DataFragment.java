package com.example.titaijiaozheng.Ui.Fragment;

import com.example.titaijiaozheng.Base.BaseFragment;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.ViewDIY.GradeProgressBar;

import butterknife.BindView;

public class DataFragment extends BaseFragment {

    @BindView(R.id.fragment_data_progressBar)
    GradeProgressBar mGradeProgressBar;

    @Override
    protected int setFragmentLayout() {
        return R.layout.main_fragment_data;
    }

    @Override
    protected void initData() {
        mGradeProgressBar.update(100, 100 * 100 / 360 + "%");
    }

    @Override
    protected void initListener() {

    }
}
