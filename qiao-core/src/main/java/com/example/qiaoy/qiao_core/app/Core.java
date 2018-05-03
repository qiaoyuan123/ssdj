package com.example.qiaoy.qiao_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

public final class Core {

    public static Configurator init(Context context) {
        getConfigs().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigs() {
        return Configurator.getInstance().getConfigs();
    }

    public static Context getApplication() {
        return (Context) getConfigs().get(ConfigType.APPLICATION_CONTEXT.name());
    }

    public static Handler getHandler() {
        return (Handler) getConfigs().get(ConfigType.HANDLER.name());
    }
}
