package com.example.gaurav.bootcamplocator.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gaurav.bootcamplocator.R;
import com.example.gaurav.bootcamplocator.model.DevSlopes;
import com.google.android.gms.vision.text.Text;

/**
 * Created by Gaurav on 2017-04-25.
 */

public class LocationsViewHolder extends RecyclerView.ViewHolder {

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;
    public LocationsViewHolder(View itemView) {
        super(itemView);

        //Assigning the viewholder the parts of the cardview...eg. Image, title, address etc
        locationImage = (ImageView)itemView.findViewById(R.id.location_image);
        locationTitle = (TextView)itemView.findViewById(R.id.location_title);
        locationAddress = (TextView)itemView.findViewById(R.id.location_address);
    }

    public void updateUI (DevSlopes location){
        String uri = location.getLocationImgUrl();
        int resource = locationImage.getResources().getIdentifier(uri, null, locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTittle());
        locationAddress.setText(location.getLocationAddress());
    }
}
