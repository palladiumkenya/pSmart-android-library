package org.kenyahmis.psmartlibrary.Models;

/**
 * Created by GMwasi on 2/10/2018.
 */

public class Response {
    
    private boolean isSuccessful;
    private String serialNumber;
    private String message;

    public Response() {
    }


    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
