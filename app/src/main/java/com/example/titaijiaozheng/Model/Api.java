package com.example.titaijiaozheng.Model;

import com.example.titaijiaozheng.Model.mainBean.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * retrofit连接后台接口
 */
public interface Api {
    //获取实况天气信息
    @GET("v7/weather/now?location=guangdong&key=2f7f6b5c2fef47c287166d9a8d894898")
    Call<WeatherResponse> getWeatherJson();
}
