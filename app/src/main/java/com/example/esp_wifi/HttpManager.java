package com.example.esp_wifi;
import android.util.Log;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {
    public static String postData(String urlString, String data) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");

            OutputStream os = conn.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            return "Response Code: " + responseCode;

        } catch (Exception e) {
            Log.e("HttpManager", e.toString());
            return null;
        }
    }
}
