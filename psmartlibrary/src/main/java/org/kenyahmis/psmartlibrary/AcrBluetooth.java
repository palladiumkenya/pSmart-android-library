package org.kenyahmis.psmartlibrary;

import com.acs.bluetooth.BluetoothReader;

/**
 * Created by GMwasi on 2/10/2018.
 */

class AcrBluetooth implements CardReader {

    AcrBluetooth(BluetoothReader reader){

    }

    @Override
    public byte[] ReadCard() {
        return new byte[0];
    }

    @Override
    public byte[] WriteCard(byte[] data) {
        return null;
    }

}
