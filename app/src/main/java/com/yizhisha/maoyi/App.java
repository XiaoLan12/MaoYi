package com.yizhisha.maoyi;

import android.app.Application;
import android.content.Context;
/**
 * Created by lan on 2017/6/22.
 */
public class App extends Application {
    private static App context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=this;

    }
    public static Context getAppContext() {
        return context;
    }

}
