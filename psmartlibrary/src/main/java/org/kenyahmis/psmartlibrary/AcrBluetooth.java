package org.kenyahmis.psmartlibrary;

import org.kenyahmis.psmartlibrary.Models.Response;

/**
 * Created by GMwasi on 2/10/2018.
 */

public class AcrBluetooth implements CardReader {
    @Override
    public byte[] ReadCard() {
        return new byte[0];
    }

    @Override
    public byte[] WriteCard(byte[] data) {
        return null;
    }
}
