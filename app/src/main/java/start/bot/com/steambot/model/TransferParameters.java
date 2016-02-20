package start.bot.com.steambot.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TransferParameters {

    @SerializedName("steamid")
    @Expose
    private String steamid;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("auth")
    @Expose
    private String auth;
    @SerializedName("remember_login")
    @Expose
    private boolean rememberLogin;
    @SerializedName("token_secure")
    @Expose
    private String tokenSecure;

    /**
     * No args constructor for use in serialization
     */
    public TransferParameters() {
    }

    /**
     * @param rememberLogin
     * @param steamid
     * @param token
     * @param tokenSecure
     * @param auth
     */
    public TransferParameters(String steamid, String token, String auth, boolean rememberLogin, String tokenSecure) {
        this.steamid = steamid;
        this.token = token;
        this.auth = auth;
        this.rememberLogin = rememberLogin;
        this.tokenSecure = tokenSecure;
    }

    /**
     * @return The steamid
     */
    public String getSteamid() {
        return steamid;
    }

    /**
     * @param steamid The steamid
     */
    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return The auth
     */
    public String getAuth() {
        return auth;
    }

    /**
     * @param auth The auth
     */
    public void setAuth(String auth) {
        this.auth = auth;
    }

    /**
     * @return The rememberLogin
     */
    public boolean isRememberLogin() {
        return rememberLogin;
    }

    /**
     * @param rememberLogin The remember_login
     */
    public void setRememberLogin(boolean rememberLogin) {
        this.rememberLogin = rememberLogin;
    }

    /**
     * @return The tokenSecure
     */
    public String getTokenSecure() {
        return tokenSecure;
    }

    /**
     * @param tokenSecure The token_secure
     */
    public void setTokenSecure(String tokenSecure) {
        this.tokenSecure = tokenSecure;
    }

    public Map<String, String> getAsMap() {
        HashMap<String, String> map = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), String.valueOf(field.get(this)));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return map;
    }

}
