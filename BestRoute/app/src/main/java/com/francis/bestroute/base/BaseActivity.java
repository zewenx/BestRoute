package com.francis.bestroute.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.francis.bestroute.R;
import com.francis.bestroute.utils.TagUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * 类描述：
 * 创建人：zeven
 * 创建时间：16/4/13 上午11:21
 */
public class BaseActivity extends AppCompatActivity {
    public Dialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateMessage("onCreate");
    }

    protected void init(){
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //View view = getLayoutInflater().inflate(R.layout.progress);
        builder.setView(R.layout.progress);
        progressBar = builder.create();
    }

    @Override
    protected void onResume() {
        super.onResume();
        stateMessage("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        stateMessage("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stateMessage("onDestroy");
        EventBus.getDefault().unregister(this);
    }

    private void stateMessage(String s){
        Log.i(TagUtil.generateALCTag(this),s);
    }

    public void toastText(int id) {
        toastText(this.getResources().getString(id));

    }
    public float convertDpToPixel(float dp){
        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public void toastText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
