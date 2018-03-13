package org.kenyahmis.psmartlibrary;


import org.kenyahmis.psmartlibrary.Models.AcosCardResponse;
import org.kenyahmis.psmartlibrary.Models.HexString;

/**
 * Created by GMwasi on 2/10/2018.
 */

interface CardReader {

    // Returns:
    //     Data read from the card as byte array
    AcosCardResponse ReadCard();

    // Params:
    //Data to be written on card as byte array
    // Returns:
    //     Message stating if written successfully or if not error message
    byte[] WriteCard(byte[] data);
}
