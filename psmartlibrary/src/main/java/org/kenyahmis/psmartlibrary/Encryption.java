package org.kenyahmis.psmartlibrary;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

/**
 * Created by GMwasi on 2/9/2018.
 */

class Encryption {

    private String key = "!A%D*F-JaNdRgUkX";
    private String intializationVector = "PdSgVkXp2s5v8y/B";
    private String encryptedString;
    private String originalMessage;

    protected String Encrypt(String message) {
        try {
            IvParameterSpec iv = new IvParameterSpec(intializationVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(message.getBytes());
            encryptedString = Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return encryptedString;
    }

    protected String Decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(intializationVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            originalMessage = new  String(original);
        } catch (Exception ex) {
            ex.getMessage();
        }

        return originalMessage;
    }
}

