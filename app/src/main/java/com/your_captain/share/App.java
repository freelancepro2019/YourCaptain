package com.your_captain.share;


import android.content.Context;

import androidx.multidex.MultiDexApplication;

import com.your_captain.language.Language;


public class App extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(Language.updateResources(newBase,"en"));
    }

}

