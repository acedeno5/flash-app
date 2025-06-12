package edu.vassar.cmpu203.flash.controller;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

import edu.vassar.cmpu203.flash.model.Leaderboard;
import edu.vassar.cmpu203.flash.model.Trips;
import edu.vassar.cmpu203.flash.view.IAddTripsView;
import edu.vassar.cmpu203.flash.view.IMainView;
import edu.vassar.cmpu203.flash.view.MainView;
import edu.vassar.cmpu203.flash.view.Fragment_Add_Trips;
import edu.vassar.cmpu203.flash.view.Fragment_Leaderboard;

public class MainActivity extends AppCompatActivity implements IAddTripsView.Listener {

    private IMainView mainView;
    private Trips currentTrip;
    private Leaderboard leaderboard;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize views and models
        this.mainView = new MainView(this, this);
        setContentView(this.mainView.getRootView());

        // Initialize models
        this.currentTrip = new Trips();
        this.leaderboard = new Leaderboard();

        // Load saved data from Firestore
        loadLeaderboardData();

        // Display initial fragment
        Fragment_Add_Trips fragment = new Fragment_Add_Trips(this, this.leaderboard);
        this.mainView.displayFragment(fragment);
    }

    // Method to show leaderboard
    public void showLeaderboard() {
        if (leaderboard != null) {
            Fragment_Leaderboard leaderboardFragment = new Fragment_Leaderboard(this.leaderboard);
            this.mainView.displayFragment(leaderboardFragment, "leaderboard");
        }
    }

    // Method to add a trip to leaderboard
    public void addTripToLeaderboard(double distance, double time, double avgSpeed, String comment) {  // Added comment parameter
        if (leaderboard != null) {
            // Add to local leaderboard
            Leaderboard.Record record = new Leaderboard.Record(time, distance, avgSpeed,comment);
            this.leaderboard.addWorldRecord(record);

            // Save to Firestore with comment
            Map<String, Object> tripData = new HashMap<>();
            tripData.put("distance", distance);
            tripData.put("time", time);
            tripData.put("avgSpeed", avgSpeed);
            tripData.put("comment", comment);  // Save the comment
            tripData.put("timestamp", System.currentTimeMillis());

            db.collection("trips")
                    .add(tripData)
                    .addOnSuccessListener(documentReference ->
                            Toast.makeText(MainActivity.this,
                                    "Trip saved successfully", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e ->
                            Toast.makeText(MainActivity.this,
                                    "Error saving trip: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }


    // Method to load leaderboard data from Firestore
    private void loadLeaderboardData() {
        db.collection("trips")
                .orderBy("avgSpeed", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        if (document != null) {
                            Double time = document.getDouble("time");
                            Double distance = document.getDouble("distance");
                            Double avgSpeed = document.getDouble("avgSpeed");
                            String comment = document.getString("comment");

                            if (time != null && distance != null && avgSpeed != null) {
                                Leaderboard.Record record = new Leaderboard.Record(
                                        time, distance, avgSpeed, comment);
                                leaderboard.addWorldRecord(record);
                            }
                        }
                    }
                    // Update leaderboard if it's visible
                    if (getSupportFragmentManager().findFragmentByTag("leaderboard") != null) {
                        showLeaderboard();
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(MainActivity.this,
                                "Error loading leaderboard: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    // Method to return to add trips screen
    public void showAddTrips() {
        Fragment_Add_Trips fragment = new Fragment_Add_Trips(this, this.leaderboard);
        this.mainView.displayFragment(fragment);
    }
}
