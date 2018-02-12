package org.kenyahmis.psmartlibrary;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by GMwasi on 2/9/2018.
 */

public class EncryptionTest {

    private Encryption x = new Encryption();
    private String message = "Hello World!";
    @Test
    public void MessageCanBeEncrypted() throws Exception {

        String result = x.Encrypt(message);
        Assert.assertNotEquals(message, result);
    }

    @Test
    public void MessageCanBeDecrypted() throws Exception {
        String result = x.Encrypt(message);
        String messageDecrypt = x.Decrypt(result);
        Assert.assertEquals(message, messageDecrypt);
    }
}
