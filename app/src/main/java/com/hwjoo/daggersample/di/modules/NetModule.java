package com.hwjoo.daggersample.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by hwjoo on 2017-02-02.
 */

@Module
public class NetModule {
    String mBaseUrl;

    public NetModule(String baseUrl){
        this.mBaseUrl = baseUrl;
    }
    @Singleton
    @Provides
    SharedPreferences providesSharedPreferences(Application application){
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
    @Singleton
    @Provides
    Cache provideOkHttpCache(Application application){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }
    @Singleton
    @Provides
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }
    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache){
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(cache);
        return okHttpClient;
    }
    @Singleton
    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }
}
