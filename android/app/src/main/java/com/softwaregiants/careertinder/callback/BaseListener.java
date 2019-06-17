package com.softwaregiants.careertinder.callback;

public interface BaseListener {
    public void callback(ACTION_PERFORMED action,int pos, Object...args);
}

