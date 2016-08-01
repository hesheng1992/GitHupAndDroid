package com.example.administrator.githupanddroid.hotvirepagerfragment.viewfragment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.githupanddroid.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-07-28.
 */

public class FooterView extends FrameLayout {
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.tv_complete)
    TextView tvComplete;
    @Bind(R.id.tv_error)
    TextView tvError;

    private static final int STATE_LOADING=0;
    private static final int STATE_LOADCOMPELE=1;
    private static final int STATE_ERROR=2;
    private int state=STATE_LOADING;


    public FooterView(Context context) {
        super(context);
        initview();

    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initview() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_load_footer, this, true);
        ButterKnife.bind(this);

    }
    public void showLoading(){

        progressBar.setVisibility(View.VISIBLE);
        state=STATE_LOADING;
        tvComplete.setVisibility(View.GONE);
        tvError.setVisibility(View.GONE);
    }

    public void showCommpele(){
        progressBar.setVisibility(View.GONE);
        state=STATE_LOADCOMPELE;
        tvComplete.setVisibility(View.VISIBLE);
        tvError.setVisibility(View.GONE);

    }

    public void showError(String msg){
        progressBar.setVisibility(View.GONE);
        state=STATE_ERROR;
        tvComplete.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }

    //是否正在加载
    public boolean isLoding(){
        return state==STATE_LOADING;

    }
    //是否加载完成
    public boolean loadCommpelet(){
        return state==STATE_LOADCOMPELE;
    }

    //设置加载错误的监听
    public void setErrorListener(OnClickListener onClickListener){
        tvError.setOnClickListener(onClickListener);
    }



}
