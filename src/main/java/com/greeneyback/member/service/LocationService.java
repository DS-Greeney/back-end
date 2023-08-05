package com.greeneyback.member.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LocationService {

    public static double getDistance(HashMap<String, String> myLocation, HashMap<String, String> tourspotLocation) {
        double myLa = Double.parseDouble(myLocation.get("Latitude"));
        double myLo = Double.parseDouble(myLocation.get("Longitude"));

        double tourspotLa = Double.parseDouble(tourspotLocation.get("Latitude"));
        double tourspotLo = Double.parseDouble(tourspotLocation.get("Longitude"));

        double theta = myLo - tourspotLo;
        double distance = Math.sin(degTorad(myLa)) * Math.sin(degTorad(tourspotLa)) +
                Math.cos(degTorad(myLa))*Math.cos(degTorad(tourspotLa)) * Math.cos(degTorad(theta));

        distance = Math.acos(distance);
        distance = radTodeg(distance);
        distance = distance * 60 * 1.1515 *1609.344;

        return distance;
    }

    // 10진수를 radian(라디안)으로 변환
    private static double degTorad(double deg) {
        return (deg *Math.PI/180.0);
    }

    //radian(라디안)을 10진수로 변환
    private static double radTodeg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
