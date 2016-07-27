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

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by Administrator on 2016-07-27.
 */

public class LangugeFragment extends Fragment {
    @Bind(R.id.lvRepos)
    ListView listView;
    @Bind(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @Bind(R.id.emptyView)
    TextView emptyView;
    @Bind(R.id.errorView)
    TextView errorView;

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
        String []data={"这是最热门里面的各种语言的数据显示"};
        adapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,data);
        listView.setAdapter(adapter);
        //下拉刷新的设置封装类
        StoreHouseHeader houseHeader=new StoreHouseHeader(getContext());
        houseHeader.initWithString("I LIKE ");
        houseHeader.setPadding(0,50,0,50);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
