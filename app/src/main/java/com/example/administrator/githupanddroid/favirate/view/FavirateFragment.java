package com.example.administrator.githupanddroid.favirate.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.favirate.dao.DBhelper;
import com.example.administrator.githupanddroid.favirate.dao.LocalRepoDao;
import com.example.administrator.githupanddroid.favirate.dao.RepoGroupDao;
import com.example.administrator.githupanddroid.favirate.model.RepoGroup;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016-08-04.
 */

public class FavirateFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    @Bind(R.id.tvGroupType)
    TextView tvGroupType;
    @Bind(R.id.btnFilter)
    ImageButton btnFilter;
    @Bind(R.id.listView)
    ListView listView;

    private RepoGroupDao repoGroupDao;
    private LocalRepoDao localRepoDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        initdata();
        return view;
    }

    private void initdata() {
        repoGroupDao = new RepoGroupDao(DBhelper.getdBhelper(getContext()));
        localRepoDao = new LocalRepoDao(DBhelper.getdBhelper(getContext()));


    }

    @OnClick(R.id.btnFilter)
    public void showpopMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.setOnMenuItemClickListener(this);
        //加载需要弹出的窗口布局菜单
        popupMenu.inflate(R.menu.menu_context_favorite);
        //得到菜单
        Menu menu = popupMenu.getMenu();
        //向菜单里添加我们自己想要添加的条目
        try {
            List<RepoGroup> repoGroups = repoGroupDao.queryForAll();
            for (RepoGroup repoGroup : repoGroups) {
                //想菜单添加我们的条目
                menu.add(Menu.NONE, repoGroup.getId(), Menu.NONE, repoGroup.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        popupMenu.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String s = menuItem.getTitle().toString();
        tvGroupType.setText(s);
        setData(menuItem.getGroupId());
        return true;
    }

    private void setData(int id){
        switch (id){
            case R.id.repo_group_all:
                break;
            case R.id.repo_group_no:
                break;

        }

    }

}
