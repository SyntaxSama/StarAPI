package net.syntaxsama.StarAPI.File;

import java.io.File;
import java.io.IOException;

public class Files {
   public static void createFile(String filePath) {
      File file = new File(filePath);
      File parentDir = file.getParentFile();
      if (parentDir != null && !parentDir.exists()) {
         if (parentDir.mkdirs()) {
            System.out.println("Parent directories created: " + parentDir.getAbsolutePath());
         } else {
            System.err.println("Failed to create parent directories: " + parentDir.getAbsolutePath());
         }
      }

      try {
         if (file.createNewFile()) {
            System.out.println("File created: " + file.getAbsolutePath());
            file.mkdirs();
         } else {
            System.out.println("File already exists: " + file.getAbsolutePath());
         }
      } catch (IOException var4) {
         System.err.println("Failed to create file: " + file.getAbsolutePath());
         var4.printStackTrace();
      }

   }
}
