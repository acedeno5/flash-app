import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.ListIterator;

public class Trips {
    String date;
    String startTime;
    String endTime;
    boolean verification;
    double timer;
    double startLatitude;
    double startLongitude;
    double endLatitude;
    double endLongitude;
    double duration;
    double avgMPH;

    // time will be in military time for this iteration
    public String startTrip(double startLatitude, double startLongitude, String startTime) {
        // have user input for latitude/longitude
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.startTime = startTime;
        return "Your start latitude is " + startLatitude +
                " and start longitude is " + startLongitude +
                " and start time is " + startTime;

    }

    public String endTrip(double endLatitude, double endLongitude, String endTime) {
        this.endLongitude = endLongitude;
        this.endLatitude = endLatitude;
        this.endTime = endTime;
        return "Your end latitude is " + endLatitude +
                " and end longitude is " + endLongitude +
                " and end time is " + endTime;

    }

    public double getDistance(double startLatitude, double startLongitude, double endLatitude, double endLongitude) {
        //haversine logic for distance +total time+ avg speed
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
        double startLatitudeRadians = Math.toRadians(startLatitude);
        double endLatitudeRadians = Math.toRadians(endLongitude);
        double distanceBetweenLats = Math.toRadians(endLatitude - startLatitude);
        double distanceBetweenLongs = Math.toRadians(endLongitude - startLongitude);
        double a = Math.pow(Math.sin(distanceBetweenLats/2), 2) +
                Math.pow(Math.sin(distanceBetweenLongs/2), 2) *
                        Math.cos(startLatitudeRadians) *
                        Math.cos(endLatitudeRadians);
        double radius = 3958.8;
        double c = 2 * Math.asin(Math.sqrt(a));
        double haversineFormula = radius * c;
        return haversineFormula ;
    }

    public double calcTimeDifference(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");
        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);
        double startTotalHours = startHour + startMinute/ 60.0;
        double endTotalHours = endHour + endMinute/ 60.0;
        if(endTotalHours < startTotalHours){
            endTotalHours += 24;
        }
        return endTotalHours - startTotalHours;
    }


}