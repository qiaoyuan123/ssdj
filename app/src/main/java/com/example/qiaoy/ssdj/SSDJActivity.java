package com.example.qiaoy.ssdj;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.qiaoy.qiao.ec.launcher.ILauncherListener;
import com.example.qiaoy.qiao.ec.launcher.LauncherDelegate;
import com.example.qiaoy.qiao.ec.launcher.OnLauncherFinishTag;
import com.example.qiaoy.qiao.ec.launcher.ScrollDelegate;
import com.example.qiaoy.qiao.ec.sign.ISignListener;
import com.example.qiaoy.qiao.ec.sign.SignInDelegate;
import com.example.qiaoy.qiao_core.activitys.ProxyActivity;
import com.example.qiaoy.qiao_core.delegates.CoreDelegate;

public class SSDJActivity extends ProxyActivity implements
        ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        getSupportDelegate().start(new LauncherDelegate());
    }

    @Override
    public CoreDelegate setRootDelegare() {
        return new SSDJDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().pop();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
        getSupportDelegate().pop();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SSDJDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
