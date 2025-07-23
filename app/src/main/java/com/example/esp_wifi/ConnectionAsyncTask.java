package com.example.esp_wifi;
import android.app.Activity;
import android.os.AsyncTask;
public class ConnectionAsyncTask extends AsyncTask<String, Void, String> {
    MainActivity activity;

    public ConnectionAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String url = params[0];
        String message = params[1];
        System.out.println(url);
        System.out.println(message);
        return HttpManager.postData(url, message);
    }
    @Override
    protected void onPostExecute(String result) {
        // Optional: handle the response here
        // For example: Toast.makeText(activity, "Done", Toast.LENGTH_SHORT).show();
    }


}
