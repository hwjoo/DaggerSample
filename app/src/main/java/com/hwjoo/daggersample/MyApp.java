package com.hwjoo.daggersample;

import android.app.Application;

import com.hwjoo.daggersample.di.components.DaggerGitHubComponent;
import com.hwjoo.daggersample.di.components.DaggerNetComponent;
import com.hwjoo.daggersample.di.components.GitHubComponent;
import com.hwjoo.daggersample.di.components.NetComponent;
import com.hwjoo.daggersample.di.modules.AppModule;
import com.hwjoo.daggersample.di.modules.GitHubModule;
import com.hwjoo.daggersample.di.modules.NetModule;

/**
 * Created by hwjoo on 2017-02-02.
 */

public class MyApp extends Application {
    private NetComponent mNetComponent;
    private GitHubComponent mGitHubComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("http://api.github.com"))
                .build();
        mGitHubComponent = DaggerGitHubComponent.builder()
                .netComponent(mNetComponent)
                .gitHubModule(new GitHubModule())
                .build();

    }

    public NetComponent getmNetComponent() {
        return mNetComponent;
    }
    public GitHubComponent getmGitHubComponent() {
        return mGitHubComponent;
    }
}
