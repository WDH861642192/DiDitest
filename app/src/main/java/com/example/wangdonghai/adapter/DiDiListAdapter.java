package com.example.wangdonghai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.wangdonghai.didiactivity.R;
import com.example.wangdonghai.didimodel.DiDiOrder;

import java.util.ArrayList;

/**
 * Created by wangdonghai on 16/10/17.
 */

public class DiDiListAdapter extends BaseAdapter {
    private LayoutInflater mDiInflater;
    private ArrayList<DiDiOrder> mData;

    public DiDiListAdapter(LayoutInflater mInflater, ArrayList<DiDiOrder> mList) {
        mDiInflater = mInflater;
        mData = mList;
    }

    @Override

    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = mDiInflater.inflate(R.layout.item_list, parent, false);
            holder = new MyViewHolder();
            holder.fromAdress = (TextView) convertView.findViewById(R.id.didi_fromadress_text);
            holder.toAddree = (TextView) convertView.findViewById(R.id.didi_toadress_text);
            holder.time = (TextView) convertView.findViewById(R.id.item_didi_time);
            holder.isSelected = (ImageView) convertView.findViewById(R.id.didi_select_img);
            convertView.setTag(holder);
        } else
        holder= (MyViewHolder) convertView.getTag();
        holder.fromAdress.setText(mData.get(position).getFromAdress());
        holder.time.setText(mData.get(position).getTime());
        holder.toAddree.setText(mData.get(position).getToAdress());
            return convertView;
    }

    private class MyViewHolder {
        TextView fromAdress;
        TextView toAddree;
        TextView time;
        ImageView isSelected;

    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
