package com.gs.android.weatherjerk;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.gs.android.weatherjerk.dto.WeatherDto;
import com.gs.android.weatherjerk.dto.WeatherInfoDto;
import com.gs.android.weatherjerk.util.TemperatureUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public final class MainController {
    @Bind(R.id.btn_weather) protected View mWeatherButton;
    @Bind(R.id.text) protected TextView mWeatherText;

    private final WeatherRestService mRestService;

    public MainController(WeatherRestService restService) {
        mRestService = restService;
    }

    // FIXME[26/11/2015]: Has access to the activity
    public void bind(Activity activity) {
        ButterKnife.bind(this, activity);
    }

    public void unbind(Activity activity) {
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_weather) protected void onWeatherClick() {
        final String appId = "a71ddaf12cec1dd12d86edd3fe853b18";
        String cityName = "Yerevan";

        Call<WeatherDto> weatherCall = mRestService.weather(appId, cityName);
        weatherCall.enqueue(new Callback<WeatherDto>() {
            @Override
            public void onResponse(final Response<WeatherDto> response, final Retrofit retrofit) {
                WeatherInfoDto weatherInfo = response.body().main;
                String text = String.format("temp: %d°%npressure: %d%nhumidity: %d",
                        Math.round(TemperatureUtils.kelvinToCelsius(weatherInfo.temp)),
                        Math.round(weatherInfo.pressure),
                        Math.round(weatherInfo.humidity));
                mWeatherText.setText(text);
            }

            @Override public void onFailure(final Throwable t) {

            }
        });
    }
}
