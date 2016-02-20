package start.bot.com.steambot;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import start.bot.com.steambot.volley.AllHttpsHostsHurlStack;


public class App extends Application {
    private static final String TAG = App.class.getSimpleName();

    private static App instance;
    private Toast signingOutToast;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null) {
            instance = this;
        }

        mRequestQueue = Volley.newRequestQueue(this, getStack());
    }

    private HurlStack getStack() {
        HurlStack stack = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            // Use a socket factory that removes sslv3
            stack = new AllHttpsHostsHurlStack();
        }
        return stack;
    }

    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }
        final String packageName = getPackageName();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess
                    .processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }

    public void showWaitingMessage(String string) {
        if (signingOutToast != null) {
            signingOutToast.cancel();
        }
        signingOutToast = Toast.makeText(this, string, Toast.LENGTH_LONG);
        signingOutToast.show();
    }


    public String getDeviceId() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static App getInstance() {
        return instance;
    }

    public boolean isConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();

/*        if(isNetworkAvailable()){
            if(connectUrl()){
                return true;
            }else {
                return connectUrl();
            }
        } else {
            return false;
        }*/
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    public static boolean connectUrl() {
        try {
            HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
            urlc.setRequestProperty("User-Agent", "Test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(10000);
            urlc.connect();
            return (urlc.getResponseCode() == 200);

        } catch (IOException e) {

            return false;
        }
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

}

