package org.kenyahmis.psmartlibrary.Models;

import java.util.List;

/**
 * Created by Muhoro on 2/24/2018.
 */

public class WriteResponse extends Response {
    private String serialNumber;

    public WriteResponse(String serialNumber, List<String> errors){
        super(errors);
        this.serialNumber = serialNumber;
    }

    public String getMessage(){
        return this.serialNumber;
    }
}
