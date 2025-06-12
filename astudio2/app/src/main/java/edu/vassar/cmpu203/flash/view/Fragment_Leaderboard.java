package edu.vassar.cmpu203.flash.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.List;
import edu.vassar.cmpu203.flash.R;
import edu.vassar.cmpu203.flash.databinding.FragmentLeaderboardBinding;
import edu.vassar.cmpu203.flash.model.Leaderboard;
import edu.vassar.cmpu203.flash.controller.MainActivity;

public class Fragment_Leaderboard extends Fragment implements ILeaderboardView {
    private FragmentLeaderboardBinding binding;
    private final Leaderboard leaderboard;

    public Fragment_Leaderboard(@NonNull Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLeaderboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View startNewTripButton = view.findViewById(R.id.startNewTripButton);
        startNewTripButton.setOnClickListener(v -> {
            MainActivity mainActivity = (MainActivity) requireActivity();
            mainActivity.showAddTrips();
        });

        updateLeaderboardDisplay();
    }

    private void updateLeaderboardDisplay() {
        if (leaderboard == null) return;

        List<Leaderboard.Record> records = leaderboard.getWorldLeaderboard();
        if (records == null) return;

        for (int i = 0; i < Math.min(10, records.size()); i++) {
            Leaderboard.Record record = records.get(i);
            if (record == null) continue;

            TextView nameView = getTextViewById("tripname" + (i + 1));
            TextView distanceView = getTextViewById("distance" + (i + 1));
            TextView timeView = getTextViewById("time" + (i + 1));
            TextView avgSpeedView = getTextViewById("avgspeed" + (i + 1));

            if (nameView != null) nameView.setText(record.getComment());
            if (distanceView != null) distanceView.setText(String.format("%.1f mi", record.getDistance()));
            if (timeView != null) timeView.setText(String.format("%.1f hrs", record.getTotalTime()));
            if (avgSpeedView != null) avgSpeedView.setText(String.format("%.1f mph", record.getAvgSpeed()));
        }
    }

    private TextView getTextViewById(String fieldName) {
        try {
            int resId = getResources().getIdentifier(fieldName, "id", requireContext().getPackageName());
            return getView().findViewById(resId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
