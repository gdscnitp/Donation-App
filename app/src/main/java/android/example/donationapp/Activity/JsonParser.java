package android.example.donationapp.Activity;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
    private HashMap<String , String> parseJsonObject(JSONObject object){
        //Initialize hash map
        HashMap<String,String> datalist = new HashMap<>();
        try {
            // Get name from object
            String name = object.getString("name");
            // Get Latitude from object
            String latitude = object.getJSONObject("geometry").getJSONObject("location").getString("lat");
            //get Longitude from object
            String longitude = object.getJSONObject("geometry").getJSONObject("location").getString("lng");
            // get place icon
            String imageUrl = object.getString("icon");
            // get open now details will return true or false
            String openNow = object.getJSONObject("opening_hours").getString("open_now");
            // get Address
            String address = object.getString("vicinity");

            // put all value in hash map
            Log.e("Name" ,name);
            datalist.put("name" ,name);
            datalist.put("lat", latitude);
            datalist.put("lng",longitude);
            datalist.put("imageUrl",imageUrl);
            datalist.put("openNow",openNow);
            datalist.put("address",address);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return HashMAp
        return  datalist;
    }

    private List<HashMap<String,String>> parseJsonArray(JSONArray jsonArray){
        // Initialize hash map List
        List<HashMap<String,String>> dataList = new ArrayList<>();

        for (int i =0; i<jsonArray.length();i++){
            try {
                 // Initialize hash map
                HashMap<String, String> data = parseJsonObject((JSONObject) jsonArray.get(i));
                // add data in hash map list
                dataList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
       // return HashMap list
        return dataList;

    }

    public List<HashMap<String , String>> parseResult(JSONObject object){
        // Initialize json array
        JSONArray jsonArray = null;
        // Get Result Array
        try {
            jsonArray = object.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // return array
        return  parseJsonArray(jsonArray);
    }
}
