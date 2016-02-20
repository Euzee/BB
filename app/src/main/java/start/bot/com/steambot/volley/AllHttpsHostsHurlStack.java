package start.bot.com.steambot.volley;

import com.android.volley.toolbox.HurlStack;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AllHttpsHostsHurlStack extends HurlStack {

    public AllHttpsHostsHurlStack() {
        super(null);
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        HttpURLConnection connection = super.createConnection(url);
        if (connection instanceof HttpsURLConnection)
            ((HttpsURLConnection) connection).setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        return connection;
    }


}
