package com.example.fengdeyu.myconnectdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fengdeyu on 2016/10/12.
 */

public class FictionAdapter extends BaseAdapter {
    private List<FictionsBean> mList;
    private LayoutInflater mInflater;

    public FictionAdapter(Context context, List<FictionsBean> mList) {
        this.mList = mList;
        mInflater=LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder =null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.item_layout,null);
            //viewHolder.ivIcon= (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent= (TextView) convertView.findViewById(R.id.tv_url);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        //viewHolder.ivIcon.setImageResource(R.mipmap.ic_launcher);
        viewHolder.tvTitle.setText(mList.get(position).fictionTitle);
        viewHolder.tvContent.setText(mList.get(position).fictionUrl);


        return convertView;
    }

    class ViewHolder{
        public TextView tvTitle,tvContent;
        //public ImageView ivIcon;
    }

}
