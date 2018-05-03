package com.example.qiaoy.qiao_core.uril.timer;

import java.util.TimerTask;

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerlistener = null;

    public BaseTimerTask(ITimerListener mITimerlistener) {
        this.mITimerlistener = mITimerlistener;
    }

    @Override
    public void run() {
        if(mITimerlistener != null){
            mITimerlistener.onTimer();
        }
    }
}
