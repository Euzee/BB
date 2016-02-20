package start.bot.com.steambot.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import start.bot.com.steambot.App;

public final class PM {
    private SharedPreferences preferences;
    private static PM preferencesManager = null;

    public static PM getInstance(Context c) {
        if (preferencesManager == null) {
            preferencesManager = new PM(c);
        }
        return preferencesManager;
    }

    public static PM getInstance() {
        if (preferencesManager == null) {
            preferencesManager = new PM(App.getInstance());
        }
        return preferencesManager;
    }

    private PM(Context c) {
        preferences = PreferenceManager.getDefaultSharedPreferences(c);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public void clear() {
        preferences.edit().clear().apply();
    }


    public void clear(String key) {
        preferences.edit().remove(key).apply();
    }

    public Boolean isFirstLogin() {
        return getBoolean(Keys.IS_FIRST_LOGIN, true);
    }

    public void setIsFirstLogin(boolean value) {
        setBoolean(Keys.IS_FIRST_LOGIN, value);
    }


    public Boolean isUserActive() {
        return getBoolean(Keys.IS_USER_ACTIVE, true);
    }

    public void setUserActive(boolean value) {
        setBoolean(Keys.IS_USER_ACTIVE, value);
    }

    public void setPreferredConsumerNetwork(int value) {
        setInt(Keys.PREFERED_NETWORK, value);
    }

    public int getPreferredConsumerNetwork() {
        return getInt(Keys.PREFERED_NETWORK, 0);
    }

    public void setPatientEmail(String value) {
        setString(Keys.PATIENT_EMAIL, value);
    }

    public String getPatientEmail() {
        return getString(Keys.PATIENT_EMAIL, null);
    }

    public int getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }


    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public long getLong(String key, long defaultValue) {
        return getPreferences().getLong(key, defaultValue);
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public float getFloat(String key, float defaultValue) {
        return getPreferences().getFloat(key, defaultValue);
    }

    public void setFloat(String key, float value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public String getString(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public <T> T getObjectByClass(Class<T> className) {
        return getObject(className.getName(), className);
    }

    public <T> T getObject(String key, Class<T> className) {
        String walkingJson = PM.getInstance().getString(key, "");
        return !walkingJson.isEmpty() ? Util.readJson(walkingJson, className) : null;
    }

    public void setObject(String key, Object object) {
        PM.getInstance().setString(key, object != null ? Util.getGson().toJson
                (object) : "");
    }

    public String getDateString() {
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss", Locale.UK);
        dateFormatter.setLenient(false);
        Date today = new Date();
        return dateFormatter.format(today);
    }

    public void setToken(String value) {
        setString(Keys.TOKEN, value);
    }

    public String getToken() {
        return getString(Keys.TOKEN, null);
    }

}
