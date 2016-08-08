package com.example.administrator.githupanddroid.httputils;

import com.example.administrator.githupanddroid.Repoinfo.model.RepoContentResult;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersUpToal;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.RepoResult;
import com.example.administrator.githupanddroid.login.model.AccessTokenResult;
import com.example.administrator.githupanddroid.login.model.User;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    //搜索热门开发者
    @GET("/search/users")
    Call<UsersUpToal> searchCoder(
            @Query("q")String types ,
            @Query("page")int page,
            @Query("order") String type
    );

    String utl="https://api.github.com/search/users?q=followers:>0&page=1&order=desc";
    @GET("users/{use}")
    Call<UsersInfo> getUserinfom(
            @Path("use") String loginname
    );

    /***
     * 获取readme
     * @param owner 仓库拥有者
     * @param repo 仓库名称
     * @return 仓库的readme页面内容,将是markdown格式且做了base64处理
     */
    @GET("/repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(
            @Path("owner") String owner,
            @Path("repo") String repo);

    /***
     * 获取一个markdonw内容对应的HTML页面
     *
     * @param body 请求体,内容来自getReadme后的RepoContentResult
     */
    @Headers({
            "Content-Type:text/plain"
    })
    @GET("/markdown/raw")
    Call<ResponseBody> markDown(
            @Body RequestBody body
    );


}
