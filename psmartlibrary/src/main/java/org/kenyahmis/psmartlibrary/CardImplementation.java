package org.kenyahmis.psmartlibrary;

/**
 * Created by GMwasi on 2/9/2018.
 */

class CardImplementation implements Card {
    public CardImplementation() {
        Compression compression = new Compression();
        Encryption encryption = new Encryption();
    }

    @Override
    public String Read() {
        return null;
    }

    @Override
    public String Write(String message) {
        return null;
    }
}
