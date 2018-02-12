package org.kenyahmis.psmartlibrary;

import org.kenyahmis.psmartlibrary.Models.WriteResponse;

/**
 * Created by GMwasi on 2/10/2018.
 */
    // Summary:
    //Provides a means of reading and writting to the card
public interface Card {

    // Returns:
    //     Data stored on the  card in the message format SHR
    String Read();

    // Params:
    //A string message in SHR format
    // Returns:
    //     An object containing the serial number of the card and the response message
    WriteResponse Write (String message);
}
