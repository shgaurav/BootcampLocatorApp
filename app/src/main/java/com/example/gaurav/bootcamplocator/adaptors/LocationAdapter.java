package com.example.gaurav.bootcamplocator.adaptors;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gaurav.bootcamplocator.R;
import com.example.gaurav.bootcamplocator.holders.LocationsViewHolder;
import com.example.gaurav.bootcamplocator.model.DevSlopes;

import java.util.ArrayList;

/**
 * Created by Gaurav on 2017-04-25.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationsViewHolder>{
    private ArrayList<DevSlopes> locations;
    public LocationAdapter(ArrayList<DevSlopes> locations) {
        this.locations = locations;
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        final DevSlopes location = locations.get(position);
        holder.updateUI(location);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Load Details page
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlocation, parent, false);
        return new LocationsViewHolder(card);
    }
}
