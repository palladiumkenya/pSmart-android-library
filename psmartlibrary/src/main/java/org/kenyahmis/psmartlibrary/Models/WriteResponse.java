package org.kenyahmis.psmartlibrary.Models;

/**
 * Created by GMwasi on 2/10/2018.
 */

public class WriteResponse {
    public WriteResponse() {
    }

    public WriteResponse(String serialNumber, String responseMessage) {
        SerialNumber = serialNumber;
        ResponseMessage = responseMessage;
    }

    public String SerialNumber;
    public String ResponseMessage;

    public String getSerialNumber() {
        return SerialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        SerialNumber = serialNumber;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }
}
