package com.example.gaurav.bootcamplocator.services;

import com.example.gaurav.bootcamplocator.model.DevSlopes;

import java.util.ArrayList;

/**
 * Created by Gaurav on 2017-04-25.
 */

public class DataService {
    //declaring zips to compare searched zips with...
    private String zip1 = "L5R3W1";
    private String zip2 = "L4W0B8";
    private String zip3 = "L5R 1B2";
    private static final DataService instance = new DataService();

    public static DataService getInstance() {

        return instance;
    }

    private DataService() {


    }

    public ArrayList<DevSlopes> getBootStrapLocationWithin10MilesOfZip(String zipCode)
    {
        //pretending we are downloading data from the server.
        ArrayList<DevSlopes> list = new ArrayList<>();

        if (zipCode.equalsIgnoreCase(zip1) || zipCode.equalsIgnoreCase(zip3))
        {
            list.add(new DevSlopes(43.615818f, -79.654070f, "Elementary School", "Bristol Road Middle School, Mississauga, ON L4Z3V5", "slo"));
            list.add(new DevSlopes(43.614245f, -79.661309f, "Cafe Coding", "44 Bristol Rd E, Mississauga, ON L4Z3K8", "blo"));
            list.add(new DevSlopes(43.608001f, -79.652285f, "East Side Tower", "5033 Hurontario St, Mississauga, ON L4Z3X7", "clo"));
        }
        else if (zipCode.equalsIgnoreCase(zip2))
        {
            list.add(new DevSlopes(43.627412f, -79.627342f, "Studio 89 - FreeCodersCamp", "1065 Canadian Place, Unit #104, ON L4W0B8", "hlo"));
            list.add(new DevSlopes(43.627512f, -79.628824f, "Starbuck Coding Session", "1016 Eglinton Ave E, Mississauga, ON L4W1K3", "ylo"));
            list.add(new DevSlopes(43.625783f, -79.627669f, "Tim Horton Coding Session Meetup", "4595 Tomken Rd, Mississauga, ON L4W1J9", "tlo"));
        }

        return list;

    }
}
