package com.nacho.spidermanmemes;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

public class RandomMemeApp extends Application{

    private static WeakReference<Context> context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = new WeakReference<Context>(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getContext() {
        return context.get();
    }
}
