package com.example.wangdonghai.didiactivity;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by wangdonghai on 16/10/16.
 */

public class BaseApplication extends Application {
    //Volley的全局请求队列
    private static RequestQueue sRequestQueue;

    /**
     * @return Volley全局请求队列
     */
    public static RequestQueue getRequestQueue() {
        return sRequestQueue;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sRequestQueue= Volley.newRequestQueue(getApplicationContext());
    }
}
