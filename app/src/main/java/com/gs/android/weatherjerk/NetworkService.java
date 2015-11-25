package com.gs.android.weatherjerk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;

public class NetworkService extends Service {

    private final NetworkServiceBinder mBinder;

    public NetworkService() {
        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        WeatherRestService weatherRestService = retrofit.create(WeatherRestService.class);

        mBinder = new NetworkServiceBinder(weatherRestService);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
