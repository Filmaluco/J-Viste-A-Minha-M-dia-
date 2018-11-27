package pt.isec.gps1819g11.javisteaminhamedia.Modules;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * This module is responsible for the communication with the server, it handles all communication with the external server
 *
 * @version 0.0
 */
public class Bologna extends AsyncTask<Float, Void, Character> {

    public final static String TAG = "[BolognaModule]";
    private final static String API_URL = "https://gps.filmaluco.cloud/index.php?grade=";

    @Override
    protected Character doInBackground(Float... floats) {
        URL url;
        JSONArray jsonResponse;
        try {
            url = new URL(API_URL + floats[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Loads Response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //Converts response to JSONArray
            jsonResponse = new JSONArray(response.toString());
        }catch (Exception e){
            Log.w(TAG, "Request {"+ API_URL+floats[0]+ "Failed to reach the server \n" + e);
            return '#';
        }

        //Checks if there are any servers active
        if(jsonResponse.length() == 0){
            return '#';
        }

        try {
            String response = jsonResponse.getJSONObject(0).getString("Grade");
            return response.charAt(0);
        } catch (JSONException e) {
            Log.w(TAG, "Failed to get data from JSON object \n" + e);
        }

        return '#';
    }
}
