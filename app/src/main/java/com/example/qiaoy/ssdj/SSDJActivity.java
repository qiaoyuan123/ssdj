package com.example.qiaoy.ssdj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.qiaoy.qiao.ec.launcher.LauncherDelegate;
import com.example.qiaoy.qiao.ec.launcher.ScrollDelegate;
import com.example.qiaoy.qiao_core.activitys.ProxyActivity;
import com.example.qiaoy.qiao_core.delegates.CoreDelegate;

public class SSDJActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public CoreDelegate setRootDelegare() {
        return new LauncherDelegate();
    }


}
