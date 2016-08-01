package com.example.administrator.githupanddroid.login.presenter;

/**
 * Created by Administrator on 2016-07-30.
 */

public interface LoginView {
    void showProgress();
    void showMessage(String msg);
    //重置webview
    void resetWebo();

    // 导航切换至Main页面
    void navigateToMain();

}
