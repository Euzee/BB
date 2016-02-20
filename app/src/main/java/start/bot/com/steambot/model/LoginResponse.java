package start.bot.com.steambot.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements IResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("captcha_needed")
    @Expose
    private boolean captchaNeeded;
    @SerializedName("captcha_gid")
    @Expose
    private String captchaGid;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("requires_twofactor")
    @Expose
    private boolean requiresTwofactor;
    @SerializedName("login_complete")
    @Expose
    private boolean loginComplete;
    @SerializedName("transfer_parameters")
    @Expose
    private TransferParameters transferParameters;

    /**
     * No args constructor for use in serialization
     */
    public LoginResponse() {
    }

    /**
     * @param message
     * @param loginComplete
     * @param captchaGid
     * @param transferParameters
     * @param success
     * @param requiresTwofactor
     * @param captchaNeeded
     */
    public LoginResponse(String message, boolean captchaNeeded, String captchaGid, boolean success, boolean requiresTwofactor, boolean loginComplete, TransferParameters transferParameters) {
        this.message = message;
        this.captchaNeeded = captchaNeeded;
        this.captchaGid = captchaGid;
        this.success = success;
        this.requiresTwofactor = requiresTwofactor;
        this.loginComplete = loginComplete;
        this.transferParameters = transferParameters;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The captchaNeeded
     */
    public boolean isCaptchaNeeded() {
        return captchaNeeded;
    }

    /**
     * @param captchaNeeded The captcha_needed
     */
    public void setCaptchaNeeded(boolean captchaNeeded) {
        this.captchaNeeded = captchaNeeded;
    }

    /**
     * @return The captchaGid
     */
    public String getCaptchaGid() {
        return captchaGid;
    }

    /**
     * @param captchaGid The captcha_gid
     */
    public void setCaptchaGid(String captchaGid) {
        this.captchaGid = captchaGid;
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
     * @return The requiresTwofactor
     */
    public boolean isRequiresTwofactor() {
        return requiresTwofactor;
    }

    /**
     * @param requiresTwofactor The requires_twofactor
     */
    public void setRequiresTwofactor(boolean requiresTwofactor) {
        this.requiresTwofactor = requiresTwofactor;
    }

    /**
     * @return The loginComplete
     */
    public boolean isLoginComplete() {
        return loginComplete;
    }

    /**
     * @param loginComplete The login_complete
     */
    public void setLoginComplete(boolean loginComplete) {
        this.loginComplete = loginComplete;
    }

    /**
     * @return The transferParameters
     */
    public TransferParameters getTransferParameters() {
        return transferParameters;
    }

    /**
     * @param transferParameters The transfer_parameters
     */
    public void setTransferParameters(TransferParameters transferParameters) {
        this.transferParameters = transferParameters;
    }

}


