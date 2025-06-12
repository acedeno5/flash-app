package edu.vassar.cmpu203.flash.model;
import java.util.Calendar;

public class Trips {
    private double startLatitude;
    private double startLongitude;
    private double endLatitude;
    private double endLongitude;

    public Trips(){}


    public String startTrip(double startLatitude, double startLongitude) {
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        return "Start coordinates set: " + startLatitude + ", " + startLongitude;
    }

    public String endTrip(double endLatitude, double endLongitude) {
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        return "End coordinates set: " + endLatitude + ", " + endLongitude;
    }
    public double getDistance() {
        double startLatitudeRadians = Math.toRadians(startLatitude);
        double endLatitudeRadians = Math.toRadians(endLatitude);
        double latDiff = Math.toRadians(endLatitude - startLatitude);
        double lonDiff = Math.toRadians(endLongitude - startLongitude);
        double a = Math.pow(Math.sin(latDiff / 2), 2) + Math.cos(startLatitudeRadians) * Math.cos(endLatitudeRadians) * Math.pow(Math.sin(lonDiff / 2), 2);
        return 2 * 3958.8 * Math.asin(Math.sqrt(a));
    }

    public static String convertToStandardTime(String militaryTime) {
        String[] parts = militaryTime.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String period = hour >= 12 ? "PM" : "AM";
        hour = (hour % 12 == 0) ? 12 : hour % 12;
        return String.format("%02d:%02d %s", hour, minute, period);
    }
}
