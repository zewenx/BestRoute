package com.francis.bestroute.base;

import android.app.Application;

//import com.tencent.bugly.crashreport.CrashReport;

/**
 * 类描述：
 * 创建人：zeven
 * 创建时间：16/4/22 下午4:39
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        CrashReport.initCrashReport(getApplicationContext(), "900027562", false);
    }
}
