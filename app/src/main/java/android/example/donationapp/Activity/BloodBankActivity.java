package android.example.donationapp.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.example.donationapp.Adapters.BloodBankAdapter;
import android.example.donationapp.Model.BloodBankAdapterClass;
import android.example.donationapp.R;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BloodBankActivity extends AppCompatActivity implements OnMapReadyCallback {
    RecyclerView recyclerView;
    BloodBankAdapter bloodBankAdapter;
    ArrayList<BloodBankAdapterClass> bloodBankAdapterClassList;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;
    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        btn = (TextView) findViewById(R.id.searchbtn);
        recyclerView = findViewById(R.id.blood_bank_recycler_view);
        bloodBankAdapterClassList = new ArrayList<>();
        bloodBankAdapter = new BloodBankAdapter(bloodBankAdapterClassList,BloodBankActivity.this);

        // Assign Variables
        try {

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        }catch (Exception e){
            Log.e("SupportMapInitializatin", "onCreate: Exception:"+e.getMessage() );
        }


        // Initialize fused location provider client
        try {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        }catch (Exception e){
            Log.e("Fusedlocation Trycatch:" , e.getMessage());
        }

        // Check Permission

        if (ActivityCompat.checkSelfPermission(BloodBankActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // when permission granted
            // call method
            getCurrentLocation();
        } else {
            // When permission denied
            // Request Permission
            ActivityCompat.requestPermissions(BloodBankActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        Log.e("In url Lat: ", String.valueOf(currentLat));
        Log.e("In url Long: ", String.valueOf(currentLong));
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                "?location=" + currentLat + "," + currentLong + "&radius=5000" + "&type=hospital" +
                "&sensor=true" + "&key=" + "";
        Log.e("Url:", "onCreate: " + url );

//      //Execute place task method to download json data
        try {

        new PlaceTask().execute(url);
        }catch (Exception e){
            Log.e( "Inside NewPlaceTask() ", "onCreate: " +e.getMessage());
        }
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BloodBankActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bloodBankAdapter);
    }

    private void getCurrentLocation() {
        // Initialize task Location


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(BloodBankActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // When Success
                if(location != null){
                    //when Location is not equal to null
                    // Get current Latitude
                    currentLat = location.getLatitude();
                    Log.e("CurrentLat", ""+currentLat );
                    // Get current Longitude
                    currentLong = location.getLongitude();
                    Log.e("CurrentLong ", ""+currentLong );
                    // Sync map
                    try {

                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            // When map is ready
                            map = googleMap;
                            LatLng latLng = new LatLng(currentLat , currentLong);
                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latLng);
                            markerOptions.title("You are here");
                            markerOptions.visible(true);
                            Log.e("Lat: ", String.valueOf(currentLat));
                            Log.e("Long: ", String.valueOf(currentLong));
                            // zoom current Location on map
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat,currentLong),14));
                            map.addMarker(markerOptions);
                        }
                    });
                    }catch (Exception e){
                        Log.e("InsideGetMapAsyncCatch", "Exception:"+e.getMessage() );
                    }

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("Exception: ", e.getMessage());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // When permission Granted
                // Call method
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
//        map = googleMap;
//        LatLng latLng = new LatLng(currentLat , currentLong);
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("You are here");
//        Log.e("Lat: ", String.valueOf(currentLat));
//        Log.e("Long: ", String.valueOf(currentLong));
//        // zoom current Location on map
//        map.clear();
//        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat,currentLong),10));
//        map.addMarker(markerOptions);
    }


    private class PlaceTask extends AsyncTask<String ,Integer , String> {

        @Override
        protected String doInBackground(String... strings) {
            String data = null;
            try {
                // Initialize data
                data = downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            // Execute parser task
            Log.e("PlaceTask postExecute :", "onPostExecute: "+s);
            new ParserTask().execute(s);
        }
    }

    private String downloadUrl(String string) throws IOException {


        // Initialize URl
        URL url = new URL(string);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // Connect connection
        connection.connect();
        //Initialize Input Stream
        InputStream stream = connection.getInputStream();
        //Initialize Buffer reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        //Initialize String Builder
        StringBuilder builder = new StringBuilder();
        //Initialize string variable
        String line = "";
        // Use While loop
        while((line = reader.readLine()) != null){
            //Append line
            builder.append(line);
        }
        // Get Append Data
        String data = builder.toString();
        // Close reader
        reader.close();
        // return data
        return data;


    }

    private class ParserTask extends AsyncTask<String , Integer, List<HashMap<String ,String>>>{


        @Override
        protected List<HashMap<String, String>> doInBackground(String... strings) {
            // Create json parser class
            JsonParser jsonParser = new JsonParser();
            // Initialize hashMap List
            List<HashMap<String , String>> mapList =null;
            JSONObject object = null;
            try {
                // Initialize Json Object
                object =new JSONObject(strings[0]);
                // Parse Json Object
                mapList =jsonParser.parseResult(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Return map List
            return mapList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> hashMaps) {
            // Clear Map
            if(map != null){
                map.clear();}
            LatLng latLng1 = new LatLng(currentLat , currentLong);
            MarkerOptions markerOptions1 = new MarkerOptions();
            markerOptions1.position(latLng1);
            markerOptions1.title("You are here");
            Log.e("Lat: ", String.valueOf(currentLat));
            Log.e("Long: ", String.valueOf(currentLong));
            // zoom current Location on map
            if(map != null){
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLat,currentLong),14));
            map.addMarker(markerOptions1);}
            // Use for loop
            bloodBankAdapterClassList.clear();
            for(int i =0 ; i<hashMaps.size(); i++){
                HashMap<String , String> hashMapList = hashMaps.get(i);
                try {
                // Get Latitude
                double lat = Double.parseDouble(hashMapList.get("lat"));

                // Get Longitude
                double lng = Double.parseDouble(hashMapList.get("lng"));
                // get name
                String name = hashMapList.get("name");
                Log.e("Name of blood banks: ",name);
                // get imageUrl
                String imageUrl = hashMapList.get("imageUrl");
                // get address
                String address = hashMapList.get("address");
                // get status open or closed\
                String status = hashMapList.get("openNow");

                // concat latitude and longitude
                LatLng latLng = new LatLng(lat , lng);
                // Initialize Marker Options
                MarkerOptions options = new MarkerOptions();
                // Set position
                options.position(latLng);
                // set title
                options.title(name);
                // add marker on map

                    bloodBankAdapterClassList.add(new BloodBankAdapterClass(name ,address,imageUrl));
                    map.addMarker(options);

                }catch (Exception e){
                    Log.e("InsideParserTask", "onPostExecute: " +e.getMessage() );
                }
                Log.e("list of blood banks:", "onPostExecute: "+bloodBankAdapterClassList.size() );
                bloodBankAdapter.notifyDataSetChanged();



            }
        }
    }
}
