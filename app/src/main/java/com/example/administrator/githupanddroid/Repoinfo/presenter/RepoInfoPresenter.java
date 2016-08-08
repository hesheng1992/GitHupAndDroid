package com.example.administrator.githupanddroid.Repoinfo.presenter;

import android.util.Base64;

import com.example.administrator.githupanddroid.Repoinfo.model.RepoContentResult;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;
import com.example.administrator.githupanddroid.httputils.OkhttpUtils;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016-08-03.
 */

public class RepoInfoPresenter {


    public interface RepoInfoApi{
        void showProgressbar();
        void hideProgressbar();
        void showMessage(String message);
        void setData(String html);
    }

    private RepoInfoApi repoInfoApi;
    private Call<RepoContentResult> contentResultCall;
    private Call<ResponseBody> responseBodyCall;

    public RepoInfoPresenter(RepoInfoApi repoInfoApi){
        this.repoInfoApi=repoInfoApi;
    }

    public void getReadme(ItemsBean itemsBean){
        repoInfoApi.showProgressbar();
        String name = itemsBean.getName();
        String login = itemsBean.getOwner().getLogin();
        if (contentResultCall!=null){
            contentResultCall.cancel();
        }
        contentResultCall= OkhttpUtils.getOkhttpUtils().getReadme(name,login);
        contentResultCall.enqueue(repoContentResultCallback);
    }

    private Callback<RepoContentResult> repoContentResultCallback=new Callback<RepoContentResult>() {
        @Override
        public void onResponse(Call<RepoContentResult> call, Response<RepoContentResult> response) {
            String content = response.body().getContent();
            // BASE64解码
            byte[] data = Base64.decode(content, Base64.DEFAULT);
            // 根据data获取到markdown（也就是readme文件）的HTML格式文件
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, data);
            if (responseBodyCall!=null){
                responseBodyCall.cancel();
            }
            responseBodyCall=OkhttpUtils.getOkhttpUtils().markDown(body);
            responseBodyCall.enqueue(bodyCallback);

        }

        @Override
        public void onFailure(Call<RepoContentResult> call, Throwable t) {
            repoInfoApi.hideProgressbar();
            repoInfoApi.showMessage(t.getMessage());
        }
    };

    private Callback<ResponseBody> bodyCallback=new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            repoInfoApi.hideProgressbar();
            String s = response.body().toString();
            repoInfoApi.setData(s);
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            repoInfoApi.hideProgressbar();
            repoInfoApi.showMessage(t.getMessage());
        }
    };


}
