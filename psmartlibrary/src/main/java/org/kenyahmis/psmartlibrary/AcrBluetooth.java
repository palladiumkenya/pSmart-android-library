package org.kenyahmis.psmartlibrary;

import android.util.Log;

import com.acs.bluetooth.Acr1255uj1Reader;
import com.acs.bluetooth.Acr3901us1Reader;
import com.acs.bluetooth.BluetoothReader;

import org.kenyahmis.psmartlibrary.Models.AcosCardResponse;
import org.kenyahmis.psmartlibrary.Models.AcosCommand;
import org.kenyahmis.psmartlibrary.Models.HexString;

import java.util.ArrayList;

/**
 * Created by GMwasi on 2/10/2018.
 */

class AcrBluetooth implements CardReader {


    private boolean authenticated = false;
    private boolean apduAvailable = false;
    private String responseInHexString = null;
    private byte[] byteResponse = null;
    private boolean successfulResponse = false;

    private String TAG = "BluetoothReader";
    private BluetoothReader bluetoothReader;

    AcrBluetooth(BluetoothReader reader){
        if(reader != null) bluetoothReader = reader;
        else throw new NullPointerException();

        registerReaderListeners();
    }

    @Override
    public AcosCardResponse ReadCard() {

        authenticate();
        bluetoothReader.powerOnCard();
        if(!checkIfAuthenticated()){

        }
        selectFile();
        read();

        int max = 5;
        int counter = 0;
        while(!apduAvailable) {
            try{
                Thread.sleep(1000);
                counter+=1;
                if(counter == max) break;

            } catch (InterruptedException ex){}
        }

        if(!apduAvailable)
        {
            return new AcosCardResponse(null, null, new ArrayList<String>(){{add("Response not available");}});
        }

        else
        {
            if(successfulResponse){
                return new AcosCardResponse(byteResponse, responseInHexString, null);
            }
            return new AcosCardResponse(null, null, new ArrayList<String>(){{add(responseInHexString);}});
        }

    }

    @Override
    public byte[] WriteCard(byte[] data) {
        authenticate();
        bluetoothReader.powerOnCard();
        if(!checkIfAuthenticated()){

        }
        selectFile();
        write();
        return null;
    }

    private void registerReaderListeners(){
        setOnBatteryStatusChangeListener();
        setOnCardStatusListener();
        setOnAuthenticationCompleteListener();
        setOnAtrAvailableListener();
        setOnPowerOffCompleteListener();
        setOnResponseApduAvailableListener();
        setOnEscapeResponseAvailableListener();
        setOnDeviceInfoAvailableListener();
        setOnBatteryLevelAvailableListener();
        setOnCardStatusAvailableListener();
        setOnEnableNotificationCompleteListener();

    }

    // Listeners
    private void setOnBatteryStatusChangeListener(){
        if (bluetoothReader instanceof Acr3901us1Reader) {
            ((Acr3901us1Reader) bluetoothReader)
                    .setOnBatteryStatusChangeListener(new Acr3901us1Reader.OnBatteryStatusChangeListener() {

                        @Override
                        public void onBatteryStatusChange(
                                BluetoothReader bluetoothReader,
                                final int batteryStatus) {

                            Log.i(TAG, "mBatteryStatusListener data: "
                                    + batteryStatus);
                        }

                    });
        } else if (bluetoothReader instanceof Acr1255uj1Reader) {
            ((Acr1255uj1Reader) bluetoothReader)
                    .setOnBatteryLevelChangeListener(new Acr1255uj1Reader.OnBatteryLevelChangeListener() {

                        @Override
                        public void onBatteryLevelChange(
                                BluetoothReader bluetoothReader,
                                final int batteryLevel) {

                            Log.i(TAG, "mBatteryLevelListener data: "
                                    + batteryLevel);
                        }

                    });
        }
    }

    private void setOnCardStatusListener(){
        bluetoothReader
                .setOnCardStatusChangeListener(new BluetoothReader.OnCardStatusChangeListener() {

                    @Override
                    public void onCardStatusChange(
                            BluetoothReader bluetoothReader, final int sta) {

                        Log.i(TAG, "mCardStatusListener sta: " + sta);
                    }
                });
    }

    private void setOnAuthenticationCompleteListener(){
        bluetoothReader
                .setOnAuthenticationCompleteListener(new BluetoothReader.OnAuthenticationCompleteListener() {

                    @Override
                    public void onAuthenticationComplete(
                            BluetoothReader bluetoothReader, final int errorCode) {
                        // TODO:
                        authenticated = true;
                    }
                });
    }

    private void setOnAtrAvailableListener(){
        bluetoothReader
                .setOnAtrAvailableListener(new BluetoothReader.OnAtrAvailableListener() {

                    @Override
                    public void onAtrAvailable(BluetoothReader bluetoothReader,
                                               final byte[] atr, final int errorCode) {

                        // TODO:
                    }

                });
    }

    private void setOnPowerOffCompleteListener(){
        bluetoothReader
                .setOnCardPowerOffCompleteListener(new BluetoothReader.OnCardPowerOffCompleteListener() {

                    @Override
                    public void onCardPowerOffComplete(
                            BluetoothReader bluetoothReader, final int result) {

                        // TODO:
                    }

                });
    }

    private void setOnResponseApduAvailableListener(){
        bluetoothReader
                .setOnResponseApduAvailableListener(new BluetoothReader.OnResponseApduAvailableListener() {

                    @Override
                    public void onResponseApduAvailable(
                            BluetoothReader bluetoothReader, final byte[] apdu,
                            final int errorCode) {
                        // TODO:
                        responseInHexString = getResponseString(apdu, errorCode);
                        apduAvailable = true;
                    }

                });
    }

    private void setOnEscapeResponseAvailableListener(){
        bluetoothReader
                .setOnEscapeResponseAvailableListener(new BluetoothReader.OnEscapeResponseAvailableListener() {

                    @Override
                    public void onEscapeResponseAvailable(
                            BluetoothReader bluetoothReader,
                            final byte[] response, final int errorCode) {

                        // TODO:
                    }

                });
    }

    private void setOnDeviceInfoAvailableListener(){
        bluetoothReader
                .setOnDeviceInfoAvailableListener(new BluetoothReader.OnDeviceInfoAvailableListener() {

                    @Override
                    public void onDeviceInfoAvailable(
                            BluetoothReader bluetoothReader, final int infoId,
                            final Object o, final int status) {

                    }

                });
    }

    private void setOnBatteryLevelAvailableListener(){
        if (bluetoothReader instanceof Acr1255uj1Reader) {
            ((Acr1255uj1Reader) bluetoothReader)
                    .setOnBatteryLevelAvailableListener(new Acr1255uj1Reader.OnBatteryLevelAvailableListener() {

                        @Override
                        public void onBatteryLevelAvailable(
                                BluetoothReader bluetoothReader,
                                final int batteryLevel, int status) {
                            Log.i(TAG, "mBatteryLevelListener data: "
                                    + batteryLevel);


                        }

                    });
        }

        /* Handle on battery status available. */
        if (bluetoothReader instanceof Acr3901us1Reader) {
            ((Acr3901us1Reader) bluetoothReader)
                    .setOnBatteryStatusAvailableListener(new Acr3901us1Reader.OnBatteryStatusAvailableListener() {

                        @Override
                        public void onBatteryStatusAvailable(
                                BluetoothReader bluetoothReader,
                                final int batteryStatus, int status) {
                            // TODO:
                        }

                    });
        }
    }

    private void setOnCardStatusAvailableListener(){
        bluetoothReader
                .setOnCardStatusAvailableListener(new BluetoothReader.OnCardStatusAvailableListener() {

                    @Override
                    public void onCardStatusAvailable(
                            BluetoothReader bluetoothReader,
                            final int cardStatus, final int errorCode) {

                        // TODO:
                    }

                });
    }

    private void setOnEnableNotificationCompleteListener(){
        bluetoothReader
                .setOnEnableNotificationCompleteListener(new BluetoothReader.OnEnableNotificationCompleteListener() {

                    @Override
                    public void onEnableNotificationComplete(
                            BluetoothReader bluetoothReader, final int result) {

                        // TODO:
                    }

                });
    }

    // Authentication
    private boolean authenticate(){
        byte masterKey[] = Utils.getTextinHexBytes(AcosCommand.AUTHENTICATION_KEY);
        if(masterKey != null && masterKey.length > 0)
            return bluetoothReader.authenticate(masterKey);
        return false;
    }

    // start session
    private boolean startSession(){
        byte command[] = Utils.getTextinHexBytes(AcosCommand.START_SESSION);
        if(command != null && command.length > 0)
            return bluetoothReader.transmitApdu(command);
        return false;
    }

    // select file
    private boolean selectFile(){
        byte command[] = Utils.getTextinHexBytes(AcosCommand.SELECT_FILE);
        if(command != null && command.length > 0)
            return bluetoothReader.transmitApdu(command);
        return false;
    }

    private boolean checkIfAuthenticated(){
        int limit = 5;
        int counter = 0;
        while(!authenticated)
        {
            try{
                if(counter == limit)
                    break;
                Thread.sleep(1000);
                counter+=1;
            }
            catch (Exception ex){ex.printStackTrace();}
        }
        return authenticated;
    }

    private boolean read(){
        byte command[] = Utils.getTextinHexBytes(AcosCommand.READ_BINARY);
        if(command != null && command.length > 0)
            return bluetoothReader.transmitApdu(command);
        return false;
    }

    private boolean write(){
        byte command[] = Utils.getTextinHexBytes(AcosCommand.WRITE_BINARY);
        if(command != null && command.length > 0)
            return bluetoothReader.transmitApdu(command);
        return false;
    }

    private String getResponseString(byte[] response, int errorCode) {
        if (errorCode == BluetoothReader.ERROR_SUCCESS) {
            if (response != null && response.length > 0) {
                successfulResponse = true;
                return Utils.toHexString(response);
            }
            return "";
        }
        return getErrorString(errorCode);
    }

    private String getErrorString(int errorCode) {
        if (errorCode == BluetoothReader.ERROR_SUCCESS) {
            return "";
        } else if (errorCode == BluetoothReader.ERROR_INVALID_CHECKSUM) {
            return "The checksum is invalid.";
        } else if (errorCode == BluetoothReader.ERROR_INVALID_DATA_LENGTH) {
            return "The data length is invalid.";
        } else if (errorCode == BluetoothReader.ERROR_INVALID_COMMAND) {
            return "The command is invalid.";
        } else if (errorCode == BluetoothReader.ERROR_UNKNOWN_COMMAND_ID) {
            return "The command ID is unknown.";
        } else if (errorCode == BluetoothReader.ERROR_CARD_OPERATION) {
            return "The card operation failed.";
        } else if (errorCode == BluetoothReader.ERROR_AUTHENTICATION_REQUIRED) {
            return "Authentication is required.";
        } else if (errorCode == BluetoothReader.ERROR_LOW_BATTERY) {
            return "The battery is low.";
        } else if (errorCode == BluetoothReader.ERROR_CHARACTERISTIC_NOT_FOUND) {
            return "Error characteristic is not found.";
        } else if (errorCode == BluetoothReader.ERROR_WRITE_DATA) {
            return "Write command to reader is failed.";
        } else if (errorCode == BluetoothReader.ERROR_TIMEOUT) {
            return "Timeout.";
        } else if (errorCode == BluetoothReader.ERROR_AUTHENTICATION_FAILED) {
            return "Authentication is failed.";
        } else if (errorCode == BluetoothReader.ERROR_UNDEFINED) {
            return "Undefined error.";
        } else if (errorCode == BluetoothReader.ERROR_INVALID_DATA) {
            return "Received data error.";
        } else if (errorCode == BluetoothReader.ERROR_COMMAND_FAILED) {
            return "The command failed.";
        }
        return "Unknown error.";
    }
}
