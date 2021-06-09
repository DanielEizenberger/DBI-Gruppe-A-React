package com.data;

import lombok.Data;

@Data
public class Location {
    private double lat;
    private double lon;

    public Location(){}
    public Location(double latitude, double longitude){
        lat = latitude;
        lon = longitude;
    }
}
