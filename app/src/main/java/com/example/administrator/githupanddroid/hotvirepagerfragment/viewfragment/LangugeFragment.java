package com.example.administrator.githupanddroid.hotvirepagerfragment.viewfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.adpater.HotListAdapter;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.Lugague;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter.AddLoadMore;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter.HotInterface;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter.PublicInterFace;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotpresenter.TestP;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

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

 Presenter: 作为Model和View的中间协调部分,负责两者之间的业务逻辑处理,将视图层收到的指令，系统命令分发给逻辑处理层。
 它的作用就是管理视图层和逻辑处理层之间的数据交换关系，将视图层发过来的指令进行判断在发给逻辑层处理在返回给
 presenter层，presenter在传给视图层。这样就隔离了视图层与逻辑处理层。没有之间的联系。
 *
 *
 * 利用接口，将方法抽到接口中，p层调用的是接口中的方法，接口的方法在这个页面的需要操作，在p层调用实现。
 */

public class LangugeFragment extends Fragment implements PublicInterFace{
    @Bind(R.id.lvRepos)
    ListView listView;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrFrameLayout;
    @Bind(R.id.emptyView)
    TextView emptyView;
    @Bind(R.id.errorView)
    TextView errorView;

    private TestP testP;
    private FooterView footerView;

    private HotListAdapter adapter;
    private List<ItemsBean> list;

    public static LangugeFragment getInstance(Lugague lugague){
        LangugeFragment fragment=new LangugeFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("key",lugague);
        fragment.setArguments(bundle);
        return fragment;
    }
    private Lugague getLanguge(){
        return (Lugague) getArguments().getSerializable("key");

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);
        initdata();
        return view;
}

    private void initdata() {
        testP=new TestP(this,getLanguge());
        footerView=new FooterView(getContext());

//        listView.addFooterView(footerView);
        list=new ArrayList<>();

        adapter=new HotListAdapter(list,getContext());
//        listView.addFooterView(footerView);
        listView.setAdapter(adapter);
//        testP.refresh();
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
        //第三方上拉加载监听
        Mugen.with(listView, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                testP.begianloadMore();
            }

            @Override
            public boolean isLoading() {
                return listView.getFooterViewsCount()>0&&footerView.isLoding();
            }

            @Override
            public boolean hasLoadedAllItems() {
                return listView.getFooterViewsCount() > 0 && footerView.loadCommpelet();
            }
        }).start();


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
    public void getdata(ArrayList<ItemsBean> data){
        this.list.clear();
        if (data!=null){
            this.list=data;
            Log.e("TAG","集合大小"+this.list.size());
            adapter=new HotListAdapter(list,getContext());
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
        }


    }



//加载更多
    //隐藏加载
    @Override
    public void hintloadMore() {
//        footerView.showCommpele();
        listView.removeFooterView(footerView);
    }

    //加载完成，显示数据
    @Override
    public void showData(List<ItemsBean> list) {
        this.list=list;
        adapter=new HotListAdapter(list,getContext());
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }

    @Override
    public void showError(String message) {
        if (listView.getFooterViewsCount() == 0) {
            listView.addFooterView(footerView);
        }
        footerView.showError(message);
        footerView.setErrorListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testP.begianloadMore();
            }
        });

    }

    //正在加载
    @Override
    public void loading() {
        if (listView.getFooterViewsCount()==0){
            Log.e("TAG","loading");
//            footerView=new FooterView(getContext());
            listView.addFooterView(footerView);
        }
        Log.e("TAG","zhengzaijiazai");
        footerView.showLoading();
    }

}
