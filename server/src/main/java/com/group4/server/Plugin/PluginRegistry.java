package com.group4.server.Plugin;

import com.group4.shared.plugin.IPersistencePlugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;



public class PluginRegistry {
    private static IPersistencePlugin loadedPlugin = null;
    private Map<String,IPersistencePlugin> availablePlugins = new HashMap<>();

    public void scanDirectory(File folder){
        File[] fileList = folder.listFiles();

        for(File f : fileList){
            try {
                URLClassLoader loader = URLClassLoader.newInstance(new URL[] {
                        f.toURI().toURL()
                });

                String classname = f.getName();
                classname = classname.substring(0,classname.length()-4);
                String pluginName = "plugin." + classname;

                Class fileClass  = loader.loadClass(pluginName);
                IPersistencePlugin plugin = (IPersistencePlugin) fileClass.newInstance();

                availablePlugins.put(classname,plugin);

            } catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }



}
