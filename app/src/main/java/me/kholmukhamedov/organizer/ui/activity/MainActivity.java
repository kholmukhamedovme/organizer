package me.kholmukhamedov.organizer.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.kholmukhamedov.organizer.presentation.view.MainView;
import me.kholmukhamedov.organizer.presentation.presenter.MainPresenter;

import com.arellomobile.mvp.MvpAppCompatActivity;

import me.kholmukhamedov.organizer.R;
import me.kholmukhamedov.organizer.ui.fragment.calendar.CalendarFragment;
import me.kholmukhamedov.organizer.ui.fragment.time_tracker.TimeTrackerFragment;
import me.kholmukhamedov.organizer.ui.fragment.todo.TodoFragment;
import me.kholmukhamedov.organizer.ui.fragment.calendar.AddAppointmentFragment;
import me.kholmukhamedov.organizer.ui.fragment.time_tracker.AddChronographFragment;
import me.kholmukhamedov.organizer.ui.fragment.todo.AddTodoFragment;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Map;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    public static final String TAG = "MainActivity";

    private BottomSheetBehavior bottomSheetBehavior;
    private FragmentManager fragmentManager;
    private Map<String, Fragment> fragments = new ArrayMap<>();

    @InjectPresenter
    MainPresenter presenter;

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_bottom_sheet)
    LinearLayout bottomSheet;
    @BindView(R.id.main_bottom_sheet_navigation)
    BottomNavigationView bottomSheetNavigation;
    @BindView(R.id.main_bottom_sheet_hint)
    TextView bottomSheetHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(bottomSheetCallback);
        bottomSheetNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_toolbar_add:
                presenter.expandAddScreen();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void navigateToCalendar() {
        Fragment savedFragment = fragments.get(CalendarFragment.TAG);
        Fragment savedAddScreenFragment = fragments.get(AddAppointmentFragment.TAG);

        if (savedFragment != null && savedAddScreenFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment, savedFragment, CalendarFragment.TAG)
                    .replace(R.id.main_bottom_sheet_fragment, savedAddScreenFragment, AddAppointmentFragment.TAG)
                    .commit();
        } else {
            Fragment newFragment = CalendarFragment.newInstance();
            Fragment newAddScreenFragment = AddAppointmentFragment.newInstance();

            fragments.put(CalendarFragment.TAG, newFragment);
            fragments.put(AddAppointmentFragment.TAG, newAddScreenFragment);

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment, newFragment, CalendarFragment.TAG)
                    .replace(R.id.main_bottom_sheet_fragment, newAddScreenFragment, AddAppointmentFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void navigateToTodo() {
        Fragment savedFragment = fragments.get(TodoFragment.TAG);
        Fragment savedAddScreenFragment = fragments.get(AddTodoFragment.TAG);

        if (savedFragment != null && savedAddScreenFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment, savedFragment, TodoFragment.TAG)
                    .replace(R.id.main_bottom_sheet_fragment, savedAddScreenFragment, AddTodoFragment.TAG)
                    .commit();
        } else {
            Fragment newFragment = TodoFragment.newInstance();
            Fragment newAddScreenFragment = AddTodoFragment.newInstance();

            fragments.put(TodoFragment.TAG, newFragment);
            fragments.put(AddTodoFragment.TAG, newAddScreenFragment);

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment, newFragment, TodoFragment.TAG)
                    .replace(R.id.main_bottom_sheet_fragment, newAddScreenFragment, AddTodoFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void navigateToTimeTracker() {
        Fragment savedFragment = fragments.get(TimeTrackerFragment.TAG);
        Fragment savedAddScreenFragment = fragments.get(AddChronographFragment.TAG);

        if (savedFragment != null && savedAddScreenFragment != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment, savedFragment, TimeTrackerFragment.TAG)
                    .replace(R.id.main_bottom_sheet_fragment, savedAddScreenFragment, AddChronographFragment.TAG)
                    .commit();
        } else {
            Fragment newFragment = TimeTrackerFragment.newInstance();
            Fragment newAddScreenFragment = AddChronographFragment.newInstance();

            fragments.put(TimeTrackerFragment.TAG, newFragment);
            fragments.put(AddChronographFragment.TAG, newAddScreenFragment);

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.main_fragment, newFragment, TimeTrackerFragment.TAG)
                    .replace(R.id.main_bottom_sheet_fragment, newAddScreenFragment, AddChronographFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void expandAddScreen() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetHint.setVisibility(View.INVISIBLE);
    }

    @Override
    public void collapseAddScreen() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetHint.setVisibility(View.VISIBLE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.main_bottom_sheet_navigation_calendar:
                    presenter.navigateToCalendar();
                    return true;
                case R.id.main_bottom_sheet_navigation_todo:
                    presenter.navigateToTodo();
                    return true;
                case R.id.main_bottom_sheet_navigation_time_tracker:
                    presenter.navigateToTimeTracker();
                    return true;
            }

            return false;
        }
    };

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback =
            new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_EXPANDED:
                    presenter.expandAddScreen();
                    break;
                case BottomSheetBehavior.STATE_DRAGGING:
                    bottomSheetHint.setVisibility(View.INVISIBLE);
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                    presenter.collapseAddScreen();
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {}
    };
}
