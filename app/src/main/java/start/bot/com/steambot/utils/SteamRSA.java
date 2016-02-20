package start.bot.com.steambot.utils;

import android.util.Base64;

import java.math.BigInteger;
import java.nio.charset.Charset;

/**
 * Created by Dmitriy on 19.02.2016.
 */
public class SteamRSA {


    private final BigInteger modulus;
    private final BigInteger exponent;
    private final Charset charset = Charset.forName("UTF-8");

    SteamRSA(String modHex, String expHex) {
        modulus = new BigInteger(modHex, 16);
        exponent = new BigInteger(expHex, 16);
    }

    String encrypt(String password) {
        BigInteger data = pkcs1pad2(password.getBytes(charset), (modulus.bitLength() + 7) >> 3);
        BigInteger d2 = data.modPow(exponent, modulus);
        String dataHex = d2.toString(16);
        if ((dataHex.length() & 1) == 1) {
            dataHex = "0" + dataHex;
        }
        byte[] encrypted = hexStringToByteArray(dataHex);
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private BigInteger pkcs1pad2(byte[] data, int n) {
        byte[] bytes = new byte[n];
        int i = data.length - 1;
        while ((i >= 0) && (n > 0)) {
            bytes[--n] = data[i--];
        }
        bytes[--n] = 0;

        while (n > 2) {
            bytes[--n] = (byte) (Math.floor(Math.random() * 254) + 1);
        }

        bytes[--n] = 0x2;
        bytes[--n] = 0;

        return new BigInteger(bytes);
    }
}
