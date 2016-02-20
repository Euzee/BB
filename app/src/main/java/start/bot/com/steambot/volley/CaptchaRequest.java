package start.bot.com.steambot.volley;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

import start.bot.com.steambot.utils.URLS;

public class CaptchaRequest extends Request<byte[]> {
    private Response.Listener<Bitmap> listener;
    public Map<String, String> responseHeaders;

    public CaptchaRequest(String captchaGid, Response.Listener<Bitmap> listener) {
        super(Method.GET, URLS.CAPTCHA + captchaGid, null);
        setShouldCache(true);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(byte[] response) {
        listener.onResponse(response != null ? BitmapFactory.decodeByteArray(response, 0, response.length) : null);
    }

    @Override
    protected Response<byte[]> parseNetworkResponse(NetworkResponse response) {
        responseHeaders = response.headers;
        return Response.success(response.data, HttpHeaderParser.parseCacheHeaders(response));
    }
}