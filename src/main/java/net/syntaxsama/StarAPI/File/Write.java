package net.syntaxsama.StarAPI.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
   public static void writeToFile(String filePath, String content) {
      try {
         BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

         try {
            writer.write(content);
            System.out.println("Content written to file: " + filePath);
         } catch (Throwable var6) {
            try {
               writer.close();
            } catch (Throwable var5) {
               var6.addSuppressed(var5);
            }

            throw var6;
         }

         writer.close();
      } catch (IOException var7) {
         System.err.println("Failed to write to file: " + filePath);
         var7.printStackTrace();
      }

   }
}
