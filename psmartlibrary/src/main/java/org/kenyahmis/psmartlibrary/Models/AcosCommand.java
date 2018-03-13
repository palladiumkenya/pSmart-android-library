package org.kenyahmis.psmartlibrary.Models;

/**
 * Created by Muhoro on 3/12/2018.
 */

public class AcosCommand {
    public final static String START_SESSION = "80 84 00 00 08";
    public final static String BINARY_READ = "";
    public final static String AUTHENTICATION_KEY = "FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF FF";
    public final static String CLEAR_CARD = "80 30 00 00 00";
    public final static String SELECT_FILE = "80 A4 00 00 02 FF 04";
    public final static String READ_BINARY = "80 B0 00 FF FF";
    public final static String CREATE_BINARY_FILE = "00 ";
}
