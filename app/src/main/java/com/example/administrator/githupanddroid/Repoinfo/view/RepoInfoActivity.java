package com.example.administrator.githupanddroid.Repoinfo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.githupanddroid.MainActivity;
import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.Repoinfo.presenter.RepoInfoPresenter;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016-08-03.
 */

public class RepoInfoActivity extends AppCompatActivity implements RepoInfoPresenter.RepoInfoApi{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ivIcon)
    ImageView ivIcon;
    @Bind(R.id.tvRepoName)
    TextView tvRepoName;
    @Bind(R.id.tvRepoStars)
    TextView tvRepoStars;
    @Bind(R.id.tvRepoInfo)
    TextView tvRepoInfo;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    ItemsBean key_repo;
    private RepoInfoPresenter presenter;

    public static void open(Context context,@NonNull ItemsBean itemsBean){
        Intent intent=new Intent(context,RepoInfoActivity.class);
        intent.putExtra("key_repo",itemsBean);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_info);
        ButterKnife.bind(this);
        presenter=new RepoInfoPresenter(this);
        key_repo = (ItemsBean) getIntent().getSerializableExtra("key_repo");

        presenter.getReadme(key_repo);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RepoInfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(key_repo.getName());
        tvRepoName.setText(key_repo.getFull_name());
        tvRepoStars.setText(String.format("start: %d  fork: %d",key_repo.getStargazers_count(),key_repo.getForks_count()));
        tvRepoInfo.setText(key_repo.getDescription());
        ImageLoader.getInstance().displayImage(key_repo.getOwner().getAvatar(),ivIcon);

    }

    @Override
    public void showProgressbar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(String html) {
        //webview不止可以加载url，还可以加载数据
        webView.loadData(html,"text/html","UTF-8");
    }
}
