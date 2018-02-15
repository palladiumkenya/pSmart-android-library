package org.kenyahmis.psmartlibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by GMwasi on 2/14/2018.
 */

public class Util {
    // This will reference one line at a time
    String line = null;
    String output =null;

    public String ReadFile(String fileName){
        StringBuilder builder = new StringBuilder();
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                builder.append(line+"\n");
                //line = line + "\n"+ bufferedReader.readLine();
            }

            System.out.println("Read " + builder.toString().getBytes().length + " bytes");

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            return new String("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            return ex.getMessage();
        }
        return builder.toString();
    }

    public byte[] ReadBinaryFile(String fileName){
        byte[] buffer = new byte[100000];
        try {

            FileInputStream inputStream = new FileInputStream(fileName);

            int total = 0;
            int nRead = 0;
            while((nRead = inputStream.read(buffer)) != -1) {
                // Convert to String so we can display it.
                // Of course you wouldn't want to do this with
                // a 'real' binary file.
                System.out.println(new String(buffer));
                total += nRead;
            }

            // Always close files.
            inputStream.close();

            System.out.println("Read " + total + " bytes");
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return buffer;
    }

    public void WriteFile(String writeFile, String data){
        try {
            FileWriter fileWriter = new FileWriter(writeFile);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(data);

            // Always close files.
            bufferedWriter.close();

            System.out.println("Wrote " + data.getBytes().length + " bytes");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void WriteBinaryFile(String writeFile, byte[] data){
        try {
            FileOutputStream outputStream = new FileOutputStream(writeFile);

            outputStream.write(data);

            // Always close files.
            outputStream.close();

            System.out.println("Wrote " + data.length + " bytes");
        }
        catch(IOException ex) {
             ex.printStackTrace();
        }
    }

    public void ReadCompressWrite(String fileName, String writeFile){

        try {
            Compression compression = new Compression();
            String read = ReadFile(fileName);
            byte[] compressed = compression.Compress(read);
            WriteBinaryFile(writeFile, compressed);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ReadEncryptWrite(String fileName, String writeFile){

        Encryption encryption = new Encryption();
        String read = ReadFile(fileName);
        String encrypted = encryption.Encrypt(read);
        WriteFile(writeFile, encrypted);

    }

    public void ReadEncryptCompressWrite(String fileName, String writeFile){

        try {
            Encryption encryption = new Encryption();
            Compression compression = new Compression();
            String read = ReadFile(fileName);
            String encrypted = encryption.Encrypt(read);
            byte[] compressed = compression.Compress(encrypted);
            WriteBinaryFile(writeFile, compressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadDecompressDecryptWrite(String fileName, String writeFile){

        try {
            Encryption encryption = new Encryption();
            Compression compression = new Compression();
            byte[] read = ReadBinaryFile(fileName);
            String decompressed = compression.Decompress(read);
            String decrypted = encryption.Decrypt(decompressed);
            WriteFile(writeFile, decrypted);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
