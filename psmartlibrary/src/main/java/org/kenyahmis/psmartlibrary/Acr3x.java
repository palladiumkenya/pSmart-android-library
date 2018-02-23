package org.kenyahmis.psmartlibrary;

//audiojack library
import org.kenyahmis.psmartlibrary.Models.Response;

class Acr3x implements CardReader{


     @Override
     public byte[] ReadCard() {
         return new byte[0];
     }

     @Override
     public byte[] WriteCard(byte[] data) {
         return null;
     }
 }
