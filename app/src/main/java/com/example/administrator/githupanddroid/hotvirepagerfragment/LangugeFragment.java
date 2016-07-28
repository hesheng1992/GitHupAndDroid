package com.example.administrator.githupanddroid.hotvirepagerfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.TestP;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016-07-27.
 *
 * Model:       负责数据的检索,持久化等操作

 View:         负责UI的绘制和用户的交互

 Presenter: 作为Model和View的中间协调部分,负责两者之间的业务逻辑处理
 *
 */

public class LangugeFragment extends Fragment {
    @Bind(R.id.lvRepos)
    ListView listView;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrFrameLayout;
    @Bind(R.id.emptyView)
    TextView emptyView;
    @Bind(R.id.errorView)
    TextView errorView;

    private TestP testP;

    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);
        initdata();
        return view;
}

    private void initdata() {
        testP=new TestP(this);

        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,new ArrayList<String>());
        listView.setAdapter(adapter);
        // 使用当前对象做为key，来记录上一次的刷新时间,如果两次下拉太近，将不会触发新刷新
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
        // 关闭header所用时长
        ptrFrameLayout.setDurationToCloseHeader(2000);

        //下拉刷新的监听
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                testP.refresh();
            }
        });

        //下拉刷新的设置封装类
        StoreHouseHeader houseHeader=new StoreHouseHeader(getContext());
        //修改下拉刷新的样式
        ptrFrameLayout.setHeaderView(houseHeader);
        ptrFrameLayout.addPtrUIHandler(houseHeader);
        ptrFrameLayout.setBackgroundResource(R.color.colorRefresh);
        houseHeader.initWithString("I LIKE ");
        houseHeader.setPadding(0,50,0,50);



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //用来显示是否得到数据，然后这个页面显示对应的内容
    public void showContentView() {
        ptrFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    public void showErrorView(String errorMsg) {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    public void showEmptyView() {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    //当数据刷新，停止刷新的方法
    public void stopRefresh(){
        ptrFrameLayout.refreshComplete();

    }
    //当在p层分线程处理完后得到的数据，传递给这个页面的适配器加载数据
    public void getdata(ArrayList<String> data){
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }


}
