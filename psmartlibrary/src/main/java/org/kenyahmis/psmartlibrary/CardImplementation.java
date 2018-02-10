package org.kenyahmis.psmartlibrary;

import org.kenyahmis.psmartlibrary.Models.WriteResponse;
import org.kenyahmis.psmartlibrary.Readers.CardReader;

import java.io.IOException;

/**
 * Created by GMwasi on 2/9/2018.
 */

class CardImplementation implements Card {
    private CardReader _reader;
    private Encryption _encryption;
    private Compression _compression;
    public CardImplementation() {
        _compression = new Compression();
        _encryption = new Encryption();
    }

    @Override
    public String Read() {
        byte[] cardData = _reader.ReadCard();
        String decompressedMessage = "";
        try {
           decompressedMessage =  _compression.Decompress(cardData);
        } catch (IOException e) {
            e.getMessage();
        }
        String decryptedMessage = _encryption.Decrypt(decompressedMessage);
        return decryptedMessage;
    }

    @Override
    public WriteResponse Write(String message) {
        byte[] compressedMessage = new byte[0];
        String encryptedMessage = _encryption.Encrypt(message);
        try {
             compressedMessage = _compression.Compress(encryptedMessage);
        } catch (IOException e) {
            e.getMessage();
        }
        WriteResponse output = _reader.WriteCard(compressedMessage);
        WriteResponse response = new WriteResponse();
        response.setResponseMessage(output.ResponseMessage);
        response.setSerialNumber(output.SerialNumber);
        return response;
    }
}
