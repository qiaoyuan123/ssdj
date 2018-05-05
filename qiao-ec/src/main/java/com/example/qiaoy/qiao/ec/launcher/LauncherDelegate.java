package com.example.qiaoy.qiao.ec.launcher;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.example.qiaoy.qiao.ec.R;
import com.example.qiaoy.qiao_core.app.AccountManager;
import com.example.qiaoy.qiao_core.app.IUserChecker;
import com.example.qiaoy.qiao_core.delegates.BackDelegate;
import com.example.qiaoy.qiao_core.delegates.CoreDelegate;
import com.example.qiaoy.qiao_core.uril.storage.SPUtil;
import com.example.qiaoy.qiao_core.uril.timer.BaseTimerTask;
import com.example.qiaoy.qiao_core.uril.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

public class LauncherDelegate extends BackDelegate implements ITimerListener {

    private AppCompatTextView mTvTimer = null;
    private Timer mTimer = null;
    private int mCount = 5;

    private ILauncherListener mILauncherListener;

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mTvTimer = (AppCompatTextView) rootView.findViewById(R.id.tv_launcher_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                    checkIsShowScroll();
                }
            }
        });
        initTimer();
    }

    //判断是否显示滑动启动页
    private void checkIsShowScroll() {
        if (!SPUtil.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().startWithPop(new ScrollDelegate());
        } else {
            //检查登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

}
