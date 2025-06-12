package edu.vassar.cmpu203.flash.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import edu.vassar.cmpu203.flash.databinding.ActivityMainBinding;


public class MainView implements IMainView {
    final private ActivityMainBinding binding;
    final private FragmentManager fmanager;

    public MainView(final Context context, final FragmentActivity factivity) {
        this.binding = ActivityMainBinding.inflate(LayoutInflater.from(context));

        EdgeToEdge.enable(factivity);
        ViewCompat.setOnApplyWindowInsetsListener(this.binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.fmanager = factivity.getSupportFragmentManager();
    }

    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    @Override
    public void displayFragment(@NonNull final Fragment fragment) {
        this.displayFragment(fragment, null);
    }

    @Override
    public void displayFragment(@NonNull final Fragment fragment, final String transName) {
        final FragmentTransaction ft = this.fmanager.beginTransaction();
        ft.replace(this.binding.fragmentContainerView.getId(), fragment);
        if (transName != null) ft.addToBackStack(transName);
        ft.commit();
    }
}
