package com.example.admin.calculator.model.shared;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPrecence implements SharedPreferences.OnSharedPreferenceChangeListener {
    public static final String PREF_KEY_RESULT_CACULATOR = "PREF_KEY_RESULT_CACULATOR";

    private SharedPreferences mSharedPreferences;

    public MySharedPrecence(Context context){
        mSharedPreferences=context.getSharedPreferences("MySharedPreference",Context.MODE_PRIVATE);
    }

    public void setData (String key,String value){
        SharedPreferences.Editor editor= mSharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public String getData(String key){
        String value=mSharedPreferences.getString(key,null);
        return value;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
