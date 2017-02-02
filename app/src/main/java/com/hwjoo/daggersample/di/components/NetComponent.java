package com.hwjoo.daggersample.di.components;

import android.content.SharedPreferences;

import com.hwjoo.daggersample.di.modules.AppModule;
import com.hwjoo.daggersample.di.modules.NetModule;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;
import retrofit.Retrofit;

/**
 * Created by hwjoo on 2017-02-02.
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    Retrofit retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
}
