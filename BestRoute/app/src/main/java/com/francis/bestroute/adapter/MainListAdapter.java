package com.francis.bestroute.adapter;

import android.content.Context;
import android.view.View;

import com.francis.bestroute.R;
import com.francis.bestroute.vo.MainItemVO;

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
//        holder.getViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDatas.remove(mainItemVO);
//                MainListAdapter.this.notifyDataSetChanged();
//            }
//        });
    }
}
