package com.hwjoo.daggersample;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hwjoo.daggersample.models.Repository;
import com.hwjoo.daggersample.network.interfaces.GitHubApiInterface;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static com.hwjoo.daggersample.R.id.fab;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.textView)
    TextView textView;

    @Inject
    SharedPreferences mSharedPreferences;
    @Inject
    Retrofit mRetrofit;
    @Inject
    GitHubApiInterface mGitHubApiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ((MyApp)getApplication()).getmGitHubComponent().inject(this);
    }

    @OnClick(R.id.fab)
    void fabOnclick(){
        Log.d("TEST", "onClick~!");
        Call<ArrayList<Repository>> call = mGitHubApiInterface.getRepository("hwjoo");
        call.enqueue(new Callback<ArrayList<Repository>>() {
            @Override
            public void onResponse(Response<ArrayList<Repository>> response, Retrofit retrofit) {
                if(response.isSuccess()) {
                    Log.d("TEST", "size : "+response.body().size());
                    Repository repository = response.body().get(0);
                    textView.setText("full name :"+repository.getFullName()+"\n"+
                            "name :"+repository.getName()+"\n"+
                            "description :"+repository.getDescription()+"\n");
                }else{
                    Log.d("TEST","Error : "+ String.valueOf(response.code()));
                    textView.setText(response.code());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("TEST","Failure : "+ t.getMessage());
                textView.setText(t.getMessage());
            }
        });

    }
}
