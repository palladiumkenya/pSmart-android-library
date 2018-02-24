package org.kenyahmis.psmartlibrary;

import org.kenyahmis.psmartlibrary.Models.ReadResponse;
import org.kenyahmis.psmartlibrary.Models.Response;
import org.kenyahmis.psmartlibrary.Models.WriteResponse;

import java.io.IOException;

/**
 * Created by GMwasi on 2/9/2018.
 */

public class PSmartCard implements Card {

    private CardReader _reader;
    private Encryption _encryption;
    private Compression _compression;

    public PSmartCard(CardReaderType type) {
        _compression = new Compression();
        _encryption = new Encryption();
        //TODO: create reader from type
        initializeReader(type);
    }

    void initializeReader(CardReaderType type){
        switch (type){
            case Acr3x:
                _reader = new Acr3x();
                break;

            case AcrBluetooth:
                _reader = new AcrBluetooth();
                break;

            default:
                throw new IllegalArgumentException();
        }
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

        // Mock Message
        String mockMessage = "{\n" +
                "  \"PATIENT_IDENTIFICATION\": {\n" +
                "    \"EXTERNAL_PATIENT_ID\": {\n" +
                "      \"ID\": \"110ec58a-a0f2-4ac4-8393-c866d813b8d1\",\n" +
                "      \"IDENTIFIER_TYPE\": \"GODS_NUMBER\",\n" +
                "      \"ASSIGNING_AUTHORITY\": \"MPI\",\n" +
                "      \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "    },\n" +
                "    \"INTERNAL_PATIENT_ID\": [\n" +
                "      {\n" +
                "        \"ID\": \"12345678-ADFGHJY-0987654-NHYI890\",\n" +
                "        \"IDENTIFIER_TYPE\": \"CARD_SERIAL_NUMBER\",\n" +
                "        \"ASSIGNING_AUTHORITY\": \"CARD_REGISTRY\",\n" +
                "        \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"ID\": \"12345678\",\n" +
                "        \"IDENTIFIER_TYPE\": \"HEI_NUMBER\",\n" +
                "        \"ASSIGNING_AUTHORITY\": \"MCH\",\n" +
                "        \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"ID\": \"12345678\",\n" +
                "        \"IDENTIFIER_TYPE\": \"CCC_NUMBER\",\n" +
                "        \"ASSIGNING_AUTHORITY\": \"CCC\",\n" +
                "        \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"ID\": \"001\",\n" +
                "        \"IDENTIFIER_TYPE\": \"HTS_NUMBER\",\n" +
                "        \"ASSIGNING_AUTHORITY\": \"HTS\",\n" +
                "        \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"ID\": \"12345678\",\n" +
                "        \"IDENTIFIER_TYPE\": \"PMTCT_NUMBER\",\n" +
                "        \"ASSIGNING_AUTHORITY\": \"PMTCT\",\n" +
                "        \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"PATIENT_NAME\": {\n" +
                "      \"FIRST_NAME\": \"THERESA\",\n" +
                "      \"MIDDLE_NAME\": \"MAY\",\n" +
                "      \"LAST_NAME\": \"WAIRIMU\"\n" +
                "    },\n" +
                "    \"DATE_OF_BIRTH\": \"20171111\",\n" +
                "    \"DATE_OF_BIRTH_PRECISION\": \"ESTIMATED/EXACT\",\n" +
                "    \"SEX\": \"F\",\n" +
                "    \"DEATH_DATE\": \"\",\n" +
                "    \"DEATH_INDICATOR\": \"N\",\n" +
                "    \"PATIENT_ADDRESS\": {\n" +
                "      \"PHYSICAL_ADDRESS\": {\n" +
                "        \"VILLAGE\": \"KWAKIMANI\",\n" +
                "        \"WARD\": \"KIMANINI\",\n" +
                "        \"SUB_COUNTY\": \"KIAMBU EAST\",\n" +
                "        \"COUNTY\": \"KIAMBU\",\n" +
                "        \"NEAREST_LANDMARK\": \"KIAMBU EAST\"\n" +
                "      },\n" +
                "      \"POSTAL_ADDRESS\": \"789 KIAMBU\"\n" +
                "    },\n" +
                "    \"PHONE_NUMBER\": \"254720278654\",\n" +
                "    \"MARITAL_STATUS\": \"\",\n" +
                "    \"MOTHER_DETAILS\": {\n" +
                "      \"MOTHER_NAME\": {\n" +
                "        \"FIRST_NAME\": \"WAMUYU\",\n" +
                "        \"MIDDLE_NAME\": \"MARY\",\n" +
                "        \"LAST_NAME\": \"WAITHERA\"\n" +
                "      },\n" +
                "      \"MOTHER_IDENTIFIER\": [\n" +
                "        {\n" +
                "          \"ID\": \"1234567\",\n" +
                "          \"IDENTIFIER_TYPE\": \"NATIONAL_ID\",\n" +
                "          \"ASSIGNING_AUTHORITY\": \"GOK\",\n" +
                "          \"ASSIGNING_FACILITY\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"ID\": \"12345678\",\n" +
                "          \"IDENTIFIER_TYPE\": \"NHIF\",\n" +
                "          \"ASSIGNING_AUTHORITY\": \"NHIF\",\n" +
                "          \"ASSIGNING_FACILITY\": \"\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"ID\": \"12345-67890\",\n" +
                "          \"IDENTIFIER_TYPE\": \"CCC_NUMBER\",\n" +
                "          \"ASSIGNING_AUTHORITY\": \"CCC\",\n" +
                "          \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"ID\": \"ABC567\",\n" +
                "          \"IDENTIFIER_TYPE\": \"ANC_NUMBER\",\n" +
                "          \"ASSIGNING_AUTHORITY\": \"ANC\",\n" +
                "          \"ASSIGNING_FACILITY\": \"10829\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"NEXT_OF_KIN\": [\n" +
                "    {\n" +
                "      \"NOK_NAME\": {\n" +
                "        \"FIRST_NAME\": \"WAIGURU\",\n" +
                "        \"MIDDLE_NAME\": \"KIMUTAI\",\n" +
                "        \"LAST_NAME\": \"WANJOKI\"\n" +
                "      },\n" +
                "      \"RELATIONSHIP\": \"**AS DEFINED IN GREENCARD\",\n" +
                "      \"ADDRESS\": \"4678 KIAMBU\",\n" +
                "      \"PHONE_NUMBER\": \"25489767899\",\n" +
                "      \"SEX\": \"F\",\n" +
                "      \"DATE_OF_BIRTH\": \"19871022\",\n" +
                "      \"CONTACT_ROLE\": \"T\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"HIV_TESTS\": [\n" +
                "    {\n" +
                "      \"DATE\": \"20180101\",\n" +
                "      \"RESULT\": \"POSITIVE/NEGATIVE/INCONCLUSIVE\",\n" +
                "      \"TYPE\": \"SCREENING/CONFIRMATORY\",\n" +
                "      \"FACILITY\": \"10829\",\n" +
                "      \"STRATEGY\": \"HP/NP/VI/VS/HB/MO/O\",\n" +
                "      \"PROVIDER_DETAILS\": {\n" +
                "        \"NAME\": \"AFYA JIJINI***STILL IN REVIEW\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"IMMUNIZATIONS\": [\n" +
                "    {\n" +
                "      \"NAME\": \"BCG/OPV_AT_BIRTH/OPV1/OPV2/OPV3/PCV10-1/PCV10-2/PCV10-3/PENTA1/PENTA2/PENTA3/MEASLES6/MEASLES9/MEASLES18/ROTA1/ROTA2\",\n" +
                "      \"DATE_ADMINISTERED\": \"20180101\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"MERGE_PATIENT_INFORMATION\": {\n" +
                "    \"PRIOR_INTERNAL_IDENTIFIERS\": [\n" +
                "      {\n" +
                "        \"ID\": \"12345678-67676767-0987654-XXXXYYYY\",\n" +
                "        \"IDENTIFIER_TYPE\": \"CARD_SERIAL_NUMBER\",\n" +
                "        \"ASSIGNING_AUTHORITY\": \"CARD_REGISTRY\",\n" +
                "        \"ASSIGNING_FACILITY\": \"12345\",\n" +
                "        \"REPLACEMENT_REASON\": \"LOST\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"CARD_DETAILS\": {\n" +
                "    \"STATUS\": \"ACTIVE/INACTIVE\",\n" +
                "    \"REASON\": \"\",\n" +
                "    \"LAST_UPDATED\": \"20180101\",\n" +
                "    \"LAST_UPDATED_FACILITY\": \"10829\"\n" +
                "  }\n" +
                "}";

        Response response = new ReadResponse(mockMessage, null);
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
        String mockMessage = "{\"SerialNumber\" : \"7654554333345-4323455\"}";
        Response response = new WriteResponse(mockMessage, null);
        // TODO build response message
        return response;
    }
}
