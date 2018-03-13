package org.kenyahmis.psmartlibrary.AcosCard;

/**
 * Created by GMwasi on 3/13/2018.
 */

public class Acos3 {

    public enum CODE_TYPE
    {
        AC1(0x01),
        AC2(0x02),
        AC3(0x03),
        AC4(0x04),
        AC5(0x05),
        PIN(0x06),
        IC(0x07);

        private final int _id;
        CODE_TYPE(int id){this._id = id;}
    }

    public enum INTERNAL_FILE
    {
        MCUID_FILE(0),
        MANUFACTURER_FILE(1),
        PERSONALIZATION_FILE(2),
        SECURITY_FILE(3),
        USER_FILE_MGMT_FILE(4),
        ACCOUNT_FILE(5),
        ACCOUNT_SECURITY_FILE(6),
        ATR_FILE(7);

        private final int _id;
        INTERNAL_FILE(int id){this._id = id;}
    }

    public enum CARD_INFO_TYPE
    {
        CARD_SERIAL,
        EEPROM,
        VERSION_NUMBER;
    }

    public void clearCard() throws Exception
    {

    }

    public String submitCode(CODE_TYPE codeType, String code){
        return null;
    }

    public String submitCode(CODE_TYPE codeType, byte[] code){
        return null;
    }

    public void selectFile(INTERNAL_FILE internalFile) throws Exception{
        byte[] fileID;

        if (internalFile == INTERNAL_FILE.MCUID_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x00 };
        else if (internalFile == INTERNAL_FILE.MANUFACTURER_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x01 };
        else if (internalFile == INTERNAL_FILE.PERSONALIZATION_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x02 };
        else if (internalFile == INTERNAL_FILE.SECURITY_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x03 };
        else if (internalFile == INTERNAL_FILE.USER_FILE_MGMT_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x04 };
        else if (internalFile == INTERNAL_FILE.ACCOUNT_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x05 };
        else if (internalFile == INTERNAL_FILE.ACCOUNT_SECURITY_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x06 };
        else if (internalFile == INTERNAL_FILE.ATR_FILE)
            fileID = new byte[] { (byte)0xFF, (byte)0x07 };
        else
            throw new Exception ("Invalid internal file");

        this.selectFile(fileID);
    }

    public void selectFile(byte[] fileID) throws Exception
    {

    }

    public void writeRecord (byte recordNumber, byte offset, byte[] dataToWrite) throws Exception
    {

    }

    public void configurePersonalizationFile(OptionRegister optionRegister,
                                             SecurityOptionRegister securityRegister, byte NumberOfFiles) throws Exception
    {
        try
        {
            byte[] data;

            this.selectFile(INTERNAL_FILE.PERSONALIZATION_FILE);

            data = new byte[] { optionRegister.getRawValue(), securityRegister.getRawValue(), NumberOfFiles, 0x00 };
            this.writeRecord((byte)0x00, (byte)0x00, data);

        }
        catch (Exception ex)
        {
            throw new Exception(ex.getMessage());
        }
    }


}
