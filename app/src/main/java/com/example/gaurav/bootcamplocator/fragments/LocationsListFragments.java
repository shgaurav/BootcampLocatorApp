package com.example.gaurav.bootcamplocator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaurav.bootcamplocator.R;
import com.example.gaurav.bootcamplocator.adaptors.LocationAdapter;
import com.example.gaurav.bootcamplocator.services.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationsListFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationsListFragments extends Fragment {


    public LocationsListFragments() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LocationsListFragments newInstance() {
        LocationsListFragments fragment = new LocationsListFragments();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_locations_list_fragments, container, false);
        //creating our recycler view
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_locations);
        recyclerView.setHasFixedSize(true);
        //setting the adapter on the recylerview and getting location list from the data services ...
        LocationAdapter locationAdapter = new LocationAdapter(DataService.getInstance().getBootStrapLocationWithin10MilesOfZip("L5R3W1"));
        recyclerView.setAdapter(locationAdapter);
        //Setting orientation for recycler view...
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //Setting the layout for the recylerview...
        recyclerView.setLayoutManager(layoutManager);

        return v;
    }

}
