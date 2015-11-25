package com.gs.android.weatherjerk;


import com.gs.android.weatherjerk.dto.WeatherDto;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface WeatherRestService {
    @GET("weather") Call<WeatherDto> weather(@Query("APPID") String appId,
                                             @Query("lat") int lat,
                                             @Query("lon") int lon);

    @GET("weather") Call<WeatherDto> weather(@Query("APPID") String appId,
                                             @Query("q") String cityName);
}
