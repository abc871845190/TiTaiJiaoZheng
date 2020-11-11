package com.example.titaijiaozheng.Model.mainBean;

public class HomePlanTypeOne extends HomePlanListBean {
    //type = 1
    int img;
    String dataName;
    String dataValue;

    public HomePlanTypeOne(int img, String dataName, String dataValue, int dataType) {
        super(dataType);
        this.img = img;
        this.dataName = dataName;
        this.dataValue = dataValue;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }
}
