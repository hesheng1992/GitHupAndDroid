package com.example.administrator.githupanddroid.login.presenter;

import android.media.MediaRouter;

import com.example.administrator.githupanddroid.httputils.GitHupApi;
import com.example.administrator.githupanddroid.httputils.OkhttpUtils;
import com.example.administrator.githupanddroid.login.model.AccessTokenResult;
import com.example.administrator.githupanddroid.login.model.User;
import com.example.administrator.githupanddroid.login.model.UserRepo;

import java.io.IOException;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016-07-30.
 */

public class LoginPresenter {
    private LoginView loginView;
    private Call<AccessTokenResult> tokencall;
    private Call<User> userCall;

    public LoginPresenter(LoginView loginView){
        this.loginView=loginView;
    }

    //将webview联网授权成功后，返回的code码拿到这里换token，在用token换用户信息。
    public void login(String code){
        if (tokencall!=null){
            tokencall.cancel();
        }
        tokencall= OkhttpUtils.getOkhttpUtils().getOAuthToken(GitHupApi.githupapplictionID
        ,GitHupApi.getGithupapplictionSecret,code
                );
        tokencall.enqueue(tokenCallBack);

    }

    private Callback<AccessTokenResult> tokenCallBack=new Callback<AccessTokenResult>() {
        @Override
        public void onResponse(Call<AccessTokenResult> call, Response<AccessTokenResult> response) {
            //得到响应体，返回的是封装的实体，其中就有token
            AccessTokenResult accessTokenResult=response.body();
            //得到token
            String accessToken = accessTokenResult.getAccessToken();
            //通过静态的方法缓存tonken
            UserRepo.setAccessToken(accessToken);
            //再将token换用户信息
            if (userCall!=null){
                userCall.cancel();
            }
            userCall=OkhttpUtils.getOkhttpUtils().getUserInfo();
            userCall.enqueue(userCallback);
        }

        @Override
        public void onFailure(Call<AccessTokenResult> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWebo();
        }
    };

    private Callback<User> userCallback=new Callback<User>() {
        @Override
        public void onResponse(Call<User> call, Response<User> response) {
            User user=response.body();
            UserRepo.setUser(user);
            loginView.showMessage("登陆成功");
            loginView.navigateToMain();
        }

        @Override
        public void onFailure(Call<User> call, Throwable t) {
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWebo();
        }
    };

}
