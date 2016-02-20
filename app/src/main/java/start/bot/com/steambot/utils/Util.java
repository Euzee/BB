package start.bot.com.steambot.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

/**
 * Created by Dmitriy on 19.02.2016.
 */
public class Util {

    private static Gson gson;

    public static Gson getGson() {
        if (gson == null) {
            gson = new GsonBuilder().create();
        }
        return gson;
    }

    public static <T> T readJson(String json, Class<T> classOfT) {
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        return getGson().fromJson(reader, classOfT);
    }
}
