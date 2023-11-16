package org.sematec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        FileWriter fw = null;
        boolean bool = false;
        try  {
            File f = new File("/Users/daneshvar/Downloads/file23.txt");
//            bool = f.createNewFile();

            fw = new FileWriter(f.getPath(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append("OkOkKoko");
            bw.newLine();
            bw.write(System.lineSeparator());
            bw.flush();
            System.out.println("File created: "+ bool);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {

                }
            }
        }
    }
}