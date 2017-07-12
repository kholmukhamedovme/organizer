package me.kholmukhamedov.organizer.ui.fragment.time_tracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import me.kholmukhamedov.organizer.presentation.view.time_tracker.TimeTrackerView;
import me.kholmukhamedov.organizer.presentation.presenter.time_tracker.TimeTrackerPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import me.kholmukhamedov.organizer.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class TimeTrackerFragment extends MvpAppCompatFragment implements TimeTrackerView {
    public static final String TAG = "TimeTrackerFragment";

    @InjectPresenter
    TimeTrackerPresenter mTimeTrackerPresenter;

    public static TimeTrackerFragment newInstance() {
        TimeTrackerFragment fragment = new TimeTrackerFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_tracker, container, false);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.setGroupVisible(R.id.main_toolbar_time_tracker_options, true);
    }
}
