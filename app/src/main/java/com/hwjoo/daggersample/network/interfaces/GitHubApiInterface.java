package com.hwjoo.daggersample.network.interfaces;

import com.hwjoo.daggersample.models.Repository;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by hwjoo on 2017-02-02.
 */

public interface GitHubApiInterface {
    @GET("/users/{user}/repos")
    Call<ArrayList<Repository>> getRepository(@Path("user") String userName);
}
