package com.example.titaijiaozheng.Ui.Activity;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.titaijiaozheng.Base.BaseActivity;
import com.example.titaijiaozheng.Base.BaseFragment;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Fragment.DataFragment;
import com.example.titaijiaozheng.Ui.Fragment.HomeFragment;
import com.example.titaijiaozheng.Ui.Fragment.PlanFragment;
import com.example.titaijiaozheng.Ui.Fragment.UserFragment;
import com.example.titaijiaozheng.Utils.LogUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_bottom_navigation_bar)
    public BottomNavigationView mBottomNavigationView;
    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private DataFragment mDataFragment;
    private PlanFragment mPlanFragment;
    private UserFragment mUserFragment;


    @Override
    protected int setActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        mHomeFragment = new HomeFragment();
        mDataFragment = new DataFragment();
        mPlanFragment = new PlanFragment();
        mUserFragment = new UserFragment();
        //fragment管理器
        mFragmentManager = getSupportFragmentManager();
        switchFragment(mHomeFragment);

    }

    @Override
    protected void initListener() {
        //导航栏监听
        mBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(mHomeFragment);
                    LogUtils.i(this, "切换到首页");
                    break;
                case R.id.navigation_data:
                    switchFragment(mDataFragment);
                    LogUtils.i(this, "切换到数据");
                    break;
                case R.id.navigation_plan:
                    switchFragment(mPlanFragment);
                    LogUtils.i(this, "切换到日程");
                    break;
                case R.id.navigation_user:
                    switchFragment(mUserFragment);
                    LogUtils.i(this, "切换到用户");
                    break;
            }
            return true;
        });

    }

    /**
     * 显示上一次的fragment
     */
    private BaseFragment lastFragment = null;

    /**
     * 切换fragment
     *
     * @param targetFragment
     */
    private void switchFragment(BaseFragment targetFragment) {
        //如果是跟上一个是同一个fragment，则不用切换
        if (lastFragment == targetFragment) {
            return;
        }
        //使用add和hide的方式来切换 在与此FragmentManager关联的Fragment上启动一系列编辑操作。
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!targetFragment.isAdded()) {
            //没有被添加，则添加
            fragmentTransaction.add(R.id.main_frameLayout, targetFragment);
        } else {
            //有被添加了，则展示
            fragmentTransaction.show(targetFragment);
        }
        if (lastFragment != null) {
            //如果切换时上一次fragment并不是空的,则隐藏该fragment
            fragmentTransaction.hide(lastFragment);
        }
        //记录当前显示的fragment
        lastFragment = targetFragment;
        fragmentTransaction.commit();
    }
}