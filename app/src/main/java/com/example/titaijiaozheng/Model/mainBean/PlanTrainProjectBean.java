package com.example.titaijiaozheng.Model.mainBean;

/**
 * 日程训练项目实体类
 */
public class PlanTrainProjectBean {
    String TrainName;
    String TrainDesc;
    int TrainFlag;

    public PlanTrainProjectBean(String trainName, String trainDesc, int trainFlag) {
        TrainName = trainName;
        TrainDesc = trainDesc;
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
}
