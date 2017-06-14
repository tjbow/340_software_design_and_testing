package com.group4.server.Plugin;

import com.group4.shared.plugin.IPersistencePlugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by beefhead on 6/14/2017.
 */

public class PluginRegistry {
    private static IPersistencePlugin loadedPlugin = null;
    private Map<String,IPersistencePlugin> availablePlugins = new HashMap<>();

    public void scanDirectory(File folder){
        File[] fileList = folder.listFiles();

        for(File f : fileList){
            try {
                URLClassLoader loader = URLClassLoader.newInstance(new URL[] {
                        new URL(f.getAbsolutePath())
                });
                String classname = f.getName();
                classname = classname.substring(0,classname.length()-4);
                Class fileClass  = loader.loadClass(classname);
                IPersistencePlugin a = (IPersistencePlugin) fileClass.newInstance();
            } catch (MalformedURLException | ClassNotFoundException | InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }



}
