package com.francis.bestroute.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.francis.bestroute.R;
import com.francis.bestroute.activities.MainActivity;
import com.francis.bestroute.activities.MapsActivity;
import com.francis.bestroute.vo.MainItemVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeven on 2/28/2018.
 */

public class MainListAdapter extends AbsCommomAdapter<MainItemVO> {
    private List<MainItemVO> mDatas ;

    public MainListAdapter(Context context, List<MainItemVO> datas, int resourceID){
        super(context,datas,resourceID);
        mDatas = datas;
    }

    @Override
    protected void injectData(ViewHolder holder, final MainItemVO mainItemVO) {
        holder.setTextViewText(R.id.num,Integer.toString(mainItemVO.getNum()));
        holder.setTextViewText(R.id.address,mainItemVO.getAddress());
        holder.setTextViewText(R.id.address2,mainItemVO.getAddress2());
        holder.getViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(mainItemVO);
                MainListAdapter.this.notifyDataSetChanged();
            }
        });
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainListAdapter.super.getContext(), MapsActivity.class);
                intent.putExtra("data", (ArrayList<MainItemVO>)mDatas);
                intent.putExtra("center",mainItemVO);
                MainListAdapter.super.getContext().startActivity(intent);
            }
        });
    }
}
