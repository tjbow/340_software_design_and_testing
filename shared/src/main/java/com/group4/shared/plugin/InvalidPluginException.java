package com.group4.shared.plugin;

public class InvalidPluginException extends Exception {
    public InvalidPluginException(String s, Exception e) {
        super(s,e);
    }
}
