package com.gs.android.weatherjerk;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            NetworkServiceBinder binder = (NetworkServiceBinder) service;
            WeatherRestService weatherRestService = binder.getWeatherRestService();

            mController = new MainController(weatherRestService);
            mController.bind(MainActivity.this);
        }

        @Override public void onServiceDisconnected(final ComponentName name) {
            mController.unbind(MainActivity.this);
        }
    };

    private MainController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, NetworkService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    @Override protected void onStop() {
        super.onStop();

        unbindService(mConnection);
    }
}
