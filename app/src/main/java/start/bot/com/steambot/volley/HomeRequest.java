package start.bot.com.steambot.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

import start.bot.com.steambot.model.LoginResponse;
import start.bot.com.steambot.utils.URLS;
import start.bot.com.steambot.utils.Util;

public class HomeRequest extends Request<String> {

    public HomeRequest() {
        super(Method.GET, URLS.HOME, null);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(Util.readJson(jsonString, String.class),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
    }
}