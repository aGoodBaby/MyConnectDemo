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
 * Created by fengdeyu on 2016/10/24.
 */

public class BookAdapter extends BaseAdapter {
    private List<BookBean> mList;
    private LayoutInflater mInflater;

    public BookAdapter(Context context, List<BookBean> mList) {
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
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView=mInflater.inflate(R.layout.book_item_layout,null);
            viewHolder.tvTitle= (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent= (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }


        viewHolder.tvTitle.setText(mList.get(position).bookTitle);
        viewHolder.tvContent.setText(mList.get(position).bookContent);


        return convertView;
    }

    class ViewHolder{
        public TextView tvTitle,tvContent;

    }

}
