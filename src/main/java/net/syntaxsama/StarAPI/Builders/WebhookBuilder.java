package net.syntaxsama.StarAPI.Builders;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebhookBuilder {
   private final String webhookUrl;
   private String message;

   private WebhookBuilder(String webhookUrl) {
      this.webhookUrl = webhookUrl;
   }

   public static WebhookBuilder create(String webhookUrl) {
      return new WebhookBuilder(webhookUrl);
   }

   public WebhookBuilder addMessage(String message) {
      this.message = message;
      return this;
   }

   public void build() {
      try {
         URL url = new URL(this.webhookUrl);
         HttpURLConnection connection = (HttpURLConnection)url.openConnection();
         connection.setRequestMethod("POST");
         connection.setRequestProperty("Content-Type", "application/json");
         connection.setDoOutput(true);
         String payload = "{\"content\":\"" + this.message + "\"}";
         OutputStream outputStream = connection.getOutputStream();

         try {
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
         } catch (Throwable var8) {
            if (outputStream != null) {
               try {
                  outputStream.close();
               } catch (Throwable var7) {
                  var8.addSuppressed(var7);
               }
            }

            throw var8;
         }

         if (outputStream != null) {
            outputStream.close();
         }

         connection.disconnect();
      } catch (Exception var9) {
         var9.printStackTrace();
      }

   }
}
