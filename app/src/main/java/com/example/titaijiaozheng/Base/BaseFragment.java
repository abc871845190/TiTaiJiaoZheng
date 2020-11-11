package com.example.titaijiaozheng.Base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * 对fragment主要功能的封装
 */
public abstract class BaseFragment extends Fragment {

    private View mView;
    protected FragmentActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(setFragmentLayout(),null,false);
        return mView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mActivity = (FragmentActivity) context;
    }

    /**
     * 设置碎片布局文件
     * @return
     */
    protected abstract int setFragmentLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,mView);
        initData();
        initListener();
    }

    protected abstract void initData();
    protected abstract void initListener();
}
