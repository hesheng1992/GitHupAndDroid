package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.CoderItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016-08-02.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MyHoder> {
    private LayoutInflater inflater;
    private List<UsersInfo> list;

    public RecyAdapter(List<UsersInfo> list, Context context){
        this.list=list;
        inflater=LayoutInflater.from(context);

    }


    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.coder_item,parent,false);
        MyHoder hoder=new MyHoder(view);
        return hoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {

        ImageLoader.getInstance().displayImage(list.get(position).getAvatar_url(),holder.coder_icon);
        holder.coder_name.setText(list.get(position).getName());
//        holder.coder_type.setText(list.get(position).getType());
        holder.bio.setText(list.get(position).getBio());
        holder.blog.setText(list.get(position).getBlog());
        holder.coder_location.setText(list.get(position).getLocation());
        holder.company.setText(list.get(position).getCompany());
        holder.cretetime.setText(list.get(position).getCreated_at());
        holder.update.setText(list.get(position).getUpdated_at());
        holder.star.setText(list.get(position).getFollowers()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHoder extends RecyclerView.ViewHolder{
        ImageView coder_icon;
        TextView coder_name,star,coder_location,company,bio,blog,cretetime,update;


        public MyHoder(View itemView) {
            super(itemView);
            coder_icon= (ImageView) itemView.findViewById(R.id.coder_icon);
            coder_name= (TextView) itemView.findViewById(R.id.coder_name);
//            coder_type= (TextView) itemView.findViewById(R.id.coder_type);
            star= (TextView) itemView.findViewById(R.id.star);
            coder_location= (TextView) itemView.findViewById(R.id.coder_location);
            company= (TextView) itemView.findViewById(R.id.company);
            bio= (TextView) itemView.findViewById(R.id.bio);
            blog= (TextView) itemView.findViewById(R.id.blog);
            cretetime= (TextView) itemView.findViewById(R.id.cretetime);
            update= (TextView) itemView.findViewById(R.id.update);
        }
    }
}
