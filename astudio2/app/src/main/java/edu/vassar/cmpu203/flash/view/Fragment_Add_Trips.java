package edu.vassar.cmpu203.flash.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.material.snackbar.Snackbar;

import edu.vassar.cmpu203.flash.databinding.FragmentAddTripsBinding;
import edu.vassar.cmpu203.flash.model.Trips;
import edu.vassar.cmpu203.flash.model.Leaderboard;
import edu.vassar.cmpu203.flash.controller.MainActivity;

public class Fragment_Add_Trips extends Fragment implements IAddTripsView {
    private FragmentAddTripsBinding binding;
    private Listener listener;
    private Trips currentTrip;
    private Leaderboard leaderboard;
    private long startTime;
    private MainActivity mainActivity;
    private Handler timerHandler;
    private boolean isTimerRunning;

    private LocationManager locationManager;
    private Location currentLocation;
    private ActivityResultLauncher<String[]> locationPermissionRequest;

    public Fragment_Add_Trips(@NonNull Listener listener, @NonNull Leaderboard leaderboard) {
        this.listener = listener;
        this.leaderboard = leaderboard;
        this.currentTrip = new Trips();
        this.timerHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize location permission launcher
        locationPermissionRequest = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION, false);

                    if (fineLocationGranted != null && fineLocationGranted) {
                        // Precise location access granted
                        startLocationUpdates();
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted
                        startLocationUpdates();
                    } else {
                        // No location access granted
                        Snackbar.make(binding.getRoot(),
                                "Location permission is required to track trips",
                                Snackbar.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding = FragmentAddTripsBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) requireActivity();
        locationManager = (LocationManager) requireContext()
                .getSystemService(Context.LOCATION_SERVICE);
        checkLocationPermissions();
        binding.startLatitude.setVisibility(View.GONE);
        binding.startLongitude.setVisibility(View.GONE);
        binding.endLatitude.setVisibility(View.GONE);
        binding.endLongitude.setVisibility(View.GONE);
        // Leaderboard button click handler
        binding.viewLeaderboardButton.setOnClickListener(v -> {
            mainActivity.showLeaderboard();
        });

        binding.startTripButton.setOnClickListener(v -> {
            if (currentLocation != null) {
                double startLat = currentLocation.getLatitude();
                double startLong = currentLocation.getLongitude();

                currentTrip.startTrip(startLat, startLong);
                startTime = System.currentTimeMillis();
                startTimer();

                binding.endTripButton.setEnabled(true);
                binding.startTripButton.setEnabled(false);

                binding.tripDetailsText.setText(String.format("Trip started at: %.6f, %.6f",
                        startLat, startLong));
            } else {
                Snackbar.make(view, "Waiting for GPS location...",
                        Snackbar.LENGTH_LONG).show();
            }
        });

        binding.endTripButton.setOnClickListener(v -> {
            if (currentLocation != null) {
                stopTimer();
                double endLat = currentLocation.getLatitude();
                double endLong = currentLocation.getLongitude();
                currentTrip.endTrip(endLat, endLong);
                long endTime = System.currentTimeMillis();
                double timeInHours = (endTime - startTime) / (1000.0 * 60 * 60);
                double distance = currentTrip.getDistance();
                double avgSpeed = distance / timeInHours;
                String tripComment = binding.tripCommentInput.getText().toString().trim();
                String tripSummary = String.format(
                        "Trip Summary:\nName: %s\nDistance: %.2f miles\nTime: %s\nAverage Speed: %.2f mph",
                        tripComment, distance, formatTime((endTime - startTime) / 1000), avgSpeed
                );
                binding.tripDetailsText.setText(tripSummary);
                mainActivity.addTripToLeaderboard(distance, timeInHours, avgSpeed, tripComment);
                resetUIState();
            } else {
                Snackbar.make(view, "Waiting for GPS location...",
                        Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void checkLocationPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates();
        } else {
            // Request permissions
            locationPermissionRequest.launch(new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            currentLocation = location;
            updateLocationDisplay();
        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {}

        @Override
        public void onProviderDisabled(@NonNull String provider) {}
    };

    private void startLocationUpdates() {
        try {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000, // Update every 1 second
                    1,    // Or when location changes by 1 meter
                    locationListener
            );
        } catch (SecurityException e) {
            Snackbar.make(binding.getRoot(),
                    "Location permission is required",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateLocationDisplay() {
        if (currentLocation != null) {
            String locationText = String.format("Current Location: %.6f, %.6f",
                    currentLocation.getLatitude(),
                    currentLocation.getLongitude());
            binding.tripDetailsText.setText(locationText);
        }
    }

    private void startTimer() {
        isTimerRunning = true;
        updateTimer();
    }

    private void stopTimer() {
        isTimerRunning = false;
        timerHandler.removeCallbacksAndMessages(null);
    }

    private void updateTimer() {
        if (!isTimerRunning) return;
        long currentTime = System.currentTimeMillis();
        long elapsedTime = (currentTime - startTime) / 1000; // Convert to seconds
        binding.timerText.setText(formatTime(elapsedTime));
        // Schedule the next update in 1 second
        timerHandler.postDelayed(this::updateTimer, 1000);
    }

    private String formatTime(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    private void resetUIState() {
        binding.startTripButton.setEnabled(true);
        binding.endTripButton.setEnabled(false);
        binding.timerText.setText("00:00:00");
        binding.tripCommentInput.setText(""); // Clear the comment input
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        stopTimer();
        locationManager.removeUpdates(locationListener);
        binding = null;
    }
}
