package com.example.administrator.githupanddroid.login.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.githupanddroid.MainActivity;
import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.httputils.GitHupApi;
import com.example.administrator.githupanddroid.login.presenter.LoginPresenter;
import com.example.administrator.githupanddroid.login.presenter.LoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;

/**
 * Created by Administrator on 2016-07-29.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.gifImageView)
    GifImageView gifImageView;
    private LoginPresenter presenterl;
    WebChromeClient chromeClient=new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress>=100){
                gifImageView.setVisibility(View.GONE);
            }
        }
    };

    WebViewClient webViewClient=new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri=Uri.parse(url);
            if(GitHupApi.CALL_BACK.equals(uri.getScheme())){
                String code = uri.getQueryParameter("code");
                //用code做登录操作
                presenterl.login(code);
                return true;
            }


            return super.shouldOverrideUrlLoading(view, url);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("TAG","jinru");
        ButterKnife.bind(this);
//        webView.loadUrl();
        presenterl=new LoginPresenter(this);
        initview();


    }

    private void initview() {
        // 删除所有的Cookie,主要为了清除以前的登陆记录
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        // 授权登陆URL
        Log.e("TAG","denglu");
        webView.loadUrl(GitHupApi.AUTH_RUL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        // 主要为了监听进度
        webView.setWebChromeClient(chromeClient);
        // 监听webview(url会刷新的)
        webView.setWebViewClient(webViewClient);

    }

    @Override
    public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void resetWebo() {
        //当输入密码错误的时候，重新执行webview
        initview();
    }

    @Override
    public void navigateToMain() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
