package com.francis.bestroute.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.francis.bestroute.R;
import com.francis.bestroute.vo.MainItemVO;

import java.util.List;


/**
 * Created by zeven on 2/28/2018.
 */

public class MainListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MainItemVO> mDatas ;
    int layoutID;

    public MainListAdapter(List<MainItemVO> datas){
        mDatas = datas;
    }
    public MainListAdapter(List<MainItemVO> datas, int layoutID){
        this(datas);
        this.layoutID = layoutID;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainItemVO item = mDatas.get(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.titleView.setText(item.getTitle());
        vh.authorView.setText(item.getAuthor());
        vh.imageView.setImageURI(Uri.parse(item.getImageUrl()));
        vh.data = item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutID, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
