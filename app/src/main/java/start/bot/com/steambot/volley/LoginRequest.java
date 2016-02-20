package start.bot.com.steambot.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import start.bot.com.steambot.model.LoginResponse;
import start.bot.com.steambot.model.RSAResponse;
import start.bot.com.steambot.utils.URLS;
import start.bot.com.steambot.utils.Util;

public class LoginRequest extends Request<LoginResponse> {

    private Listener<LoginResponse> listener;
    private Map<String, String> params;

    public LoginRequest(Map<String, String> params, Listener<LoginResponse> responseListener, ErrorListener errorListener) {
        super(Method.POST, URLS.DO_LOGIN, errorListener);
        this.listener = responseListener;
        this.params = params;
    }

    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return params;
    }

    @Override
    protected Response<LoginResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(Util.readJson(jsonString, LoginResponse.class),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(LoginResponse response) {
        listener.onResponse(response);
    }
}