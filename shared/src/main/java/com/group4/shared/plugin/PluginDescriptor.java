package com.group4.shared.plugin;

import java.net.URL;

/**
 * Created by beefhead on 6/14/2017.
 */

public class PluginDescriptor {
    public String pluginName;
    public Class pluginClass;

    public PluginDescriptor(String pluginName, Class pluginClass) {
        this.pluginName = pluginName;
        this.pluginClass = pluginClass;
    }
}
