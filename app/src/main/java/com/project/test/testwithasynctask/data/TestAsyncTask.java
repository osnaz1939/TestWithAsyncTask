package com.project.test.testwithasynctask.data;

import android.os.AsyncTask;
import android.util.Log;

import com.project.test.testwithasynctask.utils.TestApplicationImpl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TestAsyncTask extends AsyncTask<String, Void, String> {
    String server_response;
    JSONObject jObj;
    List<NotificationModel> notifyList = new ArrayList<NotificationModel>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'", Locale.US);
    
    @Override
    protected String doInBackground(String... strings) {
        
        URL url;
        HttpURLConnection urlConnection = null;
        
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            
            int responseCode = urlConnection.getResponseCode();
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                server_response = readStream(urlConnection.getInputStream());
                Log.v("CatalogClient", server_response);
            }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONArray News = new JSONArray(server_response);
            for(int i=0; i<News.length(); i++)
            {
                JSONObject temp = News.getJSONObject(i);
                NotificationModel testNotify = new NotificationModel(temp.getString("subject"),
                        temp.getString("text"),
                        format.parse(temp.getString("startDateTime")),
                        format.parse(temp.getString("endDateTime")));
                notifyList.add(testNotify);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    
        TestApplicationImpl.getTest().startServ(notifyList);
        Log.e("Response", "" + notifyList.size());
        super.onPostExecute(s);
    }
    
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}