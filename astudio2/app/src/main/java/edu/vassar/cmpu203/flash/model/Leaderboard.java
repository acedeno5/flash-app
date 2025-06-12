package edu.vassar.cmpu203.flash.model;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard extends Trips {
    private List<Record> worldLeaderboard;
    private List<Record> personalLeaderboard;

    public Leaderboard() {
        worldLeaderboard = new ArrayList<>();
        personalLeaderboard = new ArrayList<>();
    }

    public void addWorldRecord(Record record) {
        if (record != null) {
            worldLeaderboard.add(record);
            sortLeaderboard(worldLeaderboard);
        }
    }

    public void addPersonalRecord(Record record) {
        if (record != null) {
            personalLeaderboard.add(record);
            sortLeaderboard(personalLeaderboard);
        }
    }

    private void sortLeaderboard(List<Record> leaderboard) {
        // Sort by average speed in descending order (fastest first)
        leaderboard.sort((r1, r2) -> Double.compare(r2.getAvgSpeed(), r1.getAvgSpeed()));
    }

    public List<Record> getWorldLeaderboard() {
        return new ArrayList<>(worldLeaderboard);
    }

    public static class Record {
        private double totalTime;
        private double distance;
        private double avgSpeed;
        private String comment;

        public Record(double totalTime, double distance, double avgSpeed, String comment) {
            this.totalTime = totalTime;
            this.distance = distance;
            this.avgSpeed = avgSpeed;
            this.comment = comment != null ? comment : "";  // Use empty string only for null comments
        }

        public double getTotalTime() {
            return totalTime;
        }

        public double getDistance() {
            return distance;
        }

        public double getAvgSpeed() {
            return avgSpeed;
        }

        public String getComment() {
            return comment;
        }
    }
}
