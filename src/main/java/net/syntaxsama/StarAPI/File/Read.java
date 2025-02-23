package net.syntaxsama.StarAPI.File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Read {
   public static String readFromFile(String filePath) {
      StringBuilder content = new StringBuilder();

      try {
         BufferedReader reader = new BufferedReader(new FileReader(filePath));

         String line;
         try {
            while((line = reader.readLine()) != null) {
               content.append(line).append(System.lineSeparator());
            }
         } catch (Throwable var7) {
            try {
               reader.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         reader.close();
      } catch (IOException var8) {
         System.err.println("Failed to read from file: " + filePath);
         var8.printStackTrace();
      }

      return content.toString();
   }
}
