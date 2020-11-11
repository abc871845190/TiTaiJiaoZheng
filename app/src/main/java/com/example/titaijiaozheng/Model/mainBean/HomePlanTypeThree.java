package com.example.titaijiaozheng.Model.mainBean;

import java.util.List;
import java.util.Map;

public class HomePlanTypeThree extends HomePlanListBean {

    //type = 3
    int img;
    String dataName;
    List<String> mMapList;

    public HomePlanTypeThree(int img, String dataName, List<String> mapList, int dataType) {
        super(dataType);
        this.img = img;
        this.dataName = dataName;
        mMapList = mapList;
    }

    public List<String> getMapList() {
        return mMapList;
    }

    public void setMapList(List<String> mapList) {
        mMapList = mapList;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


}


