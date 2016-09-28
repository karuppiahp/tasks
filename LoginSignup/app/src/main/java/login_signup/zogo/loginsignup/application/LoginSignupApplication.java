package login_signup.zogo.loginsignup.application;

import android.content.Context;
import android.content.res.Resources;

import com.activeandroid.ActiveAndroid;

/**
 * Created by karuppiah on 9/27/2016.
 */
public class LoginSignupApplication extends com.activeandroid.app.Application {

    private static Context context;

    public static final String TAG = LoginSignupApplication.class
            .getSimpleName();

    private static LoginSignupApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = null;
        context = getApplicationContext();
        ActiveAndroid.initialize(this);
    }

    public static Context getGlobalContext() {
        return context;
    }

    public static Resources getAppResources() {
        return context.getResources();
    }

    public static String getAppString(int resourceId, Object... formatArgs) {
        return getAppResources().getString(resourceId, formatArgs);
    }

    public static String getAppString(int resourceId) {
        return getAppResources().getString(resourceId);
    }

    public static synchronized LoginSignupApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
