package com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.githupanddroid.R;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.CoderItemsBean;
import com.example.administrator.githupanddroid.hotvirepagerfragment.hotcoder.model.UsersInfo;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016-08-02.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private List<UsersInfo> list;
    private static final int LOAD_MORE=2;
    private static final int MAIN_CONTENT=1;

    public RecyAdapter(List<UsersInfo> list, Context context){
        this.list=list;
        inflater=LayoutInflater.from(context);

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType== LOAD_MORE){
            return new FooterViewHodler(inflater.inflate(R.layout.content_load_footer,parent,false));
        }
        View view=inflater.inflate(R.layout.coder_item,parent,false);
        MyHoder hoder=new MyHoder(view);
        return hoder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHodler){

        }else if (holder instanceof MyHoder){
            MyHoder hoder1 = (MyHoder) holder;
            ImageLoader.getInstance().displayImage(list.get(position).getAvatar_url(), hoder1.coder_icon);
            hoder1.coder_name.setText(list.get(position).getName());
//        holder.coder_type.setText(list.get(position).getType());
            hoder1.bio.setText(list.get(position).getBio());
            hoder1.blog.setText(list.get(position).getBlog());
            hoder1.coder_location.setText(list.get(position).getLocation());
            hoder1.company.setText(list.get(position).getCompany());
            hoder1.cretetime.setText(list.get(position).getCreated_at());
            hoder1.update.setText(list.get(position).getUpdated_at());
            hoder1.star.setText(list.get(position).getFollowers() + "");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
//        if (position>=getItemCount()+1){
//            return LOAD_MORE;
//        }else {
//            return MAIN_CONTENT;
//        }
        return position==getItemCount()+1 ? LOAD_MORE :MAIN_CONTENT;

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
    class FooterViewHodler extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public FooterViewHodler(View itemView) {
            super(itemView);
            progressBar= (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }
}
