package com.example.qiaoy.ssdj;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.qiaoy.qiao_core.delegates.CoreDelegate;
import com.example.qiaoy.qiao_core.net.RestClient;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;

public class SSDJDelegate extends CoreDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_ssdj;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

}
