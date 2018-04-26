package com.example.qiaoy.qiao_core;

import android.media.audiofx.AudioEffect;
import android.text.style.IconMarginSpan;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurator {

    private static  final HashMap<String, Object> CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator(){
        initIcons();
        CONFIGS .put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<String , Object> getConfigs(){return CONFIGS;}


    private static class Holder{
        private static final  Configurator INSTANCE = new Configurator();
    }

    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i=1; i<ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    public final void configure(){
        CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withApiHost(String host){
        CONFIGS.put(ConfigType.API_HOST.name() , host);
        return this;
    }

    private void checkCOnfiguration(){
        final boolean isReady = (boolean) CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(isReady){
            throw new RuntimeException("Configuration is not read, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkCOnfiguration();
        return (T) CONFIGS.get(key.name());
    }
}
