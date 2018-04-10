package dinson.customview._global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;

import com.squareup.leakcanary.LeakCanary;

import dinson.customview.BuildConfig;


public class GlobalApplication extends BLingTinkerApplication {

    public static final Boolean IS_DEBUG = BuildConfig.DEBUG;

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static Handler handler;
    private static int mainThreadId;


    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        handler = new Handler();
        mainThreadId = android.os.Process.myTid();

        CrashHandler.INSTANCE.init(ConstantsUtils.INSTANCE.getSDCARD_PRIVATE());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }


    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}