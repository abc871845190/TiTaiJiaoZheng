package com.example.titaijiaozheng.Model.mainBean;

/**
 * 日程训练项目实体类
 */
public class PlanTrainProjectBean {
    String TrainName;
    String TrainDesc;
    String VideoUrl;
    int TrainFlag;

    public PlanTrainProjectBean(String trainName, String trainDesc, String videoUrl, int trainFlag) {
        TrainName = trainName;
        TrainDesc = trainDesc;
        VideoUrl = videoUrl;
        TrainFlag = trainFlag;
    }

    public String getTrainName() {
        return TrainName;
    }

    public void setTrainName(String trainName) {
        TrainName = trainName;
    }

    public String getTrainDesc() {
        return TrainDesc;
    }

    public void setTrainDesc(String trainDesc) {
        TrainDesc = trainDesc;
    }

    public int getTrainFlag() {
        return TrainFlag;
    }

    public void setTrainFlag(int trainFlag) {
        TrainFlag = trainFlag;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }
}
