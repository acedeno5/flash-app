package edu.vassar.cmpu203.flash;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

import edu.vassar.cmpu203.flash.model.Leaderboard;

public class LeaderboardTest {

    @Test
    public void testLeaderboardOrdering() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addWorldRecord(new Leaderboard.Record(2.0, 100.0, 50.0, "Medium")); // 50 mph
        leaderboard.addWorldRecord(new Leaderboard.Record(1.0, 80.0, 80.0, "Fast"));  // 80 mph
        leaderboard.addWorldRecord(new Leaderboard.Record(3.0, 90.0, 30.0, "Slow"));  // 30 mph
        List<Leaderboard.Record> records = leaderboard.getWorldLeaderboard();
        assertEquals("Wrong number of records", 3, records.size());
        assertEquals("First record should be fastest", 80.0,
                records.get(0).getAvgSpeed(), 0.01);
        assertEquals("Second record should be middle speed", 50.0,
                records.get(1).getAvgSpeed(), 0.01);
        assertEquals("Third record should be slowest", 30.0,
                records.get(2).getAvgSpeed(), 0.01);
    }

    @Test
    public void testNullHandling() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addWorldRecord(null);
        assertTrue("Leaderboard should be empty after adding null",
                leaderboard.getWorldLeaderboard().isEmpty());
        leaderboard.addWorldRecord(new Leaderboard.Record(1.0, 50.0, 50.0, "Test"));
        assertEquals("Leaderboard should have one record", 1,
                leaderboard.getWorldLeaderboard().size());
    }

    @Test
    public void testTripComments() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addWorldRecord(new Leaderboard.Record(1.0, 50.0, 50.0, "Test Trip"));
        assertEquals("Trip comment not stored correctly",
                "Test Trip",
                leaderboard.getWorldLeaderboard().get(0).getComment());
        leaderboard.addWorldRecord(new Leaderboard.Record(1.0, 50.0, 50.0, ""));
        assertEquals("Empty comment not handled correctly",
                "",
                leaderboard.getWorldLeaderboard().get(1).getComment());
        leaderboard.addWorldRecord(new Leaderboard.Record(1.0, 50.0, 50.0, null));
        assertEquals("Null comment not handled correctly",
                "",
                leaderboard.getWorldLeaderboard().get(2).getComment());
    }


    @Test
    public void testMultipleRecords() {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addWorldRecord(new Leaderboard.Record(1.0, 50.0, 50.0, "First"));
        leaderboard.addWorldRecord(new Leaderboard.Record(2.0, 60.0, 30.0, "Second"));
        leaderboard.addWorldRecord(new Leaderboard.Record(3.0, 90.0, 70.0, "Third"));
        List<Leaderboard.Record> records = leaderboard.getWorldLeaderboard();
        assertEquals("Should have three records", 3, records.size());
        Leaderboard.Record fastestRecord = records.get(0);
        assertEquals("Time not stored correctly", 3.0, fastestRecord.getTotalTime(), 0.01);
        assertEquals("Distance not stored correctly", 90.0, fastestRecord.getDistance(), 0.01);
        assertEquals("Speed not stored correctly", 70.0, fastestRecord.getAvgSpeed(), 0.01);
        assertEquals("Comment not stored correctly", "Third", fastestRecord.getComment());
    }
}
