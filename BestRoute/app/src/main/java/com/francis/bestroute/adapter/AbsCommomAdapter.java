package com.francis.bestroute.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zeven on 15/10/30.
 */
 abstract  public class AbsCommomAdapter<T> extends BaseAdapter {
    protected Context context;
    protected List<T> dataList;
    private int resourceID;

    public AbsCommomAdapter(Context context, List<T> datas, int resourceID) {
        this.context = context;
        this.dataList = datas;
        this.resourceID = resourceID;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder = ViewHolder.getViewHolder(context,resourceID,parent,position,convertView);

        injectData(viewHolder, getItem(position));

        return viewHolder.getConvertView();
    }

    protected abstract void injectData(final ViewHolder holder, T t);

}
