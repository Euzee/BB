package start.bot.com.steambot.utils;

import start.bot.com.steambot.model.RSAResponse;

/**
 * Created by Dmitriy on 19.02.2016.
 */
public class CipherHandler {

    private static CipherHandler instance;

    public CipherHandler() {

    }

    public static String parseCipher(RSAResponse model, String enteredPassword) {
        return new SteamRSA(model.getPublickeyMod(), model.getPublickeyExp()).encrypt(enteredPassword);
    }
}
