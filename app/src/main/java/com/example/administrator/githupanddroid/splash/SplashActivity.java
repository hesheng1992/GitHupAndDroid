package com.example.administrator.githupanddroid.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.githupanddroid.MainActivity;
import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.fragment.SplashFragment;
import com.example.administrator.githupanddroid.login.view.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016-07-26.
 */

public class SplashActivity extends AppCompatActivity {


//    @Bind(R.id.splashPagerFragment)
    private SplashFragment splashPagerFragment;
    @Bind(R.id.btnLogin)
    Button btnLogin;
    @Bind(R.id.btnEnter)
    Button btnEnter;
    @Bind(R.id.buttonBar)
    LinearLayout buttonBar;
    private LinearLayout content;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        content= (LinearLayout) findViewById(R.id.content);
        splashPagerFragment=new SplashFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,splashPagerFragment).commit();



    }

    @OnClick({R.id.btnLogin, R.id.btnEnter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent intent1=new Intent(this, LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.btnEnter:
                Intent intent=new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }
}
