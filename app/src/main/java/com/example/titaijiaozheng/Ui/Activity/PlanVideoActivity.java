package com.example.titaijiaozheng.Ui.Activity;

import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.example.titaijiaozheng.Base.BaseActivity;
import com.example.titaijiaozheng.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import butterknife.BindView;

/**
 * 视频activity
 */
public class PlanVideoActivity extends BaseActivity {

    @BindView(R.id.activity_plan_video)
    StandardGSYVideoPlayer activityPlanVideo;
    private OrientationUtils mOrientationUtils;
    private boolean isPlay;
    private boolean isLock;
    private boolean isPause;

    @Override
    protected int setActivityLayout() {
        return R.layout.activity_plan_video;
    }

    @Override
    protected void initData() {
        String[] urls = {"https://3070180753@qq.com:ans5kgf23i9b4s3h@dav.jianguoyun.com/dav/%E6%88%91%E7%9A%84%E5%9D%9A%E6%9E%9C%E4%BA%91/%E5%BA%B7%E5%A4%8D%E8%A7%86%E9%A2%912.mp4", "https://3070180753@qq.com:ans5kgf23i9b4s3h@dav.jianguoyun.com/dav/%E6%88%91%E7%9A%84%E5%9D%9A%E6%9E%9C%E4%BA%91/%E5%BA%B7%E5%A4%8D%E8%A7%86%E9%A2%911.mp4"};

        //添加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.img1);

        //添加title
        activityPlanVideo.getTitleTextView().setVisibility(View.GONE);
        activityPlanVideo.getBackButton().setVisibility(View.GONE);

        //外部辅助旋转。帮助全屏
        mOrientationUtils = new OrientationUtils(this, activityPlanVideo);
        //初始化不打开外部旋转
        mOrientationUtils.setEnable(false);

        GSYVideoOptionBuilder gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        gsyVideoOptionBuilder.setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(urls[0])
                .setCacheWithPlay(false)
                .setVideoTitle("视频1")
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放才可以选择和全屏
                        mOrientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        if (mOrientationUtils != null) {
                            mOrientationUtils.backToProtVideo();
                        }
                    }

                }).setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (mOrientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    mOrientationUtils.setEnable(!lock);
                }
            }
        }).build(activityPlanVideo);

        activityPlanVideo.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                mOrientationUtils.resolveByClick();
                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                activityPlanVideo.startWindowFullscreen(PlanVideoActivity.this, true, true);
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {
        if (mOrientationUtils != null) {
            mOrientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        activityPlanVideo.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isPlay) {
            activityPlanVideo.getCurrentPlayer().release();
        }
        if (mOrientationUtils != null) {
            mOrientationUtils.releaseListener();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay&&isPause){
            activityPlanVideo.onConfigurationChanged(this,newConfig,mOrientationUtils,true,true);
        }

    }
}
