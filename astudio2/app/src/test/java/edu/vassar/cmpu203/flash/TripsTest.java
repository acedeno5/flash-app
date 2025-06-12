package edu.vassar.cmpu203.flash;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.vassar.cmpu203.flash.model.Trips;

/**
 * A class designed to test our model's Trips class.
 */
public class TripsTest {

    /**
     * Tests the behavior of Trips's getDistance() method by inspecting its return value.
     * Tests distance calculation between known points.
     */
    @Test
    public void testGetDistance() {
        Trips trip = new Trips();
        trip.startTrip(41.6866, -73.8974);
        trip.endTrip(41.7224, -73.9340);
        double distance = trip.getDistance();
        assertEquals("Distance calculation between Vassar and Marist incorrect",
                3.2, distance, 0.5);
        trip = new Trips();
        trip.startTrip(41.6866, -73.8974);
        trip.endTrip(41.6866, -73.8974);
        assertEquals("Distance between same points should be 0",
                0.0, trip.getDistance(), 0.1);
    }


    /**
     * Tests the behavior of time conversion method.
     * Verifies military time is correctly converted to standard time.
     */
    @Test
    public void testTimeConversion() {
        assertEquals("12:00 military time conversion incorrect",
                "12:00 PM", Trips.convertToStandardTime("12:00"));
        assertEquals("00:00 military time conversion incorrect",
                "12:00 AM", Trips.convertToStandardTime("00:00"));
        assertEquals("13:30 military time conversion incorrect",
                "01:30 PM", Trips.convertToStandardTime("13:30"));
        assertEquals("09:45 military time conversion incorrect",
                "09:45 AM", Trips.convertToStandardTime("09:45"));
    }

}
