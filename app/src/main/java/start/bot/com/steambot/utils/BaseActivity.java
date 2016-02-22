package start.bot.com.steambot.utils;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;

import start.bot.com.steambot.App;

/**
 * Created by Dmitriy on 22.02.2016.
 */
public class BaseActivity extends AppCompatActivity {

    public void post(Request<?> request) {
        App.getInstance().getRequestQueue().add(request);
    }
}
