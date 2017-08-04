package dinson.customview.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference封装
 */
public class SPUtils {


    /////////////////////////////////// 分割线 /////////////////////////////////////////////////////

    public static boolean getBoolean(Context ctx, String fileName, String key, boolean defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putBoolean(Context ctx, String fileName, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static void putString(Context ctx, String fileName, String key, String value) {
        SharedPreferences sp = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context ctx, String fileName, String key, String defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context ctx, String fileName, String key, int value) {
        SharedPreferences sp = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context ctx, String fileName, String key, int defValue) {
        SharedPreferences sp = ctx.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }
}