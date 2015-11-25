package com.gs.android.weatherjerk;

import android.os.Binder;

public class NetworkServiceBinder extends Binder {
    private final WeatherRestService mWeatherRestService;

    public NetworkServiceBinder(final WeatherRestService weatherRestService) {
        mWeatherRestService = weatherRestService;
    }

    public WeatherRestService getWeatherRestService() {
        return mWeatherRestService;
    }
}
