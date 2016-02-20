package start.bot.com.steambot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RSAResponse implements IResponse {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("publickey_mod")
    @Expose
    private String publickeyMod;
    @SerializedName("publickey_exp")
    @Expose
    private String publickeyExp;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("token_gid")
    @Expose
    private String tokenGid;

    /**
     * No args constructor for use in serialization
     */
    public RSAResponse() {
    }

    /**
     * @param timestamp
     * @param tokenGid
     * @param publickeyExp
     * @param publickeyMod
     * @param success
     */
    public RSAResponse(boolean success, String publickeyMod, String publickeyExp, String timestamp, String tokenGid) {
        this.success = success;
        this.publickeyMod = publickeyMod;
        this.publickeyExp = publickeyExp;
        this.timestamp = timestamp;
        this.tokenGid = tokenGid;
    }

    /**
     * @return The success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success The success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return The publickeyMod
     */
    public String getPublickeyMod() {
        return publickeyMod;
    }

    /**
     * @param publickeyMod The publickey_mod
     */
    public void setPublickeyMod(String publickeyMod) {
        this.publickeyMod = publickeyMod;
    }

    /**
     * @return The publickeyExp
     */
    public String getPublickeyExp() {
        return publickeyExp;
    }

    /**
     * @param publickeyExp The publickey_exp
     */
    public void setPublickeyExp(String publickeyExp) {
        this.publickeyExp = publickeyExp;
    }

    /**
     * @return The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return The tokenGid
     */
    public String getTokenGid() {
        return tokenGid;
    }

    /**
     * @param tokenGid The token_gid
     */
    public void setTokenGid(String tokenGid) {
        this.tokenGid = tokenGid;
    }

    @Override
    public String getMessage() {
        return "Request failed";
    }
}