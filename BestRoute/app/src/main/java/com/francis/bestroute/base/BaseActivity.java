package com.francis.bestroute.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.francis.bestroute.utils.TagUtil;

import butterknife.ButterKnife;


/**
 * 类描述：
 * 创建人：zeven
 * 创建时间：16/4/13 上午11:21
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateMessage("onCreate");
    }

    protected void init(){
        ButterKnife.bind(this);
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
    }

    private void stateMessage(String s){
        Log.i(TagUtil.generateALCTag(this),s);
    }

    public void toastText(int id) {
        toastText(this.getResources().getString(id));

    }

    public void toastText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
