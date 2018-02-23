package org.kenyahmis.psmartlibrary;

import org.kenyahmis.psmartlibrary.Models.Response;

import java.io.IOException;

/**
 * Created by GMwasi on 2/9/2018.
 */

class CardImplementation implements Card {
    private CardReader _reader;
    private Encryption _encryption;
    private Compression _compression;

    public CardImplementation(CardReaderType type) {
        _compression = new Compression();
        _encryption = new Encryption();
        //TODO: create reader from type
    }

    @Override
    public Response Read() {
        byte[] cardData = _reader.ReadCard();
        String decompressedMessage = "";
        try {
           decompressedMessage =  _compression.Decompress(cardData);
        } catch (IOException e) {
            e.getMessage();
        }
        String decryptedMessage = _encryption.Decrypt(decompressedMessage);

        Response response = new Response();
        response.setMessage(decryptedMessage);
        response.isSuccessful();

        return response;
    }

    @Override
    public Response Write(String message) {
        byte[] compressedMessage = new byte[0];
        String encryptedMessage = _encryption.Encrypt(message);
        try {
             compressedMessage = _compression.Compress(encryptedMessage);
        } catch (IOException e) {
            e.getMessage();
        }
        byte[] output = _reader.WriteCard(compressedMessage);

        // TODO convert output to string
        // TODO get card serialnumber
        Response response = new Response();
        // TODO build response message
        return response;
    }
}
