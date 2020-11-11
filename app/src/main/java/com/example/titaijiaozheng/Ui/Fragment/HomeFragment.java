package com.example.titaijiaozheng.Ui.Fragment;

import android.Manifest;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.titaijiaozheng.Base.BaseFragment;
import com.example.titaijiaozheng.Model.Api;
import com.example.titaijiaozheng.Model.mainBean.HomePlanListBean;
import com.example.titaijiaozheng.Model.mainBean.HomePlanTypeOne;
import com.example.titaijiaozheng.Model.mainBean.HomePlanTypeThree;
import com.example.titaijiaozheng.Model.mainBean.HomePlanTypeTwo;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Activity.MainActivity;
import com.example.titaijiaozheng.Ui.Adapter.HomePlanRecyclerViewAdapter;
import com.example.titaijiaozheng.Utils.LogUtils;
import com.example.titaijiaozheng.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks {


    public LocationClient mLocationClient = null;
    @BindView(R.id.fragment_home_location_text) TextView fragmentHomeLocationText;
    @BindView(R.id.fragment_home_weather_text) TextView fragmentHomeWeatherText;
    @BindView(R.id.fragment_home_temperature_text) TextView fragmentHomeTemperatureText;
    @BindView(R.id.fragment_home_state_text) TextView fragmentHomeStateText;
    @BindView(R.id.fragment_home_recyclerView) RecyclerView fragmentHomeRecyclerView;

    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
    //原有BDLocationListener接口暂时同步保留。具体介绍请参考后文第四步的说明
    private MyLocationListener myListener = new MyLocationListener();
    private static final int requestCode = 1;
    private LocationClientOption mOption;

    @Override
    protected int setFragmentLayout() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initData() {
        LogUtils.i(this, "init Data ing----");
        //设置title
        //mQMUITopBar.setTitle("首页");
        //声明LocationClient类
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        //权限申请
        permissionVersion();

        //recyclerView配置 模拟数据
        List<HomePlanListBean> listBeans = new ArrayList<>();
        HomePlanTypeOne homePlanTypeOne1 = new HomePlanTypeOne(R.drawable.fragment_home_ring_img_one,"今日步数","5200",HomePlanRecyclerViewAdapter.TYPE_ONE);
        HomePlanTypeOne homePlanTypeOne2 = new HomePlanTypeOne(R.drawable.fragment_home_ring_img_two,"全天心率","86/分",HomePlanRecyclerViewAdapter.TYPE_ONE);
        HomePlanTypeTwo homePlanTypeTwo = new HomePlanTypeTwo(R.drawable.fragment_home_ring_img_three,"今日提醒","久坐提醒，康复训练提醒",HomePlanRecyclerViewAdapter.TYPE_TWO);
        List<String> stringList = new ArrayList<>();
        stringList.add("贴墙站");
        stringList.add("静态飞鸟");
        stringList.add("健身球右侧弯");
        HomePlanTypeThree homePlanTypeThree = new HomePlanTypeThree(R.drawable.fragment_home_ring_img_four,"训练打卡",stringList,HomePlanRecyclerViewAdapter.TYPE_THREE);
        listBeans.add(homePlanTypeOne1);
        listBeans.add(homePlanTypeOne2);
        listBeans.add(homePlanTypeTwo);
        listBeans.add(homePlanTypeThree);
        HomePlanRecyclerViewAdapter homePlanRecyclerViewAdapter = new HomePlanRecyclerViewAdapter(listBeans, this.mActivity);
        fragmentHomeRecyclerView.setAdapter(homePlanRecyclerViewAdapter);
        fragmentHomeRecyclerView.setLayoutManager(new LinearLayoutManager(this.mActivity));

    }

    @Override
    protected void initListener() {
        LogUtils.i(this, "init Listener ing----");
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        //switch监听

    }

    /**
     * 权限判断
     */
    private void permissionVersion() {
        LogUtils.i(this, "permission version ing----");
        //判断是否有权限，没有就要动态申请权限
        if (!EasyPermissions.hasPermissions(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            //没有权限,动态申请权限
            permissionsRequest();
        } else {
            //有权限
            startLocation();
        }
    }

    /**
     * 申请权限,使用EasyPermissions框架来申请权限
     * code是指请求码
     */
    @AfterPermissionGranted(requestCode)
    private void permissionsRequest() {
        LogUtils.i(this, "permission request ing----");
        EasyPermissions.requestPermissions(this, "使用本应用需要打开定位权限", requestCode, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    /**
     * 配置定位信息，并打开定位功能
     */
    private void startLocation() {
        LogUtils.i(this, "start location ing----");
        mOption = new LocationClientOption();
        mOption.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mOption.setNeedNewVersionRgc(true);
        //可选，设置是否需要最新版本的地址信息。默认需要，即参数为true

        mLocationClient.setLocOption(mOption);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        mLocationClient.start();
        //mLocationClient为第二步初始化过的LocationClient对象
        //调用LocationClient的start()方法，便可发起定位请求
    }

    /**
     * 定义定位监听信息，并处理返回的定位信息
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            LogUtils.i(this, "get location ing----");
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            fragmentHomeLocationText.setText(city);
            //获得天气
            setWeather(city);
            //获取错误码
            //LogUtils.e(this,"error Location code is:"+location.getLocType());
        }
    }

    /**
     * 根据地区获取天气
     * @param city
     */
    private void setWeather(String city) {
        Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://devapi.heweather.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api = retrofit.create(Api.class);
                Call task = api.getWeatherJson();
                task.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        LogUtils.i(getActivity(),"response -> "+response);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        LogUtils.i(getActivity(),"error -> "+t.toString());
                    }
                });
    }

    /**
     * 使用权限申请复写方法
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将结果转发给EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 申请权限成功
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        startLocation();
    }

    /**
     * 申请被拒绝
     *
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtils.showShortToast(this.getActivity(), "权限被拒绝了哭哭");
        //若是在权限弹窗中，用户勾选了'不在提示'，且拒绝权限。
        //这时候，需要跳转到设置界面去，让用户手动开启。
        if (EasyPermissions.somePermissionPermanentlyDenied(this.getActivity(), perms)) {
            new AppSettingsDialog.Builder(this.getActivity()).build().show();
        }
    }
}
