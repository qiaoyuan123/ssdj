package com.example.qiaoy.ssdj;

import android.app.Application;

import com.example.qiaoy.qiao.ec.icon.FontEcModule;
import com.example.qiaoy.qiao_core.app.Core;
import com.example.qiaoy.qiao_core.net.interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class SSDJApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Core.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
