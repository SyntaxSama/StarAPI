package net.syntaxsama.StarAPI.File;

import java.io.File;

public class Folders {
   public static void createFolder(String folderPath) {
      File folder = new File(folderPath);
      if (!folder.exists()) {
         if (folder.mkdirs()) {
            System.out.println("Folder created: " + folder.getAbsolutePath());
            folder.mkdirs();
         } else {
            System.err.println("Failed to create folder: " + folder.getAbsolutePath());
         }
      } else {
         System.out.println("Folder already exists: " + folder.getAbsolutePath());
      }

   }
}
