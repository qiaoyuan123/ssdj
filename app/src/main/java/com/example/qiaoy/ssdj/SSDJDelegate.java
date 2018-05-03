package com.example.qiaoy.ssdj;


import android.os.Bundle;
import android.view.View;

import com.example.qiaoy.qiao_core.delegates.QiaoDelegate;
import com.example.qiaoy.qiao_core.net.RestClient;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;

public class SSDJDelegate extends QiaoDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_ssdj;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        teatRestCliet();
    }

    private void teatRestCliet() {
        RestClient.builder()
                .url("https://www.baidu.com/")
//                .params("", "")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).build()
                .get();

    }

}
