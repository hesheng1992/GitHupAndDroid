package com.example.administrator.githupanddroid.httputils;

import com.example.administrator.githupanddroid.Repoinfo.model.RepoContentResult;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersUpToal;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.RepoResult;
import com.example.administrator.githupanddroid.login.model.AccessTokenResult;
import com.example.administrator.githupanddroid.login.model.User;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016-07-29.
 */

public class OkhttpUtils implements GitHupApi {
    private GitHupApi gitapi;

    private static OkhttpUtils okhttpUtils;
    private OkhttpUtils(){

        //创建okhttp的实例
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // 添加token拦截器
                .addInterceptor(new TokenInterceptor())
                .build();
        //
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //创建GitHupApi的实例，通过retrofit，后面的参数是接口的实现类。
        gitapi=retrofit.create(GitHupApi.class);
    }



    public static OkhttpUtils getOkhttpUtils(){
        if (okhttpUtils==null){
            okhttpUtils=new OkhttpUtils();
        }
        return okhttpUtils;
    }

    @Override
    public Call<AccessTokenResult> getOAuthToken(@Field("client_id") String client, @Field("client_secret") String clientSecret, @Field("code") String code) {
        return gitapi.getOAuthToken(client,clientSecret,code);
    }

    @Override
    public Call<User> getUserInfo() {
        return gitapi.getUserInfo();
    }

    @Override
    public Call<RepoResult> searchRepos(@Query("q") String query, @Query("page") int pageId) {

        return gitapi.searchRepos(query,pageId);
    }

    @Override
    public Call<UsersUpToal> searchCoder(@Query("q") String types, @Query("page") int page, @Query("order") String type) {


        return gitapi.searchCoder(types,page,type);
    }

    @Override
    public Call<UsersInfo> getUserinfom(@Path("use") String loginname) {
        return gitapi.getUserinfom(loginname);
    }

    @Override
    public Call<RepoContentResult> getReadme(@Path("owner") String owner, @Path("repo") String repo) {
        return gitapi.getReadme(owner,repo);
    }

    @Override
    public Call<ResponseBody> markDown(@Body RequestBody body) {
        return gitapi.markDown(body);
    }


}
