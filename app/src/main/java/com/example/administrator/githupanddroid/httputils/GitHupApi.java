package com.example.administrator.githupanddroid.httputils;

import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.RepoResult;
import com.example.administrator.githupanddroid.login.model.AccessTokenResult;
import com.example.administrator.githupanddroid.login.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016-07-30.
 */

public interface GitHupApi {
    String githupapplictionID="2bf14779c6f03922c61a";
    String CALL_BACK=" ";
    String getGithupapplictionSecret="8dea07108b84ea5b07acd27deec59de4911ed1e6";
    String AUTH_SCOPE="user,public_repo,repo";
    String AUTH_RUL = "https://github.com/login/oauth/authorize?client_id=" + githupapplictionID + "&scope=" + AUTH_SCOPE;
//    获取访问令牌
    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    Call<AccessTokenResult> getOAuthToken(
            @Field("client_id") String client,
            @Field("client_secret") String clientSecret,
            @Field("code") String code
    );

    @GET("User")
    Call<User> getUserInfo();

    /**
     * 获取仓库
     * @Param query 查询参数(language:java)
     * @Param pageId 查询页数据(从1开始)
     */
    @GET("/search/repositories")
    Call<RepoResult> searchRepos(
            @Query("q")String query,
            @Query("page")int pageId);


}
