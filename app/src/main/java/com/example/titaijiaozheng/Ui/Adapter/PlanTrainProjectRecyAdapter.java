package com.example.titaijiaozheng.Ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.example.titaijiaozheng.Model.mainBean.PlanTrainProjectBean;
import com.example.titaijiaozheng.R;
import com.example.titaijiaozheng.Ui.Activity.PlanVideoActivity;
import com.shuyu.gsyvideoplayer.GSYVideoBaseManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.video.GSYSampleADVideoPlayer;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wseemann.media.FFmpegMediaMetadataRetriever;

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
        String video = null;
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

        //视频绑定
        holder.onBind(position,mPlanTrainProjectBeans.get(position));
    }

    private SpannableString addTextClickableSpan(String more, String video, String desc, int flag) {
        //处理text
        SpannableString spannableString = new SpannableString(desc + more + "    ");
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //TODO:查看更多activity跳转
            }
        };
//        ClickableSpan clickableSpan2 = new ClickableSpan() {
//            @Override
//            public void onClick(@NonNull View widget) {
//                //跳转到视频界面
//                Intent intent = new Intent();
//                intent.setClass(mContext, PlanVideoActivity.class);
//                intent.putExtra("flag", flag);
//                mContext.startActivity(intent);
//            }
//        };
        //设置监听
        spannableString.setSpan(clickableSpan1, desc.length(), desc.length() + more.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        //spannableString.setSpan(clickableSpan2, desc.length() + more.length() + 4, desc.length() + more.length() + video.length() + 4, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public int getItemCount() {
        return mPlanTrainProjectBeans.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "InnerHolder";
        private final TextView mTitle;
        private final TextView mSubtitle;
        private final StandardGSYVideoPlayer mGsyVideoPlayer;
        ImageView imageView;
        GSYVideoOptionBuilder gsyVideoOptionBuilder;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.fragment_plan_recyclerView_trainProject_item_title);
            mSubtitle = itemView.findViewById(R.id.fragment_plan_recyclerView_trainProject_item_subtitle);
            mGsyVideoPlayer = itemView.findViewById(R.id.fragment_plan_recyclerView_trainProject_item_player);
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(R.mipmap.img1);
            gsyVideoOptionBuilder = new GSYVideoOptionBuilder();
        }

        public void onBind(final int position, PlanTrainProjectBean planTrainProjectBean) {
            String title = planTrainProjectBean.getTrainName();
            String url = planTrainProjectBean.getVideoUrl();
            Map<String, String> header = new HashMap<>();
            header.put("ee", "33");
            //防止错位，离开释放
            mGsyVideoPlayer.initUIState();
            gsyVideoOptionBuilder
                    .setIsTouchWiget(false)
                    .setThumbImageView(imageView)
                    .setUrl(url)
                    .setVideoTitle(title)
                    .setCacheWithPlay(false)
                    .setRotateViewAuto(true)
                    .setLockLand(false)
                    .setPlayTag(TAG)
                    .setMapHeadData(header)
                    .setShowFullAnimation(true)
                    .setNeedLockFull(true)
                    .setPlayPosition(position)
                    .setAutoFullWithSize(false)
                    .setVideoAllCallBack(new GSYSampleCallBack() {
                        //加载成功，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                        @Override
                        public void onPrepared(String url, Object... objects) {
                            super.onPrepared(url, objects);
                            //如果不是全屏
                            if (!mGsyVideoPlayer.isIfCurrentIsFullscreen()) {
                                //静音
                                GSYVideoManager.instance().setNeedMute(true);
                            }
                        }

                        //退出全屏，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
                        @Override
                        public void onQuitFullscreen(String url, Object... objects) {
                            super.onQuitFullscreen(url, objects);
                            //全屏不静音 退出全屏静音
                            GSYVideoManager.instance().setNeedMute(true);
                        }

                        @Override
                        public void onEnterFullscreen(String url, Object... objects) {
                            super.onEnterFullscreen(url, objects);
                            GSYVideoManager.instance().setNeedMute(false);
                            mGsyVideoPlayer.getCurrentPlayer().getTitleTextView().setText((String) objects[0]);
                        }
                    }).build(mGsyVideoPlayer);

            //增加title
            mGsyVideoPlayer.getTitleTextView().setVisibility(View.GONE);

            //设置返回键 不可见
            mGsyVideoPlayer.getBackButton().setVisibility(View.INVISIBLE);

            //设置全屏按键功能
            mGsyVideoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resolveFullBtn(mGsyVideoPlayer);
                }
            });
        }

        /**
         * 全屏幕按键处理
         */
        private void resolveFullBtn(final StandardGSYVideoPlayer standardGSYVideoPlayer) {
            standardGSYVideoPlayer.startWindowFullscreen(mContext, true, true);
        }
    }

    //回调接口
    public void setOnItemListener(setOnItemListener onItemListener) {
        mSetOnItemListener = onItemListener;
    }

    //加一个bean
    public void loadMoreData(PlanTrainProjectBean planTrainProjectBean) {
        this.mPlanTrainProjectBeans.add(planTrainProjectBean);
        notifyDataSetChanged();
    }

    public List<PlanTrainProjectBean> getPlanTrainProjectBeans(){
        return mPlanTrainProjectBeans;
    }
}
