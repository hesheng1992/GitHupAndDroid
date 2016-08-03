package com.example.administrator.githupanddroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.administrator.githupanddroid.hotvirepagerfragment.HotReponFragment;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.view.HotCoderFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.navigationView)
    NavigationView navigationView;
    @Bind(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    private HotReponFragment fragment;
    private HotCoderFragment hotCoderFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("GitAndroidHup");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();
//        drawerLayout.addDrawerListener(toggle);
//        toggle.setDrawerIndicatorEnabled(true);
        navigationView.setNavigationItemSelectedListener(itemListener);
        fragment=new HotReponFragment();
        initshowFragment(fragment);

    }

    private void initshowFragment(Fragment fragment) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.commit();
    }

    //抽屉里的Navigation菜单的监听
    private NavigationView.OnNavigationItemSelectedListener itemListener=new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.github_hot_repo:
                    fragment=new HotReponFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                    break;
                case R.id.github_hot_coder:
                    hotCoderFragment=new HotCoderFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,hotCoderFragment).commit();
                    break;
            }


            return true;
        }
    };


}
