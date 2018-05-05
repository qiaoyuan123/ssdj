package com.example.qiaoy.qiao_core.delegates;

public abstract class BackDelegate extends CoreDelegate  {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            if (TOUCH_TIME != 0) {
                TOUCH_TIME = 0;
            }
        } else {
            TOUCH_TIME = System.currentTimeMillis();
        }
        return true;
    }
}
