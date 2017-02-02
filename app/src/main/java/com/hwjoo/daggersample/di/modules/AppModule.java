package com.hwjoo.daggersample.di.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hwjoo on 2017-02-02.
 */

@Module
public class AppModule {
    Application mApplication;

    public AppModule(Application application){
        this.mApplication = application;
    }

    @Singleton
    @Provides
    Application providesApplication(){
        return mApplication;
    }
}
