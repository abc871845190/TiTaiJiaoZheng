package com.example.titaijiaozheng.Model.mainBean;

public class DataRatingBean {
    //评分指标
    private String standard;
    //标准值
    private int defaultStandard;
    //评分值
    private float standardValue;
    //数值
    private String value;
    //type
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public DataRatingBean(String standard, int defaultStandard, float standardValue, String value, int type) {
        this.standard = standard;
        this.defaultStandard = defaultStandard;
        this.standardValue = standardValue;
        this.value = value;
        this.type = type;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public int getDefaultStandard() {
        return defaultStandard;
    }

    public void setDefaultStandard(int defaultStandard) {
        this.defaultStandard = defaultStandard;
    }

    public float getStandardValue() {
        return standardValue;
    }

    public void setStandardValue(float standardValue) {
        this.standardValue = standardValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
