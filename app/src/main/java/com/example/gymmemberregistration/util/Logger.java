package com.example.gymmemberregistration.util;

import android.util.Log;

public class Logger {
    private static final String ERROR_TAG = "Logger";
    private static final String TAG = "TAG_DEBUG";


    public static void logError(Throwable throwable){
        Log.e(ERROR_TAG, throwable.getMessage());

    }

    public static void logDebug(String log){
        Log.d(TAG, log);
    }
}
