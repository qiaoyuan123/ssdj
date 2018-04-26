package com.example.qiaoy.qiao.ec.icon;

import com.joanzapata.iconify.Icon;

public enum EcIcons implements Icon{

    icon_edit('\ue69e'),
    icon_delete('\ue69d'),
    icon_cry('\ue69c'),
    icon_comments('\ue69b'),
    icon_close('\ue69a')

    ;

    private char character;

    EcIcons(char character){
        this.character = character;
    }
    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
