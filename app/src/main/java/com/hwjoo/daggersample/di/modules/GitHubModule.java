package com.hwjoo.daggersample.di.modules;

import com.hwjoo.daggersample.di.scopes.UserScope;
import com.hwjoo.daggersample.network.interfaces.GitHubApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

/**
 * Created by hwjoo on 2017-02-02.
 */

@Module
public class GitHubModule {

    @UserScope
    @Provides
    public GitHubApiInterface provideGitHubInterface(Retrofit retrofit){
        return retrofit.create(GitHubApiInterface.class);
    }
}
