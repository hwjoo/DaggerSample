package com.hwjoo.daggersample.di.components;

import com.hwjoo.daggersample.MainActivity;
import com.hwjoo.daggersample.di.modules.GitHubModule;
import com.hwjoo.daggersample.di.scopes.UserScope;

import dagger.Component;

import static android.os.Build.VERSION_CODES.N;

/**
 * Created by hwjoo on 2017-02-02.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = GitHubModule.class)
public interface GitHubComponent {
    void inject(MainActivity activity);
}
