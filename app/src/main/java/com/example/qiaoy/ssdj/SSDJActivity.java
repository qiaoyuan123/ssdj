package com.example.qiaoy.ssdj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.qiaoy.qiao_core.activitys.ProxyActivity;
import com.example.qiaoy.qiao_core.app.Core;
import com.example.qiaoy.qiao_core.delegates.QiaoDelegate;
import com.example.qiaoy.qiao_core.net.RestClient;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;

public class SSDJActivity extends ProxyActivity {

    @Override
    public QiaoDelegate setRootDelegare() {
        return new SSDJDelegate();
    }


}
