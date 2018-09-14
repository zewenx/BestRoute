package com.francis.bestroute.adapter;

import android.view.View;
import android.widget.TextView;

import com.francis.bestroute.R;
import com.francis.bestroute.vo.MainItemVO;

import co.dift.ui.SwipeToAction;

public class ViewHolder extends SwipeToAction.ViewHolder<MainItemVO> {
    TextView address;
    TextView address2;
    TextView number;

    public ViewHolder(View v) {
        super(v);
        address = v.findViewById(R.id.address);
        address2 = v.findViewById(R.id.address2);
        number = v.findViewById(R.id.num);
    }

}
