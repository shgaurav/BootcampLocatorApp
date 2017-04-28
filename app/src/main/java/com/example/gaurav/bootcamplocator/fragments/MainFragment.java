package com.example.gaurav.bootcamplocator.fragments;


import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gaurav.bootcamplocator.R;
import com.example.gaurav.bootcamplocator.activities.MapsActivity;
import com.example.gaurav.bootcamplocator.model.DevSlopes;
import com.example.gaurav.bootcamplocator.services.DataService;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private LocationsListFragments locationsListFragments;
    private String zip;


    public MainFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        //loading the map...
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //loading the locations list frangment on the main fragment...
        locationsListFragments =  (LocationsListFragments) getActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.container_locations_list);
        if(locationsListFragments == null){
            locationsListFragments = LocationsListFragments.newInstance();
            getActivity().
                    getSupportFragmentManager().
                    beginTransaction().
                    add(R.id.container_locations_list, locationsListFragments).
                    commit();
        }

        //declaring the searchbar for zipcode entry in code...
        final EditText zipText = (EditText) v.findViewById(R.id.zipText);
        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Looking for input on the searchbar and checking when enter is pressed...
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    //perform action on key pressed...
                    zip = zipText.getText().toString();
                    Toast.makeText(getContext(), zip, Toast.LENGTH_SHORT).show();
                    //Dismiss the keyboard
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(zipText.getWindowToken(), 0);
                    updateMapForZip(zip);
                    showList();
                    return true;
                }
                return false;
            }
        });
        //Show the locations recycler view/list to the user when the user enters a zip...
        hideList();
        return v;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
    }

    //Setting marker on the map to users current location...
    public void setUserMarkers(LatLng latLng){
        if(userMarker == null){
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mMap.addMarker(userMarker);
            Log.v("DONKEY", "Current Location");
        }
        //geocoding to find zip of current users location retrieved from the phone...
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<android.location.Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            String postal_code = addresses.get(0).getPostalCode();
            updateMapForZip(postal_code);
        }
        catch (IOException exception){
            //catching IO.
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private void updateMapForZip(String zip_code){

        Toast.makeText(getContext(), zip_code, Toast.LENGTH_SHORT).show();
        ArrayList<DevSlopes> locations = DataService.getInstance().getBootStrapLocationWithin10MilesOfZip(zip_code);

        for (int x = 0; x < locations.size(); x++){
            DevSlopes loc = locations.get(x);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude()));
            marker.title(loc.getLocationTittle());
            marker.snippet(loc.getLocationAddress());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));
            mMap.addMarker(marker);
        }

    }

    //Hide location list fragment function ...
    private void hideList(){
        getActivity().getSupportFragmentManager().beginTransaction().hide(locationsListFragments).commit();
    }

    //Show location list fragment function ...
    private void showList(){
        getActivity().getSupportFragmentManager().beginTransaction().show(locationsListFragments).commit();
    }
}
