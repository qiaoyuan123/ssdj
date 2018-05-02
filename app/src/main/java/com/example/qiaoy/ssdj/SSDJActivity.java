package com.example.qiaoy.ssdj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qiaoy.qiao_core.app.Core;
import com.example.qiaoy.qiao_core.net.RestClient;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;

public class SSDJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teatRestCliet();
    }

    private void teatRestCliet() {
        RestClient.builder()
                .url("https://www.baidu.com/")
//                .params("", "")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                    }
                }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).build()
                .get();

    }

}
