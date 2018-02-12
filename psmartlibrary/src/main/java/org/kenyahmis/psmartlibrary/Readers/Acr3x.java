package org.kenyahmis.psmartlibrary.Readers;

import android.app.Activity;

import org.kenyahmis.psmartlibrary.Models.WriteResponse;

/**
 * Created by GMwasi on 2/10/2018.
 */

public class Acr3x extends Activity implements CardReader{

    @Override
    public byte[] ReadCard() {
        return new byte[0];
    }

    @Override
    public WriteResponse WriteCard(byte[] data) {
        return null;
    }
}
