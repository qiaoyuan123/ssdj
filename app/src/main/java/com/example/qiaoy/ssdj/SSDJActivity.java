package com.example.qiaoy.ssdj;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.qiaoy.qiao_core.Core;

public class SSDJActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText( Core.getApplication(),"传入Context啦" ,Toast.LENGTH_SHORT).show();
    }
}
