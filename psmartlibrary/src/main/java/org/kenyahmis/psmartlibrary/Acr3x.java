package org.kenyahmis.psmartlibrary;

//audiojack library

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
