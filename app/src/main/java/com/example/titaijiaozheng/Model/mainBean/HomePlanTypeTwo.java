package com.example.titaijiaozheng.Model.mainBean;

public class HomePlanTypeTwo extends HomePlanListBean {
    //type = 2
    int img;
    String dataName;
    String subTitle;  //副标题

    public HomePlanTypeTwo(int img, String dataName, String subTitle, int dataType) {
        super(dataType);
        this.img = img;
        this.dataName = dataName;
        this.subTitle = subTitle;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
