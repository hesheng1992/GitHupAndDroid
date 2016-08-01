package com.example.administrator.githupanddroid.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotmodel.ItemsBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016-07-28.
 */

public class HotListAdapter extends BaseAdapter {
    private List<ItemsBean> list;
    private Context context;
    public HotListAdapter(List<ItemsBean> list,Context context){
        this.list=list;
        this.context=context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ItemsBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       MyViewHolder holder;
        if (view==null){
            view=LayoutInflater.from(context).inflate(R.layout.fragment_repo_list,null);
            holder=new MyViewHolder(view);
            view.setTag(holder);
        }
        holder= (MyViewHolder) view.getTag();

        ItemsBean item = getItem(i);
        Log.e("TAG","item"+item);
        holder.tvRepoName.setText(item.getFull_name());
        holder.tvRepoInfo.setText(item.getDescription());
        holder.tvRepoStars.setText(item.getStargazers_count()+"");
        ImageLoader.getInstance().displayImage(item.getOwner().getAvatar(),holder.ivIcon);





        return view;
    }



    class MyViewHolder{
        ImageView ivIcon;
        TextView tvRepoName,tvRepoInfo,tvRepoStars;
        public MyViewHolder(View view){
            ivIcon= (ImageView) view.findViewById(R.id.ivIcon);
            tvRepoInfo= (TextView) view.findViewById(R.id.tvRepoInfo);
            tvRepoName= (TextView) view.findViewById(R.id.tvRepoName);
            tvRepoStars= (TextView) view.findViewById(R.id.tvRepoStars);
        }

    }


}
