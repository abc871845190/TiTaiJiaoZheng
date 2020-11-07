package com.example.titaijiaozheng.Base;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 对activity主要方法的封装，后续继续完善
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(setActivityLayout());

        //绑定ButterKnife
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    protected abstract int setActivityLayout();

    protected abstract void initData();

    protected abstract void initListener();
}
