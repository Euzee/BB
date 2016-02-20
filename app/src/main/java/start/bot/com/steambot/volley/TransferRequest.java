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
import start.bot.com.steambot.model.TransferParameters;
import start.bot.com.steambot.utils.URLS;
import start.bot.com.steambot.utils.Util;

public class TransferRequest extends Request<String> {

    private TransferParameters params;
    private Listener<TransferParameters> listener;
    private static final String SUCCESS = "<html><body>\r\n" +
            "\t\t\t<script type=\"text/javascript\">\r\n" +
            "\t\t\t\tif ( window.parent.postMessage )\r\n" +
            "\t\t\t\t\twindow.parent.postMessage( 'transfer_success', '*' );\r\n" +
            "\t\t\t</script></body></html>";

    public TransferRequest(TransferParameters params, Listener<TransferParameters> listener) {
        super(Method.POST, URLS.TRANSFER, null);
        this.params = params;
        this.listener = listener;
    }

    protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
        return params != null ? params.getAsMap() : null;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(String response) {
        if (listener != null)
            listener.onResponse(SUCCESS.equals(response) ? params : null);
    }
}